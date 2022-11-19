package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class custom extends AppCompatActivity {
    EditText text1;
    EditText cost1;
    EditText desc1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        text1 = findViewById(R.id.text1);
        cost1 = findViewById(R.id.cost1);
        desc1 = findViewById(R.id.desc1);
        btn = findViewById(R.id.btn);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String name = bundle.getString("Name");
            String cost = bundle.getString("Cost");
            String desc = bundle.getString("Description");
            text1.setText(name);
            cost1.setText(cost);
            desc1.setText(desc);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(custom.this, order_pasta.class);
                Bundle b = new Bundle();
                b.putString("Name", text1.getText().toString());
                b.putString("Cost", cost1.getText().toString());
                b.putString("Description", desc1.getText().toString());
                i.putExtras(b);
                startActivity(i);
            }
        });
    }


}