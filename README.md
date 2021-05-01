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

## Pulling the project from the Git Repository
1. Open IntelliJ IDEA and click the ‘get from version control’ option.
<img width="450" alt="Screen Shot 2021-04-30 at 8 35 50 PM" src="https://user-images.githubusercontent.com/62889116/116695931-d162eb00-a9f3-11eb-888e-e03ff9d9216d.png">

2. Once directed to the next page, paste the URL of the repository and select the directory of the project. Repository URL:
```bash
https://github.com/Jeremiah-Jensen/LBYCPD2_GROUP2
```
<img width="450" alt="Screen Shot 2021-04-30 at 8 42 50 PM" src="https://user-images.githubusercontent.com/62889116/116696725-da07f100-a9f4-11eb-8db8-0a4a84b2b4a0.png">

3. If the git option does not appear, go to the plugins section of IntelliJ and download the Git Plugin. The git plugin should be installed by default.
4. Once step 2 is done, Click at the clone button and the project should be loaded properly.
<img width="450" alt="Screen Shot 2021-04-30 at 8 43 12 PM" src="https://user-images.githubusercontent.com/62889116/116696758-e429ef80-a9f4-11eb-9b58-65a496e537af.png">


## Initializing the JavaFX Library
1. Open the Project in IntelliJ.
2. From the main menu, select file then click project structure.
<img width="450" alt="Screen Shot 2021-04-30 at 8 49 39 PM" src="https://user-images.githubusercontent.com/62889116/116698072-8a2a2980-a9f6-11eb-9135-6a1e7e9bdff3.png">

3. In the project structures page, go to libraries and click at the ‘+’ sign to add the JavaFX Library.
<img width="450" alt="Screen Shot 2021-04-30 at 8 57 52 PM" src="https://user-images.githubusercontent.com/62889116/116698438-f60c9200-a9f6-11eb-8d89-cc8e85c6e499.png">

4. The JavaFX libraries are already installed in the project under the lib folder. The mac and windows versions are available and pick one that is for your operating system and select it.
<img width="450" alt="Screen Shot 2021-04-30 at 9 02 37 PM" src="https://user-images.githubusercontent.com/62889116/116698862-70d5ad00-a9f7-11eb-8484-3e1e6ff831da.png">

5. Make sure that in the SDK, you click on the lib directory of the folder press open.
<img width="450" alt="Screen Shot 2021-04-30 at 9 00 41 PM" src="https://user-images.githubusercontent.com/62889116/116698723-45eb5900-a9f7-11eb-8f31-5b519b7ea7a5.png">

6. Once done click apply to apply the changes.
<img width="450" alt="Screen Shot 2021-04-30 at 9 03 40 PM" src="https://user-images.githubusercontent.com/62889116/116698969-9c589780-a9f7-11eb-8d10-166f580deaee.png">


## Add VM Options
1. Open the Project in IntelliJ.
2. In the project's sidebar, right click on the lib directory folder of the JavaFX sdk and copy its absolute path.
<img width="450" alt="Screen Shot 2021-04-30 at 9 07 13 PM" src="https://user-images.githubusercontent.com/62889116/116699352-0ffaa480-a9f8-11eb-8391-5264a40534eb.png">

3. From the main menu, select run then click edit configurations.
<img width="450" alt="Screen Shot 2021-04-30 at 9 08 40 PM" src="https://user-images.githubusercontent.com/62889116/116699587-53551300-a9f8-11eb-9279-12ef2a562941.png">

4. Click on More options and select Add VM Options.
<img width="450" alt="Screen Shot 2021-04-30 at 9 10 06 PM" src="https://user-images.githubusercontent.com/62889116/116699726-80092a80-a9f8-11eb-8312-3188554ed8aa.png">

5. In the VM options field, copy paste this line and add path of the directory of the lib folder of the sdk where it is specified.
```bash
--module-path ADD PATH HERE --add-modules javafx.controls,javafx.fxml
```
<img width="450" alt="Screen Shot 2021-04-30 at 9 11 21 PM" src="https://user-images.githubusercontent.com/62889116/116699819-a333da00-a9f8-11eb-91b0-511e7c710d69.png">

6. Once done, click on apply and the project is ready to run.
<img width="450" alt="Screen Shot 2021-04-30 at 9 03 40 PM" src="https://user-images.githubusercontent.com/62889116/116698969-9c589780-a9f7-11eb-8d10-166f580deaee.png">

## Revision Logs
#### March 26, 2021: 
- Initial Set-Up of Repository and JavaFX.
- SDK and Firebase Client added

#### April 2, 2021: 
- Completion of Initial UI/UX. 
- Imported firebase to JavaFX.

#### April 7, 2021: 
- Incorporated firebase to the program.
- Created the initial  register and login feature.

#### April 9, 2021: 
- Added validation to register and login.

#### April 13, 2021: 
- Connect to the main menu.

#### April 17, 2021: 
- Initial Doctor/User Login and Register, Pre-Checkup Questionnaire.

#### April 18, 2021:
- Schedule Appointment and Main Menu UI/UX Improvements.

#### April 22, 2021:
- Working GUI and some errors fixed.
- Doctor side improvements added.

#### April 24, 2021:
- Major Revision of the Appointments System.

#### April 26, 2021:
- Final working version of user appointment booking.
- User and Doctor Appointments Connected.
- Appointments Model Created.
- Pre-Check Up Questionnaire completed.

#### April 27, 2021:
- Pre-Check Up Questionnaire Connected to the Appointmets Model.
- Doctor Side minor revision on appointments screen.
- User Appointment system can now get Pre-Check Up Questionnaire Answers.
- User can imput credit card information.

#### April 28, 2021:
- Updated credit payment system.
- Manage Children Feature added to make a user manage multiple children.
- User and Doctor Appointment Screens Added.
- Users and Doctors can now upload Profile Pictures.
 
#### April 30, 2021:
- Realtime Patient-Doctor Consultation System Working.
- Appointment Screens of User and Doctors are synced.
- Updated Questionnaire GUI.
- Follow Up Questionaire Working.

#### May 1, 2021:
- Parts of the source code has been Modularized.
- Improved log in system errors.
- UserAppointmentScreen bug has been fixed.

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
