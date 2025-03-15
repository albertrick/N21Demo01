package com.example.n21demo01.Activities;

import static com.example.n21demo01.FBRef.refStudents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.util.ConcurrentModificationException;

public class StudentDisAct extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lV;
    private String strtmp;
    private ArrayList<String> stuList = new ArrayList<String>();
    private ArrayList<Student> stuValues = new ArrayList<Student>();
    private ArrayAdapter<String> adp;
    private Boolean editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dis);

        lV = findViewById(R.id.lV);
        lV.setOnItemClickListener(this);
        lV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    protected void onStart() {
        super.onStart();

        stuList = new ArrayList<String>();
        stuValues = new ArrayList<Student>();
        adp = new ArrayAdapter<String>(StudentDisAct.this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, stuList);
        lV.setAdapter(adp);
        editData = false;
        readData();
    }

    private void readData() {
        refStudents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    String str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    stuValues.add(stuTmp);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        Student tmpStu = stuValues.get(pos);
        adb.setTitle(tmpStu.getStuName());
        strtmp = "Name: "+tmpStu.getStuName()+"\n";
        strtmp += "ID: "+tmpStu.getStuID()+"\n";
        strtmp += "Class: "+String.valueOf(tmpStu.getGradeClass())+" ";
        strtmp += String.valueOf(tmpStu.getStuClass());
        adb.setMessage(strtmp);
        adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder adb1 = new AlertDialog.Builder(StudentDisAct.this);
                adb1.setTitle("Are you sure to delete:");
                adb1.setMessage(strtmp);
                adb1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refStudents.child(tmpStu.getStuID()).removeValue();
                    }
                });
                adb1.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog ad1 = adb1.create();
                ad1.show();
            }
        });
        adb.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent t = new Intent(StudentDisAct.this,StudentInAct.class);
                t.putExtra("edit",true);
                t.putExtra("stuID",tmpStu.getStuID());
                startActivity(t);
            }
        });
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        adb.show();
    }

    public void Back(View view) {
        finish();
    }
}