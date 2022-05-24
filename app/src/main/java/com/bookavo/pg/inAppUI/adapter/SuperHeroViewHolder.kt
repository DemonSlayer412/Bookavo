package com.bookavo.pg.inAppUI.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bookavo.pg.R
import com.bookavo.pg.inAppUI.SuperHero

class SuperHeroViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val superhero = view.findViewById<TextView>(R.id.v1SuperHeroName)
    val realName = view.findViewById<TextView>(R.id.v1RealName)
    val publisher = view.findViewById<TextView>(R.id.publisher)
    val photo = view.findViewById<ImageView>(R.id.v1SuperHero)

    fun render(superheroModel: SuperHero) {
        superhero.text = superheroModel.superhero
        realName.text = superheroModel.realName
        publisher.text = superheroModel.publisher
//        photo.text = superheroModel.photo
    }
}