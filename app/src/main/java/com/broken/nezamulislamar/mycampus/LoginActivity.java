package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private EditText mEmailView;
    private EditText mPasswordView;
    ProgressBar mProgressView;
    private View mLoginFormView;
    public Button signButtong,forgotButton,mEmailSignInButton;
    String Users_mail,Users_PW;
    TextView warnMsg;
    Context context;
    CheckBox tea,stu,cr;
    int getFlag = 0;
    UserLoginProcess Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.emailLoginMainPage);
        mPasswordView = (EditText) findViewById(R.id.passwordMainPage);
        warnMsg = (TextView)findViewById(R.id.UserMsgLogin);

        signButtong = (Button) findViewById(R.id.SignUp);
        forgotButton = (Button) findViewById(R.id.forgotPassword);
        signButtong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signInActivity();
            }
        });

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                //mPasswordView.setText("");
            }
        });

        forgotButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CallForgot();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = (ProgressBar) findViewById(R.id.login_progressLogin);
        stu = (CheckBox)findViewById(R.id.StudentLog);
        tea = (CheckBox)findViewById(R.id.TeacherLog);
        cr = (CheckBox)findViewById(R.id.StudentCRLog);

        context = this;
    }

    private void CallForgot() {
        Intent go = new Intent(this,ForgotPW.class);
        startActivity(go);
    }
    private void signInActivity(){
        startActivity(new Intent(this,SignUp.class));
        //Toast.makeText(this,"Next activity",Toast.LENGTH_SHORT).show();
    }

    private void attemptLogin() {
        //check st/tea/cr
        //context ,e-mail ,pass,textWarn
        //teacher = 1;student = 2,cr = 3;

        Users_mail = mEmailView.getText().toString();
        Users_PW = mPasswordView.getText().toString();
        int flag = 0;
        int call = 0;
        int boxCK = 0;

        boxCK = boxMethod();
        if(boxCK >=2){
            call = 1;
            Toast.makeText(this,"Select Only One Job Position .",Toast.LENGTH_LONG).show();
        }else if(boxCK == 1){
            flag = callJobChk();
        }else {
            call  = 2;
            Toast.makeText(this,"Choose one job .",Toast.LENGTH_LONG).show();
        }

        if(Users_PW.length() <4){
            call = 4;//3
        }
        if(!(Users_mail.contains(".com"))){
            call = 4; //4
        }

        if(call == 0) {
            //call LoginActivity class
            context = LoginActivity.this;
            Login = new UserLoginProcess(context, Users_mail, Users_PW, mProgressView, warnMsg,flag,mPasswordView);
            Login.execute();
            //Intent goMain = new Intent(this,HomePage.class);
            //startActivity(goMain);
            Log.d("LogIn: ",context+" "+Users_mail+" "+Users_PW+" "+ mProgressView+" "+warnMsg+" "+flag);
            //Toast.makeText(this,context+" "+Users_mail+" "+Users_PW+" "+ mProgressView+" "+warnMsg+" "+flag,Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Ivalid Email or Password ! :( ",Toast.LENGTH_LONG).show();
        }
    }


    private int callJobChk() {
        int x = 0;
        if(tea.isChecked()){
            x = 1;
        }else if(stu.isChecked()){
            x = 2;
        }else if(cr.isChecked()){
            x = 3;
        }
        return(x);
    }

    private int boxMethod() {
        int a = 0,b = 0,c = 0;
        if(tea.isChecked()){
            a = 1;
        }else {
            a = 0;
        }

        if(stu.isChecked()){
            b = 1;
        }else {
            b = 0;
        }

        if(cr.isChecked()){
            c = 1;
        }else {
            c = 0;
        }
        return (a+b+c);
    }

}

