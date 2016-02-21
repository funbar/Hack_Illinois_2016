package com.hackillinois2016.minimap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Funbar on 2/20/2016.
 */
public class MainActivity extends Activity {

    private ImageView roundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roundImage = (ImageView)findViewById(R.id.roundImage);
        /*
        "toilet" is a picture that needs to be added into one of our folders
         */
        //Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.toilet);
        //roundImage.setImageBitmap(icon);
    }

}
