package com.preGMO.dailyNotes.service

import com.preGMO.dailyNotes.model.User
import com.preGMO.dailyNotes.repository.UserRepository
import com.preGMO.dailyNotes.type.UserDTO
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
