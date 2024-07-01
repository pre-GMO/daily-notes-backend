package com.pre_gmo.daily_notes.repository

import com.pre_gmo.daily_notes.model.PostVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostVersionRepository : JpaRepository<PostVersion, Long> {
    fun findByPostId(postId: Long): List<PostVersion>
}