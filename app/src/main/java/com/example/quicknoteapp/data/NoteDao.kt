package com.example.quicknoteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quicknoteapp.data.model.NoteData
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Creating
    @Insert(onConflict = OnConflictStrategy.REPLACE) // IGNORE?
    suspend fun insertNote(noteData: NoteData)

    // Reading
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteData>>

    // Read selected data
    @Query("SELECT * FROM notes_table WHERE id=:noteId")
    suspend fun getNoteById(noteId: String): NoteData // Add Flow?

    // Update
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(noteData: NoteData)

    // Delete
    @Delete
    suspend fun deleteNote(noteData: NoteData)

    // Delete all
    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()
}
