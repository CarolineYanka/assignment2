// In: app/src/main/java/com/example/s8132684assignment2/ui/dashboard/DashboardFragment.kt

package com.example.s8132684assignment2.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8132684assignment2.R
import com.example.s8132684assignment2.data.Entity
import kotlinx.coroutines.launch


class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var adapter: EntityAdapter
    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Adapter with click listener
        adapter = EntityAdapter { entity ->
            // This is one way to pass data. Safe Args is another.
            findNavController().navigate(
                R.id.action_dashboardFragment_to_detailsFragment,
                Bundle().apply { putParcelable("entity", entity) }
            )
        }

        // Setup RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Use lifecycleScope to collect StateFlows
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entities.collect { entities ->
                adapter.submitList(entities)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collect { errorMsg ->
                if (errorMsg != null) {
                    Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadDashboardData("fitness")
    }
}
