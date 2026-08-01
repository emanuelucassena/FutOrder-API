-- V1: schema inicial do FutOrder, espelhando as entidades JPA existentes.
-- Ordem importa: tabelas sem FK primeiro, depois as que dependem delas.


CREATE TABLE usuarios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    papel VARCHAR(20) NOT NULL,
    /*
    diz: "crie uma constraint chamada uk_usuarios_email, do tipo UNIQUE, na coluna email".
    Ela garante que o banco nunca vai aceitar dois usuarios com o mesmo e-mail — se você tentar inserir um duplicado,
    o banco rejeita com erro, mesmo que sua aplicação (por bug) deixe passar.
    */
    CONSTRAINT uk_usuarios_email UNIQUE (email)
);

CREATE TABLE camisas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time VARCHAR(100) NOT NULL,
    temporada VARCHAR(20) NOT NULL,
    versao VARCHAR(20) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    qtd_estoque INT NOT NULL
);

CREATE TABLE pedidos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_pedido DATETIME NOT NULL,
    status_pedido VARCHAR(20) NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    usuario_id BIGINT,
    CONSTRAINT fk_pedidos_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
     -- FK: garante que todo pedido.usuario_id exista em usuarios.id.
     -- Impede inserir pedido com usuario inexistente e impede apagar
     -- um usuario que ainda tenha pedidos (RESTRICT é o padrao do MySQL
     -- quando ON DELETE nao e especificado).

);

CREATE TABLE itens_pedidos(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tamanho VARCHAR(5) NOT NULL,
    nome_personalizacao VARCHAR(255),
    numero_personalizacao INT,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL, -- Corrigido de preco_unitario(10,2) DECIMAL para DECIMAL(10,2)
    pedido_id BIGINT NOT NULL,
    camisa_id BIGINT NOT NULL,
    CONSTRAINT fk_itens_pedidos FOREIGN KEY (pedido_id) REFERENCES pedidos (id),
    CONSTRAINT fk_itens_camisa FOREIGN KEY (camisa_id) REFERENCES camisas (id)
);