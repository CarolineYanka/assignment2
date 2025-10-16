package com.example.s8132684assignment2.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8132684assignment2.R
import com.example.s8132684assignment2.data.Entity

class EntityAdapter(
    private val onItemClick: (Entity) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    private val entities = mutableListOf<Entity>()

    fun submitList(list: List<Entity>) {
        entities.clear()
        entities.addAll(list)
        notifyDataSetChanged()
    }

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textExerciseName: TextView = itemView.findViewById(R.id.textExerciseName)
        private val textMuscleGroup: TextView = itemView.findViewById(R.id.textMuscleGroup)
        private val textDifficulty: TextView = itemView.findViewById(R.id.textDifficulty)

        fun bind(entity: Entity) {
            textExerciseName.text = entity.exerciseName
            textMuscleGroup.text = "Muscle: ${entity.muscleGroup}"
            textDifficulty.text = "Difficulty: ${entity.difficulty}"

            itemView.setOnClickListener { onItemClick(entity) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    override fun getItemCount(): Int = entities.size
}
