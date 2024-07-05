package com.preGMO.dailyNotes.controller

import com.preGMO.dailyNotes.UserDTO
import com.preGMO.dailyNotes.model.User
import com.preGMO.dailyNotes.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user", produces = ["application/json"])
class UserController(
    private val userService: UserService,
) {
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> =
        ResponseEntity
            .ok(userService.findAll())

    @GetMapping("/{id}", produces = ["application/json"])
    fun getUserById(
        @PathVariable id: Long,
    ): ResponseEntity<User> =
        userService
            .findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())

    @PostMapping(produces = ["application/json"])
    fun createUser(
        @RequestBody user: UserDTO,
    ): ResponseEntity<User> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.create((user)))

    @PutMapping("/{id}", produces = ["application/json"])
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody updatedUser: UserDTO,
    ): ResponseEntity<User> =
        userService
            .findById(id)
            .map { existingUser ->
                ResponseEntity.ok(userService.update(existingUser.copy(name = updatedUser.name, email = updatedUser.email)))
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long,
    ): ResponseEntity<Void> =
        userService
            .findById(id)
            .map { user ->
                userService.delete(user)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
}
