package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MatchDTO;
import entities.Location;
import entities.Match;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

    public Match create(String opponent, String judge, String type, boolean inDoors, String address, String city, String condition) {
        EntityManager em = emf.createEntityManager();
        Location location = LocationController.getLocationController(emf).create(address, city, condition);
        Match match = new Match(opponent, judge, type, inDoors);
        em.getTransaction().begin();
        em.persist(match);
        match.addLocation(location);
        em.getTransaction().commit();
        return match;
    }

    public String getMatches() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m) FROM Match m", MatchDTO.class);
            return GSON.toJson(query.getResultList());
        } finally {
            em.close();
        }
    }

    public String getMatchById(int id){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m) FROM Match m WHERE m.id = :id", MatchDTO.class);
            query.setParameter("id", id);
            return GSON.toJson(query.getSingleResult());
        } finally {
            em.close();
        }
    }

    public Match updateMatch(MatchDTO matchDTO, int id){
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Match match = em.find(Match.class, id);
            match.setOpponent(matchDTO.getOpponent());
            match.setJudge(matchDTO.getJudge());
            match.setType(matchDTO.getType());
            match.setInDoors(matchDTO.isInDoors());
            if(matchDTO.getLocation() != null){
                Location location = LocationController.getLocationController(emf).create(matchDTO.getLocation().getAddress(), matchDTO.getLocation().getCity(), matchDTO.getLocation().getCondition());
                match.addLocation(location);
            }
            em.getTransaction().commit();
            return match;
        } finally {
            em.close();
        }
    }

    public String getMatchesByLocation(String location) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<MatchDTO> query = em.createQuery("SELECT new dtos.MatchDTO(m) FROM Match m join m.location l WHERE l.address = :location", MatchDTO.class);
            query.setParameter("location", location);
            return GSON.toJson(query.getResultList());
        } finally {
            em.close();
        }
    }
}
