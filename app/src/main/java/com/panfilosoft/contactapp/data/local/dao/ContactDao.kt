package com.panfilosoft.contactapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.panfilosoft.contactapp.data.local.entities.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query(
        """
        SELECT * FROM contact
        ORDER BY name ASC
        """
    )
    fun getAllContacts(): Flow<List<Contact>>

    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}