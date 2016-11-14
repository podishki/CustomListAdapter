package com.example.hsport.catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by Kiran on 7/19/2016.
 */
public class AddCartActivity extends AppCompatActivity {

    public ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CartListAdapter adapter = new CartListAdapter(
                this, R.layout.activity_cart, DetailActivity.cartitems);
        lv = (ListView) findViewById(R.id.cartlistview);
        lv.setAdapter(adapter);
    }

}
