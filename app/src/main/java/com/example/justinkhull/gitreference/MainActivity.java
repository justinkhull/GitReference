package com.example.justinkhull.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView = findViewById(R.id.git_references);

        //GitReference gitReference;

        //ArrayList<GitReference> gitReferences = new ArrayList<>();

        //JsonUtils.isFilePresent(getApplicationContext(), "gitReference.json");

        String logThis = JsonUtils.read(getApplicationContext(), "gitReference.json");
        if (logThis == null) {
            Log.i("LOG_THIS", "NULLLLLLLLL");
        }
        Log.i("LOG_THIS", logThis);


    }
}
