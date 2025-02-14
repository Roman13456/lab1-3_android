package com.example.lab1_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondActivity extends AppCompatActivity {

    TextView loadedDataField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        loadedDataField = findViewById(R.id.loadedData);
        StringBuilder result = MainActivity.getData(this);
        if (result == null || result.toString().isEmpty()) {
            loadedDataField.setText(getString(R.string.empty_file));
        } else {
            loadedDataField.setText(getString(R.string.SavedData, result.toString()));
        }
    }

    public void goBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}