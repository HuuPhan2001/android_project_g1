package vn.com.fpt.android_project_g1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    ContentLoadingProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        progressBar=findViewById(R.id.quiz_timer);

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
}