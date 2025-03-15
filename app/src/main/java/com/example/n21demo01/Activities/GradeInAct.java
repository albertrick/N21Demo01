package com.example.n21demo01.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.n21demo01.R;

public class GradeInAct extends AppCompatActivity {
    private Spinner spinStuID;
    private EditText eTClassSubject, eTGradeType, eTGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_in);

        spinStuID = findViewById(R.id.spinStuID);
        eTClassSubject = findViewById(R.id.eTClassSubject);
        eTGradeType = findViewById(R.id.eTGradeType);
        eTGrade = findViewById(R.id.eTGrade);
    }

    public void gradeInsert(View view) {
        finish();
    }
}