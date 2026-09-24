package com.example.quiz_bandeira;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtSeunome;
    private Button btnIniciar;
    private Button btnSair;

    private HorizontalScrollView flagContainer;

    private ValueAnimator animacaoBandeiras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);


        // ==========================================
        // AJUSTE DA TELA
        // ==========================================

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );


        // ==========================================
        // LIGANDO OS COMPONENTES DO XML AO JAVA
        // ==========================================

        edtSeunome = findViewById(R.id.edtSeunome);

        btnIniciar = findViewById(R.id.btnIniciar);

        btnSair = findViewById(R.id.btnSair);

        flagContainer = findViewById(R.id.flagContainer);


        // ==========================================
        // BOTÃO INICIAR DESABILITADO NO COMEÇO
        // ==========================================

        btnIniciar.setEnabled(false);


        // ==========================================
        // VERIFICAÇÃO DO NOME DIGITADO
        // ==========================================

        edtSeunome.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {

                String nome = s.toString().trim();

                if (nome.isEmpty()) {

                    btnIniciar.setEnabled(false);

                } else {

                    btnIniciar.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // ==========================================
        // BOTÃO INICIAR QUIZ
        // ==========================================

        btnIniciar.setOnClickListener(v -> {

            String nome = edtSeunome.getText()
                    .toString()
                    .trim();


            // Segurança: não deixa iniciar sem nome
            if (!nome.isEmpty()) {

                Intent intent = new Intent(
                        MainActivity.this,
                        Tela_2.class
                );


                // Envia o nome para a próxima tela
                intent.putExtra("nome", nome);


                startActivity(intent);
            }
        });


        // ==========================================
        // BOTÃO SAIR
        // ==========================================

        btnSair.setOnClickListener(v -> {

            finishAffinity();

        });


        // ==========================================
        // INICIA A ANIMAÇÃO DAS BANDEIRAS
        // ==========================================

        flagContainer.post(() -> {

            iniciarAnimacaoBandeiras();

        });
    }


    // ==================================================
    // ANIMAÇÃO DAS BANDEIRAS
    // ==================================================

    private void iniciarAnimacaoBandeiras() {

        if (flagContainer.getChildCount() == 0) {
            return;
        }


        int larguraTotal = flagContainer
                .getChildAt(0)
                .getWidth();


        if (larguraTotal <= 0) {
            return;
        }


        // Como as bandeiras foram repetidas no XML,
        // usamos metade da largura para voltar
        // exatamente ao começo.

        int distancia = larguraTotal / 2;


        animacaoBandeiras = ValueAnimator.ofInt(
                0,
                distancia
        );


        animacaoBandeiras.setDuration(15000);

        animacaoBandeiras.setRepeatCount(
                ValueAnimator.INFINITE
        );


        animacaoBandeiras.addUpdateListener(
                animation -> {

                    int valor =
                            (int) animation.getAnimatedValue();

                    flagContainer.scrollTo(valor, 0);

                }
        );


        animacaoBandeiras.start();
    }


    // ==================================================
    // PARA A ANIMAÇÃO QUANDO A TELA É DESTRUÍDA
    // ==================================================

    @Override
    protected void onDestroy() {

        if (animacaoBandeiras != null) {

            animacaoBandeiras.cancel();

        }

        super.onDestroy();
    }
}