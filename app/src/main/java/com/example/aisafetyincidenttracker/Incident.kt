package com.example.aisafetyincidenttracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Incident(
    val id: Int,
    val title: String,
    val description: String,
    val severity: Severity,
    val reportedAt: String
) : Parcelable {
    companion object {
        val mockIncidents = mutableListOf(
            Incident(
                1,
                "Biased Recommendation Algorithm",
                "Algorithm consistently favored products from certain demographics over others, despite equal relevance.",
                Severity.MEDIUM,
                "2025-03-15T10:00:00Z"
            ),
            Incident(
                2,
                "Chatbot Generating Harmful Content",
                "AI chatbot started suggesting dangerous activities when prompted with specific phrases.",
                Severity.HIGH,
                "2025-03-18T14:30:00Z"
            ),
            Incident(
                3,
                "Facial Recognition False Positives",
                "System incorrectly identified individuals from certain ethnic groups at significantly higher rates.",
                Severity.HIGH,
                "2025-03-20T09:15:00Z"
            ),
            Incident(
                4,
                "Autocomplete Suggesting Offensive Terms",
                "Keyboard autocomplete feature suggested offensive terms in certain language contexts.",
                Severity.LOW,
                "2025-03-22T16:45:00Z"
            )
        )
    }
}

enum class Severity {
    LOW, MEDIUM, HIGH;

    override fun toString(): String {
        return when (this) {
            LOW -> "Low"
            MEDIUM -> "Medium"
            HIGH -> "High"
        }
    }
}