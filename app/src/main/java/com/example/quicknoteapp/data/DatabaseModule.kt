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
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        QuickNoteDatabase::class.java,
        "notes_table"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: QuickNoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun provideDatastore(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}