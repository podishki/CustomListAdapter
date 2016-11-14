package com.example.hsport.catalog;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    public static final String ITEMID = "ITEMID";
    public static List<Product> cartitems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String productId = getIntent().getStringExtra(MainActivity.PRODUCT_ID);
        final Product product = DataProvider.productMap.get(productId);

        TextView tv = (TextView) findViewById(R.id.nameText);
        tv.setText(product.getName());

        TextView descView = (TextView) findViewById(R.id.descriptionText);
        descView.setText(product.getDescription());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(product.getPrice());
        TextView priceText = (TextView) findViewById(R.id.priceText);
        priceText.setText(price);

        ImageView iv = (ImageView) findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product item = new Product(product.getProductId(), product.getName(), product.getDescription(), product.getPrice());
                cartitems.add(item);
                /*Snackbar.make(view, product.getName() + " added to cart", Snackbar.LENGTH_LONG)
                        .setAction("Goto Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(DetailActivity.this, "Goto Cart ", Toast.LENGTH_SHORT).show();
                                Intent cartintent = new Intent(DetailActivity.this, AddCartActivity.class);
                                startActivity(cartintent);
                            }
                        }).show();*/
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id1 = item.getItemId();

        switch (id1){
            case R.id.action_cart:
                Log.d("DetailActivity", "onOptionsItemSelected:"+item.getItemId());
                Intent cartintent = new Intent(DetailActivity.this, AddCartActivity.class);
                startActivity(cartintent);
                return true;
            case R.id.toolbar:
                Log.d("DetailActivity", "onOptionsItemSelected:"+item.getItemId());
                Intent cartintent1 = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(cartintent1);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
