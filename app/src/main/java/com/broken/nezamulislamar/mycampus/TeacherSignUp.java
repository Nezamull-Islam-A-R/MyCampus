package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Nezamul Islam A R on 6/29/2016.
 */
public class TeacherSignUp extends AsyncTask<Void, Void, Void> {
    String name;
    String mail;
    String inst;
    String dept;
    String pass;
    String hints;
    String fav_pet;
    String mobile;
    String Users_id;
    boolean TeacCk;
    String Teck;
    TextView Error;

    private Context context;
    ProgressBar signUpPro;
    String data;
    //paste
    int VieRes = 0;
    String st = null;
    int count  = 0;

    public TeacherSignUp(Context context, String dept, String fav_pet, String hints, String inst, String mail, String mobile, String name, String pass, ProgressBar signUpPro, boolean teacCk, String users_id, TextView warning) {
        this.context = context;
        this.dept = dept;
        this.fav_pet = fav_pet;
        this.hints = hints;
        this.inst = inst;
        this.mail = mail;
        this.mobile = mobile;
        this.name = name;
        this.pass = pass;
        this.signUpPro = signUpPro;
        TeacCk = teacCk;
        Users_id = users_id;
        this.Error = warning;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        signUpPro.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... arg) {
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            //String ParseUrl = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignChek.php";
            String ParseUrl = "http://mycampus.3eeweb.com/hit/TeacherSignChek.php";
            URL url = new URL(ParseUrl);
            //data send------------------------------------
            String data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
            data += "&" + URLEncoder.encode("Pid", "UTF-8") + "=" + URLEncoder.encode(Users_id, "UTF-8");
            Log.d("data : ",data);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write( data );
            wr.flush();

            //HttpURLConnection httpUrlConn = (HttpURLConnection)url.openConnection();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            //data get--------------------------------------------------
            String line;
            while( (line = br.readLine() ) != null){
                sb.append(line+"\n");
            }

            //httpUrlConn.disconnect();

            String json_string = sb.toString().trim();
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("row_no"); //row_no is the key by we retrievied array containing data
            st = "";

            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);

                String em[] = new String[20];

                em[count] = jo.getString("E_mail"); //table retrieve column name

                st += em[count]+"\n";

                count++;
            }


            Log.d("JSON STRING",json_string);
            Log.d("Go_On",st+" "+count+" "+" data : "+data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //data entering code to database----------------------------------------------
        InsertInDatabase();

        return  null;
    }

    private void InsertInDatabase() {
        if(count == 0){
            try{

                //String link="http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignUp.php";
                String link = "http://mycampus.3eeweb.com/hit/TeacherSignUp.php";
                /*
                `Id`, `Name`, `E_mail`, `Ints`, `Dept`, `Phn`, `Pass`, `Hint_Pass`, `Fav_Pet`, `Teacher`,
$name= $_POST['Pname'];
$email = $_POST['Pemail'];
$inst = $_POST['Pinst'];
$dept = $_POST['Pdept'];
$teacher = $_POST['Pstd'];
$id = $_POST['Pid'];
$phn = $_POST['Pmobile'];
$pass = $_POST['Ppass'];
$passHint = $_POST['PpassHint'];
$favPet = $_POST['Pfav'];
                */
                String TeacherValueBool;
                if(TeacCk) {
                     TeacherValueBool= String.valueOf(1);
                }else {
                    TeacherValueBool = String.valueOf(0);
                }

                data = URLEncoder.encode("Pname", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("Pinst", "UTF-8") + "=" + URLEncoder.encode(inst, "UTF-8");
                data += "&" + URLEncoder.encode("Pdept", "UTF-8") + "=" + URLEncoder.encode(dept, "UTF-8");
                data += "&" + URLEncoder.encode("Pstd", "UTF-8") + "=" + URLEncoder.encode(TeacherValueBool, "UTF-8");
                data += "&" + URLEncoder.encode("Pid", "UTF-8") + "=" + URLEncoder.encode(Users_id, "UTF-8");
                data += "&" + URLEncoder.encode("Pmobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
                data += "&" + URLEncoder.encode("Ppass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                data += "&" + URLEncoder.encode("PpassHint", "UTF-8") + "=" + URLEncoder.encode(hints, "UTF-8");
                data += "&" + URLEncoder.encode("Pfav", "UTF-8") + "=" + URLEncoder.encode(fav_pet, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                //return sb.toString();
                Log.e("Input Showing : ",data);
                UsersVisibleMethodSuc();
            }
            catch(Exception e){
                Log.e("Input Result Showing : ","Error Input :( "+data);
            }
        }else {
            UsersVisibleMethod();
        }

    }
    private void UsersVisibleMethod(){
        Log.d("Over Write Input","Account already exist . try again .");
    }
    private void UsersVisibleMethodSuc(){
        Log.d("Success msg: ","Congratulations .");
    }

    public void callPreviousClass(){
        String st = "Hello Mr./Mrs. "+name+" you are Successfully done .pls Log in .";
        String st1 = "Hello Mr./Mrs. "+name+" Your Account is already exist .";

        if(count == 1){
            Error.setText(st1);
        }else{
            Error.setText(st);
        }
    }

    @Override
    protected void onPostExecute(Void o) {
        callPreviousClass();
        super.onPostExecute(o);
        signUpPro.setVisibility(View.GONE);
    }
}
