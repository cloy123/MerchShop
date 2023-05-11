package com.monsieur.cloy.data.api.models.requests

import com.monsieur.cloy.data.api.models.FinishParticipantDto

class FinishEventRequest(val eventId: String, val participants: List<FinishParticipantDto>) {
}