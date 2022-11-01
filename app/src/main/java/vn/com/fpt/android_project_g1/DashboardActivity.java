package vn.com.fpt.android_project_g1;

import static vn.com.fpt.android_project_g1.MainActivity.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    ContentLoadingProgressBar progressBar;
    List<QuestionModel> allQuestionList;
    QuestionModel questionModel;
    int index = 0;
    TextView card_question, optiona, optionb, optionc, optiond;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount =0;
    int wrongCount =0;
    LinearLayout nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuestionList = list;
        Collections.shuffle(allQuestionList);
        questionModel = list.get(index);

        nextBtn.setClickable(false);
        setAllData();

        countDownTimer=new CountDownTimer( 20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this,R.style.Dialoge);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_delog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DashboardActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();

            }
        }.start();
    }

    private void Hooks(){
        progressBar = findViewById(R.id.quiz_timer);

        card_question = findViewById(R.id.card_question);
        optiona = findViewById(R.id.card_optionA);
        optionb = findViewById(R.id.card_optionB);
        optionc = findViewById(R.id.card_optionC);
        optiond = findViewById(R.id.card_optionD);

        cardOA = findViewById(R.id.cardA);
        cardOB = findViewById(R.id.cardB);
        cardOC = findViewById(R.id.cardC);
        cardOD = findViewById(R.id.cardD);

        nextBtn = findViewById(R.id.nextBtn);
    }

    private void setAllData() {
        card_question.setText(questionModel.getQuestion());
        optiona.setText(questionModel.getoA());
        optionb.setText(questionModel.getoB());
        optionc.setText(questionModel.getoC());
        optiond.setText(questionModel.getoD());
    }

    public  void Correct(CardView card){
        card.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                correctCount++;
                index++;
                questionModel = list.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });

    }
    public  void Wrong(CardView card){
        card.setCardBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                wrongCount++;
                if(index<list.size()-1){
                    index++;
                    questionModel = list.get(index);
                    setAllData();
                    resetColor();
                    enableButton();
                }else{
                    GameWong();
                }
            }
        });

    }

    private void GameWong(){
        Intent intent = new Intent(DashboardActivity.this,WonActivity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        startActivity(intent);
    }
    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);

    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);

    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void optionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        if(questionModel.getoA().equals(questionModel.getAns())){
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                Correct(cardOA);
            }else{
                GameWong();
            }
        }else{
            Wrong(cardOA);
        }
    }
    public void optionBClick(View view) {

        disableButton();
        nextBtn.setClickable(true);
        if(questionModel.getoB().equals(questionModel.getAns())){
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                Correct(cardOB);
            }else{
                GameWong();
            }
        }else{
            Wrong(cardOB);
        }
    }
    public void optionCClick(View view) {

        disableButton();
        nextBtn.setClickable(true);
        if(questionModel.getoC().equals(questionModel.getAns())){
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                Correct(cardOC);
            }else{
                GameWong();
            }
        }else{
            Wrong(cardOC);
        }
    }
    public void optionDClick(View view) {

        disableButton();
        nextBtn.setClickable(true);
        if(questionModel.getoD().equals(questionModel.getAns())){
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));

            if(index < list.size()-1){
                Correct(cardOD);
            }else{
                GameWong();
            }
        }else{
            Wrong(cardOD);
        }
    }
}