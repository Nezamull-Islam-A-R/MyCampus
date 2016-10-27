package com.broken.nezamulislamar.mycampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CT_SearchResult extends AppCompatActivity {
    //EditText srQuer;
    Button Sbutton;
    EditText RollQuery;
    ProgressBar proS;
    String getCourseS,getSrchV;
    TextView makShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct__search_result);
        //srQuer = (EditText) findViewById(R.id.queryRoll);
        RollQuery = (EditText)findViewById(R.id.queryRoll);
        Sbutton = (Button)findViewById(R.id.SearchButton);
        proS = (ProgressBar)findViewById(R.id.CTSrcPro);

        Intent getV = getIntent();

        //getDateS = getV.getExtras().getString("KeyDate");
        getCourseS = getV.getExtras().getString("KeyCourse");
        makShow = (TextView)findViewById(R.id.MarksShow);

        Sbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             methodToShow();
            }
        });

    }

    private void methodToShow() {
        getSrchV = RollQuery.getText().toString();
        Toast.makeText(this,getSrchV+" "+getCourseS, Toast.LENGTH_LONG).show();

        ResultSearch srchRes = new ResultSearch(getCourseS,getSrchV,makShow,proS);
        srchRes.execute();

    }
}
