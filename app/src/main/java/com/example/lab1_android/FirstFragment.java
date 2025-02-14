package com.example.lab1_android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.FileOutputStream;
import java.io.IOException;


public class FirstFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private String selectedLanguage = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        Spinner spinnerLanguages = view.findViewById(R.id.spinner_languages);
        Button buttonOk = view.findViewById(R.id.button_ok);

        String[] languages = {"", "Java", "Kotlin", "Python", "C++", "JavaScript", "Swift", "Go", "Rust"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    selectedLanguage = null;
                }else{
                    selectedLanguage = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLanguage = null;
            }
        });

        buttonOk.setOnClickListener(v -> {
            if (selectedLanguage == null) {
                Toast.makeText(requireContext(), "Будь ласка, виберіть мову перед продовженням!", Toast.LENGTH_SHORT).show();
            } else {
                sharedViewModel.setSelectedLanguage(selectedLanguage);
                MainActivity.saveData(selectedLanguage, requireActivity());
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new SecondFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}

