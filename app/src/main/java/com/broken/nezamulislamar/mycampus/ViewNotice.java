package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ViewNotice extends AppCompatActivity {
    EditText etDatenotice;
    Button gettDateNotice;
    TextView setTxtNotice;
    ProgressBar showNoticePro;
    String dateStby;
    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notice);

        etDatenotice = (EditText)findViewById(R.id.NoticeDateView);
        gettDateNotice = (Button)findViewById(R.id.View_Notice_B);
        showNoticePro = (ProgressBar)findViewById(R.id.noticeProView);
        setTxtNotice = (TextView)findViewById(R.id.showNotice);

        gettDateNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewNotBy();
            }
        });
    }

    private void ViewNotBy() {
        con = ViewNotice.this;
        dateStby = etDatenotice.getText().toString();
        setTxtNotice.setText("");
        ViewNoticeDatabase VfromDatabase = new ViewNoticeDatabase(con,dateStby,showNoticePro,setTxtNotice);
        VfromDatabase.execute();
    }
}
