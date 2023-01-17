//package Unit;
//
//import controllers.MatchController;
//import controllers.PlayerController;
//import dtos.MatchDTO;
//import dtos.PlayerDTO;
//import entities.Location;
//import entities.Match;
//import entities.Player;
//import junit.framework.Assert;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.TypedQuery;
//
//public class PlayerTest {
//
//    @BeforeAll
//    public static void beforeEachTestMethod() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        EntityManager em = emf.createEntityManager();
//        Player player = new Player("testPlayer", "30709907", "test1@test1.dk", "ok");
//        Player player2 = new Player("testPlayer2", "30709907", "test2@test2.dk", "ok");
//        Player player3 = new Player("testPlayer3", "30709907", "test3@test3.dk", "ok");
//        Player player4 = new Player("testPlayer4", "30709907", "test4@test4.dk", "ok");
//        Location location = new Location("testLocationvej 1", "testCity", "wet");
//        Match match = new Match("testTeam2", "testJudge", "knockout", false);
//        Match match2 = new Match("testTeam", "testJudge", "knockout", false);
//        match.addLocation(location);
//        match2.addLocation(location);
//        match.addPlayer(player);
//        match.addPlayer(player2);
//        match2.addPlayer(player3);
//        match2.addPlayer(player4);
//        try {
//            em.getTransaction().begin();
//            em.persist(match);
//            em.persist(match2);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    @Test
//    public void testPlayer() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        PlayerController pc = PlayerController.getPlayerController(emf);
//        PlayerDTO player = pc.create("newtestPlayer", "40506968", "newtest@newtest.dk", "ok");
//        Assert.assertTrue(player.getId() > 0);
//    }
//
//    @Test
//    public void testUpdatePlayer(){
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        PlayerController pc = PlayerController.getPlayerController(emf);
//        PlayerDTO playerDTO = new PlayerDTO(new Player("updatedtestPlayer", "40516967", "updated1@updated1.dk", "notok"));
//        PlayerDTO player = pc.update(1, playerDTO);
//        Assert.assertEquals(1, player.getId());
//        Assert.assertEquals("updatedtestPlayer", player.getName());
//    }
//
//    @Test
//    public void testDeletePlayer(){
//        boolean exist = false;
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        PlayerController pc = PlayerController.getPlayerController(emf);
//        PlayerDTO player = pc.delete(1);
//        TypedQuery<Player> query = emf.createEntityManager().createQuery("SELECT p FROM Player p", Player.class);
//        for (Player p : query.getResultList()) {
//            if (p.getName().equals(player.getName())) {
//                exist = true;
//                break;
//            }
//        }
//        Assert.assertFalse(exist);
//    }
//}
