package com.example.n21demo01.Activities;

import static com.example.n21demo01.FBRef.*;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.n21demo01.Obj.StuGrade;
import com.example.n21demo01.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradesDisAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinStu;
    private Spinner spinFocus;
    private Switch swOrder;
    private ListView lVGrades;
    private String stuSelected, attribute;
    private ArrayAdapter<String> adpStu, adpFiled, adpGrades;
    private ArrayList<String> stuList, attributes, gradesList;
    private String[] fileds;
    private ValueEventListener vel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_dis);

        spinStu = findViewById(R.id.spinStu);
        spinFocus = findViewById(R.id.spinFocus);
        swOrder = findViewById(R.id.swOrder);
        lVGrades = findViewById(R.id.lVGrades);

        spinStu.setOnItemSelectedListener(this);
        stuList = new ArrayList<String>();
        stuList.add("Choose a student:");
        adpStu = new ArrayAdapter<String>(GradesDisAct.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stuList);
        spinStu.setAdapter(adpStu);
        stuSelected = "";

        spinFocus.setOnItemSelectedListener(this);
        fileds = new String[]{"classSubject", "gradeType", "finalGrade"};
        attributes = new ArrayList<String>();
        attributes.add("Choose filed to show by:");
        attributes.add("Subject");
        attributes.add("Grade type");
        attributes.add("Grade");
        adpFiled = new ArrayAdapter<String>(GradesDisAct.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, attributes);
        spinFocus.setAdapter(adpFiled);
        attribute = "";

        gradesList = new ArrayList<>();
        adpGrades = new ArrayAdapter<String>(GradesDisAct.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, gradesList);
        lVGrades.setAdapter(adpGrades);

        readData();
    }

    private void readData() {
        refStuGrades.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                stuList.clear();
                stuList.add("Choose a student:");
                for (DataSnapshot data : dS.getChildren()) {
                    String stuID = data.getKey();
                    stuList.add(stuID);
                }
                adpStu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if (pos != 0) {
            if (adapterView.getId() == R.id.spinFocus) {
                attribute = fileds[pos-1];
            } else {
                stuSelected = stuList.get(pos-1);
            }
            if (!stuSelected.equals("Choose a student:") && !attribute.isEmpty()) {
                Query query = refStuGrades.child(stuSelected).orderByChild(attribute);
                getData(query);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void getData(Query query) {
        vel = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                gradesList.clear();
                for (DataSnapshot data : dS.getChildren()) {
                    StuGrade stuGrade = data.getValue(StuGrade.class);
                    String str1 = stuGrade.getClassSubject();
                    String str2 = stuGrade.getGradeType();
                    String str3 = String.valueOf(stuGrade.getFinalGrade());
                    gradesList.add(str1+", "+str2+": "+str3);
                }
                adpGrades.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        query.addValueEventListener(vel);
    }

    public void back(View view) {
        if (vel != null) {
            refStuGrades.removeEventListener(vel);
        }
        finish();
    }
}