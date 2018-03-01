package com.example.justinkhull.gitreference;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.MODE_APPEND;

/**
 * Created by kbriggs on 2/19/18.
 */

public class JsonUtils {

    public static String parseJsonToString(InputStream is) {
        String json = null;
        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<GitReference> populateGitReferences(String jsonString) {
        ArrayList<GitReference> data=new ArrayList<>();

        JSONArray jArray = null;
        try {
            // Load json String into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Extract all the "command" JSON objects into a JsonArray
            jArray = jsonObject.getJSONArray("commands");

            // Extract json objects from JsonArray and store into ArrayList as class objects
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                GitReference gitReference = new GitReference();
                gitReference.setCommand(json_data.getString("command"));
                gitReference.setExample(json_data.getString("example"));
                gitReference.setExplanation(json_data.getString("explanation"));
                gitReference.setSection(json_data.getString("section"));

                Log.i("JSON", "Adding: " + gitReference.getCommand());
                data.add(gitReference);
            }

        } catch (Exception ex) {

        }

        return data;
    }

    public static String read(Context context, String fileName) {
        String fnf = "FILE_NOT_FOUND";//ADDED TO SEE IF THE FILE WAS EVEN FOUND...IT WASN'T
        try {
            FileInputStream fis = context.openFileInput(fileName); //DOES THIS LOOK FOR THE FILE IN "ASSETS" FOLDER?
            //InputStream fis = context.getApplicationContext().getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException fileNotFound) {
            //return null; I CHANGED THIS******************************
            return fnf;
        } catch (IOException ioException) {
            //return null; I CHANGED THIS******************************
            return fnf;
        }
    }

    public static boolean create(Context context, String fileName, String jsonString){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);// TODO: Your code goes here
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();
            return true;
        } catch (FileNotFoundException fileNotFound) {
            return false;
        } catch (IOException ioException) {
            return false;
        }

    }

    public static boolean append(Context context, String fileName, String jsonString) throws IOException {
        // TODO: Your code here
        InputStream is = context.getApplicationContext().getAssets().open("gitReference.json");
        Log.i("JSON", jsonString);
        //FileOutputStream fos;
        //fos = openFileOutput(fileName, MODE_APPEND);

        return false;
    }

    public static boolean isFilePresent(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath() + "/" + fileName;
        File file = new File(path);
        return file.exists();
    }
}



//What is the correct order that I need to call these methods?

//isFilePresent
//read
//populateGitReferences
//append

//When does is create used?


/*
This assignment really doesn't seem that difficult (at least conceptually). I need to read in a json
file and break it up into json objects. Isn't that what this class does? In populateGitReferences?
Then I need to add these json objects to a listView in the activity_main.xml. But I need to write an
adaptor that takes the json strings info and breaks it up (line-by-line) and formats it for the listView.

Is this correct?
 */
