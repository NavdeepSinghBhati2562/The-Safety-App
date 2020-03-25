package com.example.thesafetyapp

class UserData {
    var name : String =""
    var myPhone : Int = 0
    var phone1 : Int = 0
    var phone2 : Int = 0
    var phone3 : Int = 0
    var email1 : String = ""
    var email2 : String = ""
    var email3 : String = ""

    constructor(
        name: String,
        myPhone: Int,
        phone1: Int,
        phone2: Int,
        phone3: Int,
        email1: String,
        email2: String,
        email3: String
    ) {
        this.name = name
        this.myPhone = myPhone
        this.phone1 = phone1
        this.phone2 = phone2
        this.phone3 = phone3
        this.email1 = email1
        this.email2 = email2
        this.email3 = email3
    }
}