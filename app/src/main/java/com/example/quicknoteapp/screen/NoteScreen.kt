package com.example.quicknoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknoteapp.R
import com.example.quicknoteapp.component.NoteButton
import com.example.quicknoteapp.component.NoteInputText
import com.example.quicknoteapp.data.NotesDummyDataSource
import com.example.quicknoteapp.data.model.NoteData
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<NoteData>,
    onAddNote: (NoteData) -> Unit,
    onRemoveNote: (NoteData) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val toastContext = LocalContext.current

    // Box, Surface, Column, Row, card?, Scaffold
    Column(
        modifier = Modifier
            .padding(6.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icons"
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFDADFE3))
        )
        // CONTENT: 2 textField and a button
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                text = title,
                label = "Title label",
                onTextChange = {
                    if (
                        it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }
                    )
                        title = it
                }
            )
            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (
                        it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }
                    )
                        description = it
                }
            )
            NoteButton(
                text = "SAVE",
                onClick = {
                    // Validate
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        // Save and add to list.
                        onAddNote(
                            NoteData(
                                title = title,
                                description = description
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(
                            toastContext,
                            "Note added",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(
                    note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    }
                )
                // Text(text = note.title)
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: NoteData,
    onNoteClicked: (NoteData) -> Unit // everytime we clicked on the row, something happened
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 33.dp,
                    bottomStart = 33.dp
                )
            )
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Column(
            modifier
                .padding(
                    horizontal = 14.dp,
                    vertical = 6.dp
                )
                .clickable { onNoteClicked(note) },
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                // Google for different format. EEE = day of the week, d = day of the month, MMM = month
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE d, MMM")),
                style = MaterialTheme.typography.bodySmall
            )

        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        notes = NotesDummyDataSource().loadNotes(), // can change to emptyList()
        onAddNote = {},
        onRemoveNote = {}
    )
}

@Preview(showBackground = true)
@Composable
fun NoteRowPreview() {
    NoteRow(
        note = NoteData(
            id = UUID.randomUUID(),
            title = "Doi toi co don",
            description = "one day"
        ),
        onNoteClicked = {}
    )
}
