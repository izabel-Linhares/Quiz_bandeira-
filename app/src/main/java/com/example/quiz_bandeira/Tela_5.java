package com.example.quiz_bandeira;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class Tela_5 extends AppCompatActivity {

    private RadioGroup radioGroup;
    private Button btnResponder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela5);

        radioGroup = findViewById(R.id.radioGroup);
        btnResponder = findViewById(R.id.btnResponder);

        String nome = getIntent().getStringExtra("nome");
        int acertos = getIntent().getIntExtra("acertos", 0);

        btnResponder.setEnabled(false);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            btnResponder.setEnabled(true);
        });

        btnResponder.setOnClickListener(v -> {

            int novosAcertos = acertos;

            // Cabo Verde = radioButton13
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton13) {
                novosAcertos++;
            }

            Intent intent = new Intent(Tela_5.this, Tela_6.class);

            intent.putExtra("nome", nome);
            intent.putExtra("acertos", novosAcertos);

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
                        Intent intent = new Intent(Tela_5.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }
}