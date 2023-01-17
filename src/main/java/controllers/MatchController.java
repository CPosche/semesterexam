package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MatchDTO;
import entities.Location;
import entities.Match;
import entities.Player;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class MatchController {

    private static EntityManagerFactory emf;
    private static MatchController instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private MatchController() {
    }

    public static MatchController getMatchController(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MatchController();
        }
        return instance;
    }

    public MatchDTO create(String opponent, String judge, String type, boolean inDoors, String address, String city, String condition) {
        EntityManager em = emf.createEntityManager();
        Location location = LocationController.getLocationController(emf).create(address, city, condition);
        Match match = new Match(opponent, judge, type, inDoors);
        location = em.find(Location.class, location.getId());
        em.getTransaction().begin();
        em.persist(match);
        match.addLocation(location);
        em.getTransaction().commit();
        return new MatchDTO(match);
    }

    public List<MatchDTO> getMatches() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m, l) FROM Match m join m.location l", MatchDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<MatchDTO> getMatchById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m) FROM Match m join m.playerList pl WHERE pl.id = :id", MatchDTO.class);
            query.setParameter("id", id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public MatchDTO updateMatch(MatchDTO matchDTO, int id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Match match = em.find(Match.class, id);
            match.setOpponent(matchDTO.getOpponent());
            match.setJudge(matchDTO.getJudge());
            match.setType(matchDTO.getType());
            match.setInDoors(matchDTO.isInDoors());
            if (matchDTO.getLocationDTO() != null){
                Location location = LocationController.getLocationController(emf).create(matchDTO.getLocationDTO().getAddress(), matchDTO.getLocationDTO().getCity(), matchDTO.getLocationDTO().getCondition());
                location = em.find(Location.class, location.getId());
                match.removeLocation(match.getLocation());
                match.addLocation(location);
            }
            em.getTransaction().commit();
            return new MatchDTO(match);
        } finally {
            em.close();
        }
    }

    public List<MatchDTO> getMatchesByLocation(String locationaddress, String locationcity){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m) FROM Match m join m.location l WHERE l.address = :locationaddress AND l.city = :locationcity", MatchDTO.class);
            query.setParameter("locationaddress", locationaddress);
            query.setParameter("locationcity", locationcity);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MatchController mc = MatchController.getMatchController(emf);
        String string = GSON.toJson(mc.getMatchById(1));
        System.out.println(string);
    }

    public MatchDTO registerToMatch(int playerid, int matchid) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Match match = em.find(Match.class, matchid);
            Player player = em.find(Player.class, playerid);
            match.addPlayer(player);
            em.getTransaction().commit();
            return new MatchDTO(match);
        } finally {
            em.close();
        }
    }
}
