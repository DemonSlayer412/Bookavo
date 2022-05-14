package com.bookavo.pg

import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bookavo.pg.databinding.LoginBinding

//hola wapo
class Login : AppCompatActivity() {
    private lateinit var binding: LoginBinding

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//       val botonRegistrar: Button = findViewById(R.id.register_button)
        //ClickLoginButton
        println("Here")
        binding.buttonLogin.setOnClickListener {
            // check login credencials
            val name = binding.Name.text.toString()
            val passowrd = binding.Password.text.toString()
            println("Login")

            firebaseAuth.signInWithEmailAndPassword(name, passowrd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Login", "signInWithEmail:success")
                        val user = firebaseAuth.currentUser
                        val intent = Intent(this, inApp::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Login", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //Toast.makeText(baseContext, "Authentication failed.",
                        //Toast.LENGTH_SHORT).show()
                    }
                }
        }
        //RegisterButton
        binding.buttonRegister.setOnClickListener {
            Log.d("-----Funca-----","la wea 2")
            startActivity(Intent(this,Register::class.java))

        }
    }
}