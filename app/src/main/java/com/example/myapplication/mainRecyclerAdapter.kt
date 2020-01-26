package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_row.view.*

class MainRecyclerAdapter(private val myOnClickListener: myOnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: List<Todos> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is MainViewHolder -> holder.bind(items[position],myOnClickListener)
        }
    }

    fun submitList(list:List<Todos>){
        items = list
    }
    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textview = itemView.textView

        fun bind(todos: Todos,myOnClickListener: myOnClickListener){
            textview.text = "id:  ${todos.id.toString()}"
            itemView.setOnClickListener { myOnClickListener.onClicked(todos) }
        }
    }

}
interface myOnClickListener{
    fun onClicked(todos:Todos)
}