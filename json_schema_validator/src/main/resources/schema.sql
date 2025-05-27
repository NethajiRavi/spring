CREATE TABLE `master_schema` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sample` json,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB

CREATE TABLE IF NOT EXISTS `master_schema_another` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sample` json,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB