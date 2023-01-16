package Unit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import controllers.MatchController;
import entities.Location;
import entities.Match;
import entities.Player;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MatchesTest {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEachTestMethod() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        EntityManager em = emf.createEntityManager();
        Player player = new Player("testPlayer", "30709907", "test1@test1.dk", "ok");
        Player player2 = new Player("testPlayer", "30709907", "test2@test2.dk", "ok");
        Player player3 = new Player("testPlayer", "30709907", "test3@test3.dk", "ok");
        Player player4 = new Player("testPlayer", "30709907", "test4@test4.dk", "ok");
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
    }

    @Test
    public void testMatches() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        MatchController mc = MatchController.getMatchController(emf);
        JsonArray matchesArray = GSON.fromJson(mc.getMatches(), JsonArray.class);
        Assert.assertEquals(2, matchesArray.size());
    }

//    @Test
//    public void testGetMatchesById(){
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        MatchController mc = MatchController.getMatchController(emf);
//        JsonArray matchesArray = GSON.fromJson(mc.getMatchById(1), JsonArray.class);
//        Assert.assertEquals(1, matchesArray.size());
//    }

    @Test
    public void testGetMatchesByLocation(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
        MatchController mc = MatchController.getMatchController(emf);
        JsonArray matchesArray = GSON.fromJson(mc.getMatchesByLocation("testLocationvej 1"), JsonArray.class);
        Assert.assertEquals(2, matchesArray.size());
    }

}
