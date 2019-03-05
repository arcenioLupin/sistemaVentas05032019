# Host: localhost  (Version 5.1.39-community)
# Date: 2019-03-05 15:37:34
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "almacenproducto"
#

DROP TABLE IF EXISTS `almacenproducto`;
CREATE TABLE `almacenproducto` (
  `AlmacenCodigo` int(10) NOT NULL COMMENT 'Código del Almacén',
  `ProductoCodigo` int(10) NOT NULL COMMENT 'Código del Producto',
  `AlmacenProductoStock` int(5) DEFAULT NULL COMMENT 'Stock del Producto en Almacén',
  `UnidadMedidaCodigo` varchar(3) DEFAULT NULL COMMENT 'Unidad de medida del Producto',
  `AlmacenProductoPrecio` decimal(15,2) DEFAULT NULL COMMENT 'Precio del Producto en Almacén',
  `MonedaCodigo` varchar(3) DEFAULT NULL COMMENT 'Moneda del Producto',
  `AlmacenProductoEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado del Producto en Almacén: A: Activo, I: Inactivo',
  PRIMARY KEY (`AlmacenCodigo`,`ProductoCodigo`),
  KEY `UnidadMedidaCodigo` (`UnidadMedidaCodigo`),
  KEY `MonedaCodigo` (`MonedaCodigo`),
  KEY `ProductoCodigo` (`ProductoCodigo`),
  CONSTRAINT `almacenproducto_ibfk_1` FOREIGN KEY (`UnidadMedidaCodigo`) REFERENCES `unidadesmedida` (`UnidadMedidaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `almacenproducto_ibfk_2` FOREIGN KEY (`ProductoCodigo`) REFERENCES `productos` (`ProductoCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `almacenproducto_ibfk_3` FOREIGN KEY (`AlmacenCodigo`) REFERENCES `almacenes` (`AlmacenCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `almacenproducto_ibfk_4` FOREIGN KEY (`MonedaCodigo`) REFERENCES `monedas` (`MonedaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "almacenproducto"
#

INSERT INTO `almacenproducto` VALUES (1,1,10,'UMU',15.56,'D','A'),(1,3,20,'UMU',78.56,'D','A'),(2,2,70,'UMU',188.56,'D','A'),(2,3,1500,'UMU',88.56,'D','A'),(2,5,1000,'UMU',158.56,'D','A');

#
# Structure for table "categorias"
#

DROP TABLE IF EXISTS `categorias`;
CREATE TABLE `categorias` (
  `CategoriaCodigo` int(3) NOT NULL AUTO_INCREMENT COMMENT 'Código de la Categoría',
  `CategoriaNombre` varchar(50) NOT NULL COMMENT 'Nombre de la Categoría',
  `CategoriaDescripcion` text COMMENT 'Descripción de la Categoría',
  `CategoriaEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado de la Categoría: A: Activo, I: Inactivo',
  `CategoriaFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `CategoriaHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `CategoriaUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `CategoriaFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `CategoriaHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `CategoriaUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`CategoriaCodigo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Data for table "categorias"
#

INSERT INTO `categorias` VALUES (5,'RELOJES','CATEGORIA QUE AGRUPA RELOJES','A','2018-08-23',NULL,NULL,NULL,NULL,NULL),(6,'AUDIFONOS','CATEGORIA QUE AGRUPA AUDIFONOS','A','2018-08-23',NULL,NULL,NULL,NULL,NULL),(7,'PARLANTES','CATEGORIA QUE AGRUPA PARLANTES','A','2018-08-23',NULL,NULL,NULL,NULL,NULL),(8,'CATEGORIA ROPA MUJER','CATEGORIA QUE AGRUPA ROPA DE MUJER','A','2018-08-23',NULL,NULL,NULL,NULL,NULL),(10,'SMARTPHONES','CATEGORIA QUE AGRUPA SMARTPHONES','A','2018-08-26',NULL,NULL,NULL,NULL,NULL),(11,'CATEGORIA JUGUETES','CATEGORIA QUE AGRUPA JUGUETES','A','2018-11-19',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "monedas"
#

DROP TABLE IF EXISTS `monedas`;
CREATE TABLE `monedas` (
  `MonedaCodigo` varchar(3) NOT NULL COMMENT 'Código de Moneda',
  `MonedaSimbolo` varchar(5) NOT NULL COMMENT 'Símbolo de Moneda',
  `MonedaNombre` varchar(20) DEFAULT NULL COMMENT 'Nombre de Moneda',
  PRIMARY KEY (`MonedaCodigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "monedas"
#

INSERT INTO `monedas` VALUES ('D','$','DOLARES'),('E','€','EURO'),('S','S/.','SOLES');

#
# Structure for table "movimientos"
#

DROP TABLE IF EXISTS `movimientos`;
CREATE TABLE `movimientos` (
  `MovimientoCodigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código del Movimiento',
  `MovimientoFecha` date NOT NULL COMMENT 'Fecha del Movimiento',
  `AlmacenCodOrigen` int(10) NOT NULL COMMENT 'Código del Almacén Origen',
  `AlmacenCodDestino` int(10) NOT NULL COMMENT 'Código del Almacén Destino',
  `MovimientoComentario` mediumtext COMMENT 'Comentario del Movimiento',
  `MovimientoFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `MovimientoHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `MovimientoUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `MovimientoFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `MovimientoHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `MovimientoUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`MovimientoCodigo`),
  KEY `AlmacenCodOrigen` (`AlmacenCodOrigen`),
  KEY `AlmacenCodDestino` (`AlmacenCodDestino`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "movimientos"
#

INSERT INTO `movimientos` VALUES (1,'2018-12-12',1,2,'Movimiento de mercaderia 2','2018-11-28',NULL,NULL,NULL,NULL,NULL),(2,'2018-08-12',1,2,'Movimiento de mercaderia 2','2018-11-28',NULL,NULL,NULL,NULL,NULL),(3,'2018-07-12',2,1,'Movimiento de mercaderia 3','2018-11-28',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "tiposalmacen"
#

DROP TABLE IF EXISTS `tiposalmacen`;
CREATE TABLE `tiposalmacen` (
  `TipoAlmacenCodigo` int(3) NOT NULL AUTO_INCREMENT COMMENT 'Código de Tipo de Almacén',
  `TipoAlmacenNombre` varchar(50) NOT NULL COMMENT 'Nombre de Tipo de Almacén',
  `TipoAlmacenDescripcion` mediumtext COMMENT 'Descripción de Tipo de Almacén',
  `TipoAlmacenEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado de Tipo de Almacén: A: Activo, I: Inactivo',
  `TipoAlmacenFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `TipoAlmacenHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `TipoAlmacenUscre` int(11) DEFAULT NULL COMMENT 'Usuario de Creación',
  `TipoAlmacenFemod` varchar(12) DEFAULT NULL COMMENT 'Fecha de Modificación',
  `TipoAlmacenHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `TipoAlmacenUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`TipoAlmacenCodigo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

#
# Data for table "tiposalmacen"
#

INSERT INTO `tiposalmacen` VALUES (5,'ALMACEN A','ALMACEN ELECTRONICOS','A','2018-09-20',NULL,NULL,NULL,NULL,NULL),(6,'ALMACEN B','ALMACEN REPUESTOS MOTOS','A','2018-09-20',NULL,NULL,NULL,NULL,NULL),(7,'ALMACEN C','ALMACEN ROPA','A','2018-09-20',NULL,NULL,NULL,NULL,NULL),(8,'ALMACEN D','ALMACEN JUGUETES NIÃ‘OS','A','2018-09-20',NULL,NULL,NULL,NULL,NULL),(9,'TIPO ALMACEN F','ALMACEN DE PRODUCTOS QUIMICOS','I','2018-11-19',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "almacenes"
#

DROP TABLE IF EXISTS `almacenes`;
CREATE TABLE `almacenes` (
  `AlmacenCodigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código del Almacén',
  `AlmacenNombre` varchar(50) NOT NULL COMMENT 'Nombre del Almacén',
  `TipoAlmacenCodigo` int(3) DEFAULT NULL COMMENT 'Código del Tipo de Almacén',
  `AlmacenDireccion` text COMMENT 'Dirección del Almacén',
  `AlmacenDescripcion` mediumtext COMMENT 'Descripción del Almacén',
  `AlmacenEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado del Almacén: A: Activo, I: Inactivo',
  `AlmacenFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `AlmacenHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `AlmacenUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `AlmacenFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `AlmacenHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `AlmacenUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`AlmacenCodigo`),
  KEY `TipoAlmacenCodigo` (`TipoAlmacenCodigo`),
  CONSTRAINT `almacenes_ibfk_1` FOREIGN KEY (`TipoAlmacenCodigo`) REFERENCES `tiposalmacen` (`TipoAlmacenCodigo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "almacenes"
#

INSERT INTO `almacenes` VALUES (1,'ALMACEN CENTRAL',5,'AV.REVOLUCION 34578 V.E.S','ALMACEN DE REPUESTOS MOTOS','A','2018-11-19',NULL,NULL,NULL,NULL,NULL),(2,'ALMACEN CENTRAL',6,'AV.REVOLUCION 345 V.E.S','ALMACEN DE REPUESTOS MOTOS','A','2018-11-19',NULL,NULL,NULL,NULL,NULL),(3,'ALMACEN EL TAMBO',6,'AV.LA MOLINA 3457 LA MOLINA','ALMACEN DE COMESTIBLES','A','2018-11-19',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "unidadesmedida"
#

DROP TABLE IF EXISTS `unidadesmedida`;
CREATE TABLE `unidadesmedida` (
  `UnidadMedidaCodigo` varchar(3) NOT NULL COMMENT 'Código de la Unidad de Medida',
  `UnidadMedidaSimbolo` varchar(5) NOT NULL COMMENT 'Símbolo de la Unidad de Medida',
  `UnidadMedidaNombre` varchar(20) DEFAULT NULL COMMENT 'Nombre de la Unidad de Medida',
  PRIMARY KEY (`UnidadMedidaCodigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "unidadesmedida"
#

INSERT INTO `unidadesmedida` VALUES ('UMK','K','KILOGRAMO'),('UML','L','LITRO'),('UMM','M','METRO'),('UMU','U','UNIDADES');

#
# Structure for table "productos"
#

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `ProductoCodigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código del Producto',
  `ProductoNombre` varchar(50) NOT NULL COMMENT 'Nombre del Producto',
  `ProductoDescripcion` longtext COMMENT 'Descripción del Producto',
  `CategoriaCodigo` int(3) DEFAULT NULL COMMENT 'Código de la Categoría',
  `UnidadMedidaCodigo` varchar(3) DEFAULT NULL COMMENT 'Código de la Unidad de Medida',
  `ProductoPrecio` decimal(15,2) DEFAULT NULL COMMENT 'Precio del Producto',
  `MonedaCodigo` varchar(3) DEFAULT NULL COMMENT 'Código de la Moneda',
  `ProductoEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado de Producto: A: Activo, I: Inactivo',
  `ProductoFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `ProductoHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `ProductoUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `ProductoFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `ProductoHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `ProductoUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`ProductoCodigo`),
  KEY `ProductoNombre` (`ProductoNombre`),
  KEY `UnidadMedidaCodigo` (`UnidadMedidaCodigo`),
  KEY `CategoriaCodigo` (`CategoriaCodigo`),
  KEY `MonedaCodigo` (`MonedaCodigo`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`CategoriaCodigo`) REFERENCES `categorias` (`CategoriaCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`MonedaCodigo`) REFERENCES `monedas` (`MonedaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `productos_ibfk_3` FOREIGN KEY (`UnidadMedidaCodigo`) REFERENCES `unidadesmedida` (`UnidadMedidaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "productos"
#

INSERT INTO `productos` VALUES (1,'AUDIFONOS URBEATS WIRELESS','AUDIFONOS URBEATS WIRELESS ,SONIDO ALTA CALIDAD',6,'UMU',255.50,'D','A','2018-09-14',NULL,NULL,NULL,NULL,NULL),(2,'AUDIFONOS TOUR BEATS WIRELESS','AUDIFONOS TOUR BEATS WIRELESS ,SONIDO ALTA CALIDAD',6,'UMU',753.50,'D','A','2018-09-14',NULL,NULL,NULL,NULL,NULL),(3,'RELOJ SPORT HOMBRE','RELOJ SPORT HOMBRE,CORREA DE CUERO',5,'UMU',53.50,'D','A','2018-09-14',NULL,NULL,NULL,NULL,NULL),(5,'PRODUCTO TEST 2','PRODUCTO TEST 2',5,'UMU',13.50,'D','A','2018-09-14',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "movimientoproducto"
#

DROP TABLE IF EXISTS `movimientoproducto`;
CREATE TABLE `movimientoproducto` (
  `MovimientoCodigo` int(10) NOT NULL COMMENT 'Código del Movimiento',
  `ProductoCodigo` int(10) NOT NULL COMMENT 'Código del Producto',
  `MovimientoProductoCant` int(5) NOT NULL COMMENT 'Cantidad de Producto del Movimiento',
  PRIMARY KEY (`MovimientoCodigo`,`ProductoCodigo`),
  KEY `ProductoCodigo` (`ProductoCodigo`),
  CONSTRAINT `movimientoproducto_ibfk_1` FOREIGN KEY (`MovimientoCodigo`) REFERENCES `movimientos` (`MovimientoCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `movimientoproducto_ibfk_2` FOREIGN KEY (`ProductoCodigo`) REFERENCES `productos` (`ProductoCodigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "movimientoproducto"
#

INSERT INTO `movimientoproducto` VALUES (1,1,10000),(2,1,10000);

#
# Structure for table "usuarios"
#

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador interno del usuario',
  `alias` varchar(30) NOT NULL COMMENT 'alias o nickname del usuario',
  `correo` varchar(100) NOT NULL COMMENT 'correo electronico del usuario',
  `contrasenya` varchar(100) NOT NULL COMMENT 'contraseña del usuario cifrada',
  `registro` datetime DEFAULT NULL COMMENT 'fecha de creacion',
  `acceso` datetime DEFAULT NULL COMMENT 'fecha de ultimo acceso',
  `modificacion` datetime DEFAULT NULL COMMENT 'fecha de modificacion como alias, correo o contraseña',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Usuarios_UN_ALIAS` (`alias`),
  UNIQUE KEY `Usuarios_UN_CORREO` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

#
# Data for table "usuarios"
#

INSERT INTO `usuarios` VALUES (1,'arcenioLupin','arcenioLupin.dev@gmail.com','03a25c10eb66ec11aebe5fd4b01e207d',NULL,'2018-10-31 01:25:39','2018-11-19 22:56:39'),(2,'arcenio','arcenio.dev@gmail.com','fc5e4620066dcb6e9f5081d2ee9ce4b6',NULL,NULL,'2018-08-15 00:06:59'),(18,'test33695','test62760@r.com','369fdaa2741244f57b484d8ac1828fd3',NULL,'2018-07-19 01:06:22','2018-07-19 01:06:22'),(21,'test15051','test15051@r.com','206f6a7ada917912e9389da75d80be3b',NULL,'2018-07-19 01:06:23','2018-07-19 01:06:23'),(22,'test45957','test76214@r.com','0566a85f6665677610d6af3482ea8dc4',NULL,'2018-07-19 01:14:31','2018-07-19 01:14:31'),(24,'test42228','test42228@r.com','3039a72740ccf74a6c840aacbef79b0b',NULL,'2018-07-19 01:14:31','2018-07-19 01:14:31'),(25,'test33961','test33961@r.com','cc1293b4eae66aa4fa2960e6ccb8ae96',NULL,'2018-07-19 01:14:31','2018-07-19 01:14:31'),(26,'akim','akimvilca.caceres@gmail.com','6d14ff7e8dc341f3c29b14e5cc5ec06a',NULL,'2018-07-21 23:45:08',NULL),(27,'rafexone2','rafex2.dev@gmail.com','79f87f817fdfed82e5d21800b7189f18',NULL,NULL,NULL),(28,'diego','diegovilca.saboya@gmail.com','b0ac23d94dcef21e36e457b4abf6e0cb',NULL,NULL,NULL),(31,'akim2','akimvilca1.caceres@gmail.com','3965fd2f8d55d7714988c1e3286a1553',NULL,NULL,NULL),(32,'arceniodev01','arcenio.dev01@gmail.com','03a25c10eb66ec11aebe5fd4b01e207d',NULL,'2018-11-19 22:23:13','2018-11-19 22:23:01');

#
# Structure for table "vendedores"
#

DROP TABLE IF EXISTS `vendedores`;
CREATE TABLE `vendedores` (
  `VendedorCodigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código del Vendedor',
  `VendedorNombre1` varchar(50) NOT NULL COMMENT 'Primer Nombre del Vendedor',
  `VendedorNombre2` varchar(50) DEFAULT NULL COMMENT 'Segundo Nombre del Vendedor',
  `VendedorApepat` varchar(50) NOT NULL COMMENT 'Apellido Paterno del Vendedor',
  `VendedorApemat` varchar(50) NOT NULL COMMENT 'Apellido Materno del Vendedor',
  `VendedorDocid` varchar(20) DEFAULT NULL COMMENT 'Documento de identidad del Vendedor',
  `VendedorDireccion` text COMMENT 'Dirección del Vendedor',
  `VendedorCelular1` varchar(20) DEFAULT NULL COMMENT 'Celular del Vendedor',
  `VendedorCelular2` varchar(20) DEFAULT NULL COMMENT 'Celular del Vendedor',
  `UsuarioCodigo` varchar(12) DEFAULT NULL COMMENT 'Código de Usuario',
  `VendedorComentado` mediumtext COMMENT 'Comentario sobre el Vendedor',
  `VendedorEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado de Vendedor: A: Activo, I: Inactivo',
  `VendedorFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `VendedorHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `VendedorUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `VendedorFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `VendedorHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `VendedorUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`VendedorCodigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "vendedores"
#

INSERT INTO `vendedores` VALUES (1,'ROSA','MERCEDES','SABOYA','SILVA','41917487','Av.Cesar Vallejo 1289 VES','992171543','','RSABOYA','NADA','A','2019-01-09',NULL,NULL,NULL,NULL,NULL),(2,'ARCENIO','FORTUNATO','VILCA','CACERES','41917487','Av.Cesar Vallejo 1289 VES','992171543','','AVILCA','NADA','A','2019-01-09',NULL,NULL,NULL,NULL,NULL);

#
# Structure for table "almacenvendedor"
#

DROP TABLE IF EXISTS `almacenvendedor`;
CREATE TABLE `almacenvendedor` (
  `AlmacenCodigo` int(10) NOT NULL COMMENT 'Código del Almacén',
  `VendedorCodigo` int(10) NOT NULL COMMENT 'Código del Vendedor',
  `AlmacenVendedorEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado del Vendedor en Almacén: A: Activo, I: Inactivo',
  PRIMARY KEY (`AlmacenCodigo`,`VendedorCodigo`),
  KEY `VendedorCodigo` (`VendedorCodigo`),
  CONSTRAINT `almacenvendedor_ibfk_1` FOREIGN KEY (`AlmacenCodigo`) REFERENCES `almacenes` (`AlmacenCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `almacenvendedor_ibfk_2` FOREIGN KEY (`VendedorCodigo`) REFERENCES `vendedores` (`VendedorCodigo`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "almacenvendedor"
#

INSERT INTO `almacenvendedor` VALUES (1,1,'A'),(2,1,'A'),(2,2,'A'),(3,1,'A');

#
# Structure for table "ventas"
#

DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `VentaCodigo` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Código de la Venta',
  `VentaFecha` date NOT NULL COMMENT 'Fecha de la Venta',
  `AlmacenCodigo` int(10) NOT NULL COMMENT 'Código del Almacén',
  `VendedorCodigo` int(10) NOT NULL COMMENT 'Código del Vendedor',
  `MonedaCodigo` varchar(3) NOT NULL COMMENT 'Código de Moneda',
  `VentaSubtotal` decimal(15,2) NOT NULL COMMENT 'Monto Subtotal de la Venta',
  `VentaDescuento` decimal(15,2) DEFAULT NULL COMMENT 'Monto Descuento de la Venta',
  `VentaTotal` decimal(15,2) NOT NULL COMMENT 'Monto Total de la Venta',
  `VentaEfectivo` decimal(15,2) DEFAULT NULL COMMENT 'Monto en Efectivo de la Venta',
  `VentaTarjeta` decimal(15,2) DEFAULT NULL COMMENT 'Monto en Tarjeta de la Venta',
  `VentaReferTarjeta` varchar(20) DEFAULT NULL COMMENT 'Referencia Tarjeta de la Venta',
  `VentaComentario` mediumtext COMMENT 'Comentario de la Venta',
  `VentaEstado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'Estado de la Venta: A: Aprobado, R: Revertido',
  `VentaFecre` date DEFAULT NULL COMMENT 'Fecha de Creación',
  `VentaHocre` time DEFAULT NULL COMMENT 'Hora de Creación',
  `VentaUscre` varchar(12) DEFAULT NULL COMMENT 'Usuario de Creación',
  `VentaFemod` date DEFAULT NULL COMMENT 'Fecha de Modificación',
  `VentaHomod` time DEFAULT NULL COMMENT 'Hora de Modificación',
  `VentaUsmod` varchar(12) DEFAULT NULL COMMENT 'Usuario de Modificación',
  PRIMARY KEY (`VentaCodigo`),
  KEY `VendedorCodigo` (`VendedorCodigo`),
  KEY `AlmacenCodigo` (`AlmacenCodigo`),
  KEY `MonedaCodigo` (`MonedaCodigo`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`VendedorCodigo`) REFERENCES `vendedores` (`VendedorCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`AlmacenCodigo`) REFERENCES `almacenes` (`AlmacenCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`MonedaCodigo`) REFERENCES `monedas` (`MonedaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ventas"
#


#
# Structure for table "ventadetalle"
#

DROP TABLE IF EXISTS `ventadetalle`;
CREATE TABLE `ventadetalle` (
  `VentaCodigo` int(10) NOT NULL COMMENT 'Código de la Venta',
  `VentaPosicion` int(3) NOT NULL COMMENT 'Posición de la Venta',
  `ProductoCodigo` int(10) NOT NULL COMMENT 'Código del Producto',
  `VentaProductoCant` int(5) NOT NULL COMMENT 'Cantidad de Producto de la Venta',
  `UnidadMedidaCodigo` varchar(3) NOT NULL COMMENT 'Código de la Unidad de Medida',
  `VentaProductoPrecio` decimal(15,2) NOT NULL COMMENT 'Precio del Producto de la Venta',
  `VentaProductoTotal` decimal(15,2) NOT NULL COMMENT 'Monto total del Producto de la Venta',
  PRIMARY KEY (`VentaCodigo`,`VentaPosicion`),
  KEY `ProductoCodigo` (`ProductoCodigo`),
  KEY `UnidadMedidaCodigo` (`UnidadMedidaCodigo`),
  CONSTRAINT `ventadetalle_ibfk_1` FOREIGN KEY (`VentaCodigo`) REFERENCES `ventas` (`VentaCodigo`) ON UPDATE CASCADE,
  CONSTRAINT `ventadetalle_ibfk_2` FOREIGN KEY (`ProductoCodigo`) REFERENCES `productos` (`ProductoCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `ventadetalle_ibfk_3` FOREIGN KEY (`UnidadMedidaCodigo`) REFERENCES `unidadesmedida` (`UnidadMedidaCodigo`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "ventadetalle"
#

