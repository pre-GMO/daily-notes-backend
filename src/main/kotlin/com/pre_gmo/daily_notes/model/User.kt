package com.pre_gmo.daily_notes.model

import java.time.OffsetDateTime
import jakarta.persistence.*

@Entity
@Table(name = "users") // userは予約語なため
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val email: String,
    @Column(name = "created_at", updatable = false)
    var createdAt: OffsetDateTime? = null,
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        val now = OffsetDateTime.now()
        if (createdAt == null) {
            createdAt = now
        }
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = OffsetDateTime.now()
    }
}