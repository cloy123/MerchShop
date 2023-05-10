package com.monsieur.cloy.domain.models.common

import com.monsieur.cloy.domain.models.*

class UpdateEventDataResult(
    val events: List<Event>?,
    val eventResponsibles: List<EventResponsible>?,
    val eventRoles: List<EventRole>?,
    val eventParticipants: List<EventParticipant>?,
    val isSuccessful: Boolean,
    val code: Int
) {
}