package com.example.quicknoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.quicknoteapp.data.NotesDummyDataSource
import com.example.quicknoteapp.data.model.NoteData

class NoteScreenViewModel :  ViewModel() {
    private var noteList = mutableStateListOf<NoteData>()

    init {
        noteList.addAll(NotesDummyDataSource().loadNotes())
    }

    fun addNote(note: NoteData) {
        noteList.add(note)
    }

    fun removeNote(note: NoteData) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<NoteData> {
        return noteList
    }
}
