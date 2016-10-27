package com.broken.nezamulislamar.mycampus;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by Nezamul Islam A R on 7/25/2016.
 */
public class UserLog extends AsyncTask<Void,Void,Void> {

    Context context;

    public UserLog(Context context) {
        this.context = context;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        Intent Home = new Intent(context,HomePage.class);
        context.startActivity(Home);
        super.onPostExecute(aVoid);
    }
}
