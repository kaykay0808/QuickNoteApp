package com.example.quicknoteapp.ui.screen

import com.example.quicknoteapp.data.model.NoteData

data class NoteScreenViewState(
    val allNotes: List<NoteData> = emptyList(),
    val title: String = "",
    val description: String = "",
    val selectedNote: NoteData? = null
)
