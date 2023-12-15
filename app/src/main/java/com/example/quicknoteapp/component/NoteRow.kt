package com.example.quicknoteapp.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknoteapp.data.model.NoteData
import com.example.quicknoteapp.ui.theme.rowBackGroundColor
import com.example.quicknoteapp.ui.theme.rowTextColor
import com.example.quicknoteapp.util.formatDate
import java.util.UUID

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: NoteData,
    onNoteClicked: (NoteData) -> Unit, // everytime we clicked on the row, something happened
    onDeleteClicked: (NoteData) -> Unit
) {
    val toastContext = LocalContext.current
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
        color = MaterialTheme.rowBackGroundColor,
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
                color = MaterialTheme.rowTextColor,
                style = MaterialTheme.typography.titleMedium, // subtitle2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = note.description,
                    color = MaterialTheme.rowTextColor,
                    style = MaterialTheme.typography.titleSmall, // subtitle1
                    maxLines = 1
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = {onDeleteClicked(note)}
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Text(
                // Google for different format. EEE = day of the week, d = day of the month, MMM = month
                text = formatDate(note.entryDate.time),//note.entryDate.format(DateTimeFormatter.ofPattern("EEE d, MMM")),
                color = MaterialTheme.rowTextColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteRowPreview() {
    NoteRow(
        note = NoteData(
            id = UUID.randomUUID(),
            title = "Title",
            description = "Push this Icon to the end ->"
        ),
        onNoteClicked = {},
        onDeleteClicked = {}
    )
}
