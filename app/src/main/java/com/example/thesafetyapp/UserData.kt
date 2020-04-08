package com.example.thesafetyapp

class UserData {
    var myName : String =""
    var myPhone : String =""
    var phone1 : String =""
    var phone2 : String =""
    var phone3 : String =""
    var email1 : String = ""
    var email2 : String = ""
    var email3 : String = ""

    constructor(
        myName: String,
        myPhone: String,
        phone1: String,
        phone2: String,
        phone3: String,
        email1: String,
        email2: String,
        email3: String
    ) {
        this.myName = myName
        this.myPhone = myPhone
        this.phone1 = phone1
        this.phone2 = phone2
        this.phone3 = phone3
        this.email1 = email1
        this.email2 = email2
        this.email3 = email3
    }
    constructor(){}
}