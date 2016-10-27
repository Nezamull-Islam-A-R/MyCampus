package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.graphics.Color;
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
public class ProfileBack extends AsyncTask<Void, Void, Void> {
    Context cont;
    String mail;
    int Xlink;
    ProgressBar proG;
    String link = "";
    String data;
    String json_string;
    String st;
    int count = 0;
    String name,id,ins,dpt;
    TextView tv1,tv2,tv3,tv4,tv5;

    public ProfileBack( Context cont,String mail, int Xlink,ProgressBar proG,TextView tv1,TextView tv2,TextView tv3,TextView tv4,TextView tv5) {
        this.mail = mail;
        this.cont = cont;
        this.Xlink = Xlink;
        this.proG = proG;
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        proG.setVisibility(View.VISIBLE);
       // Xlink = 2;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Xlink == 1){
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/myPro.php";
            link = "http://mycampus.3eeweb.com/hit/myPro.php";
        }else{
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/mySTPro.php";
            link = "http://mycampus.3eeweb.com/hit/mySTPro.php";
        }

        try {

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

                em[count] = jo.getString("Name"); //table retrieve column name
                em[count] = jo.getString("Id");
                em[count] = jo.getString("Ints");
                em[count] = jo.getString("Dept");

                //name,id,ins,dpt;
                name = jo.getString("Name");
                id = jo.getString("Id");
                ins = jo.getString("Ints");
                dpt = jo.getString("Dept");

                st += em[count]+"\n";

                count++;
            }


            Log.d("JSON STRING",json_string);
            Log.d("Go_On","flag: "+Xlink+" - "+st+" "+count+" "+" data : "+data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Go_On",st+"  count :  "+count+" -"+Xlink+"- Flag : "+json_string); //done


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        proG.setVisibility(View.GONE);

        tv1.setText("Your Name : "+name);
        tv2.setText("Your Id   : "+id);
        tv3.setText("Your Inst : "+ins);
        tv4.setText("Your Dept : "+dpt);
        if(Xlink == 1) {
            tv5.setText("Job Position : Teacher");
            tv5.setTextColor(Color.RED);
        }
        else if(Xlink == 2){
            tv5.setText("Job Position : Student");
            tv5.setTextColor(Color.GREEN);
        }else{
            tv5.setText("Job Position : Student - CR");
            tv5.setTextColor(Color.BLUE);
        }

    }


}
