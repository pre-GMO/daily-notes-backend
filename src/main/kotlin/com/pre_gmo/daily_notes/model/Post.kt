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
    @Column(name = "created_at")
    var createdAt: OffsetDateTime,
    @Column(name = "updated_at")
    var updatedAt: OffsetDateTime
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