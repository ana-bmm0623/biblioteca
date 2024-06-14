-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;
-- Inserção de Autor
INSERT INTO
    autor (nome, nacionalidade, dataNascimento, biografia)
VALUES
    (
        'J.K. Rowling',
        'Britânica',
        '1965-07-31',
        'Joanne Rowling, mais conhecida como J.K. Rowling, é uma escritora britânica, autora da série de livros Harry Potter.'
    ),
    (
        'George R.R. Martin',
        'Americano',
        '1948-09-20',
        'George Raymond Richard Martin, conhecido como George R. R. Martin, é um roteirista e escritor norte-americano de ficção científica, terror e fantasia.'
    ),
    (
        'Stephen King',
        'Americano',
        '1947-09-21',
        'Stephen Edwin King é um escritor norte-americano de contos de terror, ficção sobrenatural, suspense, ficção científica e fantasia.'
    );

-- Inserção de Categoria
INSERT INTO
    categoria (nome)
VALUES
    ('Ficção'),
    ('Não-ficção'),
    ('Terror'),
    ('Fantasia'),
    ('Ficção Científica');

-- Inserção de Editora
INSERT INTO
    editora (nome, cnpj, endereco, telefone, email)
VALUES
    (
        'Rocco',
        '12.345.678/0001-90',
        'Rua Rodrigo Silva, 26-5º andar - Centro, Rio de Janeiro - RJ',
        '(21) 3525-2000',
        'rocco@rocco.com.br'
    ),
    (
        'Companhia das Letras',
        '98.765.432/0001-01',
        'Rua Bandeira Paulista, 702 - cj. 32 - Itaim Bibi, São Paulo - SP',
        '(11) 3707-3500',
        'contato@companhiadasletras.com.br'
    ),
    (
        'Intrínseca',
        '56.789.012/0001-34',
        'Rua Marquês de São Vicente, 99 - 6º andar - Gávea, Rio de Janeiro - RJ',
        '(21) 3206-7400',
        'contato@intrinseca.com.br'
    );

-- Inserção de Livro (Assumindo que já existem autores, editoras e categorias no banco)
INSERT INTO
    livro (
        titulo,
        isbn,
        anoPublicacao,
        edicao,
        quantidadeDisponivel,
        quantidadeTotal,
        sinopse,
        autor_id,
        editora_id
    )
VALUES
    (
        'Harry Potter e a Pedra Filosofal',
        '9788532511010',
        1997,
        1,
        5,
        10,
        'Harry Potter é um garoto órfão que vive infeliz com seus tios, os Dursleys.',
        1,
        1
    ),
    (
        'A Guerra dos Tronos',
        '9788533613469',
        1996,
        1,
        3,
        8,
        'Em um mundo onde verões se estendem por décadas e o inverno pode durar uma vida inteira.',
        2,
        2
    ),
    (
        'O Iluminado',
        '9788532511010',
        1977,
        1,
        2,
        5,
        'Jack Torrance se torna zelador de inverno de um hotel isolado nas montanhas do Colorado.',
        3,
        3
    );

-- Correção dos valores dos enums
INSERT INTO usuario (nome, email, senha, tipoUsuario, dataCadastro, status, endereco, telefone) VALUES
    ('João Silva', 'joao.silva@email.com', 'senha_criptografada', 1, '2024-01-15', 1, 'Rua A, 123', '(63) 98765-4321'),
    ('Maria Souza', 'maria.souza@email.com', 'outra_senha_criptografada', 2, '2023-12-05', 1, 'Rua B, 456', '(63) 91234-5678');

-- Inserção de Empréstimo (Assumindo que já existem usuários e livros no banco)
INSERT INTO
    emprestimo (
        usuario_id,
        livro_id,
        dataEmprestimo,
        dataDevolucao
    )
VALUES
    (1, 1, '2024-06-10', '2024-06-24'),
    (2, 2, '2024-06-05', '2024-06-19'),
    (1, 3, '2024-05-28', '2024-06-11');

-- Inserção de Livro_Categoria (relacionamento muitos-para-muitos)
INSERT INTO
    livro_categoria (livro_id, categoria_id)
VALUES
    (1, 1),
    (1, 4),
    (2, 1),
    (2, 4),
    (2, 5),
    (3, 3);