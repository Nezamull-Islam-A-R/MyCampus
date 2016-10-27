package com.broken.nezamulislamar.mycampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Button Profile_b,Map_b,Ct_b,Notice_b;
    String Id,Name,inst,Dept,Pos;
    private int intValue;
    private String Getmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Profile_b = (Button)findViewById(R.id.Users_Profile);
        Map_b = (Button)findViewById(R.id.RUET_Map);
        Ct_b = (Button)findViewById(R.id.CT);
        Notice_b = (Button)findViewById(R.id.Notice);

        //Intent mIntent = getIntent();
        intValue = getIntent().getIntExtra("KeyWork", 0); //2stud,1tea,3cr
        Getmail = getIntent().getExtras().getString("KeyMail");

        Profile_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoProfile();
            }
        });

        Map_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMap();
            }
        });

        Ct_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCT();
            }
        });

        Notice_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoNotice();
            }
        });

    }

    private void gotoProfile() {
        Intent myProfile = new Intent(HomePage.this,MyProfile.class);
        myProfile.putExtra("OnState",1);
        myProfile.putExtra("mail",Getmail);
        myProfile.putExtra("link",intValue);

        if(Getmail ==null || !(Getmail.contains("@gmail.com")) ){
            Toast.makeText(this,"Wrong Mail Address try again",Toast.LENGTH_LONG).show();
        }else{
            startActivity(myProfile);
        }
        //Toast.makeText(this,"value for check: "+intValue+" "+ Getmail,Toast.LENGTH_LONG).show();

    }

    private void gotoMap() {

        Intent showMap = new Intent(HomePage.this,RUET_Map.class);
        startActivity(showMap);
    }

    private void gotoCT() {

        if(intValue == 1 || intValue == 3){
            Intent ForTeacher = new Intent(HomePage.this,CT_DateCourse.class);
            //putExtra pass with value
            ForTeacher.putExtra("jobFlag",intValue);//job_flag
            ForTeacher.putExtra("gmail",Getmail);//send user gmail
            startActivity(ForTeacher);
        }else{
            Intent ForStudent = new Intent(HomePage.this,CT_result_Show.class);
            //putExtra pass with value
            startActivity(ForStudent);
        }

        //Toast.makeText(this,"No Ct Marks found .",Toast.LENGTH_LONG).show();
    }

    private void gotoNotice() {
        if(intValue == 2) {
            Intent newNotice = new Intent(HomePage.this, ViewNotice.class);
            startActivity(newNotice);
        }else{
            Intent newNotice = new Intent(HomePage.this, NoticeAct.class);
            newNotice.putExtra("keyFlag",intValue);
            newNotice.putExtra("keyMail",Getmail);
            startActivity(newNotice);
        }
        //Toast.makeText(this,"No Notice found .",Toast.LENGTH_LONG).show();

    }
}
