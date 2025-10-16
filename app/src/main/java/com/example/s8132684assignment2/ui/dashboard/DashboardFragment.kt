package com.example.s8132684assignment2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8132684assignment2.R
import com.example.s8132684assignment2.data.Entity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: EntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Pass click action to adapter
        adapter = EntityAdapter { entity ->
            val action = DashboardFragmentDirections
                .actionDashboardFragmentToDetailsFragment(entity)
            findNavController().navigate(action)
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ✅ Observe data from ViewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.entities.collect { list ->
                adapter.submitList(list)
            }
        }

        // ✅ Trigger data load (this should pass the keypass from Login)
        viewModel.loadDashboardData("fitness") // replace with actual keypass later
    }
}
