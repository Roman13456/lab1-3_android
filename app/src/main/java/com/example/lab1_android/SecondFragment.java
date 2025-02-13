package com.example.lab1_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class SecondFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        TextView textSelectedLanguage = view.findViewById(R.id.text_selected_language);
        Button buttonCancel = view.findViewById(R.id.button_cancel);

        String selectedLanguage = sharedViewModel.getSelectedLanguage();
        if (selectedLanguage != null) {
            textSelectedLanguage.setText(getString(R.string.Label,selectedLanguage));
        } else {
            textSelectedLanguage.setText("Обрана мова: ");
        }

        buttonCancel.setOnClickListener(v -> {
            // Очищуємо значення вибраної мови у ViewModel
            sharedViewModel.setSelectedLanguage(null);

            // Повертаємося до FirstFragment
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new FirstFragment())
                    .commit();
        });


        return view;
    }
}

