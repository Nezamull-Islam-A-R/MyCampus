package com.broken.nezamulislamar.mycampus;

import android.content.Context;
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
 * Created by Nezamul Islam A R on 7/2/2016.
 */
public class ForgotBack extends AsyncTask<Void,Void,Void> {
    TextView forText;
    EditText forMail,forId,forHints;
    Context context;
    boolean stValueGet,teValueGet;
    ProgressBar forgotProGress;

    String mail,passHints,id,text;
    int stV,teV;
    String link;
    int count = 0;
    String st ;
    int linkWarn = 0;

    public ForgotBack(Context context, String mail, String id, String passHints, int stV, int teV, TextView forText,ProgressBar forgotPro) {
        this.context = context;

        this.mail = mail;
        this.id = id;
        this.passHints = passHints;
        this.stV = stV;
        this.teV = teV;
        this.forText = forText; //warning result showing .
        this.forgotProGress = forgotPro;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        forgotProGress.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(stV == 1 && teV == 0){
            //link = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/ForgotStudent.php";
            link = "http://mycampus.3eeweb.com/hit/ForgotStudent.php";
        }else if(teV == 1 && stV == 0){
            //link  = "http://192.168.56.1/Client/Project_Php_MysqlDataBase/ForgotTeacher.php";
            link = "http://mycampus.3eeweb.com/hit/ForgotTeacher.php";
        }else{
            linkWarn = 1;
        }

        try {
            URL url = new URL(link);
            //data send------------------------------------
            String data  = URLEncoder.encode("Pemail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");
            data += "&" + URLEncoder.encode("Pid", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
            data += "&" + URLEncoder.encode("Phint", "UTF-8") + "=" + URLEncoder.encode(passHints, "UTF-8");

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

                String name[] = new String[20];
                String pass[] = new String[20];

                name[count] = jo.getString("Name"); //table retrieve column name
                pass[count] = jo.getString("Pass");

                st += "\nYour name : "+name[count]+"\nYour Pass : "+pass[count];

                count++;
            }


            Log.d("JSON STRING",json_string);
            Log.d("Go_On Forgot ",st+" "+count+" "+" data : "+data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //data entering code to database----------------------------------------------
        //InsertInDatabase();

        return null;
    }

    private void callLinkMsg() {
        forText.setText("Select your One Profession .");
    }

    private void InitializeMethod() {
        if(count == 1)
        forText.setText(st);
        else
            forText.setText("Your name & Id can not be found");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        InitializeMethod();
        if(linkWarn == 1){
            callLinkMsg();
        }
        forgotProGress.setVisibility(View.GONE);
        super.onPostExecute(aVoid);
    }
}
