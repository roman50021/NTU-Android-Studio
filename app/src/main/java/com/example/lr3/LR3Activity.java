package com.example.lr3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lr1.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LR3Activity extends AppCompatActivity {

    private EditText inputA, inputB, inputC, inputX1, inputX2, inputY, inputStep;
    private TextView resultText;
    private Button calculateButton, saveButton, loadButton;
    private final String fileName = "result_lr3.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr3);

        inputA = findViewById(R.id.inputA);
        inputB = findViewById(R.id.inputB);
        inputC = findViewById(R.id.inputC);
        inputX1 = findViewById(R.id.inputX1);
        inputX2 = findViewById(R.id.inputX2);
        inputY = findViewById(R.id.inputY);
        inputStep = findViewById(R.id.inputStep);
        resultText = findViewById(R.id.resultText);

        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);

        calculateButton.setOnClickListener(v -> calculate());
        saveButton.setOnClickListener(v -> saveToFile());
        loadButton.setOnClickListener(v -> loadFromFile());
    }

    private double calculateU(double x, double y, double a, double b, double c) {
        double sqrt1 = b - c;
        double sqrt2 = a - b + c;

        if (sqrt1 < 0 || sqrt2 < 0) return Double.NaN;

        double numerator = Math.pow(Math.tan(y), 3) + Math.pow(Math.sin(x), 5) * Math.sqrt(sqrt1);
        double denominator = Math.sqrt(sqrt2);
        return numerator / denominator;
    }

    private void calculate() {
        try {
            double a = Double.parseDouble(inputA.getText().toString().trim());
            double b = Double.parseDouble(inputB.getText().toString().trim());
            double c = Double.parseDouble(inputC.getText().toString().trim());
            double x1 = Double.parseDouble(inputX1.getText().toString().trim());
            double x2 = Double.parseDouble(inputX2.getText().toString().trim());
            double y = Double.parseDouble(inputY.getText().toString().trim());
            double step = Double.parseDouble(inputStep.getText().toString().trim());

            if (x2 < x1 || step <= 0) {
                resultText.setText("❌ Невірний діапазон або крок");
                return;
            }

            StringBuilder builder = new StringBuilder();
            for (double x = x1; x <= x2; x += step) {
                double u = calculateU(x, y, a, b, c);
                builder.append(String.format("x = %.2f, U = %.3f\n", x, u));
            }

            resultText.setText(builder.toString());

        } catch (Exception e) {
            Toast.makeText(this, "❗ Помилка вводу", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveToFile() {
        try (FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE)) {
            fos.write(resultText.getText().toString().getBytes());
            Toast.makeText(this, "✅ Збережено у файл", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "❌ Помилка при збереженні", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFromFile() {
        try (FileInputStream fis = openFileInput(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            resultText.setText(builder.toString());
        } catch (Exception e) {
            Toast.makeText(this, "❌ Помилка при завантаженні", Toast.LENGTH_SHORT).show();
        }
    }
}

