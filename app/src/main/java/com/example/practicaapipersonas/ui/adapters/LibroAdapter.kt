package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.models.Libro

class LibroAdapter(var libros: MutableList<Libro>) : RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {
    var listener: OnLibroClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.libro_item, parent, false)
        return LibroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libros[position]
        holder.bind(libro)
    }

    override fun getItemCount() = libros.size

    inner class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val autorTextView: TextView = itemView.findViewById(R.id.autorTextView)
        private val imagenLibro: ImageView = itemView.findViewById(R.id.imagenLibro)

        fun bind(libro: Libro) {
            nombreTextView.text = libro.nombre
            autorTextView.text = libro.autor
            Glide.with(itemView.context).load(libro.imagen).into(imagenLibro)

            itemView.setOnClickListener {
                listener?.onLibroClick(libro)
            }
        }
    }

    interface OnLibroClickListener {
        fun onLibroClick(libro: Libro)
    }

    fun updateLibros(newLibros: List<Libro>) {
        libros.clear()
        libros.addAll(newLibros)
        notifyDataSetChanged()
    }

    fun setOnLibroClickListener(listener: OnLibroClickListener) {
        this.listener = listener
    }
}
