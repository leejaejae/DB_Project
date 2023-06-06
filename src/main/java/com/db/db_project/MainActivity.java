package com.db.db_project;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageButton type = (ImageButton) findViewById(R.id.type);
    ImageButton map = (ImageButton) findViewById(R.id.map);

    type.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), type.class);
            startActivity(intent);
        }
    });

    map.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), map.class);
            startActivity(intent);
        }
    });
        name = findViewById(R.id.et_name);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        name.setText(userName);
    }
}