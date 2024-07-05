package com.pre_gmo.daily_notes.repository

import com.pre_gmo.daily_notes.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByUserId(userId: Long): List<Post>
}
