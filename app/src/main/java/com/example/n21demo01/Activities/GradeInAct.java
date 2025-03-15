package com.example.n21demo01.Activities;

import static com.example.n21demo01.FBRef.refStuGrades;
import static com.example.n21demo01.FBRef.refStudents;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.n21demo01.Obj.StuGrade;
import com.example.n21demo01.Obj.Student;
import com.example.n21demo01.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GradeInAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinStuID;
    private EditText eTClassSubject, eTGradeType, eTGrade;
    private String stuID;
    private ArrayAdapter<String> adp;
    private ArrayList<String> stuList, stuIDs;
    private ArrayList<Student> students;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_in);

        spinStuID = findViewById(R.id.spinStuID);
        eTClassSubject = findViewById(R.id.eTClassSubject);
        eTGradeType = findViewById(R.id.eTGradeType);
        eTGrade = findViewById(R.id.eTGrade);
        stuID = "";
        spinStuID.setOnItemSelectedListener(this);
        stuIDs = new ArrayList<String>();
        stuList = new ArrayList<String>();
        students = new ArrayList<Student>();
        stuList.add("Choose a student:");
        adp = new ArrayAdapter<String>(GradeInAct.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stuList);
        spinStuID.setAdapter(adp);

        readData();
    }

    private void readData() {
        refStudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                stuList.clear();
                stuList.add("Choose a student:");
                students.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String str1 = (String) data.getKey();
                    stuIDs.add(str1);
                    Student stuTmp = data.getValue(Student.class);
                    students.add(stuTmp);
                    String str2 = stuTmp.getStuName();
                    stuList.add(str1+": "+str2);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError dbError) {
                Log.e("StudentDisAct/readData", "Error reading data: "+dbError);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        if (pos != 0) {
            stuID = stuIDs.get(pos-1);
            student = students.get(pos-1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void gradeInsert(View view) {
        String strClassSubject = eTClassSubject.getText().toString();
        String strGradeType = eTGradeType.getText().toString();
        String strGrade = eTGrade.getText().toString();
        if (strClassSubject.isEmpty() || strGradeType.isEmpty() || strGrade.isEmpty() || stuID.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields !", Toast.LENGTH_LONG).show();
        } else {
            int grade = Integer.parseInt(strGrade);
            StuGrade stuGrade = new StuGrade(stuID, student.getStuName(), strClassSubject, strGradeType, grade);
            String gradeID = refStuGrades.child(stuGrade.getStuID()).push().getKey();
            stuGrade.setGradeID(gradeID);
            refStuGrades.child(stuGrade.getStuID()+": "+stuGrade.getStuName())
                    .child(stuGrade.getGradeID())
                    .setValue(stuGrade);
        }
        finish();
    }
}