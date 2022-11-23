package com.garzon.as_books

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var booksList = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<FloatingActionButton>(R.id.floatingActionRegisterButton)

        registerButton.setOnClickListener {
            val registerBook = Intent(this, AddBookActivity::class.java)
            startActivity(registerBook)
        }


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
        val layout = LinearLayoutManager(this)
        rvDados.layoutManager = layout
    }
}