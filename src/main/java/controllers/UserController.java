package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import security.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author lam@cphbusiness.dk
 */
public class UserController {

    private static EntityManagerFactory emf;
    private static UserController instance;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private UserController() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserController getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserController();
        }
        return instance;
    }

//    public User create(String name, String password, String faceitnickname) {
//        FaceitUser faceitUser = new FaceitUser(faceitnickname, "idlalala13567");
//        CommunityPlayer communityPlayer = new CommunityPlayer(faceitUser);
//        Community community = new Community("abekattene");
//        community.addCommunityPlayer(communityPlayer);
//        User user = new User(name, password, faceitUser);
//        user.addCommunity(community);
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        em.persist(user);
//        em.getTransaction().commit();
//        return user;
//    }

    public boolean remove(int id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public static boolean checkUserExists(String username) {
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        for (User u : query.getResultList()) {
            if (u.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
