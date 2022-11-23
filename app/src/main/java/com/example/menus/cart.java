package com.example.menus;

import static androidx.core.app.NotificationChannelCompat.DEFAULT_CHANNEL_ID;

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
    final String CHANNEL_ID="channel1";

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

        btn = findViewById(R.id.orderBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });


    }

    void addNotification() {
        NotificationManager nm=(NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Example";
            String description = "This is a Demo Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            nm.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");
        builder.setAutoCancel(true);
        Intent notificationIntent = new Intent(this, notfication.class);

        notificationIntent.putExtra("notificationID", 123);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        nm.notify(0,builder.build());
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