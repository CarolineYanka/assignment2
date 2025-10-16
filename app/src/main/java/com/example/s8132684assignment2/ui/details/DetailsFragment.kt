package com.example.s8132684assignment2.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.s8132684assignment2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val property1Text = view.findViewById<TextView>(R.id.property1Text)
        val property2Text = view.findViewById<TextView>(R.id.property2Text)
        val descriptionText = view.findViewById<TextView>(R.id.descriptionText)

        val entity = args.entity
        property1Text.text = entity.property1
        property2Text.text = entity.property2
        descriptionText.text = entity.description
    }
}