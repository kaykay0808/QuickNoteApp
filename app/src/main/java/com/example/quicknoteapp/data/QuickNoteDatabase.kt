package com.example.quicknoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknoteapp.data.model.NoteData

// Database structure
@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class QuickNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
