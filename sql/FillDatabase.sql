USE MVCSample;
GO

INSERT INTO Students (FirstName, LastName) VALUES
('Bob', 'Smith'),
('Alice', 'Johnson'),
('Robert', 'Williams');

INSERT INTO Groups (Name) VALUES
('Math'),
('Biology');

INSERT INTO GroupStudents (GroupId, StudentId) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);
