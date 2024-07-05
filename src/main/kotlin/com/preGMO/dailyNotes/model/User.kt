package com.pre_gmo.daily_notes.model

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "users") // userは予約語なため
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @Column(unique = true)
    val email: String,
    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
)
