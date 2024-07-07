package com.pre_gmo.daily_notes.service.postversion

import com.pre_gmo.daily_notes.dto.CreatePostVersionDTO
import com.pre_gmo.daily_notes.dto.PostVersionDTO
import com.pre_gmo.daily_notes.dto.CreateRequestPostVersionDTO

typealias GetPostVersionResult = Pair<PostVersionDTO?, Boolean>;

interface PostVersionService {
    fun getPostVersionsByPostId(postId: Long): List<PostVersionDTO>
    fun getLatestPostVersionByPostId(postId: Long): GetPostVersionResult
    fun createPostVersions(createRequestPostVersionDTO: CreateRequestPostVersionDTO)
}
