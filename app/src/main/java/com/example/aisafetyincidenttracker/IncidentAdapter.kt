package com.example.aisafetyincidenttracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class IncidentAdapter(
    private var incidents: List<Incident>,
    private val onItemClick: (Incident) -> Unit
) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    class IncidentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.incident_title)
        val severity: TextView = itemView.findViewById(R.id.incident_severity)
        val date: TextView = itemView.findViewById(R.id.incident_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }
    fun updateList(newList: List<Incident>) {
        incidents = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = incidents[position]
        holder.title.text = incident.title
        holder.severity.text = incident.severity.toString()
        holder.date.text = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            .format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                .parse(incident.reportedAt) ?: Date()
            )

        // Set background color based on severity
        val colorRes = when (incident.severity) {
            Severity.HIGH -> R.color.severity_high
            Severity.MEDIUM -> R.color.severity_medium
            Severity.LOW -> R.color.severity_low
        }
        holder.severity.setBackgroundResource(colorRes)

        holder.itemView.setOnClickListener {
            onItemClick(incident)
        }
    }

    override fun getItemCount(): Int = incidents.size
}
