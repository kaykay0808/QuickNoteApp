package com.example.quicknoteapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknoteapp.R
import com.example.quicknoteapp.component.NoteButton
import com.example.quicknoteapp.component.NoteInputText
import com.example.quicknoteapp.component.NoteRow
import com.example.quicknoteapp.data.NotesDummyDataSource
import com.example.quicknoteapp.data.model.NoteData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
/** Custom component used in this compose -> (NoteInputText) (NoteButton) (NoteRow) */
fun NoteScreen(
    allNotes: List<NoteData>,
    onNoteClicked: (NoteData) -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    title: String,
    maxLine: Int,
    description: String,
    onSaveButtonClicked: () -> Unit,
    onDeleteClicked: (NoteData) -> Unit
) {
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
            // our custom NoteTextInput in our component
            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                text = title, // mutableState
                onTextChange = onTitleChange,
                // check if character is a letter and whitespace.
                label = "Title label",
                maxLine = maxLine
            )
            // our custom NoteTextInput in our component
            NoteInputText(
                modifier = Modifier
                    .padding(
                        top = 9.dp,
                        bottom = 8.dp
                    ),
                text = description,
                onTextChange = onDescriptionChange,
                label = "Add a note",
                maxLine = maxLine
            )
            // SAVE BUTTON (our custom in our component)
            NoteButton(
                text = "SAVE",
                onClick = onSaveButtonClicked,
                // Validate if it is some string in the field when clicking the button () ->
            )
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(allNotes) { note ->
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold
                )
                NoteRow(
                    note = note,
                    onNoteClicked = onNoteClicked,//{ selectedNote = it }
                    onDeleteClicked = onDeleteClicked
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(
        allNotes = NotesDummyDataSource().loadNotes(), // can change to emptyList()
        onNoteClicked = {},
        onSaveButtonClicked = {},
        onTitleChange = {},
        onDescriptionChange = {},
        onDeleteClicked = {},
        title = "Some Title",
        description = "Description of the Task",
        maxLine = 1
    )
}

