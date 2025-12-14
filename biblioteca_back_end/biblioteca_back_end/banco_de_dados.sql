CREATE DATABASE IF NOT EXISTS biblioteca
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE biblioteca;

-- ======================
-- TABELA CLIENTE
-- ======================
CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20) NOT NULL
);

-- ======================
-- TABELA LIVRO
-- ======================
CREATE TABLE IF NOT EXISTS livro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL
);

-- ======================
-- TABELA EMPRESTIMO
-- ======================
CREATE TABLE IF NOT EXISTS emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_livro INT NOT NULL,
    data_emprestimo DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_devolucao DATETIME NULL,

    CONSTRAINT fk_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente(id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_livro
        FOREIGN KEY (id_livro)
);