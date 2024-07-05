package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.model.Post
import com.pre_gmo.daily_notes.repository.PostRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postRepository: PostRepository) {

    @GetMapping
    fun getAllPosts(): List<Post> = postRepository.findAll()

    @PostMapping
    fun createPost(@RequestBody post: Post): Post = postRepository.save(post)

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<Post> =
        postRepository.findById(id).map { post ->
            ResponseEntity.ok(post)
        }.orElse(ResponseEntity.notFound().build())

    @GetMapping("/user/{userId}")
    fun getPostsByUserId(@PathVariable userId: Long): ResponseEntity<List<Post>> {
        val posts = postRepository.findByUserId(userId)
        return if (posts.isNotEmpty()) {
            ResponseEntity.ok(posts)
        } else {
            ResponseEntity.noContent().build()
        }
    }
}
