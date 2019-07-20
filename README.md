# travel-agency
## Steps to follow to run the project

### Clone the project from github

### Setup Environment
#### Create a database named ```travel-agency```
#### Go inside project 
#### Open terminal and run ```mvn spring-boot:run ```
#### Browse ```http://localhost:8080/ ``` on your browser
#### Enjoy your time

## Database schema
#### ```Location``` Table. This table holds Location information.
```xml
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `location` (`id`, `name`) VALUES
(1, 'Sylhet'),
(2, 'Bandarban'),
(3, 'Khulna')
```
#### ```Role``` Table. This Table holds Role Information
```xml
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
#### ```User``` Table. This Table holds User Information

```xml
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

#### ```Users_Roles``` Table. This Table is the Linked table of user and role

```xml
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```

#### ```post``` Table. This table holds all Posts(Status)

```xml
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `body` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `last_updated_at` datetime DEFAULT NULL,
  `visibility` varchar(15) DEFAULT NULL,
  `location` bigint(20) DEFAULT NULL,
  `owner` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
#### ```Pinned_Post``` Table. This table tracks pinned record of an user

```xml
CREATE TABLE `pinned_post` (
  `id` bigint(20) NOT NULL,
  `owner` bigint(20) DEFAULT NULL,
  `post` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
## ER Diagram

![ERD](https://github.com/zeromsi/travel-agency/blob/master/ERD.png)

