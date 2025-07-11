CREATE TABLE complaints (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    status VARCHAR(255) DEFAULT 'pendente',
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_complaints_user FOREIGN KEY (user_id) REFERENCES users(id)
);
