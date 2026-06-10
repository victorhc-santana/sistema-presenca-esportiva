-- Cria o banco se não existir
CREATE DATABASE IF NOT EXISTS presenca_esportiva;

USE presenca_esportiva;

-- Cria a tabela aluno
CREATE TABLE IF NOT EXISTS aluno (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100)    NOT NULL,
    cpf         VARCHAR(14)     NOT NULL,
    email       VARCHAR(100),
    telefone    VARCHAR(20),
    nascimento  DATE,
    endereco    VARCHAR(200),
    modalidade  VARCHAR(50)
);

-- Cria a tabela turma
CREATE TABLE IF NOT EXISTS turma (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100)    NOT NULL,
    modalidade  VARCHAR(100)    NOT NULL,
    professor   VARCHAR(100)    NOT NULL,
    horario     VARCHAR(20),
    dia_semana  VARCHAR(20),
    vagas_total BIGINT,
    nivel       VARCHAR(20)
);

-- Cria a tabela presenca
CREATE TABLE IF NOT EXISTS presenca (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    aluno_id    BIGINT          NOT NULL,
    turma_id    BIGINT          NOT NULL,
    turma_nome  VARCHAR(100)    NOT NULL,
    dia         DATE            NOT NULL,
    status      BOOLEAN         NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (turma_id) REFERENCES turma(id)

);