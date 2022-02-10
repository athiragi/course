package com.example.courseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
AppCompatButton b1;
String getTitle,getDes,getDuration,getVen,getDate;
String apiurl="http://mountzioncollege.herokuapp.com/addcourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.ct);
        e2=(EditText) findViewById(R.id.des);
        e3=(EditText) findViewById(R.id.du);
        e4=(EditText) findViewById(R.id.ven);
        e5=(EditText) findViewById(R.id.date);
        b1=(AppCompatButton) findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTitle=e1.getText().toString();
                getDes=e2.getText().toString();
                getDuration=e3.getText().toString();
                getVen=e4.getText().toString();
                getDate=e5.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<>();
                        hm.put("courseTitle",getTitle);
                        hm.put("courseDescription",getDes);
                        hm.put("courseDuration",getDuration);
                        hm.put("courseVenue",getVen);
                        hm.put("courseDate",getDate);
                        return hm;

                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);
            }
        });

    }
}