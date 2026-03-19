# Mortgage Calculator with Payment Schedule Visualization

A **mortgage and loan calculator** developed in **Java using JavaFX**.  
The application allows users to calculate loan repayment schedules, visualize payments over time, filter payment data, and export reports.

The program supports multiple loan calculation methods and provides a graphical interface for exploring how loan parameters affect monthly payments.

Note: The application interface is currently available in Lithuanian.

## Screenshot

![Application Interface](https://github.com/user-attachments/assets/1fa3f0af-71a3-4a1a-8a53-fdf28521b027)


---

## Project Overview

This project was built as a learning exercise in **financial calculations, object-oriented programming, and JavaFX application development**.

Key goals of the project:

* Implement structured **loan repayment calculation algorithms**
* Support multiple **repayment methods**
* Separate **UI, business logic, and data models**
* Provide **visualization of payment trends**
* Allow users to **export repayment schedules**

The application calculates monthly payments based on loan parameters such as **loan amount, interest rate, repayment term, and optional payment deferral**.

---

## Features

* **Loan repayment schedule calculation**
* Support for two repayment methods:
  * **Annuity payments**
  * **Linear payments**
* **Optional payment deferral period**
* **Payment table** showing date, monthly payment, interest, principal, and remaining balance
* **Graph visualization** of monthly payments
* **Date range filtering** for payment schedules
* **CSV report export** of payment data
* Desktop interface built with **JavaFX**

---

## Application Usage

The application allows the user to configure a loan and explore how the repayment evolves.

Typical workflow:

1. Enter **loan amount**
2. Enter **interest rate**
3. Choose **loan term** (years and months)
4. Select repayment type:
   * Annuity
   * Linear
5. Optionally configure a **deferral period**
6. Generate the **payment schedule**
7. View results in a **table and chart**
8. Export the schedule as a **CSV report**

---

## Architecture

Core components include:

* **LoanApplication** – main JavaFX application entry point  
* **LoanController** – handles UI logic and user input  
* **LoanCalculator** – abstract base class for loan calculation strategies  
* **AnnuityCalculator** – calculates fixed monthly payments  
* **LinearCalculator** – calculates payments with decreasing interest  
* **Loan / Payment / Deferral** – domain models representing loan data  
* **ChartDataBuilder** – builds chart data for payment visualization  
* **PaymentFilter** – filters payments by date range  
* **ReportGenerator** – exports repayment schedules to CSV files  

---

## Payment Calculation

The application supports two common mortgage repayment strategies.

### Annuity Payments

A fixed monthly payment where the **interest portion decreases over time** and the **principal portion increases**.

### Linear Payments

The principal portion remains **constant each month**, while the interest decreases as the remaining balance becomes smaller.

---

## Technologies Used

* **Java**
* **JavaFX**
* **FXML**
* **Maven**
* **Object-Oriented Programming**
* **CSV file generation**

---

## How to Run

Clone the repository:

    git clone https://github.com/gabriele-kazdailyte/mortgage-calculator.git
    cd mortgage-calculator

Run the application using Maven:

    mvn clean javafx:run

---

## Learning Outcomes

This project helped develop practical experience with:

- Java object-oriented design
- Separation of **UI and business logic**
- JavaFX desktop application development
- Implementing **financial calculation algorithms**
- Data visualization with charts
- File generation and exporting reports

---

## Author

Developed as a personal programming project while studying **Software Engineering**, focusing on building practical **Java development and software engineering skills**.
