package com.example.contactsmangerapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsmangerapp.R
import com.example.contactsmangerapp.databinding.ActivityMainBinding
import com.example.contactsmangerapp.repository.ContactRepository
import com.example.contactsmangerapp.room.Contact
import com.example.contactsmangerapp.room.ContactDAO
import com.example.contactsmangerapp.room.ContactDatabase
import com.example.contactsmangerapp.viewModel.ContactViewModel
import com.example.contactsmangerapp.viewModel.viewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var contactViewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Room
        val dao= ContactDatabase.getInstance(applicationContext).contactDAO
        val repository= ContactRepository(dao)
        val factory=viewModelFactory(repository)

      //viewModel
        contactViewModel= ViewModelProvider(this,factory).get(ContactViewModel::class.java)

        binding.contactViewModel=contactViewModel

        binding.lifecycleOwner=this

        initRecyclerView()

    }

    private fun initRecyclerView() {
       binding.recyclerview.layoutManager=LinearLayoutManager(this)
        displayUserList()
    }

    private fun displayUserList() {
       contactViewModel.contacts.observe(this, Observer {
           binding.recyclerview.adapter=RecyclerViewAdapter(this, it, {selectedItem:Contact-> listItemClicked(selectedItem)})
       })
    }

    private fun listItemClicked(selectedItem: Contact) {
        Toast.makeText(this, "You selected ${selectedItem.contact_name}", Toast.LENGTH_SHORT).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}