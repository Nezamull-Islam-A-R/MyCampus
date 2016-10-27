package com.broken.nezamulislamar.mycampus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText Et[] = new EditText[10];
    CheckBox studentCk,teacherCk;
    RadioButton CrIf;
    Button signUpBtton;

    String name;
    String mail;
    String inst;
    String dept;
    String pass;
    String hints;
    String fav_pet;
    String mobile;
    String Users_id;
    boolean st_chk;
    boolean te_chk;
    boolean cr_chk;
    ProgressBar signUpPro;
    TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Et[1] = (EditText) findViewById(R.id.Users_name);
        Et[2] = (EditText) findViewById(R.id.Users_mail);
        Et[3] = (EditText) findViewById(R.id.Users_inst);
        Et[4] = (EditText) findViewById(R.id.Users_dept);
        Et[5] = (EditText) findViewById(R.id.User_Password);
        Et[6] = (EditText) findViewById(R.id.Users_hints_password);
        Et[7] = (EditText) findViewById(R.id.Users_fav_pet);
        Et[8] = (EditText) findViewById(R.id.Users_Id);
        Et[9] = (EditText) findViewById(R.id.Users_Mobile);
        warning = (TextView)findViewById(R.id.TextFlag);
        signUpPro = (ProgressBar)findViewById(R.id.SignUp_progress);

        studentCk = (CheckBox)findViewById(R.id.StudentChk);
        CrIf = (RadioButton)findViewById(R.id.CRradioButton);
        teacherCk = (CheckBox)findViewById(R.id.TeacherChk);

        signUpBtton = (Button)findViewById(R.id.User_SignUp_Button);

        signUpBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallSignUpButtonProgress();
            }
        });

    }

    private void getTextAllString() {
        name = Et[1].getText().toString();
        mail = Et[2].getText().toString();
        inst = Et[3].getText().toString();
        dept = Et[4].getText().toString();
        pass = Et[5].getText().toString();
        hints = Et[6].getText().toString();
        fav_pet = Et[7].getText().toString();
        Users_id = Et[8].getText().toString();
        mobile = Et[9].getText().toString();

        st_chk = studentCk.isChecked();
        te_chk = teacherCk.isChecked();
        cr_chk = CrIf.isChecked();
    }

    private void initilizeStringVariable() {
        name = mail = inst = dept = pass = hints = fav_pet = mobile = Users_id = null;
        st_chk = te_chk = cr_chk = false;
    }

    private void CallSignUpButtonProgress() {
       /* String st = "St: ";
        //StringBuffer chkBoxSt = new StringBuffer();
        //StringBuffer chkBoxTe = new StringBuffer();
        //chkBoxSt.append(studentCk.isChecked());
        st += studentCk.isChecked();
        //chkBoxTe.append(teacherCk.isChecked());
        st = st+" Te: "+ teacherCk.getText();
        st = st+" CR: "+ CrIf.isChecked();
        Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        */
        initilizeStringVariable();
        getTextAllString();
        int getResult = 0;
        getResult = ExamineAllInputs();

        if(getResult == 0){
            if(!te_chk){
                StudentSignUp student = new StudentSignUp(this,cr_chk,dept,fav_pet,hints,inst,mail,mobile,name,pass,signUpPro,st_chk,Users_id,warning);
                student.execute();

                //Toast.makeText(this,st,Toast.LENGTH_LONG).show();

            }else{
                new TeacherSignUp(this,dept,fav_pet,hints,inst,mail,mobile,name,pass,signUpPro,te_chk,Users_id,warning).execute();
            }

        }else{
            String st = "Pls! Check your all Input Information Again ";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
    }

    private int ExamineAllInputs() {
        int flag1 = callNameChk();
        int flag2 = callCrChk();
        int flag3 = callTeacherChk();
        int flag4 = callStudentChk();
        int flag5 = callPhnIdChk();
        int flag6 = callAllCheakBoxChek();

        int flag = flag1+flag2+flag3+flag4+flag5+flag6;
        return flag;
    }

    private int callPhnIdChk() {
        int flag = 0;
        if(Users_id.isEmpty() || mobile.isEmpty()){
            flag = 19;
            String st = "User Id & Users phon no. needed.";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }else if(mobile.length()<11){
            flag = 20;
            String st = "Phone number should be more than or equals to 11 digit";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return flag;
    }

    private int callCrChk() {
        int flag = 0;
        if(cr_chk == true && (st_chk != true || te_chk != false)){
            flag = 2; // cr chk
            String st = "Wrong users working position input";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return flag;
    }
    private int callTeacherChk() {
        int flag = 0;
        if(te_chk == true & (st_chk != false & cr_chk != false)){
            flag = 3; // teacher chk
            String st = "Wrong users working position input";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return flag;
    }
    private int callAllCheakBoxChek(){
        int flag = 0;
        if(te_chk == false && st_chk == false ){
            flag = 90;
            String st = "Working history must be choice .";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return (flag);
    }
    private int callStudentChk() {
        int flag = 0;
        if(st_chk == true & te_chk != false){
            flag = 4; // student chk
            String st = "Wrong users working position input";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return flag;
    }

    private int callNameChk() {
        int flag = 0;
        if(name.isEmpty() || mail.isEmpty() || inst.isEmpty() || dept.isEmpty() || pass.isEmpty() ||hints.isEmpty() || fav_pet.isEmpty()){
            flag = 1;
            String st = "Input should not be blanked";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        if(pass.length() <4){
            flag = 11;
            String st = "Password should be 4-16 digit any character";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        if(!(mail.contains("@gmail.com") || mail.contains("@yahoo.com"))){
            flag = 111;
            String st = "Invalid E-mail address";
            Toast.makeText(this,st,Toast.LENGTH_LONG).show();
        }
        return flag;
    }
}
