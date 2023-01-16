package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PlayerDTO;
import entities.Player;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public Player create(String name, String phone, String email, String status) {
        EntityManager em = emf.createEntityManager();
        Player player = new Player(name, phone, email, status);
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        return player;
    }

    public Player update(int id, PlayerDTO playerDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Player player = em.find(Player.class, id);
        player.setName(playerDTO.getName());
        player.setPhone(playerDTO.getPhone());
        player.setEmail(playerDTO.getEmail());
        player.setStatus(playerDTO.getStatus());
        em.getTransaction().commit();
        return player;
    }

    public Player delete(int id) {
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class, id);
        em.getTransaction().begin();
        em.remove(player);
        em.getTransaction().commit();
        return player;
    }

}
