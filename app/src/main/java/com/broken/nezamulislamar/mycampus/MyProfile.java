package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {
    private int flag = 0;
    private int link  = 0;
    private String mail = "";
    TextView tv1,tv2,tv3,tv4,tv5;
    ProgressBar proProgress;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        tv1 = (TextView)findViewById(R.id.proName);
        tv2 = (TextView)findViewById(R.id.proID);
        tv3 = (TextView)findViewById(R.id.proInst);
        tv4 = (TextView)findViewById(R.id.proDpt);
        tv5 = (TextView)findViewById(R.id.workPosition);
        proProgress = (ProgressBar)findViewById(R.id.ProfileProgress);
        context = this;

        flag = getIntent().getIntExtra("OnState",1);
        
        if(flag == 1){
            showPro();
        }else{
            warMSG();
        }
    }

    private void showPro() {
        link = getIntent().getIntExtra("link",0);
        mail = getIntent().getExtras().getString("mail");

        ProfileBack Back = new ProfileBack(context,mail,link,proProgress,tv1,tv2,tv3,tv4,tv5);
        Back.execute();
        //Toast.makeText(this,mail+" - "+link,Toast.LENGTH_LONG).show();
    }

    private void warMSG() {
        Toast.makeText(this,"Cant find .Working !",Toast.LENGTH_LONG).show();
    }
}
