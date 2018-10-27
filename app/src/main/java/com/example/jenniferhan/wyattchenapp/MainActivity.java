package com.example.jenniferhan.wyattchenapp;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Items> aList;
    ListView listW;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //declare arraylist and listview and execute the json url
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aList = new ArrayList<>();
        listW = (ListView) findViewById(R.id.listView);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("https://api.stackexchange.com/2.2/users?site=stackoverflow");

            }
        });

    }

    class ReadJSON extends AsyncTask<String, Integer, String> { //read json url and get the gravatr, display_name, bades, and user_type

        @Override
        protected String doInBackground(String... params) {
            return readURL(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {
            try {
                JSONObject jsonObj = new JSONObject(content);
                JSONArray jsonArr =  jsonObj.getJSONArray("items");

                for(int i =0;i<jsonArr.length(); i++){
                    JSONObject itemObj = jsonArr.getJSONObject(i);
                    aList.add(new Items(
                            itemObj.getString("profile_image"),
                            "Name: "+itemObj.getString("display_name"),
                            "Badges: "+itemObj.getString("badge_counts"),
                            "User type: " +itemObj.getString("user_type")
                    ));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            ProgressBar pbar = (ProgressBar) findViewById(R.id.progress_bar);
            ListGen adapter = new ListGen(getApplicationContext(), R.layout.custom_list_layout, aList);
            listW.setAdapter(adapter);
            pbar.setVisibility(View.GONE);
        }
    }


    private static String readURL(String theUrl) { // read the url
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);

            URLConnection urlCon = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
