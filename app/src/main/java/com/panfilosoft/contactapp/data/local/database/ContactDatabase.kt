package com.panfilosoft.contactapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.panfilosoft.contactapp.data.local.dao.ContactDao
import com.panfilosoft.contactapp.data.local.entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}