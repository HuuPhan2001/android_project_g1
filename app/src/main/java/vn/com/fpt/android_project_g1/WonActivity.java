package vn.com.fpt.android_project_g1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.BuildConfig;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView resultText;
    int correct, wrong;
    LinearLayout btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resultText = findViewById(R.id.resultText);
        btnShare= findViewById(R.id.btnShare);

        circularProgressBar.setProgress(correct);
        resultText.setText(correct+"/20");

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                   Intent shareIntent = new Intent(Intent.ACTION_SEND);
                   shareIntent.setType("text/plain");
                   shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                   String shareMessage = "\nI got "+correct+"Out of 20 you can also try ";
                   shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.BUILD_TYPE + "\n\n";
                   shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                   startActivity(Intent.createChooser(shareIntent, "Choose one"));
                }catch (Exception e){
                    //e.toString();
                }
            }
        });
    }
}