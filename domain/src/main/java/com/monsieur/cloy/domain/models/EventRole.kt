package com.monsieur.cloy.domain.models

class EventRole(
    var id: String,
    var name: String,
    var userTypeId: Int,
    var eventId: String,
    var prize: Int
) {
}