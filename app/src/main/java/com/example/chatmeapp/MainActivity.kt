package com.example.chatmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private var userList: MutableList<User> = mutableListOf()
    private lateinit var adapter: userAdapter
    private val userCollection = FirebaseFirestore.getInstance().collection("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRecyclerView = findViewById(R.id.userRecyclerView)

        getUpdatedList()

    }
    private fun getUpdatedList() {
        userCollection.get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                userList = task.result.toObjects(User::class.java)
                adapter = userAdapter(this, userList)
                userRecyclerView.adapter = adapter
            }
        }
    }
}