package com.example.quicknoteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

// Data source class.
@Entity(tableName = "notes_table") // turns data class to an entity(table)
data class NoteData(
    @PrimaryKey // when we create this table it use the id as a reference which is UUID that generates a random id
    val id: UUID = UUID.randomUUID(), // UUID generates some random id

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_description")
    val description: String,

    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date.from(Instant.now()) //LocalDateTime = LocalDateTime.now()
)
