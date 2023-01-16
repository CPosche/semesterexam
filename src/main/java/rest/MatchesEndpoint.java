package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.Path;

@Path("user")
public class MatchesEndpoint {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
