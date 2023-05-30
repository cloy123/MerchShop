package com.monsieur.cloy.data.api.models.requests

import com.monsieur.cloy.data.api.models.FinishParticipantDto

class NoteVisitRequest(val eventId: String, val participants: List<FinishParticipantDto>) {
}