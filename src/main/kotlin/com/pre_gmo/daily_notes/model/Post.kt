package com.pre_gmo.daily_notes.model

import java.time.OffsetDateTime
import jakarta.persistence.*

@Entity
@Table(name="posts")
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val content: String,
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,
    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
)
