package com.example.quicknoteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quicknoteapp.data.model.NoteData
import com.example.quicknoteapp.ui.screen.NoteScreen
import com.example.quicknoteapp.ui.screen.NoteScreenViewModel
import com.example.quicknoteapp.ui.theme.QuickNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesApp(
    noteScreenViewModel: NoteScreenViewModel = viewModel()
) {
    val viewState = noteScreenViewModel.viewState
    val allTask = viewState.allNotes
    // val notesList = noteScreenViewModel.noteList.collectAsState().value

    val toastContext = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    NoteScreen(
        allNotes = allTask, //notes,//NotesDummyDataSource().loadNotes(),//emptyList(),
        onNoteClicked = {
            noteScreenViewModel.selectedNote(it)
        },
        title = viewState.title,
        description = viewState.description,
        onTitleChange = {
            if (
                it.all { char ->
                    char.isLetter() || char.isWhitespace() || char.isDigit()
                }
            )
                noteScreenViewModel.newTitleInput(it)
            // title = it
        },
        onDescriptionChange = {
            if (
                it.all { char ->
                    char.isLetter() || char.isWhitespace() || char.isDigit()
                }
            )
                noteScreenViewModel.newDescriptionInput(it)
            // description = it
        },
        onSaveButtonClicked = {
            if (viewState.title.isNotEmpty() && viewState.description.isNotEmpty()) {
                if (viewState.selectedNote != null) {
                    // Update the selected note
                    noteScreenViewModel.updateNote(
                        viewState.selectedNote!!.copy(
                            title = viewState.title,
                            description = viewState.description
                        )
                    )
                } else {
                    // Save and add to list.
                    noteScreenViewModel.addNote(
                        NoteData(
                            title = viewState.title,
                            description = viewState.description
                        )
                    )
                }

                keyboardController?.hide()
                noteScreenViewModel.defaultTitleInput()
                noteScreenViewModel.defaultDescriptionInput()
                Toast.makeText(
                    toastContext,
                    if (viewState.selectedNote != null) "Note updated" else "Note added",
                    Toast.LENGTH_SHORT
                ).show()
            }
            noteScreenViewModel.defaultSelectedNoteState()
        },
        onDeleteClicked = { noteScreenViewModel.removeNote(it) },
        maxLine = 1
    )
    // Everytime selectedNote changes this run. if it doesn't  change this doesn't launch
    LaunchedEffect(viewState.selectedNote) {
        viewState.selectedNote?.let {
            noteScreenViewModel.newTitleInput(it.title)
            noteScreenViewModel.newDescriptionInput(it.description)

        }
    }
}
