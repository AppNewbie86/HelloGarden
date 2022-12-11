package com.example.hellogarden.data.models

import com.google.firebase.firestore.DocumentId

/**
 * Datenklasse Member wo alle nötigen Parameter enthält um damit vollständig mit
 * FireBaseStorage und Auth kommunizieren kann
 */

data class Member(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val level: Long = 1,
    val image: String = "",
    val myFirstName: String = "",
    val myLastName: String = "",
    val myZip: Long = 1
)
