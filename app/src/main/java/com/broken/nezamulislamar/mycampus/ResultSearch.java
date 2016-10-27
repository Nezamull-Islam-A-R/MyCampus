package com.broken.nezamulislamar.mycampus;

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
 * Created by Nezamul Islam A R on 7/25/2016.
 */
public class ResultSearch extends AsyncTask<Void,Void,Void> {

//getDateS,getCourseS,getSrchV,makShow,proS
    String courseGet,rollGet;
    TextView markShow;
    ProgressBar pros;
    //------------database
    String link = "";
    String data = "";
    String json_string = "";
    String st;
    int count = 0;
    String dateGet = "29-07-016";
    String stGetMark,stGetDate,resultFol;

    public ResultSearch( String courseGet, String rollGet, TextView markShow, ProgressBar pros) {
        //this.dateGet = dateGet;
        this.courseGet = courseGet;
        this.rollGet = rollGet;
        this.markShow = markShow;
        this.pros = pros;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pros.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/resSearch.php";
        link = "http://mycampus.3eeweb.com/hit/resSearch.php";
        try {
            //String inCR = "1";

            //String ParseUrl = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/TeacherSignChek.php";
            URL url = new URL(link);
            //data send------------------------------------
            data  = URLEncoder.encode("Pdate", "UTF-8") + "=" + URLEncoder.encode(dateGet, "UTF-8");
            data += "&" + URLEncoder.encode("Pcourse", "UTF-8") + "=" + URLEncoder.encode(courseGet, "UTF-8");
            data += "&" + URLEncoder.encode("Proll", "UTF-8") + "=" + URLEncoder.encode(rollGet, "UTF-8");

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

                em[count] = jo.getString("Marks"); //table retrieve column name

                st += em[count]+" ";

                //stGetRoll = jo.getString("Roll");
                stGetMark = jo.getString("Marks");
                stGetDate = jo.getString("Date");

                resultFol += stGetDate+"     -     "+stGetMark+"\n\n";

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
        pros.setVisibility(View.GONE);
        if(count != 0) {
            markShow.setText("Date: "+stGetDate + " Roll: " + rollGet + " Marks: " + stGetMark);
        }else{
            markShow.setText("Result not found for Date : "+dateGet+" & Roll :"+rollGet);
        }
    }
}
