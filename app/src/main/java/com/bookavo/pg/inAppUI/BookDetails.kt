package com.bookavo.pg.inAppUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bookavo.pg.databinding.BookDetailsBinding

// TODO: Rename parameter arguments, choose names that match

class BookDetails : Fragment() {
    private var _binding: BookDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        println(arguments?.get("book_id"))
        _binding = BookDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println(arguments?.get("book_id"))
        binding.backToHome.setOnClickListener {
            // use navigationUp
            val navController = findNavController()
            navController.navigateUp()
        }
    }
}