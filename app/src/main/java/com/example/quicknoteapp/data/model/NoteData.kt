package com.example.quicknoteapp.data.model

import java.time.LocalDateTime
import java.util.UUID

// Data source class.
data class NoteData(
    val id: UUID = UUID.randomUUID(), // UUID generates some random id
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
