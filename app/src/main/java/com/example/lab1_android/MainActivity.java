package com.example.lab1_android;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerLanguages;
    private TextView textSelectedLanguage;
    private Button buttonOk, buttonCancel;
    private String selectedLanguage = null; // Змінна для збереження вибору

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ініціалізація елементів інтерфейсу
        spinnerLanguages = findViewById(R.id.spinner_languages);
        textSelectedLanguage = findViewById(R.id.text_selected_language);
        buttonOk = findViewById(R.id.button_ok);
        buttonCancel = findViewById(R.id.button_cancel);

        // Масив мов програмування
        String[] languages = {"","Java", "Kotlin", "Python", "C++", "JavaScript", "Swift", "Go", "Rust"};

        // Адаптер для Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);

        // Обробник вибору у Spinner
        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    selectedLanguage = null;
                }else {
                    selectedLanguage = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLanguage = null;
            }
        });

        // Обробник кнопки OK
        buttonOk.setOnClickListener(view -> {
            if (selectedLanguage != null) {
                textSelectedLanguage.setText("Обрана мова: " + selectedLanguage);
            } else {
                Toast.makeText(MainActivity.this, "Будь ласка, виберіть мову програмування", Toast.LENGTH_SHORT).show();
            }
        });

        // Обробник кнопки Cancel
        buttonCancel.setOnClickListener(view -> {
            textSelectedLanguage.setText("Обрана мова: ");
            spinnerLanguages.setSelection(0); // Скидає вибір на перший елемент
            selectedLanguage = null;
        });
    }
}