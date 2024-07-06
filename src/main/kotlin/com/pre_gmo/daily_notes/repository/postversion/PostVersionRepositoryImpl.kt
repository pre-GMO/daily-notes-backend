package com.pre_gmo.daily_notes.repository.postversion

import com.pre_gmo.daily_notes.dto.CreatePostVersionDTO
import com.pre_gmo.daily_notes.dto.PostVersionDTO
import org.springframework.stereotype.Repository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext


@Repository
class PostVersionRepositoryImpl : PostVersionRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findByPostId(postId: Long): List<PostVersionDTO> {
        val sql = """
            SELECT id, post_id, content, version_number
            FROM post_versions
            WHERE (:postId)
        """.trimIndent()

        val query = entityManager.createNativeQuery(sql)
        query.setParameter("postId", postId)
        val result = query.resultList
        if (result.isEmpty()) return emptyList()
        @Suppress("UNCHECKED_CAST")
        return (query.resultList as List<Array<Any>>).map { row ->
            PostVersionDTO(
                id = (row[0] as Number).toLong(),
                postId = (row[1] as Number).toLong(),
                content = row[2] as String,
                versionNumber = (row[3] as Number).toInt()
            )
        }
    }

    override fun findLatestVersionByPostId(postId: Long): List<PostVersionDTO> {
        val sql = """
            SELECT id, post_id, content, version_number
            FROM post_versions
            WHERE post_id = :postId
            ORDER BY version_number DESC
            LIMIT 1
        """.trimIndent()

        val query = entityManager.createNativeQuery(sql)
        query.setParameter("postId", postId)
        val result = query.resultList

        if (result.isEmpty()) return emptyList()

        @Suppress("UNCHECKED_CAST")
        return (query.resultList as List<Array<Any>>).map { row ->
            PostVersionDTO(
                id = (row[0] as Number).toLong(),
                postId = (row[1] as Number).toLong(),
                content = row[2] as String,
                versionNumber = (row[3] as Number).toInt()
            )
        }
    }

    override fun findLatestVersionNumberByPostId(postId: Long): Int {
        val sql = """
            SELECT version_number
            FROM post_versions
            WHERE post_id = :postId
            ORDER BY version_number DESC
            LIMIT 1
        """.trimIndent()

        val query = entityManager.createNativeQuery(sql)
        query.setParameter("postId", postId)
        val result = query.resultList

        // FIXME: ここもマジックナンバーを消して、型実装したい
        if (result.isEmpty()) return -1
        return (result[0] as Number).toInt()
    }

    override fun createByPostId(createPostVersionDto: CreatePostVersionDTO) {
        val sql = """
            INSERT INTO post_versions (post_id, content, version_number)
            VALUES (:postId, :content, :versionNumber)
        """.trimIndent()

        val query = entityManager.createNativeQuery(sql)
        query.setParameter("postId", createPostVersionDto.postId)
        query.setParameter("content", createPostVersionDto.content)
        query.setParameter("versionNumber", createPostVersionDto.nextVersionNumber)
        query.executeUpdate()
    }
}
