package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class order_pasta extends AppCompatActivity {

    private TextView text1;
    private TextView cost1;
    private TextView desc1;
    private Intent i;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pasta);

        ImageView i1 = findViewById(R.id.product1);
        ImageView i2 = findViewById(R.id.product2);
        ImageView i3 = findViewById(R.id.product3);

        ArrayList<ImageView> arr = new ArrayList<>();
        arr.add(i1);
        arr.add(i2);
        arr.add(i3);

        for (ImageView img: arr) {
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(order_pasta.this, img);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
//                            Toast.makeText(order_pasta.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(order_pasta.this, cart.class);
                            Bundle b = new Bundle();
                            switch (img.getId()) {
                                case R.id.product1:
                                    text1 = findViewById(R.id.t1);
                                    cost1 = findViewById(R.id.c1);
                                    desc1 = findViewById(R.id.d1);
                                    b.putString("Name", text1.getText().toString());
                                    b.putString("Cost", cost1.getText().toString());
                                    i.putExtras(b);
                                    startActivity(i);
                                    break;
                                case R.id.product2:
                                    text1 = findViewById(R.id.t2);
                                    cost1 = findViewById(R.id.c2);
                                    desc1 = findViewById(R.id.d2);
                                    b.putString("Name", text1.getText().toString());
                                    b.putString("Cost", cost1.getText().toString());
                                    i.putExtras(b);
                                    startActivity(i);
                                    break;
                                case R.id.product3:
                                    text1 = findViewById(R.id.t3);
                                    cost1 = findViewById(R.id.c3);
                                    desc1 = findViewById(R.id.d3);
                                    b.putString("Name", text1.getText().toString());
                                    b.putString("Cost", cost1.getText().toString());
                                    i.putExtras(b);
                                    startActivity(i);
                                    break;
                            }
                            return true;
                        }
                    });

                    popup.show();//showing popup menu
                }
            });
        }


        registerForContextMenu(i1);
        registerForContextMenu(i2);
        registerForContextMenu(i3);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            String name = b.getString("Name");
            String cost = b.getString("Cost");
            String desc = b.getString("Description");
            String id = b.getString("id");
            switch (id){
                case "Customize Product1":
                    text1 = findViewById(R.id.t1);
                    cost1 = findViewById(R.id.c1);
                    desc1 = findViewById(R.id.d1);
                    text1.setText(name);
                    cost1.setText(cost);
                    desc1.setText(desc);
                    break;
                case "Customize Product2":
                    text1 = findViewById(R.id.t2);
                    cost1 = findViewById(R.id.c2);
                    desc1 = findViewById(R.id.d2);
                    text1.setText(name);
                    cost1.setText(cost);
                    desc1.setText(desc);
                    break;
                case "Customize Product3":
                    text1 = findViewById(R.id.t3);
                    cost1 = findViewById(R.id.c3);
                    desc1 = findViewById(R.id.d3);
                    text1.setText(name);
                    cost1.setText(cost);
                    desc1.setText(desc);
                    break;
                default:
                    break;
            }



        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // you can set menu header with title icon etc
        menu.setHeaderTitle("Context Menu");
        // add menu items
        switch (v.getId()) {
            case R.id.product1:
                menu.add(0, v.getId(), 0, "Customize Product1");
                break;
            case R.id.product2:
                menu.add(0, v.getId(), 0, "Customize Product2");
                break;
            case R.id.product3:
                menu.add(0, v.getId(), 0, "Customize Product3");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle().equals("Customize Product1")) {
            text1 = findViewById(R.id.t1);
            cost1 = findViewById(R.id.c1);
            desc1 = findViewById(R.id.d1);
            i = new Intent(order_pasta.this, custom.class);
            bundle = new Bundle();
            bundle.putString("id", "Customize Product1");
            bundle.putString("Name", text1.getText().toString());
            bundle.putString("Cost", cost1.getText().toString());
            bundle.putString("Description", desc1.getText().toString());
            i.putExtras(bundle);
            startActivity(i);
        }

        else if (item.getTitle().equals("Customize Product2")) {
            text1 = findViewById(R.id.t2);
            cost1 = findViewById(R.id.c2);
            desc1 = findViewById(R.id.d2);
            i = new Intent(order_pasta.this, custom.class);
            bundle = new Bundle();
            bundle.putString("id", "Customize Product2");
            bundle.putString("Name", text1.getText().toString());
            bundle.putString("Cost", cost1.getText().toString());
            bundle.putString("Description", desc1.getText().toString());
            i.putExtras(bundle);
            startActivity(i);
        }

        else if (item.getTitle().equals("Customize Product3")) {
            text1 = findViewById(R.id.t3);
            cost1 = findViewById(R.id.c3);
            desc1 = findViewById(R.id.d3);
            i = new Intent(order_pasta.this, custom.class);
            bundle = new Bundle();
            bundle.putString("id", "Customize Product3");
            bundle.putString("Name", text1.getText().toString());
            bundle.putString("Cost", cost1.getText().toString());
            bundle.putString("Description", desc1.getText().toString());
            i.putExtras(bundle);
            startActivity(i);
        }

        return true;
    }



}