package com.example.n21demo01.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.n21demo01.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void studentInput(View view) {
        Intent t = new Intent(this,StudentInAct.class);
        startActivity(t);
    }

    public void studentDisplay(View view) {
        Intent t = new Intent(this,StudentDisAct.class);
        startActivity(t);
    }
}