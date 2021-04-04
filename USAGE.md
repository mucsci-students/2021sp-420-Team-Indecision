# Using the application

	1. Using a command prompt, find your way to the main directory of the program
	2. mvn clean
	3. mvn package
	4.1. for GUI: java -jar target\csci420-0.0.1-SNAPSHOT-jar-with-dependencies.jar
	4.2. for CLI: java -jar target\csci420-0.0.1-SNAPSHOT-jar-with-dependencies.jar -cli

# CLI Commands

As of update 3.0, our CLI interface comes with tab completion. Tab completion allows you to autocomplete commands. For example, if you wish to delete a class you could simply type "del" + tab, then the delete function will fill, and after that you would type "cl" tab, and class will autofill.

	ADD
	add class class_name - adds a class
	add field class_name attr_name - adds field to a desired class
	add method class_name method_name parameter_list(comma separated)- adds a method to a desired class
	add rel sourceClass destClass relationshipType - adds a relationship between classes
	
	DELETE
	delete class class_name - deletes given class");
	delete field class_name attr_name - deletes given field in specified class
	delete method class_name parameter_list(comma separated)- deletes given method in specified class
	delete rel class1 class2 - deletes the relationship between given classes
	
	RENAME/EDIT
	rename class class_name new_class_name - renames the specified class
	edit field class_name curr_field new_field_name - renames desired field given its class\n");
	edit method name class_name curr_method parameter_list new_method_name - renames desired method given its class
	edit method parameters class_name  method_name paramter_list new_parameter_list- renames desired attribute given its class
	
	LIST
	list class class_name - list single class and all its components
	list classes - list all classes
	list rel - lists all relationships between classes
	
	UNDO/REDO
	undo - Restores the state of the application to the previously called command.
	redo - If undo is called, redo allows you to return to the function call prior to the undo call. 
	
	SAVE/LOAD/EXIT
	save file_name - saves a file to x-destination with the given file name in .json format
	load file_name - loads file with given file name
	exit- will exit the program
	
# GUI

Our GUI comes with all of the same features as the Command Line Interface. All of our functions can be found in our toolbar.

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
	Add Relationship - choose the desired starting class, choose the receiving class, choose the relationship type. 
	Delete Relationship - choose the class that hosts the relationship, choose the specific relationship to be deleted.
	Edit Relationship Destination - enter the name of the host class, enter the name of the old class destination, enter the new class destination. 
	Edit Relationship Type - Choose the host class, choose the relationship with the type you would like to change, choose the new relationship type. 
	
	UNDO/REDO
	Undo - Restores the state of the application to the previously called command. For example, if you call "add class test", then undo, the test class will disappear.
	Redo - If undo is called, redo allows you to return to the application state prior to the undo call. If we take the example above for undo, and called redo, the test class will be added back to the application.
	
	SAVE AND LOAD TAB
	Save - enter the desired file name. This will then save the file to .json format automatically.
	Load - enter the desired file name you would like to work on/edit. This file should be in .json format.
