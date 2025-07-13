-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema `instafood`
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `instafood` DEFAULT CHARACTER SET utf8;
USE `instafood`;

-- -----------------------------------------------------
-- Table `instafood`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Utente` (
  `idUtente` INT NOT NULL,
  `Nome` VARCHAR(45) NULL,
  `Cognome` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `DataNascita` DATE NULL,
  `LuogoNascita` VARCHAR(45) NULL,
  `Biografia` BLOB NULL,
  `Immagine` LONGBLOB NULL,
  `Notifica` VARCHAR(45) NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE INDEX `idUtente_UNIQUE` (`idUtente`),
  UNIQUE INDEX `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Raccolta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Raccolta` (
  `idRaccolta` INT NOT NULL,
  `Titolo` VARCHAR(45) NULL,
  `Descrizione` BLOB NULL,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idRaccolta`,`Utente_idUtente`),
  UNIQUE INDEX `idRaccolta_UNIQUE` (`idRaccolta`),
  INDEX `fk_Raccolta_Utente1_idx` (`Utente_idUtente`),
  CONSTRAINT `fk_Raccolta_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `instafood`.`Utente` (`idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Ricetta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Ricetta` (
  `idRicetta` INT NOT NULL,
  `Titolo` VARCHAR(45) NULL,
  `Descrizione` VARCHAR(45) NULL,
  `Tempo` INT NULL,
  `Like` INT NULL,
  `Visibilita` TINYINT NULL,
  `Data` DATE NULL,
  `Raccolta_idRaccolta` INT NOT NULL,
  `Raccolta_Utente_idUtente` INT NOT NULL,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idRicetta`,`Raccolta_idRaccolta`,`Raccolta_Utente_idUtente`,`Utente_idUtente`),
  UNIQUE INDEX `idRicetta_UNIQUE` (`idRicetta`),
  INDEX `fk_Ricetta_Raccolta1_idx` (`Raccolta_idRaccolta`,`Raccolta_Utente_idUtente`),
  INDEX `fk_Ricetta_Utente1_idx` (`Utente_idUtente`),
  CONSTRAINT `fk_Ricetta_Raccolta1`
    FOREIGN KEY (`Raccolta_idRaccolta`,`Raccolta_Utente_idUtente`)
    REFERENCES `instafood`.`Raccolta` (`idRaccolta`,`Utente_idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Ricetta_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `instafood`.`Utente` (`idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Commento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Commento` (
  `idCommento` INT NOT NULL,
  `Testo` VARCHAR(45) NULL,
  `Data` DATE NULL,
  `Utente_idUtente` INT NOT NULL,
  `Ricetta_idRicetta` INT NOT NULL,
  PRIMARY KEY (`idCommento`,`Utente_idUtente`,`Ricetta_idRicetta`),
  UNIQUE INDEX `idCommento_UNIQUE` (`idCommento`),
  INDEX `fk_Commento_Utente_idx` (`Utente_idUtente`),
  INDEX `fk_Commento_Ricetta1_idx` (`Ricetta_idRicetta`),
  CONSTRAINT `fk_Commento_Utente`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `instafood`.`Utente` (`idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Commento_Ricetta1`
    FOREIGN KEY (`Ricetta_idRicetta`)
    REFERENCES `instafood`.`Ricetta` (`idRicetta`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Ingrediente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Ingrediente` (
  `idIngrediente` INT NOT NULL,
  `Nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idIngrediente`),
  UNIQUE INDEX `idIngrediente_UNIQUE` (`idIngrediente`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Tag` (
  `idTag` INT NOT NULL,
  `Nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idTag`),
  UNIQUE INDEX `idTag_UNIQUE` (`idTag`)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Ricetta_has_Ingrediente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Ricetta_has_Ingrediente` (
  `Ricetta_idRicetta` INT NOT NULL,
  `Ricetta_Raccolta_idRaccolta` INT NOT NULL,
  `Ricetta_Raccolta_Utente_idUtente` INT NOT NULL,
  `Ingrediente_idIngrediente` INT NOT NULL,
  `Qta` INT NOT NULL,
  PRIMARY KEY (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`,`Ingrediente_idIngrediente`),
  INDEX `fk_Ricetta_has_Ingrediente_Ingrediente1_idx` (`Ingrediente_idIngrediente`),
  INDEX `fk_Ricetta_has_Ingrediente_Ricetta1_idx` (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`),
  CONSTRAINT `fk_Ricetta_has_Ingrediente_Ricetta1`
    FOREIGN KEY (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`)
    REFERENCES `instafood`.`Ricetta` (`idRicetta`,`Raccolta_idRaccolta`,`Raccolta_Utente_idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Ricetta_has_Ingrediente_Ingrediente1`
    FOREIGN KEY (`Ingrediente_idIngrediente`)
    REFERENCES `instafood`.`Ingrediente` (`idIngrediente`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Ricetta_has_Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Ricetta_has_Tag` (
  `Ricetta_idRicetta` INT NOT NULL,
  `Ricetta_Raccolta_idRaccolta` INT NOT NULL,
  `Ricetta_Raccolta_Utente_idUtente` INT NOT NULL,
  `Tag_idTag` INT NOT NULL,
  PRIMARY KEY (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`,`Tag_idTag`),
  INDEX `fk_Ricetta_has_Tag_Tag1_idx` (`Tag_idTag`),
  INDEX `fk_Ricetta_has_Tag_Ricetta1_idx` (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`),
  CONSTRAINT `fk_Ricetta_has_Tag_Ricetta1`
    FOREIGN KEY (`Ricetta_idRicetta`,`Ricetta_Raccolta_idRaccolta`,`Ricetta_Raccolta_Utente_idUtente`)
    REFERENCES `instafood`.`Ricetta` (`idRicetta`,`Raccolta_idRaccolta`,`Raccolta_Utente_idUtente`)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Ricetta_has_Tag_Tag1`
    FOREIGN KEY (`Tag_idTag`)
    REFERENCES `instafood`.`Tag` (`idTag`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `instafood`.`Amministratore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `instafood`.`Amministratore` (
  `idAmministratore` INT NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAmministratore`)
) ENGINE=InnoDB;

-- Restore checks and modes
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

USE `instafood`;

-- -----------------------------------------------------
-- 1) Utente (10)
-- -----------------------------------------------------
INSERT INTO Utente (idUtente, Nome, Cognome, Password, Email, DataNascita, LuogoNascita, Biografia) VALUES
(1,'Mario','Rossi','pass123','mario.rossi@example.com','1985-02-15','Milano','Appassionato di cucina tradizionale.'),
(2,'Lucia','Bianchi','luci@2020','lucia.bianchi@example.com','1990-07-30','Firenze','Lover of vegan recipes.'),
(3,'Carlo','Verdi','verde!34','carlo.verdi@example.com','1978-12-05','Torino','Chef professionista.'),
(4,'Elena','Neri','elena99','elena.neri@example.com','1995-04-22','Roma','Food blogger.'),
(5,'Paolo','Gialli','paolo$$','paolo.gialli@example.com','1982-11-11','Napoli','Fan delle pizze fatte in casa.'),
(6,'Francesca','Blu','bluebird','francesca.blu@example.com','1993-09-18','Venezia','Dolci e dessert.'),
(7,'Marco','Rosa','rosaR0sa','marco.rosa@example.com','1988-01-27','Bologna','Specializzato in piatti di mare.'),
(8,'Anna','Viola','annaV!ola','anna.viola@example.com','1975-06-04','Palermo','Cucina regionale siciliana.'),
(9,'Stefano','Arancio','arnc2025','stefano.arancio@example.com','1991-03-12','Genova','Amante dei sughi e salse.'),
(10,'Laura','Marrone','brownie5','laura.marrone@example.com','1987-08-29','Verona','Cucina sana e leggera.');

-- -----------------------------------------------------
-- 2) Raccolta (10)
-- -----------------------------------------------------
INSERT INTO Raccolta (idRaccolta, Titolo, Descrizione, Utente_idUtente) VALUES
(101,'Ricette di famiglia','Le migliori ricette tramandate.',1),
(102,'Dolci e Dessert','Torte, biscotti e dolcetti vari.',1),
(103,'Vegan Creations','Piatto unici senza ingredienti animali.',2),
(104,'Seafood Specials','Ricette a base di pesce.',3),
(105,'Primi Piatti','Prими tradizionali italiani.',3),
(106,'Street Food','Cibo di strada da tutto il mondo.',4),
(107,'Pizza Lovers','Varвazioni di pizza fatta in casa.',5),
(108,'Healthy Meals','Pasti sani e bilanciati.',5),
(109,'Dolci al Cucchiaio','Budini, creme e mousse.',6),
(110,'Delizie Siciliane','Specialitб della tradizione siciliana.',8);

-- -----------------------------------------------------
-- 3) Ricetta (10) con campo Data
-- -----------------------------------------------------
INSERT INTO Ricetta (
  idRicetta, Titolo, Descrizione, Tempo, `Like`, Visibilita, Data, Raccolta_idRaccolta, Raccolta_Utente_idUtente, Utente_idUtente
) VALUES
(201,'Lasagne alla Bolognese','Classiche lasagne al ragù.',90,120,1,'2025-06-01',101,1,1),
(202,'Tiramisù','Dessert al caffè e mascarpone.',30,200,1,'2025-06-02',102,1,1),
(203,'Biscotti al Burro','Biscotti croccanti e friabili.',25,80,1,'2025-06-03',102,1,1),
(204,'Burger Vegano','Burger con ceci e quinoa.',40,65,1,'2025-06-04',103,2,2),
(205,'Spaghetti alle Vongole','Spaghetti con vongole fresche.',25,150,1,'2025-06-05',104,3,3),
(206,'Frittura Mista di Mare','Calamari, gamberi e alici fritti.',35,140,1,'2025-06-06',104,3,3),
(207,'Penne all’Arrabbiata','Pasta piccante al pomodoro.',20,90,1,'2025-06-07',105,3,3),
(208,'Arepas Colombiane','Focacce di mais ripiene.',50,70,1,'2025-06-08',106,4,4),
(209,'Margherita Tradizionale','Pizza con pomodoro, mozzarella e basilico.',60,180,1,'2025-06-09',107,5,5),
(210,'Buddha Bowl','Ciotola di quinoa e verdure.',30,110,1,'2025-06-10',108,5,5);

-- -----------------------------------------------------
-- 4) Commento (10)
-- -----------------------------------------------------
INSERT INTO Commento (idCommento, Testo, Data, Utente_idUtente, Ricetta_idRicetta) VALUES
(301,'Squisita!','2025-07-01',2,201),
(302,'La migliore lasagna mai fatta.','2025-07-02',3,201),
(303,'Perfetto equilibrio di sapori.','2025-07-03',5,205),
(304,'Ottima idea per una cena veloce.','2025-07-04',2,202),
(305,'Consistenza perfetta, grazie!','2025-07-05',6,203),
(306,'Un po’ troppo piccante per me.','2025-06-30',4,207),
(307,'Morbida e gustosa.','2025-07-02',1,209),
(308,'Proverò la versione vegana.','2025-07-03',8,204),
(309,'Ingredienti freschissimi!','2025-07-04',9,206),
(310,'Adoro le bowl colorate.','2025-07-05',10,210);

-- -----------------------------------------------------
-- 5) Ingrediente (10)
-- -----------------------------------------------------
INSERT INTO Ingrediente (idIngrediente, Nome) VALUES
(401,'Farina'),
(402,'Uova'),
(403,'Zucchero'),
(404,'Burро'),
(405,'Pomodoro'),
(406,'Mozzarella'),
(407,'Quinoa'),
(408,'Ceci'),
(409,'Vongole'),
(410,'Cioccolato');

-- -----------------------------------------------------
-- 6) Tag (10)
-- -----------------------------------------------------
INSERT INTO Tag (idTag, Nome) VALUES
(501,'Tradizionale'),
(502,'Vegan'),
(503,'Dolce'),
(504,'Salato'),
(505,'Rapido'),
(506,'Piccante'),
(507,'Gluten‑Free'),
(508,'Seafood'),
(509,'StreetFood'),
(510,'Healthy');

-- -----------------------------------------------------
-- 7) Ricetta_has_Ingrediente (10)
-- -----------------------------------------------------
INSERT INTO Ricetta_has_Ingrediente (Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, Ricetta_Raccolta_Utente_idUtente, Ingrediente_idIngrediente, Qta) VALUES
(201,101,1,401,500),
(201,101,1,405,6),
(202,102,1,402,12),
(202,102,1,403,300),
(204,103,2,407,250),
(204,103,2,408,200),
(205,104,3,405,6),
(205,104,3,409,500),
(209,107,5,405,6),
(209,107,5,406,4);

-- -----------------------------------------------------
-- 8) Ricetta_has_Tag (10)
-- -----------------------------------------------------
INSERT INTO Ricetta_has_Tag (Ricetta_idRicetta, Ricetta_Raccolta_idRaccolta, Ricetta_Raccolta_Utente_idUtente, Tag_idTag) VALUES
(201,101,1,501),
(201,101,1,504),
(202,102,1,503),
(202,102,1,505),
(204,103,2,502),
(204,103,2,504),
(205,104,3,508),
(207,105,3,506),
(209,107,5,501),
(210,108,5,510);

-- -----------------------------------------------------
-- 9) Amministratore (10)
-- -----------------------------------------------------
INSERT INTO Amministratore (idAmministratore, Username, Password) VALUES
(601,'admin1','adm1pass'),
(602,'admin2','adm2pass'),
(603,'supervisor','sup3pass'),
(604,'modera1','mod1pass'),
(605,'modera2','mod2pass'),
(606,'sysop','sysoppass'),
(607,'root','root123'),
(608,'gestore','gest0pass'),
(609,'editor','editpass'),
(610,'owner','own3rpass');


CREATE USER 'super_admin'@'localhost' IDENTIFIED BY 'Str0ngP@ss!';
GRANT ALL PRIVILEGES ON *.* TO 'super_admin'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;