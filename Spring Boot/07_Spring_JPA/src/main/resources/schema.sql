
-- Departments

INSERT INTO department (department_id, department_name)
VALUES (1, 'IT');

INSERT INTO department (department_id, department_name)
VALUES (2, 'HR');

INSERT INTO department (department_id, department_name)
VALUES (3, 'Finance');

INSERT INTO department (department_id, department_name)
VALUES (4, 'Admin');


-- Employees

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (101, 'Vivek', 70000, 1);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (102, 'Amit', 50000, 1);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (103, 'Neha', 45000, 2);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (104, 'Rahul', 80000, 1);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (105, 'Priya', 55000, 3);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (106, 'Karan', 60000, 3);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (107, 'Sneha', 40000, 2);

INSERT INTO employee (employee_id, employee_name, salary, department_id)
VALUES (108, 'Ajay', 35000, 4);

SELECT * FROM department;

SELECT * FROM employee;