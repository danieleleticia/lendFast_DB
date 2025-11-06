DROP DATABASE IF EXISTS AV1;
CREATE DATABASE AV1;
USE AV1;

create table Conta(
	codigo int NOT null primary key,
    senha VARCHAR(30)
);

create table Usuario(
	CPF char(11) NOT NULL PRIMARY KEY,
	nome VARCHAR(100),
	dataNasc date,
	endereço VARCHAR(60),
    Conta_codigo int,
    constraint fk4
    foreign key(Conta_codigo) references Conta(codigo)
);

create table Telefone(
	usuario_CPF char(11),
    telefone char(11),
	primary key(usuario_CPF),
    constraint fk1
    foreign key(usuario_CPF) references Usuario(CPF)
);

create table Email(
	Conta_codigo int,
    nome VARCHAR(255),
    primary key(Conta_codigo),
    constraint fk2
    foreign key(Conta_codigo) references Conta(codigo)
);

CREATE TABLE Suporte (
	protocolo INT NOT NULL PRIMARY KEY,
    status ENUM('Aberto', 'Em Andamento', 'Aguardando Cliente', 'Fechado') 
);

create table TipoProblema(
	suporte_protocolo int,
    setor VARCHAR(30),
    primary key(suporte_protocolo),
    constraint fk3
    foreign key(suporte_protocolo) references Suporte(protocolo)
);

create table Usuario_has_Suporte(
    Usuario_CPF CHAR(11),
    Suporte_protocolo INT,
    primary key (Usuario_CPF, Suporte_protocolo),
    constraint fk10
        foreign key (Usuario_CPF) references Usuario(CPF),
    constraint fk11
        foreign key (Suporte_protocolo) references Suporte(protocolo)
);

create table Locador(
	usuario_CPF char(11),
    reputacao decimal(3,2),
    primary key(usuario_CPF),
    constraint fk5
    foreign key (usuario_CPF) references Usuario(CPF)
);

create table Locatorio(
	usuario_CPF char (11),
    primary key (usuario_CPF),
    constraint fk6
    foreign key (usuario_CPF) references Usuario(CPF)
);

create table Produto(
	codigo int,
    nome varchar (30),
    nicho varchar (30),
    descricao longtext,
    locador_usuario_cpf char(11),
    primary key (codigo),
    constraint fk7
    foreign key(locador_usuario_cpf) references Locador(usuario_CPF)
);

create table Aluguel(
	codigo int,
    fase ENUM('Aberto', 'Em Andamento', 'Aguardando Cliente', 'Fechado'),
    contrato varchar(255),
    periodoAluguel int,
    produto_codigo int,
    produto_locador_usuario_cpf char(11),
    primary key (codigo),
    constraint fk8
        foreign key (produto_codigo) references Produto(codigo),
    constraint fk9
        foreign key (produto_locador_usuario_cpf) references Produto(locador_usuario_cpf)
);

create table Aluguel_has_Locatorio(
    Aluguel_codigo INT,
    Locatorio_Usuario_CPF CHAR(11),
    primary key (Aluguel_codigo, Locatorio_Usuario_CPF),
    constraint fk12
        foreign key (Aluguel_codigo) references Aluguel(codigo),
    constraint fk13
        foreign key (Locatorio_Usuario_CPF) references Locatorio(usuario_CPF)
);

create table Pagamento(
	id_pagamento int,
    metodoDePagamento varchar(30),
    valor decimal(10,2),
    fase ENUM('Aberto', 'Em Andamento', 'Aguardando Cliente', 'Fechado'),
    aluguel_codigo int,
    primary key (id_pagamento),
    constraint fk14
        foreign key (aluguel_codigo) references Aluguel(codigo)
);


-- ===========================
-- INSERÇÕES
-- ===========================

-- Conta
INSERT INTO Conta (codigo, senha) VALUES
(1, 'senha123'),
(2, 'abc456'),
(3, 'minhasenha');

-- Usuario
INSERT INTO Usuario (CPF, nome, dataNasc, endereço, Conta_codigo) VALUES
('11111111111', 'João Silva', '1990-05-10', 'Rua A, 100', 1),
('22222222222', 'Maria Souza', '1988-07-22', 'Rua B, 200', 2),
('33333333333', 'Carlos Lima', '1995-02-17', 'Rua C, 300', 3);

-- Telefone
INSERT INTO Telefone (usuario_CPF, telefone) VALUES
('11111111111', '11999999999'),
('22222222222', '11988888888'),
('33333333333', '11977777777');

-- Email
INSERT INTO Email (Conta_codigo, nome) VALUES
(1, 'joao@email.com'),
(2, 'maria@email.com'),
(3, 'carlos@email.com');

-- Suporte
INSERT INTO Suporte (protocolo, status) VALUES
(101, 'Aberto'),
(102, 'Em Andamento'),
(103, 'Fechado');

-- TipoProblema
INSERT INTO TipoProblema (suporte_protocolo, setor) VALUES
(101, 'Financeiro'),
(102, 'Técnico'),
(103, 'Entrega');

-- Usuario_has_Suporte
INSERT INTO Usuario_has_Suporte (Usuario_CPF, Suporte_protocolo) VALUES
('11111111111', 101),
('22222222222', 102),
('33333333333', 103);

-- Locador
INSERT INTO Locador (usuario_CPF, reputacao) VALUES
('11111111111', 4.8),
('22222222222', 4.5),
('33333333333', 4.2);

-- Locatorio
INSERT INTO Locatorio (usuario_CPF) VALUES
('11111111111'),
('22222222222'),
('33333333333');

-- Produto
INSERT INTO Produto (codigo, nome, nicho, descricao, locador_usuario_cpf) VALUES
(10, 'Furadeira', 'Ferramentas', 'Furadeira elétrica profissional', '11111111111'),
(20, 'Carrinho de Bebê', 'Infantil', 'Carrinho dobrável leve', '22222222222'),
(30, 'Mala de Viagem', 'Acessórios', 'Mala média com rodinhas', '33333333333');

-- Aluguel
INSERT INTO Aluguel (codigo, fase, contrato, periodoAluguel, produto_codigo, produto_locador_usuario_cpf) VALUES
(1001, 'Aberto', 'Contrato A', 7, 10, '11111111111'),
(1002, 'Em Andamento', 'Contrato B', 10, 20, '22222222222'),
(1003, 'Fechado', 'Contrato C', 5, 30, '33333333333');

-- Aluguel_has_Locatorio
INSERT INTO Aluguel_has_Locatorio (Aluguel_codigo, Locatorio_Usuario_CPF) VALUES
(1001, '11111111111'),
(1002, '22222222222'),
(1003, '33333333333');

-- Pagamento
INSERT INTO Pagamento (id_pagamento, metodoDePagamento, valor, fase, aluguel_codigo) VALUES
(1, 'Cartão', 120.00, 'Fechado', 1001),
(2, 'Pix', 200.00, 'Aberto', 1002),
(3, 'Dinheiro', 75.00, 'Em Andamento', 1003);

SELECT * FROM Conta;
SELECT * FROM Usuario;
SELECT * FROM Telefone;
SELECT * FROM Email;
SELECT * FROM Suporte;
SELECT * FROM TipoProblema;
SELECT * FROM Usuario_has_Suporte;
SELECT * FROM Locador;
SELECT * FROM Locatorio;
SELECT * FROM Produto;
SELECT * FROM Aluguel;
SELECT * FROM Aluguel_has_Locatorio;
SELECT * FROM Pagamento;

-- UPDATES/DELETES TOTAIS

-- UPDATE: todos os alugueis encerrados
UPDATE Aluguel
SET fase = 'Fechado';

-- DELETE: limpar todo o histórico de suporte
DELETE FROM TipoProblema;
DELETE FROM Usuario_has_Suporte;
DELETE FROM Suporte;


-- ===========================
-- ALTER e DROP

-- Adicionar a data de cadastro ao usuário
ALTER TABLE Usuario
ADD COLUMN dataCadastro DATE;

-- Excluir a tabela de associação de suporte
DROP TABLE Usuario_has_Suporte;


-- ===========================
-- CRIAÇÃO DE USUÁRIO DE SGBD

CREATE USER 'devlocacao'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON AV1.* TO 'devlocacao'@'localhost';
FLUSH PRIVILEGES;


-- ===========================
-- FUNÇÃO
DELIMITER //
CREATE FUNCTION calcularValorTotal(valor_dia DECIMAL(10,2), dias INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    RETURN valor_dia * dias;
END //
DELIMITER ;

-- ===========================
-- PROCEDURE
DELIMITER //
CREATE PROCEDURE registrarPagamento(
    IN p_aluguel_codigo INT,
    IN p_metodo VARCHAR(30),
    IN p_valor DECIMAL(10,2)
)
BEGIN
    INSERT INTO Pagamento (id_pagamento, metodoDePagamento, valor, fase, aluguel_codigo)
    VALUES (NULL, p_metodo, p_valor, 'Fechado', p_aluguel_codigo);

    UPDATE Aluguel
    SET fase = 'Fechado'
    WHERE codigo = p_aluguel_codigo;
END //
DELIMITER ;

-- ===========================
-- VIEW
CREATE VIEW vw_alugueis_detalhados AS
SELECT 
    A.codigo AS codigo_aluguel,
    A.fase AS status_aluguel,
    A.periodoAluguel,
    P.nome AS produto,
    L.usuario_CPF AS locador,
    U.nome AS nome_locador
FROM Aluguel A
JOIN Produto P ON A.produto_codigo = P.codigo
JOIN Locador L ON P.locador_usuario_cpf = L.usuario_CPF
JOIN Usuario U ON L.usuario_CPF = U.CPF;


