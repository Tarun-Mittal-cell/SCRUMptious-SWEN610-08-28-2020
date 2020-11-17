Instruction to run project.

1. Clone the github repository(https://github.com/Tarun-Mittal-cell/SCRUMptious-SWEN610-08-28-2020) or unzip the zipped version of the project.
2. Open project in IntelliJ
2. Download and install MySQL server and Workbench Tool.
3. Open the database model file (Updated_Nov_16_2020.mwb) contained in package in MYSQL workbench from file tab.
4. From the database tab select forward engineer option to create the Database and tables forward.
5. Use the following command to create the default Admin user (the default password is myplsadmin)

QUERIES TO TYPE:
a. USE mypls
b. INSERT INTO users
VALUES ('myplsadmin@rit.edu',
'9a387f0eb80379c80ed6a7c9d16a96355f5f102eceac04f5cf4b9acec67ff465',
'Administrator');
 
6. Create a "mypls" user and password as "password" to connect to the MySQL database using the User and Privlege Administration section of Workbench.