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


        show_name.append(data.get(0).myName)
        show_yourPhone.append(data.get(0).myPhone)
        show_phone1.append(data.get(0).phone1)
        show_phone2.append(data.get(0).phone2)
        show_phone3.append(data.get(0).phone3)
        show_email1.append(data.get(0).email1)
        show_email2.append(data.get(0).email2)
        show_email3.append(data.get(0).email3)




    }
}
