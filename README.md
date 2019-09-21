![Version Tag](https://img.shields.io/badge/Version-1.0.0-blue.svg)

# Student-Project-Finance-Portal

## Introduction 

To build a Finance Portal for the students’ project of MIT to carry out reimbursements and other Financial transactions, hassle-free. 

## Abstract

Every year, students’ projects of MIT are allotted a fixed budget. All the purchases, a team makes, are later reimbursed by the college on monthly basis. The team has to submit relevant documents and bills attested by the Faculty Advisor to the college. The money is then reimbursed to the faculty advisor’s bank account. The Faculty Advisor then transfers it to the team account.  The biggest problem is, these purchases made by the team are not made by a single person. These are made by a random team member on need basis. Hence the team in-charge has to make sure that the reimbursement amount reaches the respective person. This process involves a lot of documentation, data entry and maintenance. One person needs to enter all the bill details with respect to merchant and the student who will actually receive the reimbursement amount. For this the student has to make sure that he submits the bill to the management person who will enter the details. The team in-charge also has to keep a track of all the bills from time to time.  The Finance Portal will help to automate this process and make it user-friendly so that any team member can easily use this portal. Now any team member can add bill details directly into the database. This bill should be authorized by the faculty advisor in-charge and only then will the amount be transferred to the respective member. This portal will also keep a track of amount, that needs to be reimbursed for every team member. 

## Index
- [Introduction](#introduction)
- [Abstract](#abstract)
- [Index](#index)
- [Tools](#tools)
- [Data Tables](#data-tables)
- [Repository Description](#repository-description)
- [UI Design](#ui-design)

## Tools -

- <b>Front End</b>: [Java](https://www.eclipse.org/windowbuilder/).
- <b>Back End</b>: [Oracle Database](https://www.oracle.com/in/database/).

## Data Tables -

- <b>Faculty Advisor</b>:<br> 

|   Member  |   Type  | Size |
|:---------:|:-------:|:----:|
| Name      | VARCHAR | 20   |
| <b>ID</b> | NUMBER  | 12   |
| PASS_HASH | VARCHAR | 30   |

```SQL
CREATE TABLE FAC_ADV
(  
	Name VARCHAR(20),
	ID NUMBER(12),
	PASS_HASH VARCHAR(32),
	PRIMARY KEY (ID) 
);
```

- <b>Bills</b>:<br>

|      Member     |   Type  |  Size  |
|:---------------:|:-------:|:------:|
| <b>Bill_ID</b>  | NUMBER  | 5      |
| Bill_Date       | DATE    | -      |
| Amt             | NUMBER  | (10,2) |
| Member_ID       | NUMBER  | 12     |
| Approval_Status | VARCHAR | 9      |
| Merchant_Name   | VARCHAR | 30     |

```SQL
CREATE TABLE BILLS
(  
	Bill_ID NUMBER(5),
	Bill_Date DATE,
	Amt NUMBER(10,2),
	Member_ID NUMBER(12),
	Approval_Status VARCHAR(9),
	Merchant_Name VARCHAR(30),
	PRIMARY KEY (Bill_ID) 
); 
```

- <b>Team</b>:<br>

| 		Member  |   Type  | Size |
|:-------------:|:-------:|:----:|
| 	  Team_Name | VARCHAR | 20   |
| <b>Team_ID</b>| NUMBER  | 5    |
| <i>FA_ID</i>  | NUMBER  | 12   |

```SQL
CREATE TABLE TEAM 
(  
	Team_Name VARCHAR(20),
	Team_ID NUMBER(5),
	FA_ID NUMBER(12),
	PRIMARY KEY (Team_ID),
	FOREIGN KEY (FA_ID) REFERENCES FAC_ADV(ID) ON DELETE SET NULL 
); 
```

- <b>Member</b>:<br>

|   Member  	|   Type  | Size |
|:-------------:|:-------:|:----:|
| Name      	| VARCHAR | 20   |
| <b>ID</b>		| NUMBER  | 12   |
| <i>Team_ID</i>| NUMBER  | 5    |
| PASS_HASH 	| VARCHAR | 32   |

```SQL
CREATE TABLE MEMBER
(  
	Name VARCHAR(20),
	ID NUMBER(12),
	Team_ID NUMBER(5),
	PASS_HASH VARCHAR(32),
	PRIMARY KEY (ID),
	FOREIGN KEY (Team_ID) REFERENCES TEAM(Team_ID)  ON DELETE SET NULL 
);
``` 

## Repository Description

The files in the repository are :

### src
This Folder contains the Source Code files for the Project.

- AddBillPage.java: Code for Adding Bills.
- BooleanTableModel.java: Code for Generating Tables wrt the Bills along with Approval Status.
- DataLink.java: Linker Code to simplify operations for the Database.
- FacultyWindow.java: Code for Faculty View Page.
- LoginPage.java: Code for Login Page.
- Main.java: Main File for Project (Contains the "main" Function).
- MemberPage.java: Code for Member View Page.
- SignupPage.java: Code for Signup Page.
- WelcomePage.java: Code for Welcome Screen Page.

### README.md
The Description file containing details about the repository. The file that you looking at right now.

### DBS PROJECT REPORT.pdf
The Project Report. Contains the Same Data as this README.

### Schema and ER Block.pdf
The ER Block Diagram and Schema for Project.

### .UI_Output
This folder contains the Output images for the README.

## UI Design

Welcome Page: <br> 
[!Image](./.UI_Output/1.png) <br>
Sign Up Page (Team Member): <br>
[!Image](./.UI_Output/2.png) <br>
Sign Up Page(Faculty): <br>
[!Image](./.UI_Output/3.png) <br>
Log In Page (Team Member): <br>
[!Image](./.UI_Output/4.png) <br>
Log In Page (Faculty): <br>
[!Image](./.UI_Output/5.png) <br>
Member Page: <br>
[!Image](./.UI_Output/6.png) <br>
Add Bills: <br>
[!Image](./.UI_Output/7.png) <br>
Faculty Page: <br>	
[!Image](./.UI_Output/8.png) <br>

[![Developers Tag]( https://img.shields.io/badge/Developer-shashank3199-red.svg ) ]( https://github.com/shashank3199 )