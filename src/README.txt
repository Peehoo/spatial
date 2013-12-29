Name: Peehoo Dewan
Student ID: 5737-2200-76
Email: pdewan@usc.edu



List of Files Submitted:

HW2.java -> contains GUI for the database program
Populate.java -> contains the program that adds data to the database
AnnouncementSystemMouseListener.java -> implements mouse click and move events
createDB.sql -> creates the database
dropDB.sql -> drops the databases

Prerequisites
* MySQL 5.6 or above
* Java 6 or above
* A database named Geometric is created in mysql using the default user (root) that
 has no password. I have hardcoded the database connection string since the specification
  was missing in assignment description.
* Input files (*.xy) are copied in the src directory


For running the program:

1. Create a new database called Geometric.
2. Use Geometric
3. Run dropdb.sql first to make sure that the database does not contain the same 	
	table which you will create
4. Run createdb.sql
5. Go to command prompt and complie the files as follows:
	(1) If running from linux write -  sh compileAll.sh     at the command prompt or the command given below
	
	javac -cp ../Lib/mysql-connector-java-5.1.27-bin.jar:../Lib/jts-1.8.jar:../Lib/jtsio.jar  *.java
	
	(2) If running from Windows machine write -  complieAll.bat    at the command prompt 
	or the command given below
	
	javac -cp ../Lib/mysql-connector-java-5.1.27-bin.jar;../Lib/jts-1.8.jar;../Lib/jtsio.jar  *.java
	
Note :  Please note that my program needs 3 libraries to compile which are 
			mysql-connector-java-5.1.27-bin.jar
			jts-1.8.jar	
			jtsio.jar
			
		I have included these libraries (mysql jdbc connector and 2 jars from vividsolutions). 
		Mysql does not support distance function between geometries and while selecting 
		a geometric object, it returns a bit stream of the geometric object. I am using 
		the two jars from vividsolutions to solve the preceding problems: 
		(1) To find distances between geometries and 
		(2) to convert bitstreams returned by mysql to geometric objects.
		
References :  http://www.dev-garden.org/2011/11/27/loading-mysql-spatial-data-with-jdbc-and-jts-wkbreader/
I am using the method provided in this blog to convert the bit stream to geometric object
		 
6. For running populate.java at command prompt:
	(1) If running from linux write -       sh populate.sh       at the command prompt or the command given below
	
	java -cp ../Lib/mysql-connector-java-5.1.27-bin.jar:. Populate buildings.xy students.xy announcementSystems.xy
	
	(2) If running from Windows machine write -   populate.bat        at the command prompt or the command given below
	
	java -cp ../Lib/mysql-connector-java-5.1.27-bin.jar;. Populate buildings.xy students.xy announcementSystems.xy
	
7. For running HW2.java at the command prompt:
	(1) If running from linux write -		sh HW2.sh		at the command prompt or the command given below
	
	java -cp ../Lib/mysql-connector-java-5.1.27-bin.jar:.:../lib/jts-1.8.jar:../lib/jtsio-1.8.jar  HW2
	
	(2) If running from Windows machine write -  HW2.bat        at the command prompt or the command given below
	
	java -cp ../Lib/mysql-connector-java-5.1.27-bin.jar;.;../lib/jts-1.8.jar;../lib/jtsio-1.8.jar  HW2
	
8. dropdb.sql to drop all the tables from the database.



Resolution:
I have implemented 3 classes in my program. Brief description of each is as follows:

1. Populate.java - This program reads in the values from the files supplied as command line argument and populates the database.
The Populate class implements 5 private methods (3 for making 3 different tables buildings, students and announcement system, 1
for establishing connection with the database and 1 for executing the query)

2. HW2.java - This program provides the GUI for the application and queries the database to run the features of the application.
This class extends JFrame and we override the paintComponent method to get the desired functionality. It uses another class called
AnnouncementSystemMouseListener(description given below) that implements mouse events. For implementing the functionality of
Point Query, Range Query, Surrounding students Query and Emergency Query, this class gets the location of mouse pointer from 
AnnouncementSystemMouseListener. 

Assumption for the clicked point - I am displaying the clicked point as a small red square so that the user knows where he has 
clicked on the map.

* For point query, after getting the point where a user has clicked, this class displays the point as per the specification and when
the user presses the submit button it would query the database depending on the active features selected and displays them as per
the specifications.

* For range query, the user can click multiple points on the map. Depending on the number of points clicked (left click), this 
class displays lines between consecutive points until a right click is encountered. On getting a right click, all the previous lines
get combined to form a polygon and the class displays the polygon as per the specification. When the user clicks submit button,
it would query the database depending on the active features selected and displays them as per the specifications.

Assumptions for range query - 
(1) If the user right clicks to complete the polygon and then left click again (with or without) pressing 
the submit query button, then the already captured points do not reset and we keep adding points to polygon until the user decides
to use the formed polygon.

(2) If the user presses the submit query button without giving a right click, it resets the polygon because we do not have a closed
polygon yet.

* For surrounding students query, after getting the point where a user has clicked, this class queries the database to get the
closest announcement system to this point and displays as per the specification. After the user presses submit button, it would
query the database and display the students in the region of this announcement system as per the specification.

* For emergency query, after getting the point where a user has clicked, this class queries the database to get the
closest announcement system to this point and displays as per the specification. After the user presses the submit button, 
this class creates a map which stores the location of announcement systems (excluding the one which is broken)
as the key and the set of students
closest to it as  values. 
A random function is used to generate the colors for each key (and its associated students) and the result is displayed 
as per specification. Note that since we use a random generator to generate a new color, we may end up with similar colors
for different announcement systems.

Assumptions for emergency query - If a student is equally spaced from 2 announcement systems, we randomly choose 1 announcement
system to the assign color to the student.
 
3. AnnouncementSystemMouseListener - This class implements MouseListener, MouseMotionListener
to record the mouse location when the mouse is clicked (left click or right click) or moved on the map. It is a public class and 
is used by HW2 class to perform the desired operations like running Whole Query, Point Query etc. Whenever a user clicks on the map
image the class calls the repaint method of JFrame in class HW2.java that takes the necessary actions.


