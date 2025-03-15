package com.example.n21demo01.Activities;

import static com.example.n21demo01.FBRef.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.n21demo01.Obj.Student;
import com.example.n21demo01.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class StudentInAct extends AppCompatActivity {

    private EditText eTGradeClass, eTStuClass, eTStuName, eTStuID;
    public Boolean dataed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in);

        eTGradeClass = (EditText) findViewById(R.id.eTGradeClass);
        eTStuClass = (EditText) findViewById(R.id.eTStuClass);
        eTStuName = (EditText) findViewById(R.id.eTStuName);
        eTStuID = (EditText) findViewById(R.id.eTStuID);

//        dataed = false;
//
//        refFlags.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dS) {
//                dataed = (Boolean) dS.child("Dataedit").getValue();
//                if (dataed) {
//                    Intent gi=getIntent();
//                    eTGradeClass.setText(gi.getStringExtra("stuGradeclass"));
//                    eTStuClass.setText(gi.getStringExtra("stuClass"));
//                    eTStuName.setText(gi.getStringExtra("stuName"));
//                    oldStuId=gi.getStringExtra("stuID");
//                    eTStuID.setText(oldStuId);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError dbError) {
//                Log.e("StudentinAct/onCreate", "Error reading data: "+dbError);
//            }
//        });
    }

    public void stuInsert(View view) {
        String strGradeClass = eTGradeClass.getText().toString();
        String strStuClass = eTStuClass.getText().toString();
        String stuName = eTStuName.getText().toString();
        String stuID = eTStuID.getText().toString();
        if (strGradeClass.isEmpty() || strStuClass.isEmpty() || stuName.isEmpty() || stuID.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields !", Toast.LENGTH_LONG).show();
        } else {
            int gradeClass = Integer.parseInt(strGradeClass);
            int stuClass = Integer.parseInt(strStuClass);
            Student student = new Student(gradeClass, stuClass, stuName, stuID);
            refStudents.child(student.getStuID()).setValue(student);
            finish();
        }
//        refFlags.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dS) {
//                dataed = (Boolean) dS.child("Dataedit").getValue();
//                if (dataed) {
//                    refStudents.child(oldStuId).removeValue();
//                    refFlags.child("Dataedit").setValue(false);
//                }
//                str=eTGradeClass.getText().toString();
//                GradeClass=Integer.parseInt(str);
//                str=eTStuClass.getText().toString();
//                StuClass=Integer.parseInt(str);
//                StuName=eTStuName.getText().toString();
//                StuID=eTStuID.getText().toString();
//                student=new Student(GradeClass,StuClass,StuName,StuID);
//                refStudents.child(StuID).setValue(student);
//                finish();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError dbError) {
//                Log.e("StudentinAct/stuInsert", "Error reading data: "+dbError);
//            }
//        });
    }
}