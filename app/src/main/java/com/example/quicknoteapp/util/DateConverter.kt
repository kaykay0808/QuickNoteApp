package com.example.quicknoteapp.util

import androidx.room.TypeConverter
import java.util.Date
// If it is any type, that room doesn't know that exist or doesn't know how to handle it, then we need type converter
// Also add the type converter into the QuickNoteDatabase
class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun dateFromTimeStamp(timeStamp: Long): Date {
        return Date(timeStamp)
    }
}
