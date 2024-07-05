package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.UserDTO
import com.preGMO.dailyNotes.model.User

interface AdditionalUserRepository {
    fun createUser(user: UserDTO): User

    fun updateUser(user: User): User

    fun deleteUser(id: User)
}
