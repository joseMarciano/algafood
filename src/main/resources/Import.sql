
INSERT INTO COZINHAS(NOME) VALUES ("TAILANDESA");
INSERT INTO COZINHAS(NOME) VALUES ("INDIANA");
INSERT INTO COZINHAS(NOME) VALUES ("BRASILEIRA");
INSERT INTO COZINHAS(NOME) VALUES ("CHINESA");


INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS) VALUES ("LIONS",12.32,1);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS) VALUES ("TEXUGOS",11.4,3);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS) VALUES ("COPO & GIN",35,4);
INSERT INTO RESTAURANTES(NOME,TAXA_FRETE,I_COZINHAS) VALUES ("RESTAURANTE GUELLERE",20,1);


INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM DINHEIRO");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CARTAO");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CHEQUE");
INSERT INTO FORMAS_PAGAMENTO(DESCRICAO) VALUES ("PAGAMENTO EM CREDIARIO");


INSERT INTO ESTADOS(NOME) VALUES ("SANTA CATARINA");
INSERT INTO ESTADOS(NOME) VALUES ("PARANÁ");
INSERT INTO ESTADOS(NOME) VALUES ("RIO GRANDE DO SUL");
INSERT INTO ESTADOS(NOME) VALUES ("RIO DE JANEIRO");
INSERT INTO ESTADOS(NOME) VALUES ("SÃO PAULO");


INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("NOVA VENEZA",1);
INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("CRICIÚMA",2);
INSERT INTO CIDADES(NOME,I_ESTADOS) VALUES ("MORRO DA FUMAÇA",3);


