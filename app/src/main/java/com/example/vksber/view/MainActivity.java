package com.example.vksber.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vksber.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView apiTokenTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences("myPrefs", MODE_PRIVATE);

        apiTokenTV = findViewById(R.id.token_tv);

        apiTokenTV.setText(preferences.getString("token",""));
    }
}
