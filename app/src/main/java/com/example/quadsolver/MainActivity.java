package com.example.quadsolver;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> inputCoefficientLaucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonSubmit = findViewById(R.id.button_submit);
        Button buttonReset = findViewById(R.id.button_reset);
        Button buttonCoefficient = findViewById(R.id.button_coefficient);

        EditText inputA = findViewById(R.id.input_a);
        EditText inputB = findViewById(R.id.input_b);
        EditText inputC = findViewById(R.id.input_c);

        buttonSubmit.setEnabled(false);
        buttonSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String a = inputA.getText().toString().trim();
                String b = inputB.getText().toString().trim();
                String c = inputC.getText().toString().trim();

                if(!a.isEmpty()&&!b.isEmpty()&&!c.isEmpty()){
                    buttonSubmit.setEnabled(true);
                    buttonSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                }else {
                    buttonSubmit.setEnabled(false);
                    buttonSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                }
            }
        };

        inputA.addTextChangedListener(watcher);
        inputB.addTextChangedListener(watcher);
        inputC.addTextChangedListener(watcher);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputA.setText("");
                inputB.setText("");
                inputC.setText("");
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(inputA.getText().toString().trim());
                int b = Integer.parseInt(inputB.getText().toString().trim());
                int c = Integer.parseInt(inputC.getText().toString().trim());

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("a", a);
                intent.putExtra("b", b);
                intent.putExtra("c", c);

                startActivity(intent);
            }
        });

        inputCoefficientLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String a = data.getStringExtra("a");
                            String b = data.getStringExtra("b");
                            String c = data.getStringExtra("c");

                            Toast.makeText(this,
                                    "Nhận được: a=" + a + ", b=" + b + ", c=" + c,
                                    Toast.LENGTH_LONG).show();

                            inputA.setText(a);
                            inputB.setText(b);
                            inputC.setText(c);

                        }
                    }
                }
        );

        buttonCoefficient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputCoefficientActivity.class);
                inputCoefficientLaucher.launch(intent);
            }
        });


    }
}