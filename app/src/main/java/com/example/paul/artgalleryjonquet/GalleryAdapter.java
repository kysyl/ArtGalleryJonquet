package com.example.paul.artgalleryjonquet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by paul on 29/06/2014.
 */
public class GalleryAdapter extends ArrayAdapter<Bitmap>{

    private final Activity context;
    private final  ArrayList<Bitmap> images;

    public GalleryAdapter(Activity context,
                          ArrayList<Bitmap> images) {
        super(context, R.layout.gallery, images);
        this.context = context;
        this.images = images;
    }

    @Override
    public View getView(int position,
                        View view,
                        ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View galleryView = inflater.inflate(R.layout.gallery, null, true);

        ImageView imageView = (ImageView) galleryView.findViewById(R.id.image_item);

        try{
//            URL url = new URL();
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(images.get(position));
        }catch(Exception e){
            ;
        }

        return galleryView;
    }

}
