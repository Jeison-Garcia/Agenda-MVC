package com.example.agendamvc.models;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventModel {
    String id, date, subject, activity;
    Context ctx;

    public EventModel(){

    }

    public void insertEvent(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.20.36/Agenda/insertar.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ctx, "Evento creado con exito", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("id_agenda", getId());
                parameters.put("fecha", getDate());
                parameters.put("asunto", getSubject());
                parameters.put("actividad", getActivity());
                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(stringRequest);

    }

    public void consultEvent(String id){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://192.168.20.36/Agenda/consultar.php?id_agenda=" + id + "", (response) -> {
            JSONObject jsonObject = null;

            for (int i = 0; i < response.length(); i++) {
                try {
                    jsonObject = response.getJSONObject(i);
                    setDate(jsonObject.getString("fecha"));
                    setSubject(jsonObject.getString("asunto"));
                    setActivity(jsonObject.getString("actividad"));
                } catch (JSONException e) {
                    Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "No se registra en la agenda ningún evento con la ID ingresada", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ctx);
        requestQueue.add(jsonArrayRequest);
    }

    public void setId(String event_id){
        id = event_id;
    }
    public void setDate(String event_date){
        date = event_date;
    }
    public void setSubject(String event_subject){
        subject = event_subject;
    }
    public void setActivity(String event_description){
        activity = event_description;
    }
    public void setContext(Context context){
        ctx = context;
    }

    public String getId(){
        return id;
    }
    public String getDate(){
        return  date;
    }
    public String getSubject(){
        return subject;
    }
    public String getActivity(){
        return activity;
    }

}
