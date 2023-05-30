package com.monsieur.cloy.domain.models.common

class NoteVisitResult(
    var isSuccessful: Boolean,
    var isNoted: Boolean,
    var errorMessage: String,
    var code: Int
) {
}