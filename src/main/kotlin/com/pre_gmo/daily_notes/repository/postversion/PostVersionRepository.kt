package com.pre_gmo.daily_notes.repository.postversion

import com.pre_gmo.daily_notes.dto.CreatePostVersionDTO
import com.pre_gmo.daily_notes.dto.PostVersionDTO

interface PostVersionRepository {
    fun findByPostId(postId: Long): List<PostVersionDTO>
    // MEMO: Listの方がデータがあるないの判断がしやすく、個人的に記述しやすかったため
    fun findLatestVersionByPostId(postId: Long): List<PostVersionDTO>
    fun findLatestVersionNumberByPostId(postId: Long): Int
    fun createByPostId(createPostVersionDto: CreatePostVersionDTO)
}