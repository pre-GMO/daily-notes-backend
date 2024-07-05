package com.pre_gmo.daily_notes.service

import com.pre_gmo.daily_notes.UserDTO
import com.pre_gmo.daily_notes.model.User
import com.pre_gmo.daily_notes.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Long) = userRepository.findById(id)

    fun create(user: UserDTO) = userRepository.createUser(user)

    fun update(user: User) = userRepository.updateUser(user)

    fun delete(user: User) = userRepository.deleteUser(user)
}
