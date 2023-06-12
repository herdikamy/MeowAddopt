package com.example.meowaddopt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChatActivity extends AppCompatActivity {

    private ImageView klikProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        klikProfile = findViewById(R.id.ownerProfile);

        klikProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(ChatActivity.this, DetailOwnerActivity.class);
                startActivity(detailIntent);
            }
        });
    }
}