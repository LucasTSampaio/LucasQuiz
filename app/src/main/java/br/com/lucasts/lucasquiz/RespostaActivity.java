package br.com.lucasts.lucasquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);
        getSupportActionBar().hide();

        ImageView imgResposta = findViewById(R.id.img_resposta);
        TextView resposta = findViewById(R.id.tv_resposta);
        Button btnJogarNovamente = findViewById((R.id.btn_novamente));

        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        if (intent.hasExtra("acertou")){
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);

            if(acertou){
                imgResposta.setImageResource(R.drawable.correto);
                resposta.setText("Acertou! Pontuação: "+pontos);
            } else {
                imgResposta.setImageResource(R.drawable.errado);
                resposta.setText("Errou! Pontuação: "+pontos);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();

        } else {
            btnJogarNovamente.setVisibility(View.VISIBLE);
            resposta.setText("Fez "+pontos+" pontos!");

            if(pontos >= 3){
                imgResposta.setImageResource(R.drawable.bom);
            } else {
                imgResposta.setImageResource(R.drawable.ruim);
            }
        }


    }

    public void btnJogarNovamente(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}
