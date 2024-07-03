package com.pre_gmo.daily_notes.repository

import com.pre_gmo.daily_notes.UserDTO
import com.pre_gmo.daily_notes.model.User

interface AdditionalUserRepository {
    fun createUser(user: UserDTO): User

    fun updateUser(user: User): User

    fun deleteUser(id: User)
}
