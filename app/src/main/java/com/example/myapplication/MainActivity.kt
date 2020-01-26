package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;

class MainActivity : AppCompatActivity(), myOnClickListener {
    private lateinit var mainRecyclerAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        mainRecyclerAdapter.submitList(getData())

    }

    fun initRecyclerView(){
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mainRecyclerAdapter = MainRecyclerAdapter(this@MainActivity)
            adapter = mainRecyclerAdapter
        }
    }

    fun getData(): ArrayList<Todos>{
        val list = ArrayList<Todos>()

        val httpAsync = "https://jsonplaceholder.typicode.com/todos/"
            .httpGet()
            .responseObject(Todos.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        val (todos, err) = result
                        todos?.forEach { todo ->
                            list.add(todo)
                        }
                    }
                }
            }
        httpAsync.join()

        return list
    }

    override fun onClicked(todos: Todos) {
        Clicked.todos = todos
        val intent = Intent(this,Selected::class.java)
        startActivity(intent)

    }
    companion object Clicked{
        lateinit var todos: Todos

    }


}
