package id.rendesvouz.wicheckapps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;

import id.rendesvouz.wicheckapps.Model.Questions;

public class SelectColor extends AppCompatActivity {

    TextView colorBlack,colorBlue,colorGreen,colorOrange,colorRed,colorNo,colorBrown,colorOrange1,colorGreen1,colorBlue1,colorBlack1;
    Vector<Questions> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_color);

        // no color
        colorBlack = findViewById(R.id.colorBlack);
        colorBlack.setBackgroundColor(Color.rgb(255,255,255));

        colorBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",0);
                startActivity(intent);
            }
        });

        // kuning pucat
        colorBlue = findViewById(R.id.colorBlue);
        colorBlue.setBackgroundColor(Color.rgb(254,252,223));
        colorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Result.class);
                intent.putExtra("ColorIndex",1);
                getQuestion(1);
                intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                startActivity(intent);
            }
        });

        // kuning jernih
        colorGreen = findViewById(R.id.colorGreen);
        colorGreen.setBackgroundColor(Color.rgb(255,247,154));
        colorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Result.class);
                intent.putExtra("ColorIndex",2);
                getQuestion(2);
                intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                startActivity(intent);
            }
        });


        // kuning gelap
        colorOrange = findViewById(R.id.colorOrange);
        colorOrange.setBackgroundColor(Color.rgb(254,241,0));

        colorOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Result.class);
                intent.putExtra("ColorIndex",3);
                getQuestion(3);
                intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                startActivity(intent);
            }
        });
        // kuning orange
        colorRed = findViewById(R.id.colorRed);
        colorRed.setBackgroundColor(Color.rgb(248,207,91));
        colorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Result.class);
                intent.putExtra("ColorIndex",4);
                getQuestion(4);
                intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                startActivity(intent);
            }
        });

            // coklat
        colorNo = findViewById(R.id.colorNo);
        colorNo.setBackgroundColor(Color.rgb(190,130,77));

        colorNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",5);
                startActivity(intent);
            }
        });
        //merah
        colorBrown = findViewById(R.id.colorBrown);
        colorBrown.setBackgroundColor(Color.rgb(243,138,140));


        colorBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",6);
                startActivity(intent);
            }
        });
        // orange
        colorOrange1 = findViewById(R.id.colorOrange1);
        colorOrange1.setBackgroundColor(Color.rgb(250,176,85));
        colorOrange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",7);
                startActivity(intent);
            }
        });

        // green
        colorGreen1 = findViewById(R.id.colorGreen1);
        colorGreen1.setBackgroundColor(Color.rgb(108,189,99));

        colorGreen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",8);
                startActivity(intent);
            }
        });
        // blue
        colorBlue1 = findViewById(R.id.colorBlue1);
        colorBlue1.setBackgroundColor(Color.rgb(109,195,255));

        colorBlue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Result.class);
                intent.putExtra("ColorIndex",9);
                getQuestion(9);
                intent.putExtra("AnswerYes", questions.get(0).getJawabanYes());
                intent.putExtra("AnswersYes", questions.get(0).getAnswerYes());
                startActivity(intent);
            }
        });
        // hitam
        colorBlack1 = findViewById(R.id.colorBlack1);
        colorBlack1.setBackgroundColor(Color.rgb(0,0,0));

        colorBlack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectColor.this, Question.class);
                intent.putExtra("ColorIndex",10);
                startActivity(intent);
            }
        });
    }

    void getQuestion(int index){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(SelectColor.this);
        databaseAccess.open();
        questions = databaseAccess.getQuestion(index);
        databaseAccess.close();
    }
}
