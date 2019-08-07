# Bridge Connect
This system provides admin functionalities as well as manager functionalities. The admin functionalities include adding and removing managers, add projects to managers, removing projects under managers, and removing projects. As for manager functionalities, the system allows a manager to view a summary of their project information, update project stage based on stage dependencies, add project materials, remove project materials, and view a graphical summary of the project based on the progress at each project stage.

The application is built in Java and HTML using Java Server Faces (JSF) and it makes use of Java Persistence API (JPA) to connect with a local database. It also uses JavaScript to display tabs in the admin page, and to display a bar chart showing managers the progress of their projects. Security is enforced by hashing all passwords before storing them in the database, as well as checking view action for every page.
Validation of inputs is mostly done in the JSF pages, although some other validations that require more work are done in beans (for example validating the stage update). All error messages are displayed on the same page where the error occurred.

## Running the application
### Step 1
Clone the repository and open the project in Netbeans.

### Step 2
Under the services tab on the left panel, click on Databases and create a database called 'Construction' and give it a connection name and password 'construction'.

### Step 3
Create a schema called 'CONSTRUCTION' and copy the contents of the file 'sql-script' (found in this repo) into the commands window of the new schema. Execute the commands after you've copied them.

### Step 4
Run the Glassfish server.

### Step 5 
Deploy the project and then right click adminLogin.xhtml and click 'Run file'. Login credentials: username: admin password: admin

### Step 6 
Create a new manager in order to be able to log in as that manager later on.

Documentation of functionalities and steps, as well as screenshots of the application can be found at: https://drive.google.com/open?id=1SxVcy0ANw5dC8gV0Fo3mEDVAb1Z5aqXc
