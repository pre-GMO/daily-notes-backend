package com.preGMO.dailyNotes.controller

import com.preGMO.dailyNotes.model.PostVersion
import com.preGMO.dailyNotes.repository.PostVersionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post-version")
class PostVersionController(
    private val postVersionRepository: PostVersionRepository,
) {
    @GetMapping(produces = ["application/json"])
    fun getAllPostVersions(): List<PostVersion> = postVersionRepository.findAll()

    @GetMapping("/{id}", produces = ["application/json"])
    fun getPostVersionById(
        @PathVariable id: Long,
    ): ResponseEntity<PostVersion> =
        postVersionRepository
            .findById(id)
            .map { post ->
                ResponseEntity.ok(post)
            }.orElse(ResponseEntity.notFound().build())

    @GetMapping("/post/{postId}", produces = ["application/json"])
    fun getPostVersionsByPostId(
        @PathVariable postId: Long,
    ): ResponseEntity<List<PostVersion>> {
        val postVersions = postVersionRepository.findByPostId(postId)
        return if (postVersions.isNotEmpty()) {
            ResponseEntity.ok(postVersions)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
