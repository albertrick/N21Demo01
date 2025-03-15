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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class StudentInAct extends AppCompatActivity {

    private EditText eTGradeClass, eTStuClass, eTStuName, eTStuID;
    private Boolean editData;
    private Student tmpStu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in);

        eTGradeClass = findViewById(R.id.eTGradeClass);
        eTStuClass = findViewById(R.id.eTStuClass);
        eTStuName = findViewById(R.id.eTStuName);
        eTStuID = findViewById(R.id.eTStuID);

        Intent gi = getIntent();
        editData = gi.getBooleanExtra("edit", false);
        if (editData) {
            String stuID = gi.getStringExtra("stuID");
            refStudents.child(stuID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        DataSnapshot dS = task.getResult();
                        if (dS != null) {
                            tmpStu = dS.getValue(Student.class);
                            eTGradeClass.setText(String.valueOf(tmpStu.getGradeClass()));
                            eTStuClass.setText(String.valueOf(tmpStu.getStuClass()));
                            eTStuName.setText(tmpStu.getStuName());
                            eTStuID.setText(tmpStu.getStuID());
                        } else {
                            Log.e("StudentInAct/onCreate", "Data snapshot in edit is null");
                        }
                    }
                    else {
                        Log.e("StudentInAct/onCreate", "Error getting data in edit", task.getException());
                    }
                }
            });
        }

    }

    public void stuInsert(View view) {
        String strGradeClass = eTGradeClass.getText().toString();
        String strStuClass = eTStuClass.getText().toString();
        String stuName = eTStuName.getText().toString();
        String stuID = eTStuID.getText().toString();
        if (strGradeClass.isEmpty() || strStuClass.isEmpty() || stuName.isEmpty() || stuID.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields !", Toast.LENGTH_LONG).show();
        } else {
            if (editData) {
                if (!stuID.equals(tmpStu.getStuID())) {
                    refStudents.child(tmpStu.getStuID()).removeValue();
                }
            }
            int gradeClass = Integer.parseInt(strGradeClass);
            int stuClass = Integer.parseInt(strStuClass);
            tmpStu = new Student(gradeClass, stuClass, stuName, stuID);
            refStudents.child(tmpStu.getStuID()).setValue(tmpStu);
            finish();
        }
    }
}