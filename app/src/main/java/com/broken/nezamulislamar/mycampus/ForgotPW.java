package com.broken.nezamulislamar.mycampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ForgotPW extends AppCompatActivity {
    EditText forEmail,forId,forHints;
    CheckBox stCk,teCh;
    TextView testUp;
    Button findPW;
    boolean stValue,teValue;
    ProgressBar forgotPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

        forEmail = (EditText)findViewById(R.id.EmailForgot);
        forId = (EditText)findViewById(R.id.Enter_IDforgot);
        forHints = (EditText)findViewById(R.id.PassHintForgot);
        stCk = (CheckBox)findViewById(R.id.ForgotStudentChk);
        teCh = (CheckBox)findViewById(R.id.ForgotTeacherChk);

        testUp = (TextView)findViewById(R.id.UserMsgForgot);
        findPW = (Button)findViewById(R.id.Find);
        forgotPro = (ProgressBar)findViewById(R.id.Forgot_progress);

        findPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMethod();
            }
        });

    }

    private void callMethod() {
        stValue = stCk.isChecked();
        teValue = teCh.isChecked();
        String mail,id,hints;
        int stString,teString;

        mail = forEmail.getText().toString();
        id = forId.getText().toString();
        hints = forHints.getText().toString();
        if(stValue == true && teValue == false){
            stString = 1;
            teString = 0;
        }else if(stValue == false && teValue == true){
            stString = 0;
            teString = 1;
        }else{
            stString = 0;
            teString = 0;
        }
        ForgotBack calling = new ForgotBack(this,mail,id,hints,stString,teString,testUp,forgotPro);
        calling.execute();
    }

}
