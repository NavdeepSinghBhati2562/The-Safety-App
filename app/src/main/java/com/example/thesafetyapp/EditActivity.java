package com.example.thesafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditActivity extends AppCompatActivity {
TextView edit_phone1,edit_phone2,edit_phone3,edit_email1,edit_email2,edit_email3,edit_myname,edit_myphone;
ImageButton edit_nameBtn;
Button save_changesBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
       final DataBaseHandler db = new DataBaseHandler(getApplicationContext());

        edit_phone1 = findViewById(R.id.edit_phone1);
        edit_phone2 = findViewById(R.id.edit_phone2);
        edit_phone3 = findViewById(R.id.edit_phone3);
        edit_email1 = findViewById(R.id.edit_email1);
        edit_email2 = findViewById(R.id.edit_email2);
        edit_email3 = findViewById(R.id.edit_email3);
        edit_myname = findViewById(R.id.edit_myname);
        edit_myphone = findViewById(R.id.edit_myphone);
        save_changesBtn = findViewById(R.id.save_changesBtn);

        edit_nameBtn = findViewById(R.id.edit_nameBtn);



        List<UserData> data = db.readableData();

        edit_myphone.append(data.get(0).getMyName());
        edit_myphone.append(String.valueOf((data.get(0).getMyPhone())));
        edit_phone1.append(String.valueOf((data.get(0).getPhone1())));
        edit_phone2.append(String.valueOf((data.get(0).getPhone2())));
        edit_phone3.append(String.valueOf((data.get(0).getPhone3())));
        edit_email1.append(data.get(0).getEmail1());
        edit_email2.append(data.get(0).getEmail2());
        edit_email3.append(data.get(0).getEmail3());





        save_changesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteData();
                UserData user = new UserData(
                        edit_myname.getText().toString(),
                        Integer.parseInt(edit_myphone.getText().toString()),
                        Integer.parseInt(edit_phone1.getText().toString()),
                        Integer.parseInt(edit_phone2.getText().toString()),
                        Integer.parseInt(edit_phone3.getText().toString()),
                        edit_email1.getText().toString(),
                        edit_email2.getText().toString(),
                        edit_email3.getText().toString()
                        );

                db.insertData(user);
                Toast.makeText(EditActivity.this, "Changes Saved Successfully.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EditActivity.this,Data_Display.class);
                startActivity(i);
            }
        });


    }
}
