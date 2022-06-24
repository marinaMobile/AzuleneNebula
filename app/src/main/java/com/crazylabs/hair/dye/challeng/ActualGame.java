package com.crazylabs.hair.dye.challeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActualGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_game);
        TextView tv_v = findViewById(R.id.tv_v);
        TextView tv_e = findViewById(R.id.tv_e);
        TextView tv_n = findViewById(R.id.tv_n);
        TextView tv_u = findViewById(R.id.tv_u);
        TextView tv_s = findViewById(R.id.tv_s);
        Button submit = findViewById(R.id.submit_ans);
        EditText et_ans = findViewById(R.id.et_answer);
        CardView cv_v = findViewById(R.id.cv_v);
        CardView cv_e = findViewById(R.id.cv_e);
        CardView cv_n = findViewById(R.id.cv_n);
        CardView cv_u = findViewById(R.id.cv_u);
        CardView cv_s = findViewById(R.id.cv_s);

        cv_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv_v.setVisibility(View.VISIBLE);
            }
        });
        cv_e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv_e.setVisibility(View.VISIBLE);
            }
        });
        cv_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv_n.setVisibility(View.VISIBLE);
            }
        });
        cv_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv_u.setVisibility(View.VISIBLE);
            }
        });
        cv_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv_s.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String answer = et_ans.getText().toString().toLowerCase();
                                          if (answer.isEmpty()) {
                                              Toast.makeText(getApplicationContext(), "Input your answer", Toast.LENGTH_SHORT).show();
                                          } else {
                                              if (answer.equals("venus")) {
                                                  tv_v.setVisibility(View.VISIBLE);
                                                  tv_e.setVisibility(View.VISIBLE);
                                                  tv_n.setVisibility(View.VISIBLE);
                                                  tv_u.setVisibility(View.VISIBLE);
                                                  tv_s.setVisibility(View.VISIBLE);
                                                  startActivity(new Intent(getApplicationContext(), WinnerActivity.class));
                                                  finish();
                                              } else {
                                                  Toast.makeText(getApplicationContext(), "Think more or tap on the letter to take a hint", Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }

                                  }
        );

    }
}