package com.example.agendamvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.agendamvc.models.EventModel;

public class MainActivity extends AppCompatActivity {

    EventModel event = new EventModel();
    EditText id, date, subject, activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id = (EditText) findViewById(R.id.id_agenda);
        date = (EditText) findViewById(R.id.fecha);
        subject = (EditText) findViewById(R.id.asunto);
        activity = (EditText) findViewById(R.id.actividad);
    }

    public void insert(View v){
        event.setId(id.getText().toString());
        event.setDate(date.getText().toString());
        event.setSubject(subject.getText().toString());
        event.setActivity(activity.getText().toString());
        event.setContext(this);
        event.insertEvent();
    }

    public void consult(View v){
        event.setContext(this);
        event.consultEvent(id.getText().toString());
        date.setText(event.getDate());
        subject.setText(event.getSubject());
        activity.setText(event.getActivity());
    }
}