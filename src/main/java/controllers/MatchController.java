package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MatchDTO;

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
