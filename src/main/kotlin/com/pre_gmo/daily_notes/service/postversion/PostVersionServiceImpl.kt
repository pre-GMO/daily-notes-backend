package com.pre_gmo.daily_notes.service.postversion

import com.pre_gmo.daily_notes.dto.CreatePostVersionDTO
import com.pre_gmo.daily_notes.dto.PostVersionDTO
import com.pre_gmo.daily_notes.dto.CreateRequestPostVersionDTO
import com.pre_gmo.daily_notes.repository.postversion.PostVersionRepository
import org.springframework.stereotype.Service
import jakarta.transaction.Transactional

@Service
// よく見かける実装がServiceクラス全体にかかるようにtransactionalを置いていることが多かったためここに記述
@Transactional
class PostVersionServiceImpl(private val postVersionRepository: PostVersionRepository) : PostVersionService {
    override fun getPostVersionsByPostId(postId: Long): List<PostVersionDTO> = postVersionRepository.findByPostId(postId)
    override fun getLatestPostVersionByPostId(postId: Long): GetPostVersionResult {
        val result = postVersionRepository.findLatestVersionByPostId(postId)
        if (result.isEmpty()) {
            return GetPostVersionResult(null, false)
        }
        return GetPostVersionResult(result[0], true)
    }
    override fun createPostVersions(
        createRequestPostVersionDTO: CreateRequestPostVersionDTO) {
        val searchResult = postVersionRepository.findLatestVersionNumberByPostId(
            createRequestPostVersionDTO.postId)
        val nextVersionNumber = if(searchResult == -1) 1 else searchResult
        val createPostVersionDto = CreatePostVersionDTO(
            createRequestPostVersionDTO.postId,
            createRequestPostVersionDTO.content,
            nextVersionNumber)
        postVersionRepository.createByPostId(createPostVersionDto)
    }
}