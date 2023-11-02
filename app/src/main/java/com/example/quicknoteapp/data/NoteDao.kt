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

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NoteData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // IGNORE?
    suspend fun insertNote(taskData: NoteData)

    @Query("SELECT * FROM notes_table WHERE id=:noteId")
    suspend fun getNoteById(noteId: String): NoteData // Add Flow?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(noteData: NoteData)

    @Delete
    suspend fun deleteTask(noteData: NoteData)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()
}
