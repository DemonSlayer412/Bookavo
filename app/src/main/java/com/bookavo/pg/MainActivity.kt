package com.bookavo.pg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bookavo.pg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // isLogged?
//            println("Logged in")
//            println(firebaseAuth.currentUser?.email)
        if (firebaseAuth.currentUser != null) {
//            Log.d("MainActivity", "onCreate: user is logged in")
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
            setContentView(R.layout.home)
            startActivity(Intent(this,inApp::class.java))
        } else {
            setContentView(R.layout.login)
            //ClickLoginButton
            val boton:Button = findViewById(R.id.button_Login)
            boton.setOnClickListener {
                startActivity(Intent(this,inApp::class.java))
                finish()
            }
            //RegisterButton
            val boton2:Button = findViewById(R.id.button_Register)
            boton2.setOnClickListener {
                Log.d("-----Funca-----","la wea 2")
                startActivity(Intent(this,Register::class.java))
                finish()
            }
        }
    }
}


