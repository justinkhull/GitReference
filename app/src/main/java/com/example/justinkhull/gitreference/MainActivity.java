package com.example.justinkhull.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.git_references);

        String json ="";
        InputStream is;
        try {
            is = getApplicationContext().getAssets().open("gitReference.json");
            json = JsonUtils.parseJsonToString(is);

            Log.i("JSON", json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<GitReference> gitReferencesList = JsonUtils.populateGitReferences(json);
        GitReferenceAdapter adapter = new GitReferenceAdapter(this, gitReferencesList);
        listView.setAdapter(adapter);





        String logThis = JsonUtils.read(getApplicationContext(), "gitReference.json");
        if (logThis == null) {
            Log.i("LOG_THIS", "NULLLLLLLLL");
        }
        Log.i("LOG_THIS", logThis);

    }

    public ArrayList<String> populateData(String jsonFileName) {
        ArrayList<String> returnList = new ArrayList<>();
        String jsonString = processData(jsonFileName);
        Log.i("JSON", jsonString);


        
        ArrayList<GitReference>references = JsonUtils.populateGitReferences(jsonString);

        for (GitReference g:references) {
            returnList.add(g.getCommand());
        }
        return returnList;
    }


    public String processData(String fileName) {
        String jsonString = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, fileName);

        //if file present, read it
        if (isFilePresent) {
            jsonString = JsonUtils.read(this, fileName);

            Log.i("JSON", "JSON was present");
        }
        //if it's not present, pull gitReference.json from assets folder and parse json to jsonString variable
        else {
            Log.i("JSON", "JSON file not present...creating");
            InputStream is = null;
            try {
                is = getApplicationContext().getAssets().open("gitReference.json");
            } catch (Exception ex) {
                //...do something?
            }
            jsonString = JsonUtils.parseJsonToString(is);
            boolean isFileCreated = JsonUtils.create(this, fileName, jsonString);

            if (isFileCreated) {
                Log.i("JSON", "Created the filesystem JSON");
            } else {
                //show the error or try again (perform this whole method again)
            }
        }
        return jsonString;
    }
}
