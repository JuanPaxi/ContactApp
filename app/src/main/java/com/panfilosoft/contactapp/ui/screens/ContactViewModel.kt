package com.panfilosoft.contactapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panfilosoft.contactapp.data.local.entities.Contact
import com.panfilosoft.contactapp.data.repository.ContactRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository
) : ViewModel() {

    val contacts: StateFlow<List<Contact>> = repository.getAllContacts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            repository.insertContact(contact)
        }
    }
    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }
    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}