package com.example.laba9mobile1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var addFab: FloatingActionButton
    private val notes = mutableListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRecyclerView = findViewById(R.id.notesRecyclerView)
        addFab = findViewById(R.id.addFab)

        // Add an initial empty note
        notes.add(Note(notes.size, "First", "For test", 2))

        notesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = NoteAdapter(notes, ::onNoteClicked)
        }

        addFab.setOnClickListener {
            val newNote = Note(notes.size ,"", "", 0)
            notes.add(newNote)
            notesRecyclerView.adapter?.notifyItemInserted(notes.lastIndex)
            onNoteClicked(newNote)
        }
    }

    private fun onNoteClicked(selectedNote: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("note", selectedNote)
        startActivityForResult(intent, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedNote = data?.getParcelableExtra<Note>("note")
            val noteIndex = notes.indexOfFirst { it.id == updatedNote?.id }
            if (updatedNote != null) {
                notes[noteIndex] = updatedNote
            }
            notesRecyclerView.adapter?.notifyItemChanged(noteIndex)
        }
    }

    companion object {
        const val NOTE_REQUEST_CODE = 1001
    }
}
