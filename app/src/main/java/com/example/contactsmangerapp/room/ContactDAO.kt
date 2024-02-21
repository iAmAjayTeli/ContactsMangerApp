package com.example.contactsmangerapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface ContactDAO {

    @Insert
    suspend fun addContact(contact: Contact):Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact_table")
    fun getAllContact():LiveData<List<Contact>>
}