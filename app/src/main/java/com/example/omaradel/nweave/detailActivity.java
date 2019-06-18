package com.example.omaradel.nweave;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String productid=getIntent().getStringExtra(MainActivity.PRODUCT_ID);

        //what is Dataprovider
        product product=Dataprovider.productMap.get(productid);

        //make text like productName
        TextView tv=findViewById(R.id.nameText);
        tv.setText(product.getName());

        //set product discrbtion
        TextView descr=findViewById(R.id.descriptionText);
        descr.setText(product.getDescription());

        //set product price
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(product.getPrice());
        TextView priceText =  findViewById(R.id.priceText);
        priceText.setText(price);
        //set product image
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductid());
        iv.setImageBitmap(bitmap);//..???



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //get the photos of products from asset
    private Bitmap getBitmapFromAsset(String productid) {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productid + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
