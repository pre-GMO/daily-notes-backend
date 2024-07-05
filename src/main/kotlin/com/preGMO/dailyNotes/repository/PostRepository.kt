package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.model.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByUserId(userId: Long): List<Post>
}
