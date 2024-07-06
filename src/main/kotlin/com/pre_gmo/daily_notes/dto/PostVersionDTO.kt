package com.pre_gmo.daily_notes.dto

data class PostVersionDTO(
    val id: Long,
    val postId: Long,
    val content: String,
    val versionNumber: Int
)

data class CreatePostVersionDTO(
    val postId: Long,
    val content: String,
    val nextVersionNumber: Int
)