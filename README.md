# Online Pediatrician Consultation and Patient Monitoring System

## Introduction
The project is titled as “Online Pediatrician Consultation and Patient Monitoring System”. This product targets to help patients particularly the children aged from childbirth up to 18 years old. This online consultation system will allow the patient to consult with a doctor online to minimize the risk of going to hospitals due to the on going pandemic. This project utilizes a firebase console as a real time database in order to store the data going in or out of the program. A login and register system is implemented for both patients and doctors in order to use the program. Once the user has registered, the user will be directed to the main menu where it will prompt the user to schedule an appointment. Once the user has scheduled an appointment, the product would prompt the users to answer a pre-check up questionnaire. From here, it can determine whether the patient needs to undergo a consultation with a pediatrician or not. Hence, the doctor can provide the necessary prescriptions that will aid the patient. The online consultation will be held through zoom and a link will be attached in the program itself. The prescription of the doctor will be also given to the user where they can access it in the appointments part of the program. Those are some of the features functionalities of our program which satisfies the given software requirements and use cases set by the developers.

## Requirements and Dependencies
The following are the requirements and dependencies that were used in developing the project. The links in order to download the different requirements and dependencies are in the external links section when you scroll down:

#### IntelliJ IDEA
> IntelliJ is an integrated development environment (IDE) which is used in order to develop computer softwares in Java. This is the main development platform of the developers to create the project.

#### javafx-SDK-11.0.2
> The JavaFX Software Development Kit is used in order to provide command tools in order to produce a project. It provides the platform in developing desktop applications which can run on multiple operating systems.

#### Gluon Scene Builder
> Gluon Scene Builder is a drag and drop UI development tool which allows the developers to rapidly layout the user interface of the program. This serves as a complementary extension of JavaFX.

#### firebase-client-android-2.5.2
> The firebase client library is a jar file which allows the developers to use specific commands to read and write data to the online realtime database.

## Environment and Set-Up
This section of the readme file will be a step by step guide on how to run the project using the IntelliJ Idea IDE.

### Pulling the project from the Git Repository
1. Open IntelliJ IDEA and click the ‘get from version control’ option.
2. Once directed to the next page, paste the URL of the repository and select the directory of the project. Repository URL:
```bash
https://github.com/Jeremiah-Jensen/LBYCPD2_GROUP2
```
3. If the git option does not appear, go to the plugins section of IntelliJ and download the Git Plugin. The git plugin should be installed by default.
4. Once step 2 is done, Click at the clone button and the project should be loaded properly.

### Initializing the JavaFX Library
1. Open the Project in IntelliJ.
2. From the main menu, select file then click project structure.
3. In the project structures page, go to lib and click at the ‘+’ sign to add the JavaFX Library.
4. The JavaFX libraries are already installed in the project under the lib folder. The mac and windows versions are available and pick one that is for your operating system and select it.
5. Make sure that in the SDK, you click on the lib directory of the folder.
6. Once done click apply to apply the changes.

### Add VM Options
1. Open the Project in IntelliJ.
2. In the project's sidebar, right click on the lib directory folder of the JavaFX sdk and copy its absolute path.
3. From the main menu, select run then click project configurations.
4. Click on the More options and select Add VM Options.
5. In the VM options field, copy paste this line and add path of the directory of the lib folder of the sdk where it is specified.
```bash
--module-path ADD PATH HERE --add-modules javafx.controls,javafx.fxml
```
6. Once done, click on apply and the project is ready to run.

## Revision Logs
#### March 26, 2021: 
Initial Set-Up of Repository and JavaFX.

#### April 2, 2021: 
Completion of Initial UI/UX. Imported firebase to JavaFX.

#### April 7, 2021: 
Incorporated firebase to the program.Created the initial  register and login feature.

#### April 9, 2021: 
Added validation to register and login.

#### April 13, 2021: 
Connect to the main menu.

#### April 17, 2021: 
Doctor/User Login and Register, Pre-Checkup Questionnaire.

#### April 18, 2021:
Schedule Appointment and Main Menu  UI/UX Improvements.

## About the Contributors
For any questions about the project, you may email us directly through our personal email address.

### Antonio Miguel Bautista
Email: antonio_bautista@dlsu.edu.ph

BS Computer Engineering in De La Salle University-Manila.
> Programming Experience: C, C++, Python, Java, Object Oriented Programming, Data Structures and Algorithms.
> Previous Projects: SafeLink: Contactless Bus Payment and Reservation System, Animo Click: DLSU Services Application, Automated Food Ordering System in C, Coronavirus Risk Identification Software.



### Julienne Bernadette Silva
Email: julienne_silva@dlsu.edu.ph

BS Computer Engineering in De La Salle University-Manila.
> Programming Experience: C, C++, Python, Java, Object Oriented Programming, Data Structures and Algorithms.
> Previous Projects: AnimoClick, Hospital Management System in C, LADDER (Lookup Patient, Add Record, Diagnosis Field, Delete Record, and Edit Record) System, CoCo: Covid Connect (COVID-19 Tracker).



### Jeremiah Timothy B. Jensen
Email: jeremiah_jensen@dlsu.edu.ph

BS Computer Engineering in De La Salle University-Manila.
> Programming Experience: C++, C, Java, Python, Object Oriented Programming, Data Structures aEnd Algorithms.
> Previous Projects:  Contactless Bus Payment and Reservation System, Animo Click: DLSU Services Application.

## External Links
[IntelliJ Idea Download](https://www.jetbrains.com/idea/download/#section=mac)

[Gluon Scene Builder Download](https://gluonhq.com/products/scene-builder/)

[JavaFX SDK Download](https://gluonhq.com/products/javafx/)

[Firebase-client-android-2.5.2.jar Download](http://www.java2s.com/example/jar/f/download-firebaseclientandroid252jar-file.html)

[JavaFX Set-Up for IntelliJ](https://www.jetbrains.com/help/idea/javafx.html)
 
