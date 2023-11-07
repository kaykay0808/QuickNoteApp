package com.example.quicknoteapp.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    // Database provider
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): QuickNoteDatabase // Do we need extension?
            = Room.databaseBuilder(
        context,
        QuickNoteDatabase::class.java,
        "notes_table"
    )
        .fallbackToDestructiveMigration()
        .build()

    // Dao provider
    @Singleton
    @Provides
    fun provideDao(database: QuickNoteDatabase): NoteDao // Do we need extension?
            = database.noteDao()

    /*@Singleton
    @Provides
    fun provideDatastore(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)*/
}
