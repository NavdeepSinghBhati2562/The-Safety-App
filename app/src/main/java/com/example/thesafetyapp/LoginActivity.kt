package com.example.thesafetyapp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences =
            getSharedPreferences("Login", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("flag", 1)
        editor.apply()


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
                    my_number.text.toString(),
                    help_number1.text.toString(),
                    help_number2.text.toString(),
                    help_number3.text.toString(),
                    help_email1.text.toString(),
                    help_email1.text.toString(),
                    help_email1.text.toString())
                db.insertData(user)

            //    getSharedPreferences("login", Context.MODE_PRIVATE)
                Toast.makeText(this,"Details entered sucessfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity,Home::class.java)
                startActivity(intent)
                finish()
            }else   Toast.makeText(applicationContext,"Please Fill all the Blocks", Toast.LENGTH_LONG).show()







        })


    }
}
