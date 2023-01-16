package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Location;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class LocationController {

    private static EntityManagerFactory emf;
    private static LocationController instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private LocationController() {
    }

    public static LocationController getLocationController(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LocationController();
        }
        return instance;
    }

    public Location create(String address, String city, String condition) {
        EntityManager em = emf.createEntityManager();
        Location location = new Location(address, city, condition);
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l", Location.class);
        for (Location l : query.getResultList()) {
            if (l.getAddress().equals(address) && l.getCity().equals(city)) {
                return l;
            }
        }
        em.getTransaction().begin();
        em.persist(location);
        em.getTransaction().commit();
        return location;
    }


}
