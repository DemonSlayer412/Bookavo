package com.bookavo.pg


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.bookavo.pg.databinding.RegisterBinding
import java.util.regex.Pattern
import com.google.firebase.auth.FirebaseAuth

//la wea bugeada
class Register : AppCompatActivity() {
    private lateinit var binding: RegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botonRegistrar: Button = findViewById(R.id.register_button)
        botonRegistrar.setOnClickListener{
            validate()
        }
    }

    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        println("Resultados: ")

        if (false in result){
            Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this,"Registrado correctamente", Toast.LENGTH_LONG).show()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(binding.mailInput.text.toString(),
                binding.passwordInput.text.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, inApp::class.java))
                    finish()
                }
                else{
                    println(it.exception)
                    Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun validateEmail() : Boolean {
//        val email = binding.mailInput.text.toString()
        val email = binding.mailInput.text.toString()

        return if (email.isEmpty()) {
            Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_LONG).show()
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Enter a valid email", Toast.LENGTH_LONG).show()
            false
        }else{
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
            Toast.makeText(this,"La constreseñá necesita: 1 digito, 1 uppser case, 1 lower case, sin espacios, 1 caracter especial", Toast.LENGTH_LONG).show()
            false
        }else{
            true
        }
    }
}