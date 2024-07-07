package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.dto.CreateRequestPostVersionDTO
import com.pre_gmo.daily_notes.dto.PostVersionDTO
import com.pre_gmo.daily_notes.service.postversion.PostVersionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class DefaultResponse(
    val isSuccess: Boolean,
    val message: String
)

// TODO: とりあえず正常系のみを実装
@RestController
@RequestMapping("/api/post-version")
class PostVersionController(private val postVersionService: PostVersionService) {

    // TODO: 正常系のみで一度あげる
    @GetMapping("/{postId}")
    fun getPostVersionById(@PathVariable postId: Long): ResponseEntity<List<PostVersionDTO>> {
        val result = postVersionService.getPostVersionsByPostId(postId)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/latest/{postId}")
    fun getLatestPostVersionById(@PathVariable postId: Long): ResponseEntity<PostVersionDTO> {
        val (postVersion, isSuccess) = postVersionService.getLatestPostVersionByPostId(postId)
        if (isSuccess) {
            return ResponseEntity.ok(postVersion)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postVersion)
        }
    }

    @PostMapping("/post/{postId}", produces = ["application/json"])
    fun createPostVersionByPostId(
        @PathVariable postId: Long,
        @RequestBody requestBody: CreateRequestPostVersionDTO
    ): ResponseEntity<DefaultResponse> {
        val result = postVersionService.createPostVersions(requestBody)
        // TODO: どこの段階でtry catchを置くか検討中　(個人的には細かく置いてどこでこけたかを追えるようにログを置きたい)
        // とりあえす正常系だけ実装
//        val response = if(result) DefaultResponse(true, "success")
//            else DefaultResponse(false, "failure")
        val response = DefaultResponse(true, "success")
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}