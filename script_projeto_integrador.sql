DROP DATABASE IF EXISTS projeto_integrador;
CREATE DATABASE projeto_integrador;
USE projeto_integrador;


-- Tabela para armazenar usuários
CREATE TABLE usuario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL
);

-- Tabela para armazenar tarefas de cada usuário
CREATE TABLE tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    descricao VARCHAR(255) NOT NULL,
    concluida BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);


CREATE TABLE checkbox (
	id INT AUTO_INCREMENT PRIMARY KEY,
    tarefa_id INT,
    checkbox VARCHAR(255),
    marcada BOOLEAN DEFAULT FALSE,	
    FOREIGN KEY (tarefa_id) REFERENCES tarefas(id)
    ON DELETE CASCADE
);

INSERT INTO usuario (id,login)
VALUES (1,"Usuario 1"),
(2,"Usuario 2"),
(3,"Usuario 3");

SELECT * FROM usuario;

INSERT INTO tarefas (id,usuario_id,descricao,concluida)
VALUES (1,1,"Estudar",FALSE),
(2,1,"Lavar Louça",TRUE),
(3,2,"Ir ao Mercado",FALSE),
(4,3,"Dar banho no cachorro",TRUE),
(5,3,"Comprar Comida",FALSE);

SELECT * FROM tarefas;

INSERT INTO checkbox (id,tarefa_id,checkbox,marcada)
VALUES (1,1,"História",TRUE),
(2,1,"Geografia",FALSE),
(3,1,"Português",TRUE),
(4,3,"Comprar alvejante",FALSE),
(5,3,"Comprar ração",FALSE),
(6,5,"Espaguete",FALSE),
(7,5,"Feijão",FALSE);

SELECT * FROM checkbox;
