
# Media Storefront

This is a simple storefront application which consists of both a GUI and a CLI version. It is designed to be a user accessible app in which a person can purchase different types of media be it Movies, Video Games or Books.






## Installation

Clone the repository and position yourself into its main directory. If you have GitHub installed on your PC, you can do the following:

```bash
  gh repo clone asuta2/Projekat
  cd my-project
```


## Building
To run the standard GUI version of the app, do the following:
```bash
  mvn clean install
```
Or to run the CLI version:
```bash
  mvn clean install -P cli-app
```

## Usage
To run the compiled java files, navigate to the target folder with:
```bash
  cd target
```
Then run the following for the GUI version:
 ```bash
  java --module-path "\path\to\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml -jar
     Projekat-gui-jar-with-dependencies.jar
``` 
For the CLI version, do the following:
 ```bash
  java -jar Projekat-cli-jar-with-dependencies.jar -<parameters>
``` 


## Command Line Parameters
The following parameters can be used when running the program from the command line:
```bash
  -h
```
Description: Prints all available paramaters.

```bash
  -u value1 
```

Description: Specifies the value for username paramater.

```bash
  -p value2
```

Description: Specifies the value for password paramater.

```bash
  -e value3
```

Description: Specifies the value for email paramater.

```bash
  -b value4
```

Description: Specifies the value for balance paramater.
```bash
  -a -u value1 -p value2 -e value3 -b value4 
```

Description: Addes a new user to the database depending on the specified parameters.

```bash
  -d value1
```
Description: Deletes a user from the database with the specified paramater being the users username.

```bash
  -l 
```
Description: Lists all users from the database.
```bash
  -c -u value1 -p value2 
```
Description: Changes the users password with the specified parameters. Paramater value1 reperesents the username of the given user, while value2 reperesents the new password.

```bash
  -s value1 
```
Description: Searches for the User in the database and return all of the information about him.
```bash
  -v 
```
Description: Prints current version of project.
## Running the tests
To run the tests, do the following:
```bash
  mvn test
```
Disclaimer: When running the JavaFX tests, you may encounter a failed test due to the fact that you may have multiple windows open. This is not a problem with the code, but rather with the test itself. If you encounter this, simply close all other windows and run the test again.

