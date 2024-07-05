package com.preGMO.dailyNotes.controller

import com.preGMO.dailyNotes.model.Post
import com.preGMO.dailyNotes.repository.PostRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(
    private val postRepository: PostRepository,
) {
    @GetMapping(produces = ["application/json"])
    fun getAllPosts(): List<Post> = postRepository.findAll()

    @PostMapping(produces = ["application/json"])
    fun createPost(
        @RequestBody post: Post,
    ): Post = postRepository.save(post)

    @GetMapping("/{id}", produces = ["application/json"])
    fun getPostById(
        @PathVariable id: Long,
    ): ResponseEntity<Post> =
        postRepository
            .findById(id)
            .map { post ->
                ResponseEntity.ok(post)
            }.orElse(ResponseEntity.notFound().build())

    @GetMapping("/user/{userId}", produces = ["application/json"])
    fun getPostsByUserId(
        @PathVariable userId: Long,
    ): ResponseEntity<List<Post>> {
        val posts = postRepository.findByUserId(userId)
        return if (posts.isNotEmpty()) {
            ResponseEntity.ok(posts)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
