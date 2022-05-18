package com.example.chatmeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_Password)
        btnlogin = findViewById(R.id.btnlogin)
        btnsignup = findViewById(R.id.btnsignup)


        btnsignup.setOnClickListener {
            Intent(this, signup::class.java).also {
                startActivity(it)

            }
            btnlogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                if(email.isNotEmpty() && password.isNotEmpty()){
                    login(email,password);
                } else {
                    Toast.makeText(this, "Fields cant be empty", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            this
        ) { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Login successfull", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Login successfull", Toast.LENGTH_LONG).show()
            }
        }
    }
}