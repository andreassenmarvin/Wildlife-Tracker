# WILDLIFE TRACKER
Wildlife Tracker is an app that allows rangers to track wildlife sightings.You can create rangers,locations,animals and sightings, and view them as well.One can also view sightings a ranger has made and locations with the sightings that have occurred in them.
## Description
![Website image](/Assets/README/scr1.png)
![Website image](/Assets/README/scr2.png)
![Website image](/Assets/README/scr3.png)
![Website image](/Assets/README/scr4.png)
## Setup/Installation Requirements
* Fork/Clone the repository
```
   $ git clone https://github.com/andreassenmarvin/Wildlife-Tracker.git
```
* Open your project on IntelliJ
* Run project
```
   $ gradle run
```
## Database Setup Instructions
##### In PSQL:
* CREATE DATABASE wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar,type varchar,health varchar,age varchar);
* CREATE TABLE locations (id serial PRIMARY KEY,name varchar);
* CREATE TABLE rangers (id serial PRIMARY KEY,name varchar,badge_number varchar);
* CREATE TABLE sightings (id serial PRIMARY KEY,animal_id int,ranger_id int,location_id int,time TIMESTAMP);

* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
## Known Bugs
No known bugs
## Technologies Used
* Handlebars
* CSS
* Java
* Spark 
* JavaScript
* Bootstrap
* PostgreSQL
* Heroku
## Support and contact details
 Incase of any contributions,query or issues,you can reach me through the email below:
machariamarvin625@gmail.com
### License
This project is licensed under the [MIT License](https://github.com/andreassenmarvin/Wildlife-Tracker/blob/master/LICENSE).