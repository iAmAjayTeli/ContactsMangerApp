package com.example.contactsmangerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactsmangerapp.room.Contact
import com.example.contactsmangerapp.room.ContactDAO

class ContactRepository(val contactDAO: ContactDAO) {

    var contacts= contactDAO.getAllContact()

  suspend fun add(contact: Contact): Long {
      return contactDAO.addContact(contact)
  }
    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }

    fun getAllContact():LiveData<List<Contact>>{
        return contactDAO.getAllContact()
    }

}