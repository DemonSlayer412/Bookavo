package com.bookavo.pg.inAppUI

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bookavo.pg.Login
import com.bookavo.pg.R
import com.bookavo.pg.databinding.CardDailyBinding
import com.bookavo.pg.databinding.ProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source


class Profile: Fragment() {
    private var _binding: ProfileBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.socialTwitter.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"))
            startActivity(browserIntent)
        }
        binding.closeSession.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(context, Login::class.java))
            activity?.finish()
        }

        val correoActual = FirebaseAuth.getInstance().currentUser?.email
        binding.UserName.setText(correoActual)

        db.collection("users").get().addOnSuccessListener { result ->
            for (user in result) {
                val category = user.data.get("email").toString()
                println(category)
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}