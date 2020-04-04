package com.example.thesafetyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data__display.*

class Data_Display : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data__display)

        var db =DataBaseHandler(applicationContext)

        var data = db.readableData()
//        show.text= ""
//        for (i in 0..(data.size-1)){
//
//            show.append(data.get(i).myName +" "+ data.get(i).myPhone+" "+data.get(i).phone1
//            +" "+data.get(i).phone2+" "+data.get(i).phone3+" "+data.get(i)+" "+data.get(i).email1
//            +" "+data.get(i).email2+" "+data.get(i).email3)
//        }

        show_name.append(data.get(0).myName)
        show_yourPhone.append(data.get(0).myPhone.toString())
        show_phone1.append(data.get(0).phone1.toString())
        show_phone2.append(data.get(0).phone2.toString())
        show_phone3.append(data.get(0).phone3.toString())
        show_email1.append(data.get(0).email1)
        show_email2.append(data.get(0).email2)
        show_email3.append(data.get(0).email3)




    }
}