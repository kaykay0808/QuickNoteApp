package com.example.quicknoteapp.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknoteapp.data.model.NoteData
import com.example.quicknoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    var viewState by mutableStateOf(NoteScreenViewState())
        private set

    private var noteList: List<NoteData> = emptyList()
    private var title = ""
    private var description = ""
    private var selectedNote: NoteData? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getAllNotes()
                .distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", ":Empty List")
                        noteList = emptyList()
                        render()
                    } else {
                        noteList = listOfNotes
                        render()
                    }
                }
        }
        // noteList.addAll(NotesDummyDataSource().loadNotes())
    }

    private fun render() {
        viewModelScope.launch(Dispatchers.Main) {
            viewState = NoteScreenViewState(
                allNotes = noteList,
                title = title,
                description = description,
                selectedNote = selectedNote
            )
        }
    }

    fun addNote(note: NoteData) =
        viewModelScope.launch { repository.addNote(noteData = note) }

    fun updateNote(note: NoteData) =
        viewModelScope.launch { repository.updateNote(noteData = note) }

    fun removeNote(note: NoteData) =
        viewModelScope.launch { repository.deleteNote(noteData = note) }

    /**-----------------------------------------------------------------------*/

    fun selectedNote(clickedNote: NoteData) {
        selectedNote = clickedNote
        render()
    }

    fun defaultSelectedNoteState() {
        selectedNote = null
        render()
    }

    fun newTitleInput(newInputVal: String) {
        title = newInputVal
        render()
    }

    fun defaultTitleInput() {
        title = ""
        render()
    }

    fun newDescriptionInput(newInputVal: String) {
        description = newInputVal
        render()
    }

    fun defaultDescriptionInput() {
        description = ""
        render()
    }
}
