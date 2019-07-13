package com.meal;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Roster {


    private String[] recentSequence;
    Home home;
    Room room1, room2;
    Week week;

    Roster(File roommates, File recent) {
        init(roommates, recent);
    }

    private void init(File roommates, File recent) {
        try {
            String recentContent = FileUtils.readFileToString(recent, "utf-8");
            String roommateContent = FileUtils.readFileToString(roommates, "utf-8");

            // Convert JSON string to JSONObjectstringArray
            JSONObject recentObject = new JSONObject(recentContent);
            JSONObject roommatesObject = new JSONObject(roommateContent);

            //Get Roommates list

            Object[] temp = roommatesObject.getJSONObject("room_1").getJSONArray("roommates").toList().toArray();
            String[] roommatesList1 = Arrays.asList(temp).toArray(new String[temp.length]);
            temp = roommatesObject.getJSONObject("room_2").getJSONArray("roommates").toList().toArray();
            String[] roommatesList2 = Arrays.asList(temp).toArray(new String[temp.length]);

            // Get Recent Sequence
            temp = recentObject.getJSONArray("sequence").toList().toArray();
            String[] sequence = Arrays.asList(temp).toArray(new String[temp.length]);

            //Define rooms
            room1 = new Room(roommatesList1, recentObject.getJSONObject("room1").getString("vacuum"), recentObject.getJSONObject("room1").getString("bathroom"), recentObject.getJSONObject("room1").getString("date"));
            room2 = new Room(roommatesList2, recentObject.getJSONObject("room2").getString("vacuum"), recentObject.getJSONObject("room2").getString("bathroom"), recentObject.getJSONObject("room2").getString("date"));



            //Define Home
            home = new Home(room1, room2, sequence,
                    recentObject.getJSONObject("vacuum").getString("date"),
                    recentObject.getJSONObject("vacuum").getString("name"));

            home.setCleaningDate();

            week = new Week(home);
            System.out.println(week);









        } catch (IOException ex) {
            System.out.println("Couldn't read either among file recent.json and roomamtes.json");
            try {
                String content = FileUtils.readFileToString(roommates, "utf-8");
            } catch (IOException e) {
                System.out.println("Couldn't read file roommates.json | Can't product Roster ");
            }
        }
    }


    public static void main(String[] args) {
        Roster roster = new Roster(new File("src/main/resources/roommates.json"), new File("src/main/resources/recent.json"));


    }
}




