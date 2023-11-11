package com.example.quicknoteapp.util

import androidx.room.TypeConverter
import java.util.UUID

// If it is any type, that room doesn't know that exist or doesn't know how to handle it, then we need type converter
// Also add the type converter into the QuickNoteDatabase

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String): UUID {
        return UUID.fromString(string)
    }
}