package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
 * Created by Nezamul Islam A R on 7/25/2016.
 */
public class ResultFromDatabase extends AsyncTask<Void,Void,Void>{
    String date,course;
    ListView lv;
    TextView tv;
    ProgressBar resPro;
    Context context;
    //------------
    String link,data = "";
    String json_string,st = "";
    int count = 0;
    String stGetDate = "",stGetRoll = "",stGetMark = "",resultFol = "";

    public ResultFromDatabase(Context context, String course, ListView lv, ProgressBar resPro,TextView tv) {
        this.context = context;
        //this.date = date;
        this.course = course;
        this.lv = lv;
        this.resPro = resPro;
        this.tv = tv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        resPro.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/resultRet.php";
        link = "http://mycampus.3eeweb.com/hit/resultRet.php";
        try {
            date = "27-07-016";
            //String inCR = "1";
            //String ParseUrl = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignChek.php";
            URL url = new URL(link);
            //data send------------------------------------
            data  = URLEncoder.encode("Pdate", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
            data += "&" + URLEncoder.encode("Pcourse", "UTF-8") + "=" + URLEncoder.encode(course, "UTF-8");

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
            count = 0;
            while(count < jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);

                String em[] = new String[250];

                em[count] = jo.getString("Roll"); //table retrieve column name

                st += em[count]+" ";
                stGetDate = jo.getString("Date");
                stGetRoll = jo.getString("Roll");
                stGetMark = jo.getString("Marks");
                resultFol += "Date: "+stGetDate+" Roll: "+stGetRoll+" Marks: "+stGetMark+"\n\n";

                count++;
            }


            Log.d("JSON STRING",json_string);
            Log.d("Go_On",resultFol+" "+count+" "+" data : "+data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("GetResult: ",st+" "+count+" "+" count : "+count+" inFlag : "+json_string); //done



        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        resPro.setVisibility(View.GONE);
        if(count != 0)
        tv.setText(resultFol);
        else {
            tv.setText("Result not found .Try Again");
            tv.setTextColor(Color.RED);
        }
    }
}
