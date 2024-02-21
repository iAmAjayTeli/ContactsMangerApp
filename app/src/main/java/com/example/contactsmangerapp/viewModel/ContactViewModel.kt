package com.example.contactsmangerapp.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsmangerapp.repository.ContactRepository
import com.example.contactsmangerapp.room.Contact
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel(), Observable {
    val contacts= repository.contacts
    private var isUpdateOrDelete= false
     private lateinit var contactToUpdateOrDelete : Contact

     @Bindable
     var inputName= MutableLiveData<String?>()

    @Bindable
    var inputEmail= MutableLiveData<String?>()

    @Bindable
    val saveOrDeleteButtonText= MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText=MutableLiveData<String>()

    init {
        saveOrDeleteButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }


  fun add(contact: Contact)=viewModelScope.launch {
      repository.add(contact)
  }

    fun update(contact: Contact)=viewModelScope.launch {
        repository.update(contact)

        //Resetting the buttons and fields
        inputEmail.value=null
        inputName.value=null
        isUpdateOrDelete=false
        saveOrDeleteButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"

    }
    fun delete(contact: Contact)=viewModelScope.launch {
        repository.delete(contact)

        //Resetting the buttons and fields
        inputEmail.value=null
        inputName.value=null
        isUpdateOrDelete=false
        saveOrDeleteButtonText.value="Save"
        clearAllOrDeleteButtonText.value="Clear All"
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun SaveOrUpdate(){
        if(isUpdateOrDelete){
            //make an update
            contactToUpdateOrDelete.contact_name= inputName.value!!
            contactToUpdateOrDelete.contact_email=inputEmail.value!!
            update(contactToUpdateOrDelete)

        }
        else{
            //Inserting a new contact
            val name= inputName.value!!
            val email=inputEmail.value!!

           add(Contact(0, name, email))

            //Reset the email and name
            inputEmail.value=null
            inputName.value=null

        }
    }

    fun clearOrDelete(){
        if(isUpdateOrDelete){
            delete(contactToUpdateOrDelete)
        }
        else{
            clearAll()
        }
    }


    fun initUpdateAndDelete(contact: Contact){
        inputName.value=contact.contact_name
        inputEmail.value=contact.contact_email
        isUpdateOrDelete=true
        contactToUpdateOrDelete=contact
        saveOrDeleteButtonText.value="Update"
        clearAllOrDeleteButtonText.value="Delete"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}