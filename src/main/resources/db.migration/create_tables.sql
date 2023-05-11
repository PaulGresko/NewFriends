create table user(
  login VARCHAR(30) PRIMARY KEY ,
  password VARCHAR(200),
  status  VARCHAR(30)
);
create table user_data(
    login VARCHAR(30) PRIMARY KEY,
    name VARCHAR(50),
    description VARCHAR(200),
    sex VARCHAR(1),
    image VARCHAR(500),
    birthday DATE,
    city VARCHAR(50),
    zodiac_sign VARCHAR(20),
    FOREIGN KEY (login) REFERENCES user (login) ON DELETE CASCADE ON UPDATE CASCADE
);
create table message(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    time TIME,
    sender VARCHAR(30),
    recipient VARCHAR(30),
    text VARCHAR(200),
    FOREIGN KEY (sender) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (recipient) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE
);
create table complaint(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    date DATE,
    time TIME,
    sender VARCHAR(30),
    victim VARCHAR(30),
    text VARCHAR(200),
    FOREIGN KEY (sender) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (victim) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE
);
create table friends(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    friend1 VARCHAR(30),
    friend2 VARCHAR(30),
    status VARCHAR(30),
    FOREIGN KEY (friend1) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (friend2) REFERENCES user_data(login) ON DELETE CASCADE ON UPDATE CASCADE
);
create table token(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    token varchar(255) UNIQUE,
    expired boolean,
    user varchar(255),
    FOREIGN KEY (user) REFERENCES user(login) ON DELETE CASCADE ON UPDATE CASCADE
);