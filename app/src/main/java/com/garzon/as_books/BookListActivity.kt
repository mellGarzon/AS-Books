package com.garzon.as_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class BookListActivity : AppCompatActivity() {

    private var booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)
        val recyclerViewBooks = findViewById<RecyclerView>(R.id.rvData)
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
        recyclerViewBooks.setHasFixedSize(true)

        updateAdapter()
        initRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        val rvData = findViewById<RecyclerView>(R.id.rvData)
        val txtMsg = findViewById<TextView>(R.id.txtMsg)
        val bookDao = BookDao(this)
        //booksList.clear() //todo
        booksList = bookDao.getAll()
        if (booksList.isEmpty()) {
            rvData.visibility = View.GONE;
            txtMsg.visibility = View.VISIBLE;
            txtMsg.text = "Sem dados para exibir"
        }
        else {
            rvData.visibility = View.VISIBLE;
            txtMsg.visibility = View.GONE;
        }
        rvData.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        val rvDados = findViewById<RecyclerView>(R.id.rvData)
        Log.v("LOG", "Inicia RecyclerView")
        val adapter2 = BookAdapter(booksList)
        rvDados.adapter = adapter2
        val layout = GridLayoutManager(this, 2)
        // val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }


}





