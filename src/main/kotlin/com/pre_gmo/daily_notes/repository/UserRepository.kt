package com.pre_gmo.daily_notes.repository

import com.pre_gmo.daily_notes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>