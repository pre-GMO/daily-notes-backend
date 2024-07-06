package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.model.User
import com.preGMO.dailyNotes.type.UserDTO

interface AdditionalUserRepository {
    fun createUser(user: UserDTO): User

    fun updateUser(user: User): User

    fun deleteUser(user: User)
}
