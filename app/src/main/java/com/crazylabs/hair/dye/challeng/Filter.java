package com.crazylabs.hair.dye.challeng;

import static com.crazylabs.hair.dye.challeng.MainActivity.C_STR1;
import static com.crazylabs.hair.dye.challeng.MainActivity.DLNK1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Filter extends AppCompatActivity {
    TextView carryRep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        carryRep = findViewById(R.id.tv_hello);
        new asyncFunc().execute();

    }


    public class asyncFunc extends AsyncTask<Void, Void, Void> {


        String result;

        String cAdder = Hawk.get(C_STR1);

        String dAdder = Hawk.get(DLNK1);


        String corelnk = "https://tracker.infinitum.works/B785tL?";


        String oneis = "sub_id_1=";


        String namelnk = corelnk + oneis + cAdder;
        String deeplnk = corelnk + oneis + dAdder;

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                Document doc;
                if (cAdder!=null){

                    doc = Jsoup.connect(namelnk).get();
                } else {
                    doc = Jsoup.connect(deeplnk).get();
                }
                result = doc.text();



            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            carryRep.setText(result);

            Intent i1 = new Intent(getApplicationContext(), ActualGame.class);

            Intent i2 = new Intent(getApplicationContext(), RealActivity.class);
            if (result.equals("7bH3")) {
                startActivity(i1);
            } else {
                startActivity(i2);
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }

    }
}
