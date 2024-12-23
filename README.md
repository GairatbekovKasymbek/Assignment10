# Employee Salary Management System (JavaFX)

## Project Description

This **JavaFX** application is designed to manage a list of employees and their salaries. The system uses **object-oriented programming (OOP)** principles, with an **abstract class** `Employee` and multiple subclasses for different types of employees, such as **Full-Time**, **Part-Time**, and **Contractors**.

The application allows users to:

- View a list of employees in a table format.
- Add new employees with their details (name, type, hourly rate, etc.).
- Calculate and display their salaries based on the type of employee (Full-Time, Part-Time, Contractor).
- Remove employees from the list.

The application provides an intuitive GUI built with **JavaFX** to allow easy interaction with the employee data.

---

## Features

### Abstract Class for Employees
The `Employee` class provides an abstract method `calculateSalary()` that is implemented by subclasses for salary calculation:

- **FullTimeEmployee**: Fixed annual salary.
- **PartTimeEmployee**: Salary based on hourly wage and hours worked.
- **Contractor**: Salary based on hourly rate and a fixed number of hours worked per month.

### JavaFX GUI

- **TableView**: Displays employee details and calculated salaries.
- **Text Fields**: For entering employee details such as name, type, hourly rate, and hours worked.
- **Buttons**: For adding new employees, calculating salaries, and removing employees.
- **Dropdown (ComboBox)**: To select employee type (Full-Time, Part-Time, or Contractor).

---

## Sample Inputs

#### FullTimeEmployee
- **Name**: John Doe  
- **Type**: Full-Time  
- **Salary**: 50,000.00 (fixed annual salary)

#### PartTimeEmployee
- **Name**: Jane Smith  
- **Type**: Part-Time  
- **Hourly Rate**: 20.00  
- **Hours Worked**: 25  
- **Salary**: 500.00 (calculated based on hourly rate and hours worked)

#### Contractor
- **Name**: Mike Johnson  
- **Type**: Contractor  
- **Hourly Rate**: 30.00  
- **Max Hours**: 160  
- **Salary**: 4,800.00 (calculated based on hourly rate and max hours worked)

---

## How to Run the Program

### Prerequisites
1. **Java Development Kit (JDK)**: Version 11 or higher.
2. **JavaFX SDK**: Download and set up the JavaFX SDK and configure it in your IDE.
3. **IDE**: Use IntelliJ IDEA, Eclipse, or any Java IDE that supports JavaFX development.
4. **Git**: For cloning the repository.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/GairatbekovKasymbek/Assignment10.git

---

2. Pict 
![image](https://github.com/user-attachments/assets/258c58a1-aee8-4a62-ac4b-03c937fb936b)
![image](https://github.com/user-attachments/assets/38a76188-ef38-4a42-801c-6c5bd61e8ab2)


