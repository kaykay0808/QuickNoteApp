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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    // pass this to noteButton/onClick to update title state
    var title by remember {
        mutableStateOf("")
    }
    // pass this to noteButton/onClick to update description state
    var description by remember {
        mutableStateOf("")
    }

    var selectedNote by remember {
        mutableStateOf<NoteData?>(null)
    }

    val notesList = noteScreenViewModel.noteList.collectAsState().value

    val toastContext = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    NoteScreen(
        allNotes = notesList, //notes,//NotesDummyDataSource().loadNotes(),//emptyList(),
        /*onRemoveNote = {
            noteScreenViewModel.removeNote(it)
        },*/
        onNoteClicked = {
            selectedNote = it
        },
        title = title,
        description = description,
        onTitleChange = {
            if (
                it.all { char ->
                    char.isLetter() || char.isWhitespace() || char.isDigit()
                }
            )
                title = it
        },
        onDescriptionChange = {
            if (
                it.all { char ->
                    char.isLetter() || char.isWhitespace() || char.isDigit()
                }
            )
                description = it
        },
        onSaveButtonClicked = {
            if (title.isNotEmpty() && description.isNotEmpty()) {
                if (selectedNote != null) {
                    // Update the selected note
                    noteScreenViewModel.updateNote(
                        selectedNote!!.copy(
                            title = title,
                            description = description
                        )
                    )
                } else {
                    // Save and add to list.
                    noteScreenViewModel.addNote(
                        NoteData(
                            title = title,
                            description = description
                        )
                    )
                }

                keyboardController?.hide()
                title = ""
                description = ""
                Toast.makeText(
                    toastContext,
                    if (selectedNote != null) "Note updated" else "Note added",
                    Toast.LENGTH_SHORT
                ).show()
            }
            // selectedNote = null
        }
    )
    // Everytime selectedNote changes this run. if it doesn't  change this doesn't launch
    LaunchedEffect(selectedNote) {
        selectedNote?.let {
            title = it.title
            description = it.description
        }
    }
}
