package ru.cft.shift2023winter.screen

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entities.Character

class CharacterItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(characterItem: Character) {
        view.findViewById<TextView>(R.id.character_name).text = characterItem.name
        view.findViewById<ImageView>(R.id.character_image).load(characterItem.image)
    }
}