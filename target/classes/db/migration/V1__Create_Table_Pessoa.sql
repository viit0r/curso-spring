CREATE TABLE IF NOT EXISTS `pessoa` (
  `id_pessoa` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `sobrenome` varchar(80) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `genero` varchar(9) NOT NULL,
  PRIMARY KEY (`id_pessoa`)
);