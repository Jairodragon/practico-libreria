package com.example.practiacapipersonas.repositories

import com.example.practicaapipersonas.api.APIProductosService
import com.example.practicaapipersonas.models.Libro

import com.example.practicaapipersonas.repositories.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LibroRepository {
    fun getLibroList(success: (List<Libro>?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service: APIProductosService = retrofit.create(APIProductosService::class.java)
        service.obtenerListaDeLibros().enqueue(object : Callback<List<Libro>> {
            override fun onResponse(call: Call<List<Libro>>, response: Response<List<Libro>>) {
                success(response.body())
            }

            override fun onFailure(call: Call<List<Libro>>, t: Throwable) {
                failure(t)
            }
        })
    }

    // Similar methods for obtenerLibroPorId, eliminarLibro, insertarLibro, editarLibro
}