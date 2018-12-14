#Instructions to run RDBM

##DataBase Setup
1. Create a new database by the name "newnewsdb" in postgres in terminal, with the command 
				CREATE DATABASE newnewsdb;

2. Create username and password is test/password.
				CREATE USER test WITH PASSWORD 'password';

3. Exit from the postgres environment and import the data sql file "newnewsdb.sql" to the newly created database with the command,
				psql newnewsdb < (path of the file)/newnewsdb.sql

#Instructions to run MongoDB

##DataBase Setup
1. Create a new database by the name "newnewsdb" in MongoDB in terminal, with the command 
					use newnewsdb
2. Move to the bin folder in the MongoDB folder and import the json data by running the following import commands
				./mongoimport -d newnewsdb -c users --drop --file  <path of the file>/users.json
				./mongoimport -d newnewsdb -c news --drop --file  <path of the file>/news.json

				
##Property Files
1. There are 3 properties file in WEB-INF
		* build.properties
		* news.properties
		* rdbm.properties
2. The build.properties is the property file with configuration details for build.xml.
3. The news.properties is the property file with configuration details of the memory source to use for DAO implementation.
4. The rdbm.properties is the property file with Postgres configuration.



		