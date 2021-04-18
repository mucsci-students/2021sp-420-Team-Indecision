# Using the application

	1. Using a command prompt, find your way to the main directory of the program
	2. mvn clean
	3. mvn package
	4.1. for GUI: java -jar target\csci420-0.0.1-SNAPSHOT-jar-with-dependencies.jar
	4.2. for CLI: java -jar target\csci420-0.0.1-SNAPSHOT-jar-with-dependencies.jar -cli

# CLI Commands

	ADD
	add class class_name - adds a class 
	add field class_name field_type field_name - adds a field to the desired class 
	add method class_name method_return_type method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....)- adds a method to a desired class 
	add rel class1 class2 relationship_type - adds a relationship between classes the relationship type must be one of the following Aggregation, Composition, Inheritance or Realization

	DELETE 
	delete class class_name - deletes given class 
	delete field class_name field_name - deletes given field in specified class 
	delete method class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....)- deletes given method in specified class 
	delete rel class_name relationship_destination_name - deletes the relationship between given classes 

	RENAME/EDIT 
	edit class class_name new_class_name - renames the specified class 
	edit field name class_name curr_field new_field_name - renames desired field
	edit field type class_name curr_field new_field_type - renames desired field type
	edit method name class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_name - renames desired method
	edit method type class_name method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_type - renames desired method return type
	edit method parameters class_name  method_name method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) / new_method_parameters (method parameters must be entered in the following format: parameter_type parameter_name .....) - sets a new parameter list for the method 
	edit rel dest class_name destination_name new_destination_name - changes the destination of the relationship
	edit rel type class_name destination_name new_destination_type - changes the type of the relationship

	LIST 
	list class class_name - list single class and all its components 
	list classes - list all classes 
	list rel - lists all relationships between classes 
	SAVE/LOAD 
	save file_name - saves a file to x-destination with the given file name in .json format 
	load file_name - loads file with given file name 
	UNDO/REDO
	undo - undoes the last action that changed the state of the model
	redo - redoes the last undo

	EXIT 
	exit - will exit the program 
	
# GUI

Our GUI comes with all of the same features as the Command Line Interface. All of our functions can be found in our toolbar.

	FILE TAB
	Save - enter the desired file name. This will then save the file to .json format automatically.
	Load - enter the desired file name you would like to work on/edit. This file should be in .json format.
	Export as Image - takes a screenshot of the current program state, and save it as .jpg to the source directory of the code. 

	CLASSES TAB
	Add Class - enter desired class name
	Delete Class - enter desired class name
	Rename Class - enter current class, then the new name
	
	FIELD TAB
	Add Field - enter desired class name, then the field name
	Delete Field - enter the desired class name, enter desired field name to be deleted
	Rename Field - enter class containing the field, the current field name, then the new field name
	
	METHOD TAB
	Add Method - enter the desired class name, enter the new method name, enter list of parameters
	Delete Method - enter the desired class name, enter the desired method name, enter the list of parameters
	Edit Method Name - enter desired class name, enter the current method, enter the new method name
	Edit Method Parameters - enter the desired class name, enter the desired method name, enter the list of parameters

	RELATIONSHIP TAB
	Add Relationship - enter the host class, enter the relationship's destination class, enter the type of relationship. 
	Delete Relationship - enter the class that contains the desired relationship, enter the desired relationship.
	Edit Relationship Destination - enter the host class, enter the original desitnation class, enter the new realtionship destination. 
	Edit Relationship Type - enter the class that contains the desired relationship, enter the desired relationship, enter the new relationship type.

	UNDO AND REDO TAB
	Undo - undoes the last action that changed the state of the model
	Redo - redoes the last undo