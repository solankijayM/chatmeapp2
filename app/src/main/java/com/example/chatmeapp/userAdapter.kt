package com.example.chatmeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class userAdapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<userAdapter.userviewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewHolder {
       val  view: View = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return userviewHolder(view)
    }

    override fun onBindViewHolder(holder: userviewHolder, position: Int) {
        val  currentUser = userList[position]
        holder.textName.text = currentUser.name
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class userviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val  textName = itemView.findViewById<>(R.id.txt_name)
    }
}