package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Nezamul Islam A R on 7/5/2016.
 */
public class UserLoginProcess extends AsyncTask<Void, Void, Void> {
    Context cont;
    String mail;
    String passWord;
    TextView showMsg;
    ProgressBar ProgressShow;
    int inFlag ;
    String g = "";
    int ques = 1;
    String link = "";
    String link2 = "";
    int count = 0;
    String st = "";
    static int UserFound = 0;
    String json_string;
    String data = "";
    EditText psW;

    public UserLoginProcess(Context cont, String mail, String passWord, ProgressBar progressShow, TextView showMsg,int flag,EditText psW) {
        this.cont = cont;
        this.mail = mail;
        this.passWord = passWord;
        this.ProgressShow = progressShow;
        this.showMsg = showMsg;
        this.inFlag = flag;
        this.psW = psW;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //ques = 0;
        //UserFound = 0;
        ProgressShow.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            // Simulate network access.
            Thread.sleep(2000);
            ques = 0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(inFlag == 1){
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusLoginTeacher.php";
            link = "http://mycampus.3eeweb.com/hit/MyCampusLoginTeacher.php";
        }else if(inFlag == 2){
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusLogin.php";
            link = "http://mycampus.3eeweb.com/hit/MyCampusLogin.php";
        }else{
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusLoginCr.php";
            link = "http://mycampus.3eeweb.com/hit/MyCampusLoginCr.php";
        }

        try {
            String inCR = "1";
            //String ParseUrl = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignChek.php";
            URL url = new URL(link);
            //data send------------------------------------
            data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
            data += "&" + URLEncoder.encode("Ppass", "UTF-8") + "=" + URLEncoder.encode(passWord, "UTF-8");
            data += "&" + URLEncoder.encode("Pcr", "UTF-8") + "=" + URLEncoder.encode(inCR, "UTF-8");
            if(inFlag == 1){
                data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("Ppass", "UTF-8") + "=" + URLEncoder.encode(passWord, "UTF-8");
            }
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

            json_string = sb.toString().trim();
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("row_no"); //row_no is the key by we retrievied array containing data
            st = "";

            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);

                String em[] = new String[20];

                em[count] = jo.getString("Name"); //table retrieve column name

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
        Log.d("Go_On",st+" "+count+" "+" count : "+count+" inFlag : "+inFlag+json_string); //done

        if(count == 1){
            UserFound = 1;
            if(inFlag == 1){
                //link2 = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusUpdateTeacher.php";
                link2 = "http://mycampus.3eeweb.com/hit/MyCampusUpdateTeacher.php";
            }else if(inFlag == 2){
                //link2 = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusLoginStudentUpdate.php";
                link2 = "http://mycampus.3eeweb.com/hit/MyCampusLoginStudentUpdate.php";
            }else{
                //link2 = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/MyCampusLoginStudentUpdateCr.php";
                link2 = "http://mycampus.3eeweb.com/hit/MyCampusLoginStudentUpdateCr.php";
            }
            try{
                URL url = new URL(link2);
                //data send------------------------------------
                String data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
                data += "&" + URLEncoder.encode("Ppass", "UTF-8") + "=" + URLEncoder.encode(passWord, "UTF-8");
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

            }catch(Exception e) {
                Log.d("Error Data Updating : ","Error Update . "+inFlag);
            }

        }else{
            UserFound = 0;
            Log.d("Error User Found : ","User = "+UserFound);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        if(count == 1){
            Intent next = new Intent(cont, HomePage.class);
            next.putExtra("KeyWork",inFlag);
            next.putExtra("KeyMail",mail);
            //showMsg.setText("Log In Successfully");
            cont.startActivity(next);
        }else{
            showMsg.setText("Failed to log in, Try again.");
        }
        super.onPostExecute(aVoid);
        psW.setText("");
        ProgressShow.setVisibility(View.GONE);
        //LoginActivity logInCall = new LoginActivity(count);
    }

}
