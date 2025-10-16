package com.example.s8132684assignment2.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.s8132684assignment2.R
import com.example.s8132684assignment2.data.Entity

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        const val ARG_ENTITY = "entity"
    }

    private lateinit var entity: Entity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entity = it.getParcelable(ARG_ENTITY)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textExerciseName: TextView = view.findViewById(R.id.textExerciseName)
        val textMuscleGroup: TextView = view.findViewById(R.id.textMuscleGroup)
        val textEquipment: TextView = view.findViewById(R.id.textEquipment)
        val textDifficulty: TextView = view.findViewById(R.id.textDifficulty)
        val textCalories: TextView = view.findViewById(R.id.textCalories)
        val textDescription: TextView = view.findViewById(R.id.textDescription)

        textExerciseName.text = entity.exerciseName
        textMuscleGroup.text = "Muscle Group: ${entity.muscleGroup}"
        textEquipment.text = "Equipment: ${entity.equipment}"
        textDifficulty.text = "Difficulty: ${entity.difficulty}"
        textCalories.text = "Calories/hour: ${entity.caloriesBurnedPerHour}"
        textDescription.text = entity.description
    }
}
