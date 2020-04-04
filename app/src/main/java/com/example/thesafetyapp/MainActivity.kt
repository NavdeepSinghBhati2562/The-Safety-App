package com.example.thesafetyapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.view.View


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db =DataBaseHandler(applicationContext)

        submitBtn.setOnClickListener(View.OnClickListener {

            if (my_name.text.toString().length>0 &&
                my_number.text.toString().length>0 &&
                help_number1.text.toString().length>0 &&
                help_number2.text.toString().length>0 &&
                help_number3.text.toString().length>0 &&
                help_email1.text.toString().length>0 &&
                help_email2.text.toString().length>0 &&
                help_email3.text.toString().length>0 )
            {
                var user = UserData(my_name.text.toString(),
                    my_number.text.toString().toInt(),
                    help_number1.text.toString().toInt(),
                    help_number2.text.toString().toInt(),
                    help_number3.text.toString().toInt(),
                    help_email1.text.toString(),
                    help_email1.text.toString(),
                    help_email1.text.toString())

                db.insertData(user)

            //    getSharedPreferences("login", Context.MODE_PRIVATE)



                val intent = Intent(this@MainActivity,Home::class.java)
                startActivity(intent)
                finish()




                


            }else   Toast.makeText(applicationContext,"Please Fill all the Blocks", Toast.LENGTH_LONG).show()







        })


    }
}
