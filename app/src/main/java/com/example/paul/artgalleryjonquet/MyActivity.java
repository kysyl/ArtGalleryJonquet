package com.example.paul.artgalleryjonquet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;


import java.net.URL;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ArtGallery artGallery = new ArtGallery();
        artGallery.DownloadGallery("http://sandbox.artfavo.com/recruiting/api/v0.3.1/gallery");

        ListView imageList = (ListView) findViewById(R.id.image_list);

        for (String path : artGallery.imagePaths){

            try{
                URL url = new URL(path);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                ImageView imgv = new ImageView(this);

                imgv.setImageBitmap(bmp);
                imageList.addView(imgv);
            }catch(Exception e){
                ;
            }
        }
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
