package br.com.lucasts.lucasquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    List<Questao> questoes = new ArrayList<Questao>(){
        {
            add(new Questao("A música 'Stairway to Heaven' pertence a qual banda?", R.id.rb_resposta3, "Judas Priest","AC/DC","Led Zeppelin","Deep Purple"));
            add(new Questao("Na primeira versão de Star Wars, Luke Skywalker teria outro sobrenome. Qual seria?", R.id.rb_resposta1, "Starkiller","Starlord","Darksky","Palpatine"));
            add(new Questao("'Death Note' é o nome de um caderno com poderes capazes de...", R.id.rb_resposta4, "Permitir telepatia com aliens","Corrigir ortografia","Matar bruxas","Matar qualquer humano "));
            add(new Questao("Em que ano foi publicado o livro 1984 de George Orwell?", R.id.rb_resposta1, "1949","1919","1984","2001"));
            add(new Questao("Qual o maior artilheiro do Fortaleza em Camp. Brasileiros?", R.id.rb_resposta2, "Clodoaldo","Rinaldo","Osvaldo","Vinícius"));
        }

    };

    private void carregarQuestao(){

        if (questoes.size() > 0){
            RadioGroup rgRespostas = (RadioGroup)findViewById(R.id.rg_respostas);
            Questao q = questoes.remove(0);
            pergunta.setText(q.getPergunta());
            List<String> resposta = q.getRespostas();
            rbResposta1.setText(resposta.get(0));
            rbResposta2.setText(resposta.get(1));
            rbResposta3.setText(resposta.get(2));
            rbResposta4.setText(resposta.get(3));
            respostaCerta = q.getRespostaCerta();
            rgRespostas.setSelected(false);

        } else {
            Intent intent = new Intent(this, RespostaActivity.class);
            intent.putExtra("pontos", pontos);
            startActivity(intent);
            finish();
        }

    }

    TextView pergunta;
    RadioButton rbResposta1, rbResposta2, rbResposta3, rbResposta4;
    int respostaCerta = R.id.rb_resposta1;
    int pontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        pergunta = findViewById(R.id.tv_pergunta);
        rbResposta1 = findViewById(R.id.rb_resposta1);
        rbResposta2 = findViewById(R.id.rb_resposta2);
        rbResposta3 = findViewById(R.id.rb_resposta3);
        rbResposta4 = findViewById(R.id.rb_resposta4);
        carregarQuestao();

    }

    public void btnResponderOnClick(View v){
        RadioGroup rgRespostas = (RadioGroup)findViewById(R.id.rg_respostas);
        RadioButton rb = (RadioButton)findViewById(rgRespostas.getCheckedRadioButtonId());
        Intent intent = new Intent(this, RespostaActivity.class);
        if(rgRespostas.getCheckedRadioButtonId() == respostaCerta) {
            intent.putExtra("acertou", true);
            pontos++;
        }
        else intent.putExtra("acertou", false);
        intent.putExtra("pontos", pontos);
        startActivity(intent);
        rb.setChecked(false);
    }

    protected void onRestart(){
        super.onRestart();
        carregarQuestao();
    }
}
