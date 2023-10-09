package com.example.quicknoteapp.data

import com.example.quicknoteapp.data.model.NoteData

class NotesDummyDataSource {
    fun loadNotes(): List<NoteData> {
        return listOf(
            NoteData(
                title = "A good day",
                description = "We went on a vacation by the lake"
            ),
            NoteData(
                title = "Android Compose",
                description = "Working on Android Compose course today"
            ),
            NoteData(
                title = "Keep at it...",
                description = "Sometimes things just happen"
            ),
            NoteData(
                title = "A movie day",
                description = "Watching a movie with family today"
            ),
            NoteData(
                title = "Why is love hard",
                description = "Am I rejected?"
            ),
            NoteData(
                title = "do not have a crush",
                description = "chase your own goal"
            ),
            NoteData(
                title = "I am awesome",
                description = "Believe in yourself. Everything is going to be fine"
            ),
            NoteData(
                title = "bjj",
                description = "starting to get better"
            ),
            NoteData(
                title = "I am starting in boxing",
                description = "be the next Tyson"
            ),
            NoteData(
                title = "fuck movie day",
                description = "I live alone"
            )
        )
    }
}
