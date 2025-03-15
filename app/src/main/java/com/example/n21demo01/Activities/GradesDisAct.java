package com.example.n21demo01.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.n21demo01.R;

import java.util.ArrayList;

public class GradesDisAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinStu, spinFocus;
    private Switch swOrder;
    private ListView lVGrades;
    private String stuID;
    private ArrayAdapter<String> adpStu, adpFiled;
    private ArrayList<String> stuList, stuIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_dis);

        spinStu = findViewById(R.id.spinStu);
        spinFocus = findViewById(R.id.spinFocus);
        swOrder = findViewById(R.id.swOrder);
        lVGrades = findViewById(R.id.lVGrades);

        stuID = "";
        spinStu.setOnItemSelectedListener(this);
        stuIDs = new ArrayList<String>();
        stuList = new ArrayList<String>();
        stuList.add("Choose a student:");
        adpStu = new ArrayAdapter<String>(GradesDisAct.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stuList);
        spinStu.setAdapter(adpStu);

        spinFocus.setOnItemSelectedListener(this);
        String[] attributes = {"Choose filed to show by:", "Subject", "Grade type", "Grade"};
        adpFiled = new ArrayAdapter<String>(GradesDisAct.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, attributes);
        spinFocus.setAdapter(adpFiled);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void back(View view) {
        finish();
    }
}