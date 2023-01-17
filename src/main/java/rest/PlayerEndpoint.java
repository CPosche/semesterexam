package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controllers.PlayerController;
import dtos.PlayerDTO;
import entities.Player;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("player")
public class PlayerEndpoint {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final PlayerController pc = PlayerController.getPlayerController(EMF);

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPlayers() {
        return GSON.toJson(pc.getPlayers());
    }

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createPlayer(String prompt){
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        String name = json.get("name").getAsString();
        String phone = json.get("phone").getAsString();
        String email = json.get("email").getAsString();
        String status = json.get("status").getAsString();
        return GSON.toJson(pc.create(name, phone, email, status));
    }

    @PUT
    @Path("update")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePlayer(String prompt){
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        String phone = json.get("phone").getAsString();
        String email = json.get("email").getAsString();
        String status = json.get("status").getAsString();
        PlayerDTO playerDTO = new PlayerDTO(new Player(name, phone, email, status));
        return GSON.toJson(pc.update(id, playerDTO));
    }

    @DELETE
    @Path("delete")
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePlayer(String prompt) {
        JsonObject json = JsonParser.parseString(prompt).getAsJsonObject();
        int id = json.get("id").getAsInt();
        return GSON.toJson(pc.delete(id));
    }

}
