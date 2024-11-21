package com.panfilosoft.contactapp.data.repository

import com.panfilosoft.contactapp.data.local.dao.ContactDao
import com.panfilosoft.contactapp.data.local.entities.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(
    private val dao: ContactDao
) {

    fun getAllContacts(): Flow<List<Contact>> {
        return dao.getAllContacts()
    }

    suspend fun insertContact(contact: Contact) {
        return dao.insertContact(contact)
    }

    suspend fun updateContact(contact: Contact) {
        return dao.updateContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        return dao.deleteContact(contact)
    }

}