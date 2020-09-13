package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayAlbums extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Album> albums;
    private static String JSON_URL = "https://jsonplaceholder.typicode.com/photos";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_albums);

        recyclerView = findViewById(R.id.albumList);
        albums = new ArrayList<>();
        extractAlbums();
    }

    private void extractAlbums() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject albumObject = response.getJSONObject(i);

                        Album album = new Album();
                        album.setTitle(albumObject.getString("title").toString());
                        album.setCoverImage(albumObject.getString("thumbnailUrl").toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),albums);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("tag", "onErrorResponse: " + volleyError.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }
}
