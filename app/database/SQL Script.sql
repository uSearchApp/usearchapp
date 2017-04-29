-- MySQL Script generated by MySQL Workbench
-- 04/25/17 10:51:53
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valor` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`supermercado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`supermercado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `rua` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`produto_supermercado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`produto_supermercado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idprodutos` INT NOT NULL,
  `idsupermercado` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_supermercado_produtos_idx` (`idprodutos` ASC),
  INDEX `fk_produto_supermercado_supermercado1_idx` (`idsupermercado` ASC),
  CONSTRAINT `fk_produto_supermercado_produtos`
    FOREIGN KEY (`idprodutos`)
    REFERENCES `mydb`.`produtos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_supermercado_supermercado1`
    FOREIGN KEY (`idsupermercado`)
    REFERENCES `mydb`.`supermercado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`historico_precos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`historico_precos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `codprodutos` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_historico_precos_produtos1_idx` (`codprodutos` ASC),
  CONSTRAINT `fk_historico_precos_produtos1`
    FOREIGN KEY (`codprodutos`)
    REFERENCES `mydb`.`produtos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
