package com.example.paul.artgalleryjonquet;


import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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


        /*imageList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parentView, View childView,
                                       int position, long id) {
                ImageView imgGal = (ImageView) findViewById(R.id.imgGal);

            }

            public void onNothingSelected(AdapterView parentView) {

            }
        });*/


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
            TextView nameView = (TextView) findViewById(R.id.name);
            nameView.setText(artGallery.name);

            TextView cityView = (TextView) findViewById(R.id.city);
            cityView.setText(artGallery.city);

            TextView addressView = (TextView) findViewById(R.id.address);
            addressView.setText(artGallery.address);



            TextView countryView = (TextView) findViewById(R.id.country);
            countryView.setText(artGallery.country);


            TextView descriptionView = (TextView) findViewById(R.id.description);
            descriptionView.setText(artGallery.description);
            adapter.notifyDataSetChanged();


        }
    }


}
