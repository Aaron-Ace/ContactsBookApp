package com.aaronace.contacts.ui.contactsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aaronace.contacts.data.Contact
import com.aaronace.contacts.data.Repository
import javax.inject.Inject

class ContactsListViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val getAllContacts = repository.getAllContacts()

    fun findContactByName(query: String): LiveData<List<Contact>> {
        return repository.findContactByName(query)
    }

}