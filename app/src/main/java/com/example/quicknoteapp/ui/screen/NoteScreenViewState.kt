package com.example.quicknoteapp.ui.screen

import com.example.quicknoteapp.data.model.NoteData

data class NoteScreenViewState(
    private var allNotes: List<NoteData> = emptyList()
)
