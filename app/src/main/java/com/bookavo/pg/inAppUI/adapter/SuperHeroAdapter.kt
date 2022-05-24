package com.bookavo.pg.inAppUI.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bookavo.pg.R
import com.bookavo.pg.inAppUI.SuperHero

class SuperHeroAdapter(private val superHeroList: List<SuperHero>): RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]
        holder.render(item)
    }

    // tamaño del listado :D
    override fun getItemCount(): Int {
        return superHeroList.size
    }

}