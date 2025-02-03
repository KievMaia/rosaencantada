\connect rosaencantada;

CREATE TYPE "rosaencantada"."tipo_entrada" AS ENUM (
  'COMPRA',
  'TROCA',
  'DEVOLUCAO'
);

CREATE TYPE "rosaencantada"."tipo_saida" AS ENUM (
  'VENDA',
  'TROCA'
);

CREATE TABLE "rosaencantada"."estoque"
(
    "id"               serial PRIMARY KEY,
    "id_produto"       integer UNIQUE,
    "qtd_pecas"        decimal(10, 2) NOT NULL,
    "data_criacao"     timestamp      NOT NULL,
    "data_atualizacao" timestamp,
    "ativo"            boolean        NOT NULL
);

CREATE TABLE "rosaencantada"."produtos"
(
    "id"               SERIAL PRIMARY KEY,
    "id_categoria"     integer,
    "sku"              varchar(50)    NOT NULL,
    "nome"             varchar(150)   NOT NULL,
    "descricao"        varchar(500),
    "preco"            decimal(10, 2) NOT NULL,
    "data_criacao"     timestamp      NOT NULL,
    "data_atualizacao" timestamp,
    "ativo"            boolean        NOT NULL
);

CREATE TABLE "rosaencantada"."categorias"
(
    "id"               serial PRIMARY KEY,
    "name"             varchar(150) NOT NULL,
    "data_criacao"     timestamp    NOT NULL,
    "data_atualizacao" timestamp
);

CREATE TABLE "rosaencantada"."subcategorias"
(
    "id"               serial PRIMARY KEY,
    "id_categoria"     integer,
    "name"             varchar(150) NOT NULL,
    "data_criacao"     timestamp    NOT NULL,
    "data_atualizacao" timestamp
);

CREATE TABLE "rosaencantada"."estoque_entrada"
(
    "id"             serial PRIMARY KEY,
    "id_estoque"     integer,
    "id_fornecedor"  integer,
    "quantidade"     decimal(10, 2)             NOT NULL,
    "preco_unitario" decimal(10, 2)             NOT NULL,
    "tipo_entrada"   rosaencantada.tipo_entrada NOT NULL,
    "data_entrada"   timestamp                  NOT NULL
);

CREATE TABLE "rosaencantada"."estoque_saida"
(
    "id"         serial PRIMARY KEY,
    "id_estoque" integer,
    "quantidade" decimal(10, 2)           NOT NULL,
    "tipo_saida" rosaencantada.tipo_saida NOT NULL,
    "data_saida" timestamp                NOT NULL
);

CREATE TABLE "rosaencantada"."fornecedores"
(
    "id"               serial PRIMARY KEY,
    "nome"             varchar(200) NOT NULL,
    "cnpj"             varchar(20),
    "telefone"         varchar(20),
    "email"            varchar(50),
    "endereco"         varchar(200),
    "data_criacao"     timestamp    NOT NULL,
    "data_atualizacao" timestamp
);

CREATE TABLE "rosaencantada"."historico_movimentacoes"
(
    "id"                serial PRIMARY KEY,
    "estoque_id"        integer,
    "entrada_id"        integer,
    "saida_id"          integer,
    "quantidade"        decimal   NOT NULL,
    "data_movimentacao" timestamp NOT NULL
);

ALTER TABLE "rosaencantada"."estoque"
    ADD FOREIGN KEY ("id_produto") REFERENCES "rosaencantada"."produtos" ("id");

ALTER TABLE "rosaencantada"."produtos"
    ADD FOREIGN KEY ("id_categoria") REFERENCES "rosaencantada"."categorias" ("id");

ALTER TABLE "rosaencantada"."subcategorias"
    ADD FOREIGN KEY ("id_categoria") REFERENCES "rosaencantada"."categorias" ("id");

ALTER TABLE "rosaencantada"."estoque_entrada"
    ADD FOREIGN KEY ("id_estoque") REFERENCES "rosaencantada"."estoque" ("id");

ALTER TABLE "rosaencantada"."estoque_entrada"
    ADD FOREIGN KEY ("id_fornecedor") REFERENCES "rosaencantada"."fornecedores" ("id");

ALTER TABLE "rosaencantada"."estoque_saida"
    ADD FOREIGN KEY ("id_estoque") REFERENCES "rosaencantada"."estoque" ("id");

ALTER TABLE "rosaencantada"."historico_movimentacoes"
    ADD FOREIGN KEY ("estoque_id") REFERENCES "rosaencantada"."estoque" ("id");

ALTER TABLE "rosaencantada"."historico_movimentacoes"
    ADD FOREIGN KEY ("entrada_id") REFERENCES "rosaencantada"."estoque_entrada" ("id");

ALTER TABLE "rosaencantada"."historico_movimentacoes"
    ADD FOREIGN KEY ("saida_id") REFERENCES "rosaencantada"."estoque_saida" ("id");

CREATE INDEX "idx_estoque_produto" ON "rosaencantada"."estoque" ("id_produto");

CREATE INDEX "idx_estoque_ativo" ON "rosaencantada"."estoque" ("ativo");

CREATE INDEX "idx_produto_categoria" ON "rosaencantada"."produtos" ("id_categoria");

CREATE INDEX "idx_produto_ativo" ON "rosaencantada"."produtos" ("ativo");

CREATE INDEX "idx_produto_data_criacao" ON "rosaencantada"."produtos" ("data_criacao");

CREATE INDEX "idx_subcategoria_categoria" ON "rosaencantada"."subcategorias" ("id_categoria");

CREATE INDEX "idx_fornecedor_nome" ON "rosaencantada"."fornecedores" ("nome");

CREATE INDEX "idx_hist_mov_estoque" ON "rosaencantada"."historico_movimentacoes" ("estoque_id");

CREATE INDEX "idx_hist_mov_entrada" ON "rosaencantada"."historico_movimentacoes" ("entrada_id");

CREATE INDEX "idx_hist_mov_saida" ON "rosaencantada"."historico_movimentacoes" ("saida_id");

CREATE INDEX "idx_hist_mov_data" ON "rosaencantada"."historico_movimentacoes" ("data_movimentacao");