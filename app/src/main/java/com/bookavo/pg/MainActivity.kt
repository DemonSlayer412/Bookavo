package com.bookavo.pg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bookavo.pg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //login
        setContentView(R.layout.login)
        //ClickLoginButton
        val boton:Button = findViewById(R.id.button_Login)
        boton.setOnClickListener {
            startActivity(Intent(this,inApp::class.java))
        }
        //RegisterButton
        val boton2:Button = findViewById(R.id.button_Register)
        boton2.setOnClickListener {
            Log.d("-----Funca-----","la wea 2")
            startActivity(Intent(this,Register::class.java))
        }
    }
}


