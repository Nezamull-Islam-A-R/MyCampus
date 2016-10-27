package com.broken.nezamulislamar.mycampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CT_DateCourse extends AppCompatActivity {

    EditText dateSave,CourseSave;
    Button nextButton;
    String passDate,passCourse;
    String getUgmail;
    int getUjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct__date_course);

        dateSave = (EditText)findViewById(R.id.date);
        CourseSave = (EditText)findViewById(R.id.course);

        nextButton = (Button)findViewById(R.id.buttonNext);

        Intent getData = getIntent();

        getUgmail = getData.getExtras().getString("gmail"); //get Inten data
        getUjob = getData.getIntExtra("jobFlag",0);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSaveInfoMethod();
            }
        });

    }

    private void gotoSaveInfoMethod() {
        passDate = dateSave.getText().toString();
        passCourse = CourseSave.getText().toString();

        Toast.makeText(this,passCourse+" - "+passDate,Toast.LENGTH_LONG).show();
        Intent saveInt = new Intent(CT_DateCourse.this,CT_Save.class);

        saveInt.putExtra("date",passDate);
        saveInt.putExtra("course",passCourse);
        saveInt.putExtra("gmail",getUgmail);
        saveInt.putExtra("jobFlag",getUjob); //must be 1(te) or 3(cr)

        startActivity(saveInt);

    }
}
