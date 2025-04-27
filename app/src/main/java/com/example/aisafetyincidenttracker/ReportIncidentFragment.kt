package com.example.aisafetyincidenttracker

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aisafetyincidenttracker.databinding.FragmentReportIncidentBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportIncidentFragment : Fragment() {
    private lateinit var binding: FragmentReportIncidentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportIncidentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSeveritySpinner()
        setupSaveButton()
        var backbtn=view.findViewById<ImageButton>(R.id.backscreen)
    }

    private fun setupSeveritySpinner() {
        val severities = Severity.values().map { it.toString() }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            severities
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.severitySpinner.adapter = adapter
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            if (validateInput()) {
                saveNewIncident()
                findNavController().navigateUp()
            }
        }
    }

    private fun saveNewIncident() {
        val title = binding.titleEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val severity = Severity.values()[binding.severitySpinner.selectedItemPosition]
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            .format(Date())

        val newId = (requireActivity() as MainActivity).getAllIncidents()
            .maxOfOrNull { it.id }?.plus(1) ?: 1
        val newIncident = Incident(newId, title, description, severity, date)

        (requireActivity() as MainActivity).addNewIncident(newIncident)
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (binding.titleEditText.text.isNullOrBlank()) {
            binding.titleEditText.error = "Title is required"
            isValid = false
        }

        if (binding.descriptionEditText.text.isNullOrBlank()) {
            binding.descriptionEditText.error = "Description is required"
            isValid = false
        }

        return isValid
    }
}