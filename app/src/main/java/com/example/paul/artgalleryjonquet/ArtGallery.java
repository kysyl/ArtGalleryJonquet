package com.example.paul.artgalleryjonquet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.EventLog;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by paul on 28/06/2014.
 */
public class ArtGallery {

    public String name;
    public String address;
    public String postalCode;
    public String website;
    public String email;
    public String phoneNumber;
    public String country;
    public String manager;
    public String description;
    public String openingHours;
    public String city;
    public String twitter;

    public ArrayList<Bitmap> images;

    public ArtGallery() {
        images = new ArrayList<Bitmap>();
    }

    public boolean DownloadGallery(String url){

        try{
            DefaultHttpClient defaultClient = new DefaultHttpClient();
            HttpGet httpGetRequest = new HttpGet(url);

            HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();

            JSONObject sandbox = new JSONObject(json);
            String status = sandbox.getString("status");
            if (status.equals("success")){

                JSONObject data = sandbox.getJSONObject("data");
                this.name = data.getString("name");
                this.description = data.getString("description_en");

                images.clear();

                boolean loop = true;
                int i = 1;
                while (loop) {
                    try{
                        URL url_img = new URL(data.getString("photo" + i));
                        Bitmap bmp = BitmapFactory.decodeStream(url_img.openConnection().getInputStream());
                        images.add(bmp);
                        //imagePaths.add(data.getString("photo" + i));
                        i++;
                    }
                    catch (Exception e){
                        loop = false;
                    }
                }
            }
            else
                return false;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
