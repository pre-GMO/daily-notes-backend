package com.preGMO.dailyNotes.repository

import com.preGMO.dailyNotes.UserDTO
import com.preGMO.dailyNotes.model.User
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
class AdditionalUserRepositoryImpl : AdditionalUserRepository {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Transactional
    override fun createUser(user: UserDTO): User {
        entityManager
            .createNativeQuery("INSERT INTO users (name, email) VALUES (?, ?)", User::class.java)
            .setParameter(1, user.name)
            .setParameter(2, user.email)
            .executeUpdate()
        val query = entityManager.createNativeQuery("SELECT * FROM users WHERE email = ?", User::class.java)
        query.setParameter(1, user.email)
        return query.singleResult as User
    }

    @Transactional
    override fun updateUser(user: User): User {
        entityManager
            .createNativeQuery("UPDATE users SET name = ?, email = ? WHERE id = ?;", User::class.java)
            .setParameter(1, user.name)
            .setParameter(2, user.email)
            .setParameter(3, user.id)
            .executeUpdate()
        val query = entityManager.createNativeQuery("SELECT * FROM users WHERE id = ?", User::class.java)
        query.setParameter(1, user.id)
        return query.singleResult as User
    }

    @Transactional
    override fun deleteUser(user: User) {
        entityManager
            .createNativeQuery("DELETE FROM users WHERE id = ?;", User::class.java)
            .setParameter(1, user.id)
            .executeUpdate()
    }
}
