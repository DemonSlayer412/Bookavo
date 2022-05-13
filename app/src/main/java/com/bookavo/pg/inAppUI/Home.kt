package com.bookavo.pg.inAppUI

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.bookavo.pg.databinding.HomeBinding
import com.bookavo.pg.databinding.CardDailyBinding
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class Home : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var _binding: HomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // insert layout
        db.collection("books").get().addOnSuccessListener { result ->
            for (document in result) {
                val category = document.data.get("category").toString()
                val summary = document.data.get("summary").toString()
                val image = "@drawable/" + document.data.get("img").toString()
                val title = document.data.get("title").toString()

                val cardDailyBinding = CardDailyBinding.inflate(layoutInflater)
//                println(cardDailyBinding.root.)

                // insert card
//                val card = LayoutInflater.from(context).inflate(R.layout.card_daily, null)
                binding.linearDailyList.addView(cardDailyBinding.root)
            }
        }
//        val tabs = activity!!.findViewById<View>(R.id.content_main_tabs) as TabLayout

//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}