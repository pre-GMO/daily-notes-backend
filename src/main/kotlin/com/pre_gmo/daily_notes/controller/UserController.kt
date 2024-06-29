package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.model.User
import com.pre_gmo.daily_notes.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): List<User> = userRepository.findAll()

    @PostMapping
    fun createUser(@RequestBody user: User): User = userRepository.save(user)

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> =
        userRepository.findById(id).map { user ->
            ResponseEntity.ok(user)
        }.orElse(ResponseEntity.notFound().build())

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): ResponseEntity<User> =
        userRepository.findById(id).map { existingUser ->
            val updated: User = existingUser.copy(name = updatedUser.name, email = updatedUser.email)
            ResponseEntity.ok().body(userRepository.save(updated))
        }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> =
        userRepository.findById(id).map { user ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
}