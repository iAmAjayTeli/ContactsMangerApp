package com.example.contactsmangerapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsmangerapp.R
import com.example.contactsmangerapp.databinding.ItemSampleBinding
import com.example.contactsmangerapp.room.Contact

class RecyclerViewAdapter(
    private val context: Context, private val contactList: List<Contact>,
    private val clickListener: (Contact) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemSampleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            binding.name.text = contact.contact_name
            binding.email.text = contact.contact_email

            binding.listItemLayout.setOnClickListener {
                clickListener(contact)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemSampleBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_sample,
            parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactList[position], clickListener)


    }
}