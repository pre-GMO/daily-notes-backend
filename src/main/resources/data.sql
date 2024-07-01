-- FIXME: このテーブル定義は削除したいけど、削除すると動かない... migrate処理どうするのだ?
-- FIXME: H2 database は複数分のinsertに非対応

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE post_versions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    version_number INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (post_id, version_number),
    FOREIGN KEY (post_id) REFERENCES posts(id)
);

INSERT INTO users (name, email) VALUES ('Taro Okamoto', 'taro@example.com');
INSERT INTO users (name, email) VALUES ('Yayoi Kusama', 'yayoi@example.com');
INSERT INTO users (name, email) VALUES ('Hiroshi Sugimoto', 'hiroshi@example.com');
INSERT INTO users (name, email) VALUES ('Chiharu Shiota', 'chiharu@example.com');
INSERT INTO users (name, email) VALUES ('Yoshitomo Nara', 'yoshitomo@example.com');
INSERT INTO users (name, email) VALUES ('Takashi Murakami', 'takashi@example.com');

INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Okamoto''s first post.', 1, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Kusama''s first post.', 2, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Sugimoto''s first post.', 3, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Shiota''s first post.', 4, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Nara''s first post.', 5, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Murakami''s first post.', 6, '2024-06-28T00:00:00+00:00', '2024-06-28T00:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Okamoto''s second post.', 1, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Kusama''s second post.', 2, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Sugimoto''s second post.', 3, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Shiota''s second post.', 4, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Nara''s second post.', 5, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('This is Murakami''s second post.', 6, '2024-06-28T01:00:00+00:00', '2024-06-28T01:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Okamoto shares an interesting article.', 1, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Kusama shares an interesting article.', 2, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Sugimoto shares an interesting article.', 3, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Shiota shares an interesting article.', 4, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Nara shares an interesting article.', 5, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Murakami shares an interesting article.', 6, '2024-06-28T02:00:00+00:00', '2024-06-28T02:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Okamoto talks about her day.', 1, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Kusama talks about his day.', 2, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Sugimoto talks about his day.', 3, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Shiota talks about her day.', 4, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Nara talks about his day.', 5, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');
INSERT INTO posts (content, user_id, created_at, updated_at) VALUES ('Murakami talks about his day.', 6, '2024-06-28T03:00:00+00:00', '2024-06-28T03:00:00+00:00');

INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (1, 'First version of the post', 1, '2024-06-28T10:00:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (1, 'Second version of the post', 2, '2024-06-28T10:10:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (1, 'Third version of the post', 3, '2024-06-28T10:20:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (2, 'First version of another post', 1, '2024-06-28T10:30:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (2, 'Second version of another post', 2, '2024-06-28T10:40:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (3, 'First version of a third post', 1, '2024-06-28T10:50:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (3, 'Second version of a third post', 2, '2024-06-28T11:00:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (3, 'Third version of a third post', 3, '2024-06-28T11:10:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (4, 'First version of a fourth post', 1, '2024-06-28T11:20:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (4, 'Second version of a fourth post', 2, '2024-06-28T11:30:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (4, 'Third version of a fourth post', 3, '2024-06-28T11:40:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (5, 'First version of a fifth post', 1, '2024-06-28T11:50:00Z');
INSERT INTO post_versions (post_id, content, version_number, created_at) VALUES (5, 'Second version of a fifth post', 2, '2024-06-28T12:00:00Z');
