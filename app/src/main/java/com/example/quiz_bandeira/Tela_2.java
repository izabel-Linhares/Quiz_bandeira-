package com.example.quiz_bandeira;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class Tela_2 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button btnResponder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        radioGroup = findViewById(R.id.radioGroup);
        btnResponder = findViewById(R.id.btnResponder);

        String nome = getIntent().getStringExtra("nome");

        btnResponder.setEnabled(false);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            btnResponder.setEnabled(true);
        });

        btnResponder.setOnClickListener(v -> {

            int acertos = 0;

            // Argentina = radioButton3
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton3) {
                acertos++;
            }

            Intent intent = new Intent(Tela_2.this, Tela_3.class);

            intent.putExtra("nome", nome);
            intent.putExtra("acertos", acertos);

            startActivity(intent);
            finish();
        });

        voltarParaInicio();
    }

    private void voltarParaInicio() {
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent intent = new Intent(Tela_2.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}