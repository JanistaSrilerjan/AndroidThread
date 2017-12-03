package com.example.jnstar.process;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

   ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
    }

    public void onClick(View v){
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://www.catster.com/wp-content/uploads/2017/08/A-fluffy-cat-looking-funny-surprised-or-concerned.jpg");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Bitmap bmp = null; //ตัวช่วยรันรูป
                try {
                    bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //img.setImageBitmap(bmp);
                final Bitmap finalBmp = bmp; //function ที่รันจริง
                img.post(new Runnable() {
                    @Override
                    public void run() {
                        img.setImageBitmap(finalBmp);
                    }
                });
            }
        }).start();*/
        new LoadImageTask().execute("http://www.catster.com/wp-content/uploads/2017/08/A-fluffy-cat-looking-funny-surprised-or-concerned.jpg");

    }
    private class LoadImageTask extends AsyncTask<String,Void,Bitmap>{ //asynchronous
        @Override
        protected Bitmap doInBackground(String...urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }


        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result); //tread ยุ่งกับ ui ตรงๆไม่ได้ แต่asynchronous เรียกตรงๆได้
        }
    }
}
