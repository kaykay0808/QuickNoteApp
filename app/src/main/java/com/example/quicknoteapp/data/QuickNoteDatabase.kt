package com.example.quicknoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.quicknoteapp.data.model.NoteData
import com.example.quicknoteapp.util.DateConverter
import com.example.quicknoteapp.util.UUIDConverter

// Database structure
@Database(entities = [NoteData::class], version = 2, exportSchema = false)
// If it is any type, that room doesn't know that exist or doesn't know how to handle it, then we need type converter
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class QuickNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
