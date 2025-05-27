create a table  using script

CREATE TABLE `master_schema` (
`id` bigint NOT NULL AUTO_INCREMENT,
`sample` json,
`value` varchar(255) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB

validate a  current Json schema with a Master Json schema