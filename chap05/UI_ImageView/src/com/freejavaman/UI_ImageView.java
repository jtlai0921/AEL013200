package com.freejavaman;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class UI_ImageView extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main);
     
     ImageView img = (ImageView)this.findViewById(R.id.img1);     
     //方法一、
     img.setImageResource(R.drawable.bg);
     
     //方法二、 img.setImageDrawable(this.getResources().getDrawable(R.drawable.bg));
     
     //Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bg.jpg");
     //img.setImageBitmap(bitmap);
    }
}