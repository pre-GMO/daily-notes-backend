package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.model.PostVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostVersionRepository : JpaRepository<PostVersion, Long> {
    fun findByPostId(postId: Long): List<PostVersion>
}
