package com.example.thesafetyapp
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "Safety_App_Data"
val TABLE_NAME = "UserData"
val COL_NAME ="name"
val COL_MYPHONE = "My_Phone"
val COL_PHONE1 = "PHONE_1"
val COL_PHONE2 = "PHONE_2"
val COL_PHONE3 = "PHONE_3"
val COL_EMAIL1 = "EMAIL_1"
val COL_EMAIL2 = "EMAIL_2"
val COL_EMAIL3 = "EMAIL_3"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable ="CREATE TABLE "+ TABLE_NAME + "(" +
                COL_NAME + " VARCHAR(256),"+
                COL_MYPHONE + " INTEGER PRIMARY KEY,"+
                COL_PHONE1 + " INTEGER," +
                COL_PHONE2 + " INTEGER," +
                COL_PHONE3 + " INTEGER," +
                COL_EMAIL1 + " VARCHAR(256)," +
                COL_EMAIL2 + " VARCHAR(256)," +
                COL_EMAIL3 + " VARCHAR(256))";

        db?.execSQL(createTable)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun insertData(user : UserData)
    {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME,user.name)
        cv.put(COL_MYPHONE,user.myPhone)
        cv.put(COL_PHONE1,user.phone1)
        cv.put(COL_PHONE2,user.phone2)
        cv.put(COL_PHONE3,user.phone3)
        cv.put(COL_EMAIL1,user.email1)
        cv.put(COL_EMAIL2,user.email2)
        cv.put(COL_EMAIL3,user.email3)
        var result = db.insert(TABLE_NAME,null,cv)
        if (result == -1.toLong())
            Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show()
        else  Toast.makeText(context,"Success",Toast.LENGTH_LONG).show()



    }


}
