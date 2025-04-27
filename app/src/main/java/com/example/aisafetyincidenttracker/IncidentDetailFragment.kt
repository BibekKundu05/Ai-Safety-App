package com.example.aisafetyincidenttracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aisafetyincidenttracker.databinding.FragmentIncidentDetailBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IncidentDetailFragment : Fragment() {
    private lateinit var binding: FragmentIncidentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncidentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incident = arguments?.getParcelable<Incident>("incident")
        incident?.let { populateIncidentDetails(it) }
    }

    private fun populateIncidentDetails(incident: Incident) {
        binding.titleText.text = incident.title
        binding.severityText.text = incident.severity.toString()
        binding.descriptionText.text = incident.description

        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            .parse(incident.reportedAt)
        val formattedDate = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
            .format(date ?: Date())
        binding.dateText.text = formattedDate

        // Set severity color
        val colorRes = when (incident.severity) {
            Severity.HIGH -> R.color.severity_high
            Severity.MEDIUM -> R.color.severity_medium
            Severity.LOW -> R.color.severity_low
        }
        binding.severityText.setBackgroundResource(colorRes)
    }
}