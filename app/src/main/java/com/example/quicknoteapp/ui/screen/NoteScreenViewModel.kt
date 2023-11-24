package com.example.quicknoteapp.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknoteapp.data.model.NoteData
import com.example.quicknoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    // private var noteList = mutableStateListOf<NoteData>()
    private val _noteList = MutableStateFlow<List<NoteData>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getAllNotes()
                .distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", ":Empty List")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
        // noteList.addAll(NotesDummyDataSource().loadNotes())
    }

    fun addNote(note: NoteData) =
        viewModelScope.launch { repository.addNote(noteData = note) }

    fun updateNote(note: NoteData) =
        viewModelScope.launch { repository.updateNote(noteData = note) }

    fun removeNote(note: NoteData) =
        viewModelScope.launch { repository.deleteNote(noteData = note) }

}
