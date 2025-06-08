CREATE SCHEMA IF NOT EXISTS USERS;
CREATE SCHEMA IF NOT EXISTS CONTRACTS;
CREATE SCHEMA IF NOT EXISTS RECOM;

CREATE SEQUENCE work_contract_sequence
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- Creación de Tablas --

CREATE TABLE USERS.SUPPLIER_COMPANY
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    email               VARCHAR(150),
    user_name           VARCHAR(50),
    phone               VARCHAR(15),
    user_address        VARCHAR(150),
    cuit                VARCHAR(15),
    is_active           BOOLEAN,
    lat                 FLOAT,
    ln                  FLOAT,
    is_verified         BOOLEAN,
    company_description VARCHAR(250),
    avg_score           FLOAT,
    avg_price           FLOAT,
    comments_count      INT,
    company_type        INT
);

CREATE TABLE USERS.WORKER
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(150) NOT NULL,
    user_name    VARCHAR(50)  NOT NULL,
    phone        VARCHAR(15),
    user_address VARCHAR(150),
    cuit         VARCHAR(15),
    is_active    BOOLEAN      NOT NULL,
    company_id   BIGINT       NOT NULL,
    FOREIGN KEY (company_id) REFERENCES USERS.SUPPLIER_COMPANY (id)
);

CREATE TABLE USERS.APPLICANT_COMPANY
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    email               VARCHAR(150),
    user_name           VARCHAR(50),
    phone               VARCHAR(15),
    user_address        VARCHAR(150),
    cuit                VARCHAR(15),
    is_active           BOOLEAN,
    lat                 FLOAT,
    ln                  FLOAT,
    is_verified         BOOLEAN,
    company_description VARCHAR(250)
);

CREATE TABLE CONTRACTS.WORK_CONTRACT
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    code_number     VARCHAR(10) NOT NULL,
    price           FLOAT       NOT NULL,
    date_from       DATE        NOT NULL,
    date_to         DATE        NOT NULL,
    contract_state  INT,
    contract_detail VARCHAR(250),
    supplier_id     BIGINT      NOT NULL,
    applicant_id    BIGINT      NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES USERS.SUPPLIER_COMPANY (id),
    FOREIGN KEY (applicant_id) REFERENCES USERS.APPLICANT_COMPANY (id)
);

CREATE TABLE CONTRACTS.CONTRACT_WORKERS
(
    contract_id BIGINT NOT NULL,
    worker_id   BIGINT NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES CONTRACTS.WORK_CONTRACT (id),
    FOREIGN KEY (worker_id) REFERENCES USERS.WORKER (id)
);

CREATE TABLE CONTRACTS.CONTRACT_IMAGE
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    img_data    BLOB   NOT NULL,
    contract_id BIGINT NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES CONTRACTS.WORK_CONTRACT (id)
);

CREATE TABLE CONTRACTS.DELIVERY_NOTE
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    note_data    BLOB   NOT NULL,
    tx_hash      VARCHAR(100),
    data_hash    VARCHAR(100),
    block_number VARCHAR(15),
    created_at   DATE   NOT NULL,
    contract_id  BIGINT NOT NULL,
    is_signed  BOOLEAN NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES CONTRACTS.WORK_CONTRACT (id)
);
