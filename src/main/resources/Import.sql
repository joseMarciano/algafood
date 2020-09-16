INSERT INTO ESTADOS(NOME) VALUES ("SANTA CATARINA");
INSERT INTO ESTADOS(NOME) VALUES ("PARANÁ");
INSERT INTO ESTADOS(NOME) VALUES ("RIO GRANDE DO SUL");
INSERT INTO ESTADOS(NOME) VALUES ("RIO DE JANEIRO");
INSERT INTO ESTADOS(NOME) VALUES ("SÃO PAULO");


INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("NOVA VENEZA",1);
INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("CRICIÚMA",2);
INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("MORRO DA FUMAÇA",3);

INSERT INTO COZINHAS(NOME) VALUES ("TAILANDESA");
INSERT INTO COZINHAS(NOME) VALUES ("INDIANA");
INSERT INTO COZINHAS(NOME) VALUES ("BRASILEIRA");
INSERT INTO COZINHAS(NOME) VALUES ("CHINESA");

INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS,ENDERECO_BAIRRO,ENDERECO_CEP,ENDERECO_COMPLEMENTO,ENDERECO_LOGRADOURO,I_ENDERECO_CIDADE,DH_CADASTRO,DH_ATUALIZACAO) VALUES ("THAI GOURMET",12.3,1,"RUA FREI ELIZEU","88868-000","NUMERO 13","LOGRADOURO",1,UTC_TIMESTAMP,UTC_TIMESTAMP);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS,DH_CADASTRO,DH_ATUALIZACAO) VALUES ("TEXUGOS",11.4,3,UTC_TIMESTAMP,UTC_TIMESTAMP);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS,DH_CADASTRO,DH_ATUALIZACAO) VALUES ("LIONS",0,1,UTC_TIMESTAMP,UTC_TIMESTAMP);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS,DH_CADASTRO,DH_ATUALIZACAO) VALUES ("COPO & GIN",0,4,UTC_TIMESTAMP,UTC_TIMESTAMP);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS,DH_CADASTRO,DH_ATUALIZACAO) VALUES ("RESTAURANTE GUELLERE",20,1,UTC_TIMESTAMP,UTC_TIMESTAMP);


INSERT INTO PRODUTOS(DESCRICAO,PRECO,FL_ATIVO,I_RESTAURANTES) VALUES ("LINGUIÇA",10.65,TRUE,1);
INSERT INTO PRODUTOS(DESCRICAO,PRECO,FL_ATIVO,I_RESTAURANTES) VALUES ("PERNIL",15,TRUE,1);
INSERT INTO PRODUTOS(DESCRICAO,PRECO,FL_ATIVO,I_RESTAURANTES) VALUES ("FRANGO",160,TRUE,1);


INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM DINHEIRO");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CARTAO");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CHEQUE");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CREDIARIO");


INSERT INTO RESTAURANTES_FORMAS_PAGAMENTO(I_RESTAURANTES,I_FORMAS_PAGAMENTO) VALUES (1,1),(1,2),(1,3),(2,2),(3,3),(1,4);



