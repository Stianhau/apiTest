package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_selected.*

class Selected : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected)

        getAndSetData()

    }

    fun getAndSetData(){
        val httpAsync = "https://jsonplaceholder.typicode.com/todos/${MainActivity.Clicked.todos.id}/"
            .httpGet()
            .responseObject(Todos.Deserializerr()) { request, response, result ->
                Log.d("test", "https://jsonplaceholder.typicode.com/todos/${MainActivity.Clicked.todos.id}/")
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                        Log.d("test", "https://jsonplaceholder.typicode.com/todos/}")
                    }
                    is Result.Success -> {
                        val (todos, err) = result
                        val temp = todos
                        Log.d("test", "https://jsonplaceholder.typicode.com/todos/${MainActivity.Clicked.todos.id}")
                        textView2.text = "userID: ${temp?.userID.toString()}"
                        textView3.text = "ID: ${temp?.id.toString()}"
                        textView4.text = "Title: ${temp?.title.toString()}"
                        textView5.text = "Completed: ${temp?.completed.toString()}"
                    }
                }
            }
        httpAsync.join()
    }
}
