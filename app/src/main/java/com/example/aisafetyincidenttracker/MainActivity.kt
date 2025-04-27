package com.example.aisafetyincidenttracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    // Store incidents in the Activity
    private val incidents = Incident.mockIncidents.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getAllIncidents(): List<Incident> = incidents

    fun addNewIncident(incident: Incident) {
        incidents.add(0, incident) // Add to beginning of list
        updateIncidentList()
    }

    private fun updateIncidentList() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val incidentListFragment = navHostFragment?.childFragmentManager?.fragments?.firstOrNull() as? IncidentListFragment
        incidentListFragment?.refreshList(incidents)
    }
}