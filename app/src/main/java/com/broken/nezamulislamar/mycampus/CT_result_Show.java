package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CT_result_Show extends AppCompatActivity {
    EditText getCourse;
    Button resultShow,searchBy;
    ListView lv;
    TextView showRe;
    ProgressBar resPro;
    private String retCourse;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_result__show);

        //dateToretrieve = (EditText)findViewById(R.id.CtDateG);
        getCourse = (EditText)findViewById(R.id.Setcourse);

        resultShow = (Button)findViewById(R.id.View_All);
        searchBy = (Button)findViewById(R.id.SearchGo);
        //lv = (ListView)findViewById(R.id.resultList);
        showRe = (TextView)findViewById(R.id.showRes);
        resPro = (ProgressBar)findViewById(R.id.CTresultPro);

        resultShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callResultViewMthod();
            }
        });

        searchBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callSearch();
            }
        });
    }

    private void callSearch() {
        //retDate = dateToretrieve.getText().toString();
        retCourse = getCourse.getText().toString();
        Toast.makeText(this,retCourse,Toast.LENGTH_LONG).show();
        Intent SearchGo = new Intent(this,CT_SearchResult.class);
        //SearchGo.putExtra("KeyDate",retDate);
        SearchGo.putExtra("KeyCourse",retCourse);
        startActivity(SearchGo);
    }

    private void callResultViewMthod() {
        context = CT_result_Show.this;
        retCourse = getCourse.getText().toString();
        ResultFromDatabase GoresulTdata = new ResultFromDatabase(context,retCourse,lv,resPro,showRe);
        GoresulTdata.execute();
    }
}
