package com.example.shank.databaseexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edReg,edName,edMarks, searchText;
    Button button,dButton,searchButton;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edReg = findViewById(R.id.Reg_Id);
        edName = findViewById(R.id.name_Id);
        edMarks=  findViewById(R.id.marks_Id);
        searchText = findViewById(R.id.searchId);
        button = findViewById(R.id.storeButton);
        dButton = findViewById(R.id.disButton);
        searchButton = findViewById(R.id.searchButton);

        database = openOrCreateDatabase("mydata.db",Context.MODE_PRIVATE,null);
        database.execSQL("create table if not exists student(regno varchar,name varchar,marks varchar)");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (edReg.getText().toString().trim().length() == 0 ||
                            edName.getText().toString().trim().length() == 0 ||
                            edMarks.getText().toString().trim().length() == 0) {

                        Toast.makeText(MainActivity.this, "Enter all values ..", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    String reg = edReg.getText().toString();
                    String name = edName.getText().toString();
                    String marks = edMarks.getText().toString();
                    String sql = "INSERT or replace into student(regno,name,marks)  VALUES ('" + reg + "','" + name + "','" + marks + "')";
                    database.execSQL(sql);
                    Toast.makeText(MainActivity.this, "Stored ..", Toast.LENGTH_SHORT).show();
                    edReg.setText("");
                    edName.setText("");
                    edMarks.setText("");
                    }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error !!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = database.rawQuery("SELECT * FROM student",null);

                if (cursor.getCount() == 0){

                    showMsg("Error !!","No record found ..");
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()){
                    stringBuffer.append("Reg No :" + cursor.getString(0) +"\n");
                    stringBuffer.append("Name :" + cursor.getString(1) +"\n");
                    stringBuffer.append("Marks :" + cursor.getString(2) +"\n\n");
                }
                showMsg("Student Info ..",stringBuffer.toString());

            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void showMsg(String title,String Msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Msg);
        builder.show();
    }
}
