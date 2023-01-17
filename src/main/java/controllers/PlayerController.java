package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PlayerDTO;
import entities.Player;
import security.entities.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PlayerController {

    private static EntityManagerFactory emf;
    private static PlayerController instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private PlayerController() {
    }

    public static PlayerController getPlayerController(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PlayerController();
        }
        return instance;
    }

    public PlayerDTO create(String name, String phone, String email, String status) {
        EntityManager em = emf.createEntityManager();
        Player player = new Player(name, phone, email, status);
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        return new PlayerDTO(player);
    }

    public PlayerDTO update(int id, PlayerDTO playerDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Player player = em.find(Player.class, id);
        player.setName(playerDTO.getName());
        player.setPhone(playerDTO.getPhone());
        player.setEmail(playerDTO.getEmail());
        player.setStatus(playerDTO.getStatus());
        em.getTransaction().commit();
        return new PlayerDTO(player);
    }

    public PlayerDTO delete(int id) {
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class, id);
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u join u.player p WHERE p.id = :id", User.class);
        query.setParameter("id", id);
        User user;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        em.getTransaction().begin();
        if (user != null) {
            user.removePlayer(player);
        }
        em.remove(player);
        em.getTransaction().commit();
        return new PlayerDTO(player);
    }

    public List<PlayerDTO> getPlayers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<PlayerDTO> query = em.createQuery("SELECT NEW dtos.PlayerDTO(p) FROM Player p", PlayerDTO.class);
        return query.getResultList();
    }
}
