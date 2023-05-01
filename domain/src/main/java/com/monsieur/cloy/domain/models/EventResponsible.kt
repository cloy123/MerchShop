package com.monsieur.cloy.domain.models

class EventResponsible(
    var id: String,
    var eventId: String,
    var userId: String,
    var userTypeId: Int,
    var firstName: String,
    var lastName: String,
    var email: String,
    var className: String?,
    var genderId: Int
) {
}