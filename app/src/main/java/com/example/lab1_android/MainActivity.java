package com.example.lab1_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void startNewActivity(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    ImageButton deleteBtn;


    public static void saveData(String selectedLanguage, Context context){
        if (context == null) {
            return; // Prevent crashes if context is null
        }
        try(FileOutputStream fileInput =  context.openFileOutput("language.txt", Context.MODE_PRIVATE)) {
            fileInput.write(selectedLanguage.getBytes());
            Toast.makeText(context, "Text saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Can't process the file", Toast.LENGTH_SHORT).show();
        }
    }

    public static void removeContents(Context context){
        if (context == null) {
            return; // Prevent crashes if context is null
        }
        try(FileOutputStream fileInput =  context.openFileOutput("language.txt", Context.MODE_PRIVATE)) {
            fileInput.write("".getBytes());
            Toast.makeText(context, "Text deleted successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Can't process the file", Toast.LENGTH_SHORT).show();
        }
    }

    public static StringBuilder getData(Context context){
        File file = context.getFileStreamPath("language.txt");

        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInput = context.openFileInput("language.txt");
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader bR = new BufferedReader(reader);
            String lines = "";
            StringBuilder result = new StringBuilder();
            while ((lines = bR.readLine())!=null){
                result.append(lines).append("\n");
            }
            return result;

        } catch (IOException e) {
            Toast.makeText(context, "Couldn't process the file", Toast.LENGTH_SHORT).show();
        }
        return null;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeContents(MainActivity.this);
            }
        });

        // add first fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new FirstFragment())
                    .commit();
        }
    }
}
