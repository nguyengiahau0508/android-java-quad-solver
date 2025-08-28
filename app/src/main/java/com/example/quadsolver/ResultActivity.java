package com.example.quadsolver;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonExit = findViewById(R.id.button_exit);
        EditText inputA = findViewById(R.id.input_a);
        EditText inputB = findViewById(R.id.input_b);
        EditText inputC = findViewById(R.id.input_c);
        TextView textViewDescription = findViewById(R.id.textView_description);
        TextView textViewXFirst = findViewById(R.id.textView_x_fist);
        TextView textViewXSecond = findViewById(R.id.textView_x_second);

        int a = getIntent().getIntExtra("a", 0);
        int b = getIntent().getIntExtra("b", 0);
        int c = getIntent().getIntExtra("c", 0);

        inputA.setText(String.valueOf(a));
        inputB.setText(String.valueOf(b));
        inputC.setText(String.valueOf(c));

        int delta = (b * b) - (4 * a * c);

        if (delta < 0) {
            textViewDescription.setText("The quadratic equation has no real solution.");
        } else if (delta == 0) {
            textViewDescription.setText("The quadratic equation has a double root:");
            double x = (-b) / (2.0 * a);
            textViewXFirst.setText("X1 = X2 = " + x);
        } else {
            textViewDescription.setText("The quadratic equation has two distinct roots:");
            double x1 = (-b + Math.sqrt(delta)) / (2.0 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2.0 * a);
            textViewXFirst.setText("X1 = " + x1);
            textViewXSecond.setText("X2 = " + x2);
        }


        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
