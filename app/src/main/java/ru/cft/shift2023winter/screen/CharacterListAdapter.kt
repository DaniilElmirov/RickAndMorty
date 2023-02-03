package ru.cft.shift2023winter.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.domain.entities.Character

class CharacterListAdapter(
    private val onCharacterItemClickListener: ((Character) -> Unit)? = null,
) : RecyclerView.Adapter<CharacterItemViewHolder>() {

    var characterList: List<Character> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val layout = R.layout.character_item

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return CharacterItemViewHolder(view)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        val characterItem = characterList[position]

        holder.view.setOnClickListener {
            onCharacterItemClickListener?.invoke(characterItem)
        }

        holder.bind(characterItem)
    }
}