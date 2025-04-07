package com.example.lr1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LR1Activity extends AppCompatActivity {

    private TextView textInfo;
    private Button buttonShowInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr1);

        textInfo = findViewById(R.id.textInfo);
        buttonShowInfo = findViewById(R.id.buttonShowInfo);

        buttonShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInfo.setText(
                        "Прізвище: Федько\n" +
                                "Ім'я: Роман\n" +
                                "По батькові: Вадимович\n" +
                                "Спеціальність: Програмна інженерія\n" +
                                "Курс: 4, Група: ПІ-4-1\n" +
                                "Мета: Отримати глибокі знання з мобільної розробки"
                );
            }
        });
    }
}