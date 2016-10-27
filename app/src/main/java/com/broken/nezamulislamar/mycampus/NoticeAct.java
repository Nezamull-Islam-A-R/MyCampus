package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NoticeAct extends AppCompatActivity {
    EditText Ndate,Nnotice;
    Button Nsave,Nview;
    TextView Ntxt;
    ProgressBar Npro;
    String dateSt,noticeSt;
    private String getMail;
    private int getFlag;
    Context contex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Ndate = (EditText)findViewById(R.id.Notice_Date);
        Nnotice = (EditText)findViewById(R.id.UpNotice);

        Nsave = (Button)findViewById(R.id.Notice_b);
        Nview = (Button)findViewById(R.id.Notice_bView);

        Ntxt = (TextView)findViewById(R.id.Up_txt);
        Npro = (ProgressBar)findViewById(R.id.Up_Pro);

        getFlag = getIntent().getIntExtra("keyFlag",0);
        getMail = getIntent().getExtras().getString("keyMail");

        Nsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NsaveMethod();
            }
        });

        Nview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewNoticeMethod();
            }
        });
    }

    private void NsaveMethod() {
        dateSt = Ndate.getText().toString();
        noticeSt = Nnotice.getText().toString();
        contex = NoticeAct.this;
        //Context context, String mail, int job, String date, String notice, ProgressBar pro,TextView tv)
        SaveNoticeDatabase saveNoticeNext = new SaveNoticeDatabase(contex,getMail,getFlag,dateSt,noticeSt,Npro,Ntxt);
        saveNoticeNext.execute();

        Ntxt.setText("Date: "+dateSt+" Notice: "+noticeSt);
        //Toast.makeText(this,dateSt+" "+noticeSt,Toast.LENGTH_LONG).show();
    }

    private void viewNoticeMethod() {
        Intent nextShowNotice = new Intent(NoticeAct.this,ViewNotice.class);
        startActivity(nextShowNotice);
    }
}
