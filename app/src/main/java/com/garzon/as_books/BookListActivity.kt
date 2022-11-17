package com.garzon.as_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookListActivity : AppCompatActivity() {

    private var booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        updateAdapter()
        initRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        //var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        //var txtMsg = findViewById<TextView>(R.id.txtMsg)
        val bookDao = BookDao(this)
        booksList.clear() //todo
        booksList = bookDao.getAll()
        if (booksList.isEmpty()) {
            rvDados.setVisibility(View.GONE);
            //txtMsg.setVisibility(View.VISIBLE);
            //txtMsg.setText("Sem dados para exibir")
        }
        else {
            rvDados.setVisibility(View.VISIBLE);
            //txtMsg.setVisibility(View.GONE);
        }
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        Log.v("LOG", "Inicia RecyclerView")
        val adapter2 = BookAdapter(booksList)
        rvDados.adapter = adapter2
        val layout = GridLayoutManager(this, 2)
        // val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}





