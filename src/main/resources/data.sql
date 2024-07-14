CREATE TABLE employee (
    id            INTEGER PRIMARY KEY,
    name          VARCHAR(64) NOT NULL,
    bossId        INTEGER,

    FOREIGN KEY (bossId) REFERENCES employee (id)
);

CREATE TABLE indicator (
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


INSERT INTO indicator
VALUES
('SoftSkills', 1, 46),
('SoftSkills', 2, 90),
('SoftSkills', 3, 95),
('SoftSkills', 4, 80),
('SoftSkills', 5, 100),

('Java', 5, 70),
('Java', 4, 10),
('Java', 3, 50),
('Java', 2, 100),
('Java', 1, 100),

('JS', 5, 20),
('JS', 4, 20),
('JS', 3, 40),
('JS', 2, 50),
('JS', 1, 100);



