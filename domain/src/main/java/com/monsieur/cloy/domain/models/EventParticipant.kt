package com.monsieur.cloy.domain.models

class EventParticipant(
    var id: String,
    var eventId: String,
    var userId: String,
    var isVisit: Boolean,
    var userTypeId: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var className: String?,
    var genderId: Int,
    var role: EventRole
) {
}