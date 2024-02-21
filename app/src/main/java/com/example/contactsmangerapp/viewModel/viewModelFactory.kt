package com.example.contactsmangerapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactsmangerapp.repository.ContactRepository

class viewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(ContactViewModel::class.java)){
           return ContactViewModel(repository) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}