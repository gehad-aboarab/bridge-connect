# Bridge Connect
This system provides admin functionalities as well as manager functionalities. The admin functionalities include adding and removing managers, add projects to managers, removing projects under managers, and removing projects. As for manager functionalities, the system allows a manager to view a summary of their project information, update project stage based on stage dependencies, add project materials, remove project materials, and view a graphical summary of the project based on the progress at each project stage.


The application is built in Java and HTML using Java Server Faces (JSF) and it makes use of Java Persistence API (JPA) to connect with a local database. It also uses JavaScript to display tabs in the admin page, and to display a bar chart showing managers the progress of their projects. Security is enforced by hashing all passwords before storing them in the database, as well as checking view action for every page.
Validation of inputs is mostly done in the JSF pages, although some other validations that require more work are done in beans (for example validating the stage update). All error messages are displayed on the same page where the error occurred.

