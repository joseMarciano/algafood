create table formas_pagamento (
	id bigint not null auto_increment,
	descricao varchar(400),
	primary key (id)
) engine=InnoDB;

create table grupos (
	id bigint not null auto_increment,
	nome varchar(60) not null,

	primary key (id)
) engine=InnoDB;

create table grupos_permissoes (
	id_grupos bigint not null,
	id_permissoes bigint not null,

	primary key (id_grupos, id_permissoes)
) engine=InnoDB;

create table permissoes (
	id bigint not null auto_increment,
    nome varchar(60) not null,
	descricao varchar(400),

	primary key (id)
) engine=InnoDB;

create table produtos (
	id bigint not null auto_increment,
	id_restaurantes bigint not null,
	nome varchar(60) not null,
	descricao varchar(400),
	preco decimal(10,2) not null,
	fl_ativo bool default false,

	primary key (id)
) engine=InnoDB;


create table restaurantes (
	id bigint not null auto_increment,
	id_cozinhas bigint not null,
	nome varchar(60) not null,
	taxa_frete decimal(10,2) not null,

	data_atualizacao datetime not null,
	data_cadastro datetime not null,

	id_endereco_cidade bigint,
	endereco_cep varchar(9),
	endereco_logradouro varchar(400),
	endereco_numero varchar(60),
	endereco_complemento varchar(400),
	endereco_bairro varchar(400),

	primary key (id)
) engine=InnoDB;

create table restaurantes_formas_pagamento (
	id_restaurantes bigint not null,
	id_formas_pagamento bigint not null,

	primary key (id_restaurantes, id_formas_pagamento)
) engine=InnoDB;

create table usuarios (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	email varchar(400),
	senha varchar(400) not null,
	dh_cadastro datetime not null,

	primary key (id)
) engine=InnoDB;

create table usuarios_grupos (
	id_usuarios bigint not null,
	id_grupos bigint not null,

	primary key (id_usuarios, id_grupos)
) engine=InnoDB;

alter table grupos_permissoes add constraint fk_permissoes_grupos_permissoes
foreign key (id_permissoes) references permissoes (id);

alter table grupos_permissoes add constraint fk_grupos_permissoes_grupos
foreign key (id_grupos) references grupos (id);

alter table produtos add constraint fk_restaurantes_produtos
foreign key (id_restaurantes) references restaurantes (id);

alter table restaurantes add constraint fk_cozinhas_restaurantes
foreign key (id_cozinhas) references cozinhas (id);

alter table restaurantes add constraint fk_cidades_restaurantes
foreign key (id_endereco_cidade) references cidades (id);

alter table restaurantes_formas_pagamento add constraint fk_formas_pagamentos_restaurantes_formas_pagamento
foreign key (id_formas_pagamento) references formas_pagamento (id);

alter table restaurantes_formas_pagamento add constraint fk_restaurantes_restaurantes_formas_pagamento
foreign key (id_restaurantes) references restaurantes (id);

alter table usuarios_grupos add constraint fk_grupos_usuarios_grupos
foreign key (id_grupos) references grupos (id);

alter table usuarios_grupos add constraint fk_usuarios_usuarios_grupos
foreign key (id_usuarios) references usuarios (id);