CREATE TABLE comments (
    id SERIAL PRIMARY KEY,                -- Cria uma chave primária para a tabela de comentários
    product_name TEXT NOT NULL,             -- Cria a coluna para armazenar o id do produto
    user_email TEXT NOT NULL,                -- Cria a coluna para armazenar o id do usuário
    comment_text TEXT NOT NULL,           -- Coluna para armazenar o texto do comentário
    created_at TIMESTAMP DEFAULT NOW(),   -- Coluna para armazenar a data de criação do comentário
    CONSTRAINT fk_product_name
        FOREIGN KEY (product_name)          -- Define product_name como chave estrangeira
        REFERENCES product(name)            -- Referencia o name da tabela "product"
        ON DELETE CASCADE,                -- Se o produto for deletado, os comentários também serão
    CONSTRAINT fk_user_email
        FOREIGN KEY (user_email)             -- Define user_id como chave estrangeira
        REFERENCES usuario(email)            -- Referencia o id da tabela "usuario"
        ON DELETE CASCADE                 -- Se o usuário for deletado, seus comentários também serão
);
