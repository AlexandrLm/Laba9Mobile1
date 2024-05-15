package com.example.laba9mobile1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class NoteActivity : AppCompatActivity() {

    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)


        val titleEditText = findViewById<EditText>(R.id.titleEditText)
        val descriptionEditText = findViewById<EditText>(R.id.descriptionEditText)
        val importanceSpinner = findViewById<Spinner>(R.id.importanceSpinner)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        note = intent.getParcelableExtra<Note>("note")!!

        titleEditText.setText(note.title)
        descriptionEditText.setText(note.description)

        val importanceLevels = arrayOf("Low", "Medium", "High")
        importanceSpinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, importanceLevels)
        importanceSpinner.setSelection(note.importance)

        saveButton.setOnClickListener {
            val updateNote = Note(note.id, titleEditText.text.toString(), descriptionEditText.text.toString(),importanceSpinner.selectedItemPosition)
            val intent = Intent().apply {
                putExtra("note", updateNote)
            }
            setResult(RESULT_OK, intent)
            finish()
        }

        deleteButton.setOnClickListener {
            val intent = Intent().apply {
                putExtra("note", note)
            }
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }
}
