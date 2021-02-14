# Using the application

	1. Using a command prompt, find your way to the main directory of the program
	2. mvn clean
	3. mvn package
	4. java -jar target\csci420-0.0.1-SNAPSHOT-jar-with-dependencies.jar

# Commands

	ADD
	add class class_name - adds a class
	add attr class_name attr_name - adds attribute to a desired class
	add rel sourceClass destClass relationshipType - adds a relationship between classes
	
	DELETE
	delete class class_name - deletes given class");
	delete attr class_name attr_name - deletes given attribute in specified class
	delete rel class1 class2 - deletes the relationship between given classes
	
	RENAME
	rename class class_name new_class_name - renames the specified class
	rename attr class_name attr_name new_attr_name - renames desired attribute given its class
	LIST
	
	list class class_name - list single class and all its components
	list classes - list all classes
	list rel - lists all relationships between classes
	SAVE/LOAD
	save file_name - saves a file to x-destination with the given file name in .json format
	load file_name - loads file with given file name
	
	exit- will exit the program
