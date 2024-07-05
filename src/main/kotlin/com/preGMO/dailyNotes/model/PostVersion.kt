package com.pre_gmo.daily_notes.model

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(
    name = "post_versions",
    uniqueConstraints = [UniqueConstraint(columnNames = ["post_id", "version_number"])],
)
data class PostVersion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "post_id", referencedColumnName = "id")
    val post: Post,
    val content: String,
    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "updated_at", nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
)
