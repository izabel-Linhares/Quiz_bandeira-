package com.example.quiz_bandeira;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tela_final extends AppCompatActivity {

    TextView txtNomeJogador;
    TextView txtAcertos;

    Button btnResponderNovamente;
    Button btnTelaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_final);

        // Ligando os componentes do XML
        txtNomeJogador = findViewById(R.id.txtNomeJogador);
        txtAcertos = findViewById(R.id.txtAcertos);

        btnResponderNovamente = findViewById(R.id.btnResponderNovamente);
        btnTelaPrincipal = findViewById(R.id.btnTelaPrincipal);

        // Recebe o nome e a pontuação enviados pela última pergunta
        Intent intent = getIntent();

        String nome = intent.getStringExtra("nome");
        int acertos = intent.getIntExtra("acertos", 0);

        // Exibe as informações na tela
        if (nome != null) {
            txtNomeJogador.setText(nome);
        }

        txtAcertos.setText("Acertos: " + acertos);

        // BOTÃO RESPONDER NOVAMENTE
        btnResponderNovamente.setOnClickListener(v -> {

            Intent voltarQuiz = new Intent(Tela_final.this, Tela_2.class);

            // Envia novamente o nome para o quiz
            voltarQuiz.putExtra("nome", nome);

            startActivity(voltarQuiz);

            finish();
        });

        // BOTÃO TELA PRINCIPAL
        btnTelaPrincipal.setOnClickListener(v -> {

            Intent telaPrincipal = new Intent(Tela_final.this, MainActivity.class);

            startActivity(telaPrincipal);

            finish();
        });
    }
}