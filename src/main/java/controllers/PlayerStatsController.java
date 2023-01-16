package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayerStatsController {

    public PlayerStatsController() {

    }


//    public PlayerStatsDto fetchIdImgEloLvl(String username) throws IOException {
//        String info = HttpUtils.fetchBufferedData("https://api.faceit.com/users/v1/nicknames/" + username);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        PlayerStatsDto playerStatsDto = gson.fromJson(info, PlayerStatsDto.class);
//        String region = playerStatsDto.getPayload().getGames().getCsgo().region;
//        String faceitId = playerStatsDto.getPayload().id;
//        String country = playerStatsDto.getPayload().country;
//
//        String response = HttpUtils.fetchFromFaceit("https://open.faceit.com/data/v4/rankings/games/csgo/regions/" + region + "/players/" + faceitId+ "?limit=2");
//        String response2 = HttpUtils.fetchFromFaceit("https://open.faceit.com/data/v4/rankings/games/csgo/regions/" + region + "/players/" + faceitId + "?country=" + country + "&limit=2");
//        PlayerRegionDto playerRegionDto = gson.fromJson(response, PlayerRegionDto.class);
//        PlayerRegionDto playerRegionDto2 = gson.fromJson(response2, PlayerRegionDto.class);
//
//        if (response.length() > 100) {
//            playerStatsDto.setRegionPlacement(playerRegionDto.position);
//        }
//        if (response2.length() > 100) {
//            playerStatsDto.setCountryPlacement(playerRegionDto2.position);
//        }
//
//        return playerStatsDto;
//    }
//
//
//
//
//    public PlayerStats mapPlayerStatsDtoToPlayerStats(PlayerStatsDto playerStatsDto){
//        PlayerStats playerStats = new PlayerStats(playerStatsDto);
//        return playerStats;
//
//    }
//
//    public static void main(String[] args) throws IOException {
////        emf = EMF_Creator.createEntityManagerFactory();
//        PlayerStatsController controller = new PlayerStatsController();
//
////        PlayerStatsDto playerStatsDto = controller.fetchIdImgEloLvl("TACOCS");
//        PlayerStatsDto playerStatsDto = controller.fetchIdImgEloLvl("H3ZZ");
////        PlayerStatsDto playerStatsDto = controller.fetchIdImgEloLvl("FALLEN-");
//        PlayerStats playerStats = controller.mapPlayerStatsDtoToPlayerStats(playerStatsDto);
//        System.out.println("playerstatsdto: " + playerStatsDto);
//        System.out.println(playerStats);
//    }
}
