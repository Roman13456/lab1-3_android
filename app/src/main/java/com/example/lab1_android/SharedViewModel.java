package com.example.lab1_android;

import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private String selectedLanguage;

    public void setSelectedLanguage(String language) {
        this.selectedLanguage = language;
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }
}
