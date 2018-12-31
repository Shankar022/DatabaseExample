package com.example.shank.databaseexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edReg,edName,edMarks;
    Button button;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edReg = findViewById(R.id.Reg_Id);
        edName = findViewById(R.id.name_Id);
        edMarks=  findViewById(R.id.marks_Id);
        button = findViewById(R.id.storeButton);

        database = openOrCreateDatabase("MyData.db",Context.MODE_PRIVATE,null);
        database.execSQL("create table if not exists student(regno varchar,name varchar,marks varchar)");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
