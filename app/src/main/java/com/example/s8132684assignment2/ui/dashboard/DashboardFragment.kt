package com.example.s8132684assignment2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8132684assignment2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EntityAdapter
    private var keypass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keypass = arguments?.let { DashboardFragmentArgs.fromBundle(it).keypass }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        keypass?.let { viewModel.loadDashboard(it) }

        viewModel.entities.observe(viewLifecycleOwner) { entities ->
            if (entities.isNotEmpty()) {
                adapter = EntityAdapter(entities) { entity ->
                    val action = DashboardFragmentDirections
                        .actionDashboardFragmentToDetailsFragment(entity)
                    findNavController().navigate(action)
                }
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(requireContext(), "No entities found", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }
}