package com.example.quicknoteapp.repository

import com.example.quicknoteapp.data.NoteDao
import com.example.quicknoteapp.data.model.NoteData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// Second layer of data
@ViewModelScoped
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    // C.R.U.D
    suspend fun addNote(noteData: NoteData) = noteDao.insertNote(noteData = noteData)
    suspend fun updateNote(noteData: NoteData) = noteDao.updateNote(noteData = noteData)
    suspend fun deleteNote(noteData: NoteData) = noteDao.deleteNote(noteData = noteData)
    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()

    fun getAllNotes(): Flow<List<NoteData>> = noteDao
        .getAllNotes()
        .flowOn(Dispatchers.IO)
        .conflate()

}
