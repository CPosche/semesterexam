/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

//import security.entities.User;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        Owner owner = new Owner("John", "Street 1", "12345678");
//        Boat boat = new Boat("Tahoe", "Pontoon Cascade Elite Windshield", "The Prestige", "");
//        Harbour habour = new Harbour("Havnen", "Havnevej 1", 50);
//        boat.addHabour(habour);
//        owner.addBoat(boat);
//        try {
//            em.getTransaction().begin();
//            em.persist(owner);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
    }
    
    public static void main(String[] args) {
        populate();
    }
}
