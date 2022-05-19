package com.example.chatmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

import android.content.Intent
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import java.util.*


class signup : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth
    private val userCollection = FirebaseFirestore.getInstance().collection("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_Password)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signup(email,password)
        }
    }
    private fun signup(email: String, password: String
        ) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Log.d("signupActivity: ", task.toString())
                    val user = checkEmptyFields()
                    if(user != null) {
                        user.uid?.let { userCollection.document(it).set(user) }
                    }

                } else {
                    Toast.makeText(this, "some error occurred",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun checkEmptyFields(): com.example.chatmeapp.User? {
        val name = edtName.text.toString()
        val email = edtEmail.text.toString()
        var user: com.example.chatmeapp.User? = null
        if(name.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Fields cant be empty ", Toast.LENGTH_LONG).show()
        } else {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            user = User(
                uid = uid,
                name = name,
                email = email
            )
        }
        return user
    }
}