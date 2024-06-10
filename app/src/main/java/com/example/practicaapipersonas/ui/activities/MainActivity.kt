package com.example.practicaapipersonas.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.ui.adapters.LibroAdapter
import com.example.practicaapipersonas.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var libroAdapter: LibroAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.libroList.observe(this, { libros ->
            libros?.let {
                libroAdapter = LibroAdapter(it.toMutableList())
                recyclerView.adapter = libroAdapter
            }
        })

        viewModel.fetchLibros()
    }
}