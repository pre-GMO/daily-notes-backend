package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.model.User
import com.pre_gmo.daily_notes.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.pre_gmo.daily_notes.UserDTO

@RestController
@RequestMapping("/api/user")
class UserController(private val userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        return userRepository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<User> {
        val createdUser = userRepository.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: UserDTO): ResponseEntity<User> {
        return userRepository.findById(id)
            .map { existingUser ->
                ResponseEntity.ok(userRepository.updateUser(existingUser.copy(name = updatedUser.name, email = updatedUser.email)))
            }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return userRepository.findById(id)
            .map { user ->
                userRepository.deleteUser(user)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())
    }
}
