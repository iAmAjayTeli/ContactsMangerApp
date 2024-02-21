package com.example.contactsmangerapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact_table")
class Contact(
    @PrimaryKey(autoGenerate = true)
    val contact_id:Int,
    var contact_name:String,
    var contact_email:String
)

{

}