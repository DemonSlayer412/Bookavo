package com.bookavo.pg.inAppUI

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bookavo.pg.R
import com.bookavo.pg.databinding.BookDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.bookavo.pg.databinding.ChapterDetailsBinding

// TODO: Rename parameter arguments, choose names that match

class BookDetails : Fragment() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private var _binding: BookDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = BookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments?.get("book_id") != null) {
            db.collection("books").document(arguments?.get("book_id").toString()).get()
                .addOnSuccessListener {
                    binding.bookTitleDetails.text = it.get("title").toString()
                    binding.bookAuthorDetails.text = it.get("author").toString()
                    binding.summaryDetails.text = it.get("summary").toString()
                    binding.btnPriceDetails.text = "buy " + it.get("price").toString() + "$"
                    binding.imageBook.setImageBitmap(createBitMap(it.get("img_portrait").toString()))
                    //compare email with bookID of collection
                    db.collection("likes_books").get().addOnSuccessListener {
                        for (document in it) {
                            if (document.get("book_id").toString() == arguments?.get("book_id").toString() &&
                                document.get("user_mail").toString() == firebaseAuth.currentUser?.email) {
                                binding.likeBtn.setImageResource(R.drawable.ic_heart_active)
                            }
                        }
                    }
                    for (caps in it.get("caps") as ArrayList<HashMap<String, String>>) {
                        val chapterTime = caps.get("time").toString()
                        val chaperBinding = ChapterDetailsBinding.inflate(layoutInflater)

                        chaperBinding.nameChapter.text = caps.get("name").toString()
                        println(chapterTime is String)
                        chaperBinding.timeChapter.text = chapterTime + " minutes"
                        binding.chapterContainer.addView(chaperBinding.root)
                    }
                }
        }
        binding.backToHome.setOnClickListener {
            // use navigationUp
            val navController = findNavController()
            navController.navigateUp()
        }
        binding.likeBtn.setOnClickListener {
//            firebaseAuth.currentUser.email
            db.collection("likes_books").get().addOnSuccessListener {
                // if user already like this book show toast alert
                var isLike = false
                for (document in it) {
                    if (document.get("book_id").toString() == arguments?.get("book_id").toString() &&
                        document.get("user_mail").toString() == firebaseAuth.currentUser?.email) {
                        // toast alert
                        Toast.makeText(context, "You already like this book", Toast.LENGTH_SHORT).show()
                        isLike = true
                        break
                    }
                }
                if (!isLike) {
                    db.collection("likes_books").add(
                        mapOf("user_mail" to firebaseAuth.currentUser?.email,
                            "book_id" to arguments?.get("book_id").toString())
                    )
                    binding.likeBtn.setImageResource(R.drawable.ic_heart_active)
                }
            }
        }
    }

    fun createBitMap(url: String): Bitmap? {
        var image: Bitmap? = null
        try {
            val streamImg = java.net.URL(url).openStream()
            image = BitmapFactory.decodeStream(streamImg)
        }
        catch (e: Exception) {
            println("Error Message" + e.message.toString())
            e.printStackTrace()
        }
        return image
    }
}