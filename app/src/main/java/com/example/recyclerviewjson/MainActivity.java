package com.example.recyclerviewjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.CharacterIterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RecyclerView myRecycler;
    private ExampleAdapter myAdapter;
    private ArrayList<ExamplePost> myExamplePost;
    private RequestQueue myRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myRecycler = findViewById(R.id.recycler_view);
        myRecycler.setHasFixedSize(true);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));

        myExamplePost = new ArrayList<>();
        myRequestQueue = Volley.newRequestQueue(this);
        parseTheJSON();
    }

    private void parseTheJSON(){

        String url = "https://pixabay.com/api/?key=33159877-6ce9f0ba807dfd3d17781bd7a&q=india&image_type=photo&pretty=true";


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");
                                for(int i=0; i<jsonArray.length();i++){
                                    JSONObject hit = jsonArray.getJSONObject(i);
                                    String creatorName = hit.getString("user");
                                    String imgUrl = hit.getString("webformatURL");
                                    int likeCount = hit.getInt("likes");

                                    myExamplePost.add(new ExamplePost(imgUrl,creatorName,likeCount));

                                }

                                myAdapter = new ExampleAdapter(MainActivity.this, myExamplePost);
                                myRecycler.setAdapter(myAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    error.printStackTrace();
                }
            });

            myRequestQueue.add(request);


        }


}