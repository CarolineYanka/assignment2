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

    private val entityList = mutableListOf<Entity>()

    fun submitList(list: List<Entity>) {
        entityList.clear()
        entityList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entityList[position]
        holder.bind(entity)
        holder.itemView.setOnClickListener { onItemClick(entity) }
    }

    override fun getItemCount(): Int = entityList.size

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseName: TextView = itemView.findViewById(R.id.textExerciseName)
        private val muscleGroup: TextView = itemView.findViewById(R.id.textMuscleGroup)
        private val difficulty: TextView = itemView.findViewById(R.id.textDifficulty)

        fun bind(entity: Entity) {
            exerciseName.text = entity.exerciseName
            muscleGroup.text = entity.muscleGroup
            difficulty.text = entity.difficulty
        }
    }
}
