package com.panfilosoft.contactapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "number")
    val number: String,
    @ColumnInfo(name = "email")
    val email: String,
)