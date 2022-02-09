package com.example.apicallwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


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

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.getDataButtonId);
        tv=findViewById(R.id.textId);


        RequestQueue requestQueue=Volley.newRequestQueue(this);
        //String url="https://jsonplaceholder.typicode.com/todos/1";
        String url="https://jsonplaceholder.typicode.com/posts";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {
                            //String obj=response.getString("userId");

                            //String ob=response.getString("userId");
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            String str="",str2="";

                            for(int i=0;i<response.length();i++)

                            {
                                JSONObject obj=response.getJSONObject(i);
                                //str=str + obj.getString("userId")+"\n";
                               // str2=str2 + obj.getString("title")+"\n";
                                str=obj.getString("userId")+" "+obj.getString("title");



                            }
                            tv.setText(str);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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