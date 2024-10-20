--liquibase formatted sql

-- changeset lucas:1
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(255) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    account_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at TIMESTAMP NULL
);

-- changeset lucas:2
CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR(255) NOT NULL UNIQUE,
    branch VARCHAR(255) NOT NULL,
    balance DECIMAL(19, 2) NOT NULL,
    user_id BIGSERIAL NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- changeset lucas:3
CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    creditor_id BIGINT NOT NULL,
    debtor_id BIGINT NOT NULL,
    transaction_identification UUID NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(255) NOT NULL,
    FOREIGN KEY (creditor_id) REFERENCES users(id),
    FOREIGN KEY (debtor_id) REFERENCES users(id)
);
CREATE INDEX idx_transaction_identification ON transactions(transaction_identification);
