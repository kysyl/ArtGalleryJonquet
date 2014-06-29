package com.example.paul.artgalleryjonquet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.net.URL;


public class MyActivity extends ActionBarActivity {

    private GalleryAdapter adapter;
    private ArtGallery artGallery;
    private ListView imageList;

    private ProgressBar mProgressBar;

    private DownloadGalleryAsync downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        artGallery = new ArtGallery();
        imageList = (ListView) findViewById(R.id.image_list);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        adapter = new GalleryAdapter(this, artGallery.images);
        imageList.setAdapter(adapter);
    }

    public void onRefreshClicked(View v) {
        downloadTask = new DownloadGalleryAsync();
        downloadTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DownloadGalleryAsync extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter.clear();
            //Toast.makeText(getApplicationContext(), "Début du traitement asynchrone", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            artGallery.DownloadGallery("http://sandbox.artfavo.com/recruiting/api/v0.3.1/gallery");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            TextView addressView = (TextView) findViewById(R.id.address);
            addressView.setText(artGallery.address);
            adapter.notifyDataSetChanged();

            TextView countryView = (TextView) findViewById(R.id.country);
            countryView.setText(artGallery.country);
            adapter.notifyDataSetChanged();
            //Toast.makeText(getApplicationContext(), "Le traitement asynchrone est terminé", Toast.LENGTH_LONG).show();
        }
    }


}
