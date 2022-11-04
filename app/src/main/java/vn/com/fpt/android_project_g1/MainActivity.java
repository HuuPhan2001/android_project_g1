package vn.com.fpt.android_project_g1;

import android.content.Intent;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<QuestionModel> list;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Question");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);
                    list.add(questionModel);
                }
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        list.add(new QuestionModel("CauHoi1", "1", "2", "3", "4", "2"));
//        list.add(new QuestionModel("CauHoi2", "1", "2", "3", "4", "3"));
//        list.add(new QuestionModel("CauHoi3", "1", "2", "3", "4", "1"));
//        list.add(new QuestionModel("CauHoi4", "1", "2", "3", "4", "3"));
//        list.add(new QuestionModel("CauHoi5", "1", "2", "3", "4", "2"));
//        list.add(new QuestionModel("CauHoi6", "1", "2", "3", "4", "2"));
//        list.add(new QuestionModel("CauHoi7", "1", "2", "3", "4", "4"));
//        list.add(new QuestionModel("CauHoi8", "1", "2", "3", "4", "1"));
//        list.add(new QuestionModel("CauHoi9", "1", "2", "3", "4", "2"));
//        list.add(new QuestionModel("CauHoi10", "1", "2", "3", "4", "3"));
//        list.add(new QuestionModel("CauHoi11", "1", "2", "3", "4", "3"));
//        list.add(new QuestionModel("CauHoi12", "1", "2", "3", "4", "4"));
//        list.add(new QuestionModel("CauHoi13", "1", "2", "3", "4", "4"));
//        list.add(new QuestionModel("CauHoi14", "1", "2", "3", "4", "1"));
//        list.add(new QuestionModel("CauHoi15", "1", "2", "3", "4", "1"));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        }, 1500);
    }
}