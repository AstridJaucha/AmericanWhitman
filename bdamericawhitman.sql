
use bdamericawhitman;
CREATE TABLE `bdamericawhitman`.`personal` (
  `idPersonal` VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
  `NombrePers` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `ApellidoPers` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `ClavePers` VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
  PRIMARY KEY (`idPersonal`),
  UNIQUE INDEX `ClavePers_UNIQUE` (`ClavePers` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`nivel_educativo` (
  `nivel` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  PRIMARY KEY (`Nivel`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`grado` (
  `grado` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  PRIMARY KEY (`grado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`seccion` (
  `seccion` VARCHAR(2) CHARACTER SET 'UTF8MB4' NOT NULL,
  PRIMARY KEY (`seccion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`academicoEstudiante` (
 `idacademico` INT NOT NULL AUTO_INCREMENT,
  `nivel` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `grado` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `seccion` VARCHAR(2) CHARACTER SET 'UTF8MB4' NOT NULL,
  PRIMARY KEY (`idacademico`),
  CONSTRAINT `fk_academicoEstudiante_nivel_educativo`
    FOREIGN KEY (`nivel`)
    REFERENCES `bdamericawhitman`.`nivel_educativo` (`nivel`),
  CONSTRAINT `fk_academicoEstudiante_seccion`
    FOREIGN KEY (`seccion`)
    REFERENCES `bdamericawhitman`.`seccion` (`seccion`),
  CONSTRAINT `fk_academicoEstudiante_grado`
    FOREIGN KEY (`grado`)
    REFERENCES `bdamericawhitman`.`grado` (`grado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;


-- en DNIALUMNO se debe agregar una relación con la tabla de niños
CREATE TABLE `bdamericawhitman`.`alumno` (
  codAlumno VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
    CHECK (codAlumno REGEXP '^AW[0-9]{3}$'),
  `dniAlumno` VARCHAR(8) CHARACTER SET 'UTF8MB4' NOT NULL,
  UNIQUE INDEX `dniAlumno_UNIQUE` (`dniAlumno` ASC) VISIBLE,
  `nombreAlumno` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `apePaterno` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `apeMaterno` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `fechNacimiento` DATE NOT NULL,
  `sexo` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `domicilio` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `dniPadre` VARCHAR(8) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `dniMadre` VARCHAR(8) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `dniApoderado` VARCHAR(8) CHARACTER SET 'UTF8MB4' NOT NULL,
  `telefono1` INT NOT NULL,
  `telefono2` INT NULL,
  `email` VARCHAR(45) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `discapacidad` VARCHAR(45) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `grupoSangui` VARCHAR(4) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `alergias` VARCHAR(45) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `añoEduc` VARCHAR(4) CHARACTER SET 'UTF8MB4' NOT NULL,
  `nivel` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `grado` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `seccion` VARCHAR(2) CHARACTER SET 'UTF8MB4' NOT NULL,
  `codigoModular` VARCHAR(7) CHARACTER SET 'UTF8MB4' NOT NULL,
  UNIQUE INDEX `codigoModular_UNIQUE` (`codigoModular` ASC) VISIBLE,
  CONSTRAINT `fk_alumno_academicoEstudiante`
    FOREIGN KEY (`grado`)
    REFERENCES `bdamericawhitman`.`academicoEstudiante` (`grado`),
    FOREIGN KEY (`nivel`)
    REFERENCES `bdamericawhitman`.`academicoEstudiante` (`nivel`),
    FOREIGN KEY (`seccion`)
    REFERENCES `bdamericawhitman`.`academicoEstudiante` (`seccion`),
     FOREIGN KEY (`dniAlumno`)
  REFERENCES `bdamericawhitman`.`niños` (`dni`),
  PRIMARY KEY (codAlumno))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

use bdamericawhitman;

insert into nivel_educativo values ("Inicial");
insert into nivel_educativo values ("Primaria");
insert into nivel_educativo values ("Secundaria");
select * from nivel_educativo;

insert into grado values ("3 años");
insert into grado values ("4 años");
insert into grado values ("5 años");
insert into grado values ("Primero");
insert into grado values ("Segundo");
insert into grado values ("Tercero");
insert into grado values ("Cuarto");
insert into grado values ("Quinto");
insert into grado values ("Sexto");
select * from grado;

insert into seccion values ("A");
insert into seccion values ("B");
insert into seccion values ("C");
select * from seccion;
select * from academicoEstudiante;
insert into academicoEstudiante values (null,"Inicial","3 años","A");
insert into academicoEstudiante values (null,"Inicial","3 años","B");
insert into academicoEstudiante values (null,"Inicial","3 años","C");
insert into academicoEstudiante values (null,"Inicial","4 años","A");
insert into academicoEstudiante values (null,"Inicial","4 años","B");
insert into academicoEstudiante values (null,"Inicial","4 años","C");
insert into academicoEstudiante values (null,"Inicial","5 años","A");
insert into academicoEstudiante values (null,"Inicial","5 años","B");
insert into academicoEstudiante values (null,"Inicial","5 años","C");

insert into academicoEstudiante values (null,"Primaria","Primero","A");
insert into academicoEstudiante values (null,"Primaria","Primero","B");
insert into academicoEstudiante values (null,"Primaria","Primero","C");
insert into academicoEstudiante values (null,"Primaria","Segundo","A");
insert into academicoEstudiante values (null,"Primaria","Segundo","B");
insert into academicoEstudiante values (null,"Primaria","Segundo","C");
insert into academicoEstudiante values (null,"Primaria","Tercero","A");
insert into academicoEstudiante values (null,"Primaria","Tercero","B");
insert into academicoEstudiante values (null,"Primaria","Tercero","C");
insert into academicoEstudiante values (null,"Primaria","Cuarto","A");
insert into academicoEstudiante values (null,"Primaria","Cuarto","B");
insert into academicoEstudiante values (null,"Primaria","Cuarto","C");
insert into academicoEstudiante values (null,"Primaria","Quinto","A");
insert into academicoEstudiante values (null,"Primaria","Quinto","B");
insert into academicoEstudiante values (null,"Primaria","Quinto","C");
insert into academicoEstudiante values (null,"Primaria","Sexto","A");
insert into academicoEstudiante values (null,"Primaria","Sexto","B");
insert into academicoEstudiante values (null,"Primaria","Sexto","C");


insert into academicoEstudiante values (null,"Secundaria","Primero","A");
insert into academicoEstudiante values (null,"Secundaria","Primero","B");
insert into academicoEstudiante values (null,"Secundaria","Primero","C");
insert into academicoEstudiante values (null,"Secundaria","Segundo","A");
insert into academicoEstudiante values (null,"Secundaria","Segundo","B");
insert into academicoEstudiante values (null,"Secundaria","Segundo","C");
insert into academicoEstudiante values (null,"Secundaria","Tercero","A");
insert into academicoEstudiante values (null,"Secundaria","Tercero","B");
insert into academicoEstudiante values (null,"Secundaria","Tercero","C");
insert into academicoEstudiante values (null,"Secundaria","Cuarto","A");
insert into academicoEstudiante values (null,"Secundaria","Cuarto","B");
insert into academicoEstudiante values (null,"Secundaria","Cuarto","C");
insert into academicoEstudiante values (null,"Secundaria","Quinto","A");
insert into academicoEstudiante values (null,"Secundaria","Quinto","B");
insert into academicoEstudiante values (null,"Secundaria","Quinto","C");

CREATE TABLE `bdamericawhitman`.`registropersonal` (
`idregistropersonal` INT NOT NULL AUTO_INCREMENT,
  `idPersonal` VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
  `fechaHora` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
    CONSTRAINT `fk_registroPersonal_personal`
    FOREIGN KEY (`idPersonal`)
    REFERENCES `bdamericawhitman`.`personal` (`idPersonal`),
  PRIMARY KEY (`idregistropersonal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`registroalumno` (
  `idregistroAlumno` INT NOT NULL AUTO_INCREMENT,
  `fechaHora` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `dnialumno` VARCHAR(8) CHARACTER SET 'UTF8MB4' NOT NULL,
  `codAlumno` VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
  `idpersonal` VARCHAR(5) CHARACTER SET 'UTF8MB4' NOT NULL,
  `accion` VARCHAR(20) CHARACTER SET 'UTF8MB4' NOT NULL,
  CONSTRAINT `fk_registroalumno_alumno`
    FOREIGN KEY (`dnialumno`)
    REFERENCES `bdamericawhitman`.`alumno` (`dniAlumno`),
  CONSTRAINT `fk_registroalumno_personal`
    FOREIGN KEY (`idpersonal`)
    REFERENCES `bdamericawhitman`.`registropersonal` (`idpersonal`),
  -- Restricción CHECK para codAlumno
  CHECK (codAlumno REGEXP '^AW[0-9]{3}$'),
  PRIMARY KEY (`idregistroAlumno`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = UTF8MB4;

CREATE TABLE `bdamericawhitman`.`niños` (
  `dni` VARCHAR(8) CHARACTER SET 'UTF8MB4' NOT NULL,
  `nom` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `apePa` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `apeMa` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `fechNac` DATE NOT NULL,
  `sexo` VARCHAR(45) CHARACTER SET 'UTF8MB4' NOT NULL,
  `dniPadre` VARCHAR(8) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `dniMadre` VARCHAR(8) CHARACTER SET 'UTF8MB4' NULL DEFAULT 'null',
  `nivel` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `grado` VARCHAR(15) CHARACTER SET 'UTF8MB4' NOT NULL,
  `codigoModular` VARCHAR(7) CHARACTER SET 'UTF8MB4' NOT NULL,
  añoEduc VARCHAR(7) CHARACTER SET 'UTF8MB4' NOT NULL
    CHECK (añoEduc REGEXP '^[0-9]{4}$'),
 PRIMARY KEY (`dni`),
  UNIQUE INDEX `codigoModular_UNIQUE` (`codigoModular` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB4;

INSERT INTO `niños` VALUES
('11111116', 'Sofía', 'Martínez', 'Gómez', '2019-03-10', 'Mujer', '11113222', '31111333', 'Inicial', '3 años', '1234567', '2023'),
('11111117', 'Mateo', 'Hernández', 'López', '2018-04-15', 'Hombre', '11114464', '11115855', 'Inicial', '4 años', '2345678', '2023'),
('11111118', 'Valentina', 'García', 'Ramírez', '2017-05-20', 'Mujer', '11112666', '11116777', 'Inicial', '5 años', '3456789', '2023'),
('11111119', 'Daniel', 'Pérez', 'González', '2020-12-12', 'Hombre', '11118388', '11161999', 'Inicial', '3 años', '4567890', '2023'),
('11111120', 'Isabella', 'Torres', 'Rodríguez', '2019-03-25', 'Mujer', '11123000', '11142111', 'Inicial', '4 años', '5678901', '2023'),
('11111121', 'David', 'Gómez', 'Díaz', '2018-07-03', 'Hombre', '11122122', '11123363', 'Inicial', '5 años', '6789012', '2023'),
('11111122', 'Camila', 'Martínez', 'Sánchez', '2020-11-15', 'Mujer', '11212444', '11122555', 'Inicial', '3 años', '7890123', '2023'),
('11111123', 'Lucas', 'Ramírez', 'Fernández', '2019-04-01', 'Hombre', '11125666', '11612777', 'Inicial', '4 años', '8901234', '2023'),
('11111124', 'Eva', 'Hernández', 'López', '2018-08-22', 'Mujer', '11122888', '11123999', 'Inicial', '5 años', '9012345', '2023'),
('11111125', 'Martín', 'Silva', 'Ortega', '2020-10-05', 'Hombre', '11120020', '11312111', 'Inicial', '3 años', '9178274', '2023');

INSERT INTO `niños` VALUES
('22222228', 'Gabriel', 'Ortega', 'Martín', '2017-01-18', 'Hombre', '22225333', '22272444', 'Primaria', 'Primero', '2536463', '2023'),
('22222229', 'Lucía', 'Molina', 'Vargas', '2016-03-25', 'Mujer', '22272555', '22226466', 'Primaria', 'Segundo', '8274927', '2023'),
('22222230', 'Juan Pablo', 'Silva', 'Navarro', '2015-08-10', 'Hombre', '22422777', '22222888', 'Primaria', 'Tercero', '8264834', '2023'),
('22222231', 'Isabel', 'Gómez', 'Díaz', '2014-11-30', 'Mujer', '22229399', '22221501', 'Primaria', 'Cuarto', '1937562', '2023'),
('22222232', 'Andrés', 'Hernández', 'Jiménez', '2013-05-15', 'Hombre', '22221211', '22422222', 'Primaria', 'Quinto', '2940284', '2023'),
('22222233', 'Valeria', 'Ramírez', 'Fernández', '2012-03-02', 'Mujer', '22222333', '22224544', 'Primaria', 'Sexto', '8592757', '2023'),
('22222234', 'Martín', 'Vega', 'Sánchez', '2016-11-19', 'Hombre', '22221555', '22232666', 'Primaria', 'Primero', '9285738', '2023'),
('22222235', 'Emma', 'Romero', 'Martínez', '2016-06-20', 'Mujer', '22222777', '22822888', 'Primaria', 'Segundo', '7638474', '2023'),
('22222236', 'Daniel', 'Martínez', 'Romero', '2014-11-05', 'Hombre', '22224999', '22522101', 'Primaria', 'Tercero', '6254692', '2023'),
('22222237', 'Laura', 'Gómez', 'Pérez', '2014-10-10', 'Mujer', '22221611', '22227222', 'Primaria', 'Cuarto', '1850283', '2023');

INSERT INTO `niños` VALUES
('33333338', 'Marina', 'Ríos', 'García', '2011-02-22', 'Mujer', '33334434', '33335255', 'Secundaria', 'Primero', '7154926', '2023'),
('33333339', 'Javier', 'Mendoza', 'Pérez', '2010-05-28', 'Hombre', '33633666', '33332777', 'Secundaria', 'Segundo', '2358851', '2023'),
('33333340', 'Camila', 'Soto', 'Gutiérrez', '2009-11-15', 'Mujer', '33334888', '33333999', 'Secundaria', 'Tercero', '9173010', '2023'),
('33333341', 'Alejandro', 'Cabrera', 'Fuentes', '2008-04-02', 'Hombre', '33336000', '33339111', 'Secundaria', 'Cuarto', '1231381', '2023'),
('33333342', 'Mariana', 'Romero', 'Vega', '2007-11-19', 'Mujer', '33332722', '33339333', 'Secundaria', 'Quinto', '9825180', '2023'),
('33333343', 'Diego', 'Martínez', 'Jiménez', '2010-06-20', 'Hombre', '33335444', '33339555', 'Secundaria', 'Primero', '4729173', '2023'),
('33333344', 'Valentina', 'Gómez', 'Hernández', '2010-11-05', 'Mujer', '33336666', '33330777', 'Secundaria', 'Segundo', '8126190', '2023'),
('33333345', 'Lucas', 'Silva', 'Díaz', '2009-08-10', 'Hombre', '33343888', '33335999', 'Secundaria', 'Tercero', '1296130', '2023'),
('33333346', 'Elena', 'Pérez', 'Ortega', '2008-12-01', 'Mujer', '33334000', '33353111', 'Secundaria', 'Cuarto', '9178111', '2023'),
('33333347', 'Gabriel', 'Hernández', 'Ramírez', '2007-10-25', 'Hombre', '33337222', '33383333', 'Secundaria', 'Quinto', '9129343', '2023');


CREATE TABLE contador_alumnos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cantidad_alumnos INT not null,
  valor_año_educ VARCHAR(4)CHARACTER SET 'UTF8MB4' NOT NULL
);

INSERT INTO contador_alumnos (cantidad_alumnos, valor_año_educ) VALUES (0, '2024');
INSERT INTO contador_alumnos (cantidad_alumnos, valor_año_educ) VALUES (0, '2023');

-- TRIGERS1
DROP TRIGGER IF EXISTS `bdamericawhitman`.`alumno_AFTER_INSERT`;

DELIMITER $$
USE `bdamericawhitman`$$

CREATE DEFINER = CURRENT_USER TRIGGER `bdamericawhitman`.`alumno_AFTER_INSERT` AFTER INSERT ON `alumno` 
FOR EACH ROW
BEGIN
    -- Obtener el último idPersonal insertado en la tabla registropersonal
    SELECT idPersonal INTO @ultimoIdPersonal
    FROM registropersonal
    ORDER BY idregistropersonal DESC
    LIMIT 1;

    -- Insertar en la tabla registroalumno con el último idPersonal
    INSERT INTO registroalumno (idregistroAlumno, fechaHora, dnialumno, codAlumno, idpersonal, accion)
    VALUES (NULL, NOW(), NEW.dniAlumno, new.codAlumno, @ultimoIdPersonal, 'Agregado');
END$$

DELIMITER ;

-- TRIGERS2

DROP TRIGGER IF EXISTS `bdamericawhitman`.`before_insert_alumno`;

DELIMITER $$

CREATE TRIGGER `bdamericawhitman`.`before_insert_alumno`
BEFORE INSERT ON `alumno`
FOR EACH ROW
BEGIN
    DECLARE nuevo_cantAlumno int;
    DECLARE nuevo_añoEduc INT;
    DECLARE nuevo_codAlumno VARCHAR(5);
    

    -- Obtener el valor actual del contador y el añoEduc
    SELECT cantidad_alumnos, valor_año_educ INTO nuevo_cantAlumno, nuevo_añoEduc
    FROM contador_alumnos
    ORDER BY id DESC LIMIT 1;

-- Actualizar el nuevo valor del contador y añoEduc en la tabla auxiliar
    UPDATE contador_alumnos
    SET cantidad_alumnos = nuevo_cantAlumno + 1,
        valor_año_educ = nuevo_añoEduc;
        
    -- Formatear el nuevo código del alumno (AW***)
    SET nuevo_codAlumno = CONCAT('AW', LPAD(nuevo_cantAlumno, 3, '0'));

    -- Incrementar el valor de añoEduc
     SET nuevo_añoEduc = YEAR(NOW())+1;

    

    -- Asignar los nuevos valores al nuevo alumno
    SET NEW.codAlumno = nuevo_codAlumno;
SET NEW.añoEduc = nuevo_añoEduc;
UPDATE niños
SET añoEduc = nuevo_añoEduc,
   nivel=new.nivel,
 grado=new.grado
 WHERE DNI = NEW.dniAlumno;
END$$

DELIMITER ;




-- TRIGGER AFTER UPDATE EN ALUMNO
DROP TRIGGER IF EXISTS `bdamericawhitman`.`alumno_AFTER_UPDATE`;

DELIMITER $$
USE `bdamericawhitman`$$

CREATE DEFINER = CURRENT_USER TRIGGER `bdamericawhitman`.`alumno_AFTER_UPDATE` AFTER UPDATE ON `alumno` 
FOR EACH ROW
BEGIN
    -- Obtener la fecha y hora actual
    SET @fechaHora = NOW();

    SELECT idPersonal INTO @ultimoIdPersonal
    FROM registropersonal
    ORDER BY idregistropersonal DESC
    LIMIT 1;

        INSERT INTO registroalumno (idregistroAlumno, fechaHora, dnialumno, codAlumno, idpersonal, accion)
        VALUES (NULL, @fechaHora, NEW.dniAlumno, NEW.codAlumno, @ultimoIdPersonal, 'Actualizado');
END$$

DELIMITER ;


-- TRIGGER PARA ACTUALIZAR añoEduc EN ALUMNO Y NIÑOS (BEFORE UPDATE)
DROP TRIGGER IF EXISTS `bdamericawhitman`.`update_añoEduc_before`;

DELIMITER $$
USE `bdamericawhitman`$$

CREATE DEFINER = CURRENT_USER TRIGGER `bdamericawhitman`.`update_añoEduc_before` BEFORE UPDATE ON 
`alumno` FOR EACH ROW
BEGIN
    -- Obtener el nuevo valor de añoEduc (año actual + 1)
    SET NEW.añoEduc = YEAR(NOW()) + 1;
END$$

DELIMITER ;

-- TRIGGER PARA ACTUALIZAR NIÑOS DESPUÉS DE ACTUALIZAR ALUMNO (AFTER UPDATE)
DROP TRIGGER IF EXISTS `bdamericawhitman`.`update_niños_after`;

DELIMITER $$
USE `bdamericawhitman`$$

CREATE DEFINER = CURRENT_USER TRIGGER `bdamericawhitman`.`update_niños_after` AFTER UPDATE ON `alumno`
 FOR EACH ROW
BEGIN
    -- Actualizar añoEduc, nivel y grado en la tabla niños
    UPDATE niños
    SET añoEduc = NEW.añoEduc,
        nivel = NEW.nivel,
        grado = NEW.grado
    WHERE dni = NEW.dniAlumno;
END$$

DELIMITER ;

