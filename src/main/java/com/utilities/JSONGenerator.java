package com.utilities;

import com.meal.Home;
import com.meal.Room;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSONGenerator {

    private JSONObject schedule = new JSONObject();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");

    public JSONGenerator(Home home) {
        this.createJson(home);
    }

    private void createJson(Home home) {

        JSONArray sequence = new JSONArray();

        for (String flatmate : home.getRecent())
            sequence.put(flatmate);


        //set room1
        JSONObject room1 = new JSONObject();
        Room room = home.getRoom1();

        room1.put("vacuum", room.getVacuumCleaner());
        room1.put("bathroom", room.getBathroomCleaner());
        room1.put("bCleaningDate", dateFormat.format(room.getBathroomCleanLastDate()));
        room1.put("vCleaningDate", dateFormat.format(room.getVacuumLastDate()));


        //set room2
        JSONObject room2 = new JSONObject();
        room = home.getRoom2();

        room2.put("vacuum", room.getVacuumCleaner());
        room2.put("bathroom", room.getBathroomCleaner());
        room2.put("bCleaningDate", dateFormat.format(room.getBathroomCleanLastDate()));
        room2.put("vCleaningDate", dateFormat.format(room.getVacuumLastDate()));


        //set vacuum
        JSONObject vacuum = new JSONObject();
        vacuum.put("name", home.getVacuumCleaner());
        vacuum.put("date", dateFormat.format(home.getVacuumDate()));

        schedule.put("sequence", sequence);
        schedule.put("room1", room1);
        schedule.put("room2", room2);
        schedule.put("vacuum", vacuum);

        System.out.println(schedule.toString(4));

    }


    public void printJson(String defaultLocation, String archiveLocation) {

        if (!new File(archiveLocation).exists()) {
            try {
                new File(archiveLocation).mkdir();
                Files.copy(new File(defaultLocation + "/recent.json").toPath(), new File(archiveLocation + "/recent_first.json").toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter file = new FileWriter(defaultLocation + "/recent.json")) {

            file.write(schedule.toString(4));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM.dd.yyyy");

        try (FileWriter file = new FileWriter(archiveLocation + "/recent_" + dateFormat2.format(new Date()) + ".json")) {
            file.write(schedule.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}