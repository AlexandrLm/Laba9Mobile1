package com.example.laba9mobile1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ImportanceSpinnerAdapter(context: Context, private val values: Array<String>, private val images: IntArray) :
    ArrayAdapter<String>(context, R.layout.spinner_item, values) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val row = inflater.inflate(R.layout.spinner_item, parent, false)
        val textView = row.findViewById<TextView>(R.id.spinner_text)
        val imageView = row.findViewById<ImageView>(R.id.spinner_image)

        textView.text = values[position]
        imageView.setImageResource(images[position])

        return row
    }
}
