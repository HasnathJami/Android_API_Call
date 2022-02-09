package com.example.apicallwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.apicallwithvolley.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        binding.getDataButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="https://jsonplaceholder.typicode.com/posts";
                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject obj = null;
                        String str="";

                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                obj=response.getJSONObject(i);
                                 str=obj.getString("title");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        binding.textId.setText(str);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonArrayRequest);




            }
        });




    }
}