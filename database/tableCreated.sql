CREATE TABLE `casts` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `movies` (
  `id` int NOT NULL,
  `alt` varchar(255) DEFAULT NULL,
  `year` year DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rating` decimal(4,1) DEFAULT NULL,
  `original_title` varchar(255) DEFAULT NULL,
  `directors` varchar(255) DEFAULT NULL,
  `casts` varchar(255) DEFAULT NULL,
  `genres` varchar(255) DEFAULT NULL,
  `durations` varchar(20) DEFAULT NULL,
  ` image` varchar(255) DEFAULT NULL,
  `summary` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;