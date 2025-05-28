package com.example.tugas3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var registeredUser: User? = null  // Simpan data user terdaftar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegisterLink)

        // Ambil user dari intent jika ada
        registeredUser = intent.getParcelableExtra("registeredUser")

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (registeredUser == null) {
                Toast.makeText(this, "Silakan daftar terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else if (email == registeredUser?.email && password == registeredUser?.password) {
                val intent = Intent(this, LandingActivity::class.java)
                intent.putExtra("user", registeredUser)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
