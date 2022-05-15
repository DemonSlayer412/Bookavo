package com.bookavo.pg.inAppUI

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bookavo.pg.R
import com.bookavo.pg.databinding.CardDailyBinding
import com.bookavo.pg.databinding.HomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
import java.net.URL


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Home : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: HomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        // insert layout
        db.collection("books").get().addOnSuccessListener { result ->
            for (document in result) {
                val category = document.data.get("category").toString()
                var summary = document.data.get("summary").toString()
                if(summary.length > 100) {
                    summary = summary.substring(0, 100)
                }
                val image = document.data.get("img").toString()
                val title = document.data.get("title").toString()
                val id_book = document.id.toString()
                println(id_book)
                val cardDailyBinding = CardDailyBinding.inflate(layoutInflater)
                val cardDailyRoot = cardDailyBinding.root

                // insert data
                cardDailyBinding.bookCategory.text = category
                cardDailyBinding.bookTitle.text = title
                cardDailyBinding.bookSummary.text = summary
                cardDailyBinding.containerInfo.background = loadImageFromNetwork(image)

                // set event
                cardDailyBinding.cardDaily.setOnClickListener {
                    val bundle = bundleOf()
                    //               clave,      valor
                    bundle.putString("book_id", id_book)
                    val navController = findNavController()
                    navController.navigate(R.id.bookDetails, bundle)
                }

                // insert card
                binding.linearDailyList.addView(cardDailyRoot)
//                cardDailyRoot.card
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onClickCard(view: View) {
        println("click card")
    }

    private fun loadImageFromNetwork(imageUrl: String): Drawable? {
        var drawable: Drawable? = null
        try {
            drawable = Drawable.createFromStream(
                URL(imageUrl).openStream(), "1eAzB6g.png"
            )
        } catch (e: IOException) {
            println("test" + e.message)
        }
        if (drawable == null) {
            println("Es nula")
        } else {
            println("No es nula")
        }
        return drawable
    }
}