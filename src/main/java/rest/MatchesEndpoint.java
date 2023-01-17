package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.MatchController;
import dtos.LocationDTO;
import dtos.MatchDTO;
import entities.Location;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("matches")
public class MatchesEndpoint {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MatchController mc = MatchController.getMatchController(EMF);

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMatches() {
        return GSON.toJson(mc.getMatches());
    }

    @GET
    @Path("all/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMatchesById(@PathParam("id") int id) {
        return GSON.toJson(mc.getMatchById(id));
    }

    @GET
    @Path("all/bylocation")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMatchesByLocation(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String locationaddress = json.get("locationaddress").getAsString();
        String locationcity = json.get("locationcity").getAsString();
        return GSON.toJson(mc.getMatchesByLocation(locationaddress, locationcity));
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createMatch(String prompt){
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String opponent = json.get("opponent").getAsString();
        String judge = json.get("judge").getAsString();
        String tyoe = json.get("type").getAsString();
        boolean inDoor = json.get("inDoor").getAsBoolean();
        String address = json.get("address").getAsString();
        String city = json.get("city").getAsString();
        String condition = json.get("condition").getAsString();
        return GSON.toJson(mc.create(opponent, judge, tyoe, inDoor, address, city, condition));
    }

    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updateMatch(String prompt){
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int id = json.get("id").getAsInt();
        String opponent = json.get("opponent").getAsString();
        String judge = json.get("judge").getAsString();
        String tyoe = json.get("type").getAsString();
        boolean inDoor = json.get("inDoor").getAsBoolean();
        String address = json.get("address").getAsString();
        String city = json.get("city").getAsString();
        String condition = json.get("condition").getAsString();
        LocationDTO location = new LocationDTO(new Location(address, city, condition));
        MatchDTO match = new MatchDTO(opponent, judge, tyoe, inDoor, location);
        return GSON.toJson(mc.updateMatch(match, id));
    }

    @PUT
    @Path("registration")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String registerToMatch(String prompt){
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int playerid = json.get("playerid").getAsInt();
        int matchid = json.get("matchid").getAsInt();
        return GSON.toJson(mc.registerToMatch(playerid, matchid));
    }

}
