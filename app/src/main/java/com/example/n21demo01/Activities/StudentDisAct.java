package com.example.n21demo01.Activities;

import static com.example.n21demo01.FBRef.refStudents;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.n21demo01.Obj.Student;
import com.example.n21demo01.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentDisAct extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String str1, str2,strtmp;
    private ListView lV;
    private Student stuTmp;
    private ArrayList<String> stuList = new ArrayList<String>();
    private ArrayList<Student> stuValues = new ArrayList<Student>();
    private ArrayAdapter<String> adp;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dis);

        lV = (ListView) findViewById(R.id.lV);
        lV.setOnItemClickListener(this);
        lV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        stuList = new ArrayList<String>();
        stuValues = new ArrayList<Student>();
        adp = new ArrayAdapter<String>(StudentDisAct.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stuList);
        lV.setAdapter(adp);

        readData();
    }

    private void readData() {
        refStudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    stuValues.add(stuTmp);
                    str2 = stuTmp.getStuName();
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

    public void Back(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}