package com.example.paul.artgalleryjonquet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.net.URL;


public class MyActivity extends ActionBarActivity {

    private GalleryAdapter adapter;
    private ArtGallery artGallery;
    private ListView imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        artGallery = new ArtGallery();
        imageList = (ListView) findViewById(R.id.image_list);

        adapter = new GalleryAdapter(this, artGallery.images);
        imageList.setAdapter(adapter);
    }

    public void onRefreshClicked(View v) {
        adapter.clear();
        artGallery.DownloadGallery("http://sandbox.artfavo.com/recruiting/api/v0.3.1/gallery");
        adapter.notifyDataSetChanged();
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
}
