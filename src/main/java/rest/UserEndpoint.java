package rest;

import com.google.gson.*;
import controllers.PlayerController;
import dtos.PlayerDTO;
import entities.Player;
import facades.UserController;
import security.entities.Role;
import security.entities.User;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("user")
public class UserEndpoint {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //    @GET
//    @Path("all")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getAllUsers(){
//        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
//        List<UserDto> userDtos = new ArrayList<>();
//        try {
//            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
//            for (User user : users) {
//                userDtos.add(new UserDto(user));
//            }
//            return Response.ok(GSON.toJson(userDtos)).build();
//        } finally {
//            em.close();
//        }
//    }
//
    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createUser(String prompt) {
        User user;
        EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        if (UserController.checkUserExists(username)) {
            return "user already exists";
        }
        user = new User(username, password);
        user.addRole(em.find(Role.class, 2));
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    @POST
    @Path("addPlayer")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPlayer(String prompt) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        PlayerDTO playerDTO = PlayerController.getPlayerController(emf).create(json.get("name").getAsString(), json.get("phone").getAsString(), json.get("email").getAsString(), json.get("status").getAsString());
        EntityManager em = emf.createEntityManager();
        Player player = em.find(Player.class, playerDTO.getId());
        User user = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class)
                .setParameter("username", json.get("username").getAsString())
                .getSingleResult();
        em.getTransaction().begin();
        user.setPlayer(player);
        em.getTransaction().commit();
        return "relog for changes to take effect";
    }

    @POST
    @Path("remove")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean removeUser(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int id = json.get("id").getAsInt();
        try {
            EntityManager em = EMF_Creator.createEntityManagerFactory().createEntityManager();
            User user = em.find(User.class, id);
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
