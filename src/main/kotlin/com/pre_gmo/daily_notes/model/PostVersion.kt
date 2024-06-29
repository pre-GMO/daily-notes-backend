package com.pre_gmo.daily_notes.model

import java.time.OffsetDateTime
import jakarta.persistence.*

@Entity
@Table(
    name="post_versions",
    uniqueConstraints = [UniqueConstraint(columnNames = ["post_id", "version_number"])]
)
data class PostVersion(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne @JoinColumn(name = "post_id", referencedColumnName = "id")
    val post: Post,
    val content: String,
    @Column(name = "version_number")
    var versionNumber: Int,
    @Column(name = "created_at")
    var createdAt: OffsetDateTime
)