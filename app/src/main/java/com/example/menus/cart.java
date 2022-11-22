package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {

    private static ArrayList<Myclass> arr = new ArrayList<>();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        View linearLayout =  findViewById(R.id.inflater);

        Bundle b = getIntent().getExtras();

        if (b != null) {
            String name = b.getString("Name");
            String cost = b.getString("Cost");
            insetData(arr, new Myclass(cost, name));
        }

        renderXML(linearLayout);

        addTotalOrderView(linearLayout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });


    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.messageicon) //set icon for notification
                        .setContentTitle("Notifications Example") //set title of notification
                        .setContentText("This is a notification message")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


        Intent notificationIntent = new Intent(this, notfication.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }




    public void addViewComponent(View v, String label, String value) {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);

        TextView name_label = new TextView(this);
        name_label.setText(label);
        name_label.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((LinearLayout) l).addView(name_label);

        TextView name = new TextView(this);
        name.setText(value);
        name.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((LinearLayout) l).addView(name);

        if (label.equals("Total: ")) {
            l.setPadding(10, 20, 10, 20);
            l.setGravity(Gravity.CENTER_HORIZONTAL);
            name_label.setTextSize(30);
            name_label.setTextColor(Color.parseColor("#ffffff"));
            name.setTextSize(30);
            name.setTextColor(Color.parseColor("#ffffff"));
        }

        ((LinearLayout) v).addView(l);
    }



    public int getTotalOrderCost() {
        int totalPrice = 0;
        if (arr.size() != 0) {
            for (Myclass i: arr) {
                int q = Integer.parseInt(i.getQuantity());
                int c = Integer.parseInt(i.getProductCost().substring(1));
                totalPrice += q * c;
            }
        }

        return totalPrice;
    }

    public void insetData(ArrayList<Myclass> arr, Myclass obj) {
        if (arr.size() != 0) {
            for (Myclass m : arr) {
                if (m.getProductCost().equals(obj.getProductCost()) && m.getProductName().equals(obj.getProductName())) {
                    m.incrementQuantity();
                    return;
                }
             }
        }

        arr.add(obj);
    }

    public void addTotalOrderView(View v) {
        int totalCost = getTotalOrderCost();
        addViewComponent(v, "Total: ", "$" + Integer.toString(totalCost));
    }

    public void renderXML(View v) {
        if (arr.size() != 0) {
            for (Myclass i: arr) {
                LinearLayout l = new LinearLayout(this);
                l.setOrientation(LinearLayout.VERTICAL);
                l.setPadding(10,20, 20, 10);
                addViewComponent(l, "Name: ", i.getProductName());
                addViewComponent(l, "Cost: ", i.getProductCost());
                addViewComponent(l, "Quantity: ", i.getQuantity());
                ((LinearLayout) v).addView(l);
            }
        }
    }
}

class Myclass {
    private String cost;
    private String name;
    private int quantity;

    Myclass(String cost, String name) {
        this.cost = cost;
        this.name = name;
        this.quantity = 1;
    }

    public String getProductName() {
        return this.name;
    }

    public String getProductCost() { return this.cost; }

    public String getQuantity() {
        return Integer.toString(this.quantity);
    }

    public void incrementQuantity() {
        this.quantity += 1;
    }
}