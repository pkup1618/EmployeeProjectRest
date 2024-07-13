CREATE TABLE employee (
    id            INTEGER PRIMARY KEY,
    name          VARCHAR(64) NOT NULL,
    bossId        INTEGER,

    FOREIGN KEY (bossId) REFERENCES employee (id)
);

CREATE TABLE metric (
    name VARCHAR(64),
    employeeId INTEGER,
    `value` INTEGER NOT NULL,

    CHECK (`value` >= 0 AND `value` <= 100),
    PRIMARY KEY(name, employeeId),
    FOREIGN KEY (employeeId) REFERENCES employee (id)
);

INSERT INTO employee
VALUES
(5, 'Vlad', NULL),
(4, 'Grigory', 5),
(3, 'Nikita', 5),
(2, 'Boris', 3),
(1, 'Nikolai', 3);


INSERT INTO metric
VALUES
('SoftSkills', 1, 20),
('SoftSkills', 2, 40),
('SoftSkills', 3, 60),
('SoftSkills', 4, 80),
('SoftSkills', 5, 100),
('HardSkills', 5, 20),
('HardSkills', 4, 40),
('HardSkills', 3, 60),
('HardSkills', 2, 80),
('HardSkills', 1, 100);



