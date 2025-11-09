
package com.example.weathermaringa;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class WeatherListFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<WeatherItem> list = new ArrayList<>();
    WeatherAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather_list, container, false);
        recyclerView = v.findViewById(R.id.recyclerWeather);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WeatherAdapter(list);
        recyclerView.setAdapter(adapter);
        loadWeather();
        return v;
    }

    void loadWeather() {
        new AsyncTask<Void, Void, ArrayList<WeatherItem>>() {
            @Override
            protected ArrayList<WeatherItem> doInBackground(Void... voids) {
                ArrayList<WeatherItem> tempList = new ArrayList<>();
                try {
                    URL url = new URL("https://api.hgbrasil.com/weather?format=json&city_name=Maringá-PR&key=df7f5eae");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null){
                        sb.append(line);
                    }
                    br.close();
                    JSONObject root = new JSONObject(sb.toString());
                    JSONObject results = root.getJSONObject("results");
                    JSONArray forecast = results.getJSONArray("forecast");
                    for(int i=0;i<forecast.length();i++){
                        JSONObject day = forecast.getJSONObject(i);
                        tempList.add(new WeatherItem(
                                day.getString("weekday"),
                                day.getString("description"),
                                day.getInt("max"),
                                day.getInt("min")
                        ));
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
                return tempList;
            }

            @Override
            protected void onPostExecute(ArrayList<WeatherItem> weatherItems) {
                list.clear();
                list.addAll(weatherItems);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
