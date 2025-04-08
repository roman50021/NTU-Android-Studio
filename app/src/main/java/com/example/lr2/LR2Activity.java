package com.example.lr2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lr1.R;

public class LR2Activity extends AppCompatActivity {

    private EditText inputA, inputB, inputC, inputX, inputY;
    private TextView resultText;
    private Button calculateButton, authorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr2);

        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputC = findViewById(R.id.inputC);
        inputX = findViewById(R.id.inputX);
        inputY = findViewById(R.id.inputY);
        resultText = findViewById(R.id.resultText);
        calculateButton = findViewById(R.id.calculateButton);
        authorButton = findViewById(R.id.authorButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateExpression();
            }
        });

        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAuthorInfo();
            }
        });
    }

    private void calculateExpression() {
        try {
            double a = Double.parseDouble(inputA.getText().toString().trim());
            double b = Double.parseDouble(inputB.getText().toString().trim());
            double c = Double.parseDouble(inputC.getText().toString().trim());
            double x = Double.parseDouble(inputX.getText().toString().trim());
            double y = Double.parseDouble(inputY.getText().toString().trim());

            double sqrt1 = b - c;
            double sqrt2 = a - b + c;

            if (sqrt1 < 0 || sqrt2 < 0) {
                resultText.setText("\u274C Підкореневий вираз < 0");
                return;
            }

            double numerator = Math.pow(Math.tan(y), 3) + Math.pow(Math.sin(x), 5) * Math.sqrt(sqrt1);
            double denominator = Math.sqrt(sqrt2);
            double result = numerator / denominator;

            if (Double.isNaN(result) || Double.isInfinite(result)) {
                resultText.setText("\u274C Результат невалідний (NaN або \u221E)");
            } else {
                resultText.setText(String.format("U = %.3f", result));
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "\u2757 Введіть усі значення коректно", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAuthorInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Інформація про автора");
        builder.setMessage("\uD83D\uDC68\u200D\uD83D\uDCBB Федько Роман Вадимович\n\uD83D\uDCDA Факультет: ФІОТ\n\uD83D\uDCD6 Курс: 4, Група: ПІ-4-1\n\uD83C\uDFC6 Мета: Отримати глибокі знання з мобільної розробки");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
