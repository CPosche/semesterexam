package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.OwnerDTO;
import entities.Owner;
import facades.UserController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class OwnerController {

    private static EntityManagerFactory emf;
    private static OwnerController instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private OwnerController() {
    }

    public static OwnerController getOwnerController(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerController();
        }
        return instance;
    }

    public String getOwners() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<OwnerDTO> query = em.createQuery("SELECT new dtos.OwnerDTO(o, count(o.boatList)) FROM Owner o group by o.id", OwnerDTO.class);
            return GSON.toJson(query.getResultList());
        } finally {
            em.close();
        }
    }

}
