package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :
    JpaRepository<User, Long>,
    AdditionalUserRepository
