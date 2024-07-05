package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.model.PostVersion
import com.pre_gmo.daily_notes.repository.PostVersionRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post-version")
class PostVersionController(private val postVersionRepository: PostVersionRepository) {

    @GetMapping
    fun getAllPostVersions(): List<PostVersion> = postVersionRepository.findAll()

    @GetMapping("/{id}")
    fun getPostVersionById(@PathVariable id: Long): ResponseEntity<PostVersion> =
        postVersionRepository.findById(id).map { post ->
            ResponseEntity.ok(post)
        }.orElse(ResponseEntity.notFound().build())

    @GetMapping("/post/{postId}")
    fun getPostVersionsByPostId(@PathVariable postId: Long): ResponseEntity<List<PostVersion>> {
        val postVersions = postVersionRepository.findByPostId(postId)
        return if (postVersions.isNotEmpty()) {
            ResponseEntity.ok(postVersions)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
