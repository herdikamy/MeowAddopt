package com.example.meowaddopt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailAdoptActivity extends AppCompatActivity {

    Button btnback;
    TextView btnappointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_adopt);

        btnback = findViewById(R.id.materialButton2);
        btnappointment = findViewById(R.id.btnmakeappointment);

        btnappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(DetailAdoptActivity.this, ChatActivity.class);
                startActivity(chatIntent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(DetailAdoptActivity.this, DashboardActivity.class);
                startActivity(backIntent);
            }
        });
    }
}