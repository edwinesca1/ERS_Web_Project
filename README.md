# Employee Reimbursement System
## Description
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.
## Tech stack
- Java 8
- HTML
- CSS
- JavaScript
- JDBC
- PostgreSQL
- Maven
- JUnit 4.12
- Log4j 1.2
- Mockito 3.7
- DBeaver
- TomCat Apache Server v9

## Requirements
- The application shall employ the DAO design pattern, and properly separate your code into the appropriate layers
- The back-end system shall use JDBC to connect to an Oracle database.
- Use at least one of each of our three statements - Statement, PreparedStatement, and CallableStatement
- The application shall deploy onto a TomCat Server
- The middle tier shall use Servlet technology for dynamic Web application development
- The front-end view can use JavaScript and use AJAX to call server-side components. The web pages should look presentable (try using CSS and bootstrap); I'd rather not see a website from 1995.
- Use Log4J and JUnit. There should be 75% code coverage of your service methods
- (OPTIONAL) Passwords should be encrypted in Java nad securely stored in the database
- (OPTIONAL) Users can upload a document or image of their receipt when submitting reimbursements
- (OPTIONAL) The application will send an email to employees letting them know that they have been registered as a new user, giving them their temporary password
