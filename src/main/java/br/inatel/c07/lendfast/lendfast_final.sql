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
	endereco VARCHAR(60),
    cod_conta int,
    constraint fk4
    foreign key(cod_conta) references Conta(codigo)
);

create table Telefone(
	usuario_CPF char(11),
    telefone char(11),
	primary key(usuario_CPF),
    constraint fk1
    foreign key(usuario_CPF) references Usuario(CPF)
);

create table Email(
	cod_conta int,
    nome VARCHAR(255),
    primary key(cod_conta),
    constraint fk2
    foreign key(cod_conta) references Conta(codigo)
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

