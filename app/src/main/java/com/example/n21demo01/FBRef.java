package com.example.n21demo01;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBRef {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();

    public static DatabaseReference refStudents = FBDB.getReference("Students");
    public static DatabaseReference refStuGrades = FBDB.getReference("StuGrades");
    public static DatabaseReference refFlags = FBDB.getReference("Flags");

}
