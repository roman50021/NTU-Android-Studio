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

    private EditText inputX, inputY;
    private TextView resultText;
    private Button calculateButton, authorButton;

    // Константи
    private final double a = 5.0;
    private final double b = 2.0;
    private final double c = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr2);

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
            String xStr = inputX.getText().toString().trim();
            String yStr = inputY.getText().toString().trim();

            if (xStr.isEmpty() || yStr.isEmpty()) {
                Toast.makeText(this, "Будь ласка, введіть X та Y", Toast.LENGTH_SHORT).show();
                return;
            }

            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);

            if ((b - c) < 0 || (a - b + c) < 0) {
                resultText.setText("❌ Підкореневий вираз < 0");
                return;
            }

            double numerator = Math.pow(Math.tan(y), 3) + Math.pow(Math.sin(x), 5) * Math.sqrt(b - c);
            double denominator = Math.sqrt(a - b + c);
            double result = numerator / denominator;

            if (Double.isNaN(result) || Double.isInfinite(result)) {
                resultText.setText("❌ Обчислення дало некоректний результат (NaN або ∞)");
            } else {
                resultText.setText(String.format("U = %.3f", result));
            }

        } catch (Exception e) {
            Toast.makeText(this, "Помилка при обчисленні", Toast.LENGTH_LONG).show();
        }
    }

    private void showAuthorInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Інформація про автора");
        builder.setMessage("👨‍💻 Федько Роман Вадимович\n📚 Факультет: ФІОТ\n📖 Курс: 4, Група: ПІ-4-1\n🎯 Мета: Отримати глибокі знання з мобільної розробки");
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
