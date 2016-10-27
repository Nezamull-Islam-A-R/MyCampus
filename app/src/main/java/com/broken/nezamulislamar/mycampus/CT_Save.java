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

public class CT_Save extends AppCompatActivity {
    EditText wrRoll,wrMarks;
    TextView inputed;
    Button saveB,viewB;
    String passedDate,passedCourse,passedMail;
    int passedJob;
    ProgressBar savePro;
    Context context;

    private String stRoll,stMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct__save);

        wrRoll = (EditText)findViewById(R.id.stRoll);
        wrMarks = (EditText)findViewById(R.id.stMarks);
        inputed = (TextView)findViewById(R.id.Show_result);

        saveB = (Button)findViewById(R.id.SaveMarks);
        viewB = (Button)findViewById(R.id.ViewData);
        savePro = (ProgressBar)findViewById(R.id.CTsavePro);

        Intent get = getIntent();
        passedDate = get.getExtras().getString("date");
        passedCourse = get.getExtras().getString("course");
        passedMail = get.getExtras().getString("gmail");
        passedJob = get.getIntExtra("jobFlag",0);

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSaveMethid();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callViewMthod();
            }
        });
    }

    private void callSaveMethid() {
        stRoll = wrRoll.getText().toString();
        stMark = wrMarks.getText().toString();
        context = CT_Save.this;
        //Toast.makeText(this,passedDate+" - course: "+passedCourse+" job: "+passedJob+" mail - "+passedMail, Toast.LENGTH_LONG).show();
        inputed.setText("Roll: "+ stRoll+" - "+"Marks: "+stMark);
        //send data to database
        CT_Save_database saveDataBaseCT = new CT_Save_database(context,passedMail,passedJob,passedDate,passedCourse,stRoll,stMark,savePro,inputed);
        saveDataBaseCT.execute();

    }

    private void callViewMthod() {
        Intent viewMarks = new Intent(this,CT_result_Show.class);

        startActivity(viewMarks);
    }
}
