//package Unit;
//
//import controllers.LocationController;
//import controllers.PlayerController;
//import entities.Location;
//import entities.Player;
//import junit.framework.Assert;
//import org.junit.jupiter.api.Test;
//import utils.EMF_Creator;
//
//import javax.persistence.EntityManagerFactory;
//
//public class LocationTest {
//    @Test
//    public void testLocation() {
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactoryForTest();
//        LocationController lc = LocationController.getLocationController(emf);
//        Location location = lc.create("newtestlocation", "newtestCity", "dry");
//        Assert.assertTrue(location.getId() > 0);
//    }
//}
