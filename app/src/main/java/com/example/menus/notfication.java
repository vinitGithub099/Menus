package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class notfication extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfication);
//        textView = findViewById(R.id.textView);
//        //getting the notification message
//        String message=getIntent().getStringExtra("message");
//        textView.setText(message);

        Intent pi = getIntent();
        int i = pi.getIntExtra("notificationID", 0);
        Toast.makeText(this, "notificationID = " + i, Toast.LENGTH_SHORT).show();
    }
}