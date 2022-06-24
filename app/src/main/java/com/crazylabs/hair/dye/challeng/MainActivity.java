package com.crazylabs.hair.dye.challeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.applinks.AppLinkData;
import com.orhanobut.hawk.Hawk;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    public static final String C_STR1 = "cstR1";

    public static final String C_STR2 = "cstR2";

    public static final String C_STR3 = "par3C";

    public static final String DLNK1 = "dfB1";

    public static final String DLNK2 = "dfB2";

    public static final String DLNK3 = "dfB3";

    private static final String AF_DEV_KEY = "qCQG5F5kF2k3Z549AP32w8";

    String stMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metaLnk();

        SharedPreferences prefs = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(prefs.getBoolean("activity_exec", false)){
            Intent intent = new Intent(this, Filter.class);
            startActivity(intent);

            finish();
        } else {
            SharedPreferences.Editor exec = prefs.edit();
            exec.putBoolean("activity_exec", true);
            exec.commit();
        }
    }

    public void metaLnk() {


        AppLinkData.fetchDeferredAppLinkData(this,
                appLinkData -> {

                    if (appLinkData != null) {

                        List<String> params = appLinkData.getTargetUri().getPathSegments();
                        String conjoined = TextUtils.join("/", params);

                        StringTokenizer tokenizer = new StringTokenizer(conjoined, "/");

                        String firstLink = tokenizer.nextToken();
                        String secondLink = tokenizer.nextToken();
                        String thirdLink = tokenizer.nextToken();


                        Hawk.put(DLNK1, firstLink);
                        Hawk.put(DLNK2, secondLink);
                        Hawk.put(DLNK3, thirdLink);
                        Log.d("FB", "Positive Result");


                    } else {
                        Log.d("FB", "Error Code:");

                    }

                }
        );
    }

    public void teleport(){

        Intent intent = new Intent(MainActivity.this, Filter.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        finish();

    }
    public void appsflyer() {
        AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {



            @Override
            public void onConversionDataSuccess(Map<String, Object> conversionData) {



                Log.d("TESTING_ZONE", "af stat is " + conversionData.get("af_status"));

                stMain = (String) conversionData.get("campaign");


                Log.d("NAMING TEST", "campaign attributed: " + stMain);


                StringTokenizer tokenizer = new StringTokenizer(stMain, "_");


                String one = tokenizer.nextToken();
                String two = tokenizer.nextToken();
                String three = tokenizer.nextToken();



                Hawk.put(C_STR1, one);
                Hawk.put(C_STR2, two);
                Hawk.put(C_STR3, three);


                teleport();
                finish();




            }




            @Override
            public void onConversionDataFail(String errorMessage) {
                Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);

                teleport();



            }

            @Override
            public void onAppOpenAttribution(Map<String, String> attributionData) {

                for (String attrName : attributionData.keySet()) {
                    Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
                }

            }

            @Override
            public void onAttributionFailure(String errorMessage) {
                Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
            }

        };


        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
        AppsFlyerLib.getInstance().start(this);
        AppsFlyerLib.getInstance().setDebugLog(true);


    }


    @Override
    protected void onResume() {
        super.onResume();
        appsflyer();

    }
}