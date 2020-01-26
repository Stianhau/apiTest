package com.example.myapplication

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class Todos(
    val userID : Int,
    val id: Int,
    val title: String,
    val completed: Boolean
){
    class Deserializer: ResponseDeserializable<Array<Todos>>{
        override fun deserialize(content: String): Array<Todos>? = Gson().fromJson(content, Array<Todos>::class.java)
    }
    class Deserializerr: ResponseDeserializable<Todos>{
        override fun deserialize(content: String): Todos? = Gson().fromJson(content,Todos::class.java)
    }
}
