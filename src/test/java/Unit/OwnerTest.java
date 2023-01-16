package Unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import controllers.OwnerController;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class OwnerTest {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEachTestMethod() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        Owner owner1 = new Owner("John", "Street 1", "12345678");
        Owner owner2 = new Owner("Jane", "Street 2", "87654321");
        Boat boat1 = new Boat("Tahoe", "Pontoon Cascade Elite Windshield", "The Prestige", "");
        Harbour habour1 = new Harbour("Havnen", "Havnevej 1", 50);
        boat1.addHabour(habour1);
        owner1.addBoat(boat1);
        owner2.addBoat(boat1);
        try {
            em.getTransaction().begin();
            em.persist(owner1);
            em.persist(owner2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testOwner() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        OwnerController oc = OwnerController.getOwnerController(emf);
        JsonArray ownerarray = GSON.fromJson(oc.getOwners(), JsonArray.class);
        Assert.assertEquals(2, ownerarray.size());
    }

}
