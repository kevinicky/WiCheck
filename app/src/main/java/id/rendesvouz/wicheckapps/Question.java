package id.rendesvouz.wicheckapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Vector;

import id.rendesvouz.wicheckapps.Model.Questions;

public class Question extends AppCompatActivity {
    private Vector<Questions> questions;
    private int index = 0;
    private int step = 0;
    private Questions tempQuestion;
    private TextView tvQuestion;
    private Button btnYes, btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("ColorIndex");
        tvQuestion = findViewById(R.id.tv_question);
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);

        setQuestions();
        updateQuestions(step);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Question.this, Result.class);
                intent.putExtra("AnswerYes", tempQuestion.getJawabanYes());
                intent.putExtra("AnswersYes", tempQuestion.getAnswerYes());
                startActivity(intent);
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step++;
                if(step < questions.size()){
                    updateQuestions(step);
                }
                else{
                    Intent intent = new Intent(Question.this, Result.class);
                    intent.putExtra("AnswerNo", tempQuestion.getJawabanNo());
                    intent.putExtra("AnswersNo", tempQuestion.getAnswerNo());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setQuestions(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(Question.this);
        databaseAccess.open();

        questions = databaseAccess.getQuestion(index);
        databaseAccess.close();
    }

    private void updateQuestions(int i){
        tempQuestion = questions.get(i);
        tvQuestion.setText(tempQuestion.getPertayaan());
    }

}
