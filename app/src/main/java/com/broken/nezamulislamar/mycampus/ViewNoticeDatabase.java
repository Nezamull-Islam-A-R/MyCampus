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
 * Created by Nezamul Islam A R on 7/27/2016.
 */
public class ViewNoticeDatabase extends AsyncTask<Void, Void, Void> {
    Context cont;
    String date;
    int Xlink;
    ProgressBar proG;
    String link = "";
    String data;
    String json_string;
    String st="",folafol = "";
    int count = 0;
    String jobST,dateST,noticeST;
    TextView tv1;

    public ViewNoticeDatabase(Context cont, String date, ProgressBar proG, TextView tv1) {
        this.cont = cont;
        this.date = date;
        this.proG = proG;
        this.tv1 = tv1;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        proG.setVisibility(View.VISIBLE);
        // Xlink = 2;
        if(date.equalsIgnoreCase("All")){
            Xlink = 1;
        }else {
            Xlink = 2;
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Xlink == 1){
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/retNoticeAll.php";
            link = "http://mycampus.3eeweb.com/hit/retNoticeAll.php";
        }else{
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/retNotice.php";
            link = "http://mycampus.3eeweb.com/hit/retNotice.php";
        }

        try {

            URL url = new URL(link);
            //data send------------------------------------
            data  = URLEncoder.encode("Pdate", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");

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

                em[count] = jo.getString("Job"); //table retrieve column name

                jobST = jo.getString("Job");
                dateST = jo.getString("Date");
                noticeST = jo.getString("Notice");

                //st += em[count]+"\n";
                st += "Notice by: "+jobST+", Date: "+dateST+" Notice: "+noticeST+"\n\n";

                count++;
            }


            //Log.d("JSON STRING",json_string);
            //Log.d("Go_On","flag: "+Xlink+" - "+st+" "+count+" "+" data : "+data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        folafol = st;
        Log.d("Go_On_Last: ",folafol+"  count :  "+count+" -"+Xlink+"- Flag : "+json_string); //done

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        tv1.setText("Total notice: "+count+".\n"+folafol);
        proG.setVisibility(View.GONE);
    }


}
