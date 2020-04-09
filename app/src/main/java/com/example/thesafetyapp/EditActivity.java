package com.example.thesafetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class EditActivity extends AppCompatActivity {
TextView edit_phone1,edit_phone2,edit_phone3,edit_email1,edit_email2,edit_email3,edit_myname,edit_myphone;
ImageButton edit_nameBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        

        edit_phone1 = findViewById(R.id.edit_phone1);
        edit_phone2 = findViewById(R.id.edit_phone2);
        edit_phone3 = findViewById(R.id.edit_phone3);
        edit_email1 = findViewById(R.id.edit_email1);
        edit_email2 = findViewById(R.id.edit_email2);
        edit_email3 = findViewById(R.id.edit_email3);
        edit_myname = findViewById(R.id.edit_myname);
        edit_myphone = findViewById(R.id.edit_myphone);
        edit_nameBtn = findViewById(R.id.edit_nameBtn);

       DataBaseHandler db = new DataBaseHandler(getApplicationContext());

        //showing data on activity from database
        List<UserData> data = db.readableData();

        edit_myname.setText(data.get(0).getMyName());
        edit_myphone.setText(data.get(0).getMyPhone());
        edit_phone1.setText(data.get(0).getPhone1());
        edit_phone2.setText(data.get(0).getPhone2());
        edit_phone3.setText(data.get(0).getPhone3());
        edit_email1.setText(data.get(0).getEmail1());
        edit_email2.setText(data.get(0).getEmail2());
        edit_email3.setText(data.get(0).getEmail3());



    }

    public void onEditButtonClick(View view){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView =  inflater.inflate(R.layout.dialog_edit_details,null);
        final EditText write;
        Button saveNowBtn;

        write = dialogView.findViewById(R.id.write);
        saveNowBtn = dialogView.findViewById(R.id.saveNowBtn);

        alertDialog.setView(dialogView);
        alertDialog.show();

        if (view.getId()==R.id.edit_nameBtn) {

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_myname.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });



        } else if (view.getId()==R.id.edit_phoneBtn){

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_myphone.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_phone1Btn) {

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_phone1.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_email1Btn){

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_email1.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_phone2Btn) {

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_phone2.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_email2Btn){

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_email2.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_phone3Btn) {

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_phone3.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        } else if (view.getId()==R.id.edit_email3Btn){

            saveNowBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edit_email3.setText(write.getText().toString());
                    alertDialog.dismiss();
                    delete_get_insert();
                }
            });

        }
    }
    public void delete_get_insert() {
        DataBaseHandler db1 = new DataBaseHandler(getApplicationContext());
        db1.deleteData();
        UserData user = new UserData(
                edit_myname.getText().toString(),
                edit_myphone.getText().toString(),
                edit_phone1.getText().toString(),
                edit_phone2.getText().toString(),
                edit_phone3.getText().toString(),
                edit_email1.getText().toString(),
                edit_email2.getText().toString(),
                edit_email3.getText().toString()

        );
        db1.insertData(user);
    }
}
