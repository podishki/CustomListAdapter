package com.example.hsport.catalog;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CartListAdapter extends ArrayAdapter<Product>{

    private List<Product> products;
    private String[] quantity = new String[]{"1", "2", "3"};

    public CartListAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        products = objects;
    }
    public CartListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.cart_items, parent, false);
        }

        final Product product = products.get(position);

        Spinner qnty = (Spinner) convertView.findViewById(R.id.quantity);
        ArrayAdapter<String> arradapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, quantity );
        qnty.setAdapter(arradapter);

        TextView itemname = (TextView) convertView.findViewById(R.id.nameText);
        itemname.setText(product.getName());

        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);

        ImageView remove = (ImageView) convertView.findViewById(R.id.removefromcart);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.remove(product);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}