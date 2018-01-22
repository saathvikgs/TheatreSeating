To run the application, 
1. Open command prompt on windows.
2. Navigate to the folder which contains the executable jar "TheatreSeating.jar"
3. To execute enter the following in command line:

java -jar TheatreSeating.jar
	
	
Few other things:

1. The appliation will ask user to choose file consisting o json records for input data

2. If an unexpected event occurs during the execution of the application, then they are ignored and application continues its execution. 
   If there is an error while reading file then the application terminates. 
   Any error or unexpected event will be logged in theatreseating_log.txt file.
   The log file will be created in the same folder which consists of TheatreSeating.jar file

