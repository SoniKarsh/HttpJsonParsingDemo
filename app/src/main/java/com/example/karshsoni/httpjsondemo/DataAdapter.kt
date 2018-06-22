package com.example.karshsoni.httpjsondemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DataAdapter(var items: ArrayList<MyDataClass>, var context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.uId.text = items[holder.adapterPosition].id
        holder.uName.text = items[holder.adapterPosition].name
        holder.uEmail.text = items[holder.adapterPosition].email
        holder.uGender.text = items[holder.adapterPosition].gender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    var uId = view.findViewById<TextView>(R.id.textView)
    var uName = view.findViewById<TextView>(R.id.textView2)
    var uEmail = view.findViewById<TextView>(R.id.textView3)
    var uGender = view.findViewById<TextView>(R.id.textView4)
}