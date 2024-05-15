package com.example.laba9mobile1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes: MutableList<Note>, private val onItemClick: (Note) -> Unit) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val importanceImageView: ImageView = itemView.findViewById(R.id.importanceImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.titleTextView.text = currentNote.title

        when (currentNote.importance) {
            0 -> holder.importanceImageView.setImageResource(R.drawable.green)
            1 -> holder.importanceImageView.setImageResource(R.drawable.yellow)
            2 -> holder.importanceImageView.setImageResource(R.drawable.redpng)
        }

        holder.itemView.setOnClickListener { onItemClick(currentNote) }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
