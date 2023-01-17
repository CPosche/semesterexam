package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Location;
import entities.Match;
import entities.Player;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("generate")
public class PopulatorEndPoint {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @POST
    public String populate() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Player player = new Player("testPlayer", "30709907", "test1@test1.dk", "ok");
        Player player2 = new Player("testPlayer2", "30709907", "test2@test2.dk", "ok");
        Player player3 = new Player("testPlayer3", "30709907", "test3@test3.dk", "ok");
        Player player4 = new Player("testPlayer4", "30709907", "test4@test4.dk", "ok");
        Location location = new Location("testLocationvej 1", "testCity", "wet");
        Match match = new Match("testTeam2", "testJudge", "knockout", false);
        Match match2 = new Match("testTeam", "testJudge", "knockout", false);
        match.addLocation(location);
        match2.addLocation(location);
        match.addPlayer(player);
        match.addPlayer(player2);
        match2.addPlayer(player3);
        match2.addPlayer(player4);
        try {
            em.getTransaction().begin();
            em.persist(match);
            em.persist(match2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return GSON.toJson("Populated");
    }

}
