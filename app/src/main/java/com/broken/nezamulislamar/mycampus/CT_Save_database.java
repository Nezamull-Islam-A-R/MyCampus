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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Nezamul Islam A R on 7/24/2016.
 */
public class CT_Save_database extends AsyncTask<Void,Void,Void>{

    Context context;
    String mail,date,course,roll,mark;
    int job;
    ProgressBar pro;
    int Xlink;
    TextView tv;

    //database works
    String link,data = "";
    String json_string;
    String st;
    int count = 0;
    String link2;

    public CT_Save_database(Context context, String mail, int job, String date, String course, String roll, String mark, ProgressBar pro,TextView tv) {
        this.context = context;
        this.mail = mail;
        this.job = job;
        this.date = date;
        this.course = course;
        this.roll = roll;
        this.mark = mark;
        this.pro = pro;
        this.tv = tv;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pro.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(job == 1){
            link = "http://mycampus.3eeweb.com/hit/CT_getID.php";
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/CT_getID.php"; //hit teachers database
        }else {
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/CT_getIDst.php"; //hit student database
            link = "http://mycampus.3eeweb.com/hit/CT_getIDst.php";
        }

        try {
            //String inCR = "1";
            //String ParseUrl = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignChek.php";
            URL url = new URL(link);
            //data send------------------------------------
            data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");

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

                em[count] = jo.getString("Id"); //table retrieve column name

                st += em[count]+"";

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
        Log.d("Go_On",st+" "+count+" "+" count : "+count+" inFlag : "+job+json_string); //done

        if(count == 1){
            //link2 = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/CT_saveMarks.php";
            link2 = "http://mycampus.3eeweb.com/hit/CT_saveMarks.php";

            try{
                URL url = new URL(link2);
                //data send------------------------------------
                data  = URLEncoder.encode("Pmarks", "UTF-8") + "=" + URLEncoder.encode(mark, "UTF-8");
                data += "&" + URLEncoder.encode("Pid", "UTF-8") + "=" + URLEncoder.encode(st, "UTF-8");
                data += "&" + URLEncoder.encode("Pdate", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                data += "&" + URLEncoder.encode("Pcourse", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8");
                data += "&" + URLEncoder.encode("Proll", "UTF-8") + "=" + URLEncoder.encode(roll, "UTF-8");

                Log.d("Inserted data : ",data);
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
                Log.d("Error Data Inserting : ","Error Saving data . ");
            }

        }else{
            Log.d("Error_ID"," Error Found");
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        pro.setVisibility(View.GONE);
        //tv.setText(mail);
    }
}
