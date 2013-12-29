Create table IF NOT EXISTS Buildings
(
building_ID VARCHAR(20) NOT NULL PRIMARY KEY,
building_name VARCHAR(50),
vertices int NOT NULL,
coordinates Polygon NOT NULL
);

Create table IF NOT EXISTS Students
(
person_id varchar(20) NOT NULL Primary Key,
x_coor int NOT NULL,
y_coor int NOT NULL,
student_loc point NOT NULL
);

Create table IF NOT EXISTS Announcements 
(
as_id varchar(20) NOT NULL Primary Key,
x_coor int NOT NULL,
y_coor int NOT NULL,
radius int NOT NULL,
as_loc point NOT NULL
);

