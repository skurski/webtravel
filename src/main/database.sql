    drop database if exists webtravel;
    CREATE DATABASE IF NOT EXISTS `webtravel`;
    USE `webtravel`;  
      
    CREATE TABLE IF NOT EXISTS `user` (  
      `id` int(11) NOT NULL AUTO_INCREMENT,  
      `first_name` varchar(45) DEFAULT NULL,  
      `last_name` varchar(45) DEFAULT NULL,  
      `email` varchar(45) DEFAULT NULL,  
      `password` varchar(40) DEFAULT NULL,
      `phone` varchar(45) DEFAULT NULL,  
      PRIMARY KEY (`id`)  
    ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;  
      
	CREATE TABLE IF NOT EXISTS `travel` (
	    `id` int(11) NOT NULL AUTO_INCREMENT,
	    `user_id` int(11) DEFAULT NULL,
	    `name` varchar(512) DEFAULT NULL,
	    `location` varchar(512) DEFAULT NULL,
	    `desc` text DEFAULT NULL,
	    `coordinate` text DEFAULT NULL,
	    PRIMARY KEY (`id`),
	    KEY `user_id` (`user_id`),
	    CONSTRAINT `travel_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE   
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
     
	CREATE TABLE IF NOT EXISTS `gallery` (
	    `id` int(11) NOT NULL AUTO_INCREMENT,
	    `travel_id` int(11) DEFAULT NULL,
	    `path` varchar(512) DEFAULT NULL,
	    `title` varchar(512) DEFAULT NULL,
	    PRIMARY KEY (`id`),
	    KEY `travel_id` (`travel_id`),
	    CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`travel_id`) REFERENCES `travel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE   
	) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
	
    INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `password`, `phone`) VALUES  
     (1, 'Piotr', 'Skurski', 'p@p.pl', '22ww', '111222333');
	
    INSERT INTO `travel` (`id`, `user_id`, `name`, `location`) VALUES (1, 1, 'Wakacje 2015', 'Albania - Tirana');
    INSERT INTO `travel` (`id`, `user_id`, `name`, `location`) VALUES (2, 1, 'Wakacje 2014', 'Bułgaria - Złote piaski');
    INSERT INTO `gallery` (`id`, `travel_id`, `path`, `title`) VALUES (1, 1, '/resources/upload/thumbnail.png', 'Travel Image');
    INSERT INTO `gallery` (`id`, `travel_id`, `path`, `title`) VALUES (2, 2, '/resources/upload/thumbnail.png', 'Travel Image');

