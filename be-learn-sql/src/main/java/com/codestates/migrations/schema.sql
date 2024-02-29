CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) not NULL,
  `email` varchar(255) not NULL
);

CREATE TABLE `content` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) not NULL,
  `body` varchar(255) not NULL,
  `created_at` timestamp not NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `content` ADD userId int;
ALTER TABLE `content` ADD FOREIGN KEY (`userId`) REFERENCES `user` (`id`);


CREATE TABLE `category` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) not NULL
);


CREATE TABLE `content_category` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `contentId` int NOT NULL, 
  `categoryId` int NOT NULL
);


ALTER TABLE `content_category` ADD FOREIGN KEY (`contentId`) REFERENCES `content` (`id`);
ALTER TABLE `content_category` ADD FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`);

CREATE TABLE `role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) not NULL
);

ALTER TABLE `user` ADD roleId int;
ALTER TABLE `user` ADD FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);