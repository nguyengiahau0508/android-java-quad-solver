package com.example.quadsolver;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputCoefficientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_coefficient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.input_coefficient), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonOk = findViewById(R.id.button_ok);
        EditText inputA = findViewById(R.id.editText_a);
        EditText inputB = findViewById(R.id.editText_b);
        EditText inputC = findViewById(R.id.editText_c);
        buttonOk.setEnabled(false);
        buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));

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
                    buttonOk.setEnabled(true);
                    buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                }else {
                    buttonOk.setEnabled(false);
                    buttonOk.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                }
            }
        };

        inputA.addTextChangedListener(watcher);
        inputB.addTextChangedListener(watcher);
        inputC.addTextChangedListener(watcher);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = inputA.getText().toString().trim();
                String b = inputB.getText().toString().trim();
                String c = inputC.getText().toString().trim();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("a", a);
                resultIntent.putExtra("b", b);
                resultIntent.putExtra("c", c);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
