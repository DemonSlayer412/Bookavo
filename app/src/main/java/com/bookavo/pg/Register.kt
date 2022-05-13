package com.bookavo.pg


import android.util.Log
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.bookavo.pg.databinding.RegisterBinding
import java.util.regex.Pattern

//la wea bugeada
class Register : AppCompatActivity() {
    private var _binding: RegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val botonRegistrar: Button = findViewById(R.id.register_button)
        botonRegistrar.setOnClickListener{
            validate()
        }
    }

    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())

        if (false in result){
            Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this,"Registrado correctamente", Toast.LENGTH_LONG).show()
    }

    private fun validateEmail() : Boolean {
        val email = binding.mailInput.text.toString()

        return if (email.isEmpty()) {
            Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_LONG).show()
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Enter a valid email", Toast.LENGTH_LONG).show()
            false
        }else{
            binding.mailInput.error = null
            true
        }
    }

    private fun validatePassword() : Boolean {

        val password = binding.passwordInput.text.toString()

        // Patrón con expresiones regulares
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +        //at least 1 lower case letter
                    "(?=.*[A-Z])" +        //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$"
        )

        return if (password.isEmpty()){
            Toast.makeText(this,"Enter a password", Toast.LENGTH_LONG).show()
            false
        }else if (!passwordRegex.matcher(password).matches()){
            Toast.makeText(this,"Too weak", Toast.LENGTH_LONG).show()
            false
        }else{
            true
        }
    }
}