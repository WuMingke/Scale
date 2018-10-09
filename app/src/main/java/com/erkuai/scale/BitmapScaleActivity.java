package com.erkuai.scale;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class BitmapScaleActivity extends AppCompatActivity {

    private DoodleView doodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_bitmap_scale, null);
        setContentView(view);

        doodle = (DoodleView) findViewById(R.id.doodle);
        //doodle.setImageResource(R.drawable.ic_launcher_background);

      //  Bitmap bitmap = getBitmap(view);

    }

/*    private Bitmap getBitmap(View view){
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap cache = view.getDrawingCache();
        return Bitmap.createBitmap(cache);
    }*/


}
