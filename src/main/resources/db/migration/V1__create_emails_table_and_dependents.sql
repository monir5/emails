
CREATE TABLE emails (
    "id" BIGINT PRIMARY KEY,
    "from" VARCHAR(100) NOT NULL,
    "subject" VARCHAR(500),
    "body" VARCHAR(5000),
    "state" SMALLINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE email_address_to (
    "email_id" BIGINT,
    "email" VARCHAR(100) NOT NULL
);

CREATE TABLE email_address_cc (
    "email_id" BIGINT,
    "email" VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS email_id_sequence as BIGINT INCREMENT BY 1 START 1 OWNED BY emails.id;