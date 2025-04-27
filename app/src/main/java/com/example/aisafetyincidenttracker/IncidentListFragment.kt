package com.example.aisafetyincidenttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aisafetyincidenttracker.databinding.FragmentIncidentListBinding

class IncidentListFragment : Fragment() {
    private lateinit var binding: FragmentIncidentListBinding
    private lateinit var adapter: IncidentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncidentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFilterSpinner()
        setupFab()
        refreshList((requireActivity() as MainActivity).getAllIncidents())
    }

    fun refreshList(allIncidents: List<Incident>) {
        val filteredIncidents = if (binding.filterSpinner.selectedItemPosition == 0) {
            allIncidents
        } else {
            val selectedSeverity = Severity.values()[binding.filterSpinner.selectedItemPosition - 1]
            allIncidents.filter { it.severity == selectedSeverity }
        }
        adapter.updateList(filteredIncidents)
    }

    private fun setupRecyclerView() {
        adapter = IncidentAdapter(emptyList()) { incident ->
            val action = IncidentListFragmentDirections.actionIncidentListFragmentToIncidentDetailFragment(incident)
            findNavController().navigate(action)
        }
        binding.incidentsRecyclerView.adapter = adapter
        binding.incidentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupFilterSpinner() {
        val severities = listOf("All") + Severity.values().map { it.toString() }
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            severities
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        binding.filterSpinner.adapter = spinnerAdapter
        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                refreshList((requireActivity() as MainActivity).getAllIncidents())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_incidentListFragment_to_reportIncidentFragment)
        }
    }
}