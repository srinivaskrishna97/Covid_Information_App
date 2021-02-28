      1.INTRODUCTION

In these desperate times, Coronavirus disease is flowing through the people very quickly and brought the human race to the pandemic situation. This software requirement document refers to covid information application. This document will be used by developers, testers, designers, and project managers so that they can build a Mobile application that will be used by people who are not aware of the latest regional guidelines and want to know the latest information about the covid-19.

      2.PURPOSE

The main purpose of this covid information application is to provide the latest covid information like provincial guidelines, news about coronavirus, covid statistics for the people who are facing difficulties in finding the precise information.

      3.DESCRIPTION

The main function of this application is to help users to know the latest information on COVID-19 based on their location. The application provides different guidelines for COVID in different provinces and countries. It clears the confusion for the people to properly follow the guidelines for the particular area in which they live, sometimes the guidelines keep changing so this app sends push notifications to the device accordingly. If the person is a traveler then The app provides quarantine guidelines and travel restrictions to and from the region there will be no need to download different applications for this purpose and also it gives updated information about COVID vaccination and live statistics of covid cases by country and provinces in the form of pie chart and map view and also update their profiles for contact information.

      4.SCOPE

This mobile application is mainly used by people facing difficulties in finding the official information about the guidelines and the covid news.

      5.FUNCTIONAL REQUIREMENTS

| **Requirement ID** | **Requirement Statement**                        | **Comments**                                                                                       |
| ------------------ | ------------------------------------------------ | -------------------------------------------------------------------------------------------------- |
| FR001              | Login Page                                       | The system must have a login screen so that the registered user can log in.                        |
| FR002              | Sign up                                          | The application must have a sign-up page where the new user can register using email and password. |
| FR003              | Forgot Password                                  | Follow the secure procedure to recover the password in case a registered user forgets.             |
| FR004              | Search Location                                  | The user can search for the location by using Google map API                                       |
| FR005              | Notification                                     | The System must always send a push notification when guidelines changes                            |
| FR006              | Log Out                                          | Users must have an option to log out.                                                              |
| FR007              | Users can get their provincial and country data. | Current data must provide to the user.                                                             |
| FR008              | email verification                               | Users must get the email verification at the time of registration                                  |
| FR009              | Pie chart                                        | Provincial and country data is displayed in the form of pie charts.                                |
| FR010              | Map-View                                         | Provincial and country data is displayed on the map view.                                          |
| FR011              | Map_Animation                                    | It shows how covid-19 spread over the years                                                        |
| FR012              | Self_assessment                                  | Users can check if they have covid symptoms                                                        |
| FR0013             | Newsfeed                                         | Users can see the latest news about covid                                                          |

       6.NON FUNCTIONAL REQUIREMENTS

| **Requirement Id** | **Requirement Statement** | **Comments**                                                                                                                                                                       |
| ------------------ | ------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| NFR001             | Performance               | When the user logs in how the application executes various tasks and how instantaneously the next page opens(in 30seconds).                                                        |
| NFR002             | Capacity                  | Capacity is delivering enough functionality required by the end-users.                                                                                                             |
| NFR003             | Portability               | This application runs on Android Devices.                                                                                                                                          |
| NFR004             | Security                  | Only allows the authorized users to get access to the app either admin or user.                                                                                                    |
| NFR005             | Usability                 | This app design is simple to understand and easy to use even for the person who does not have good knowledge of phones.                                                            |
| NFR006             | Efficiency                | All the activities work smoothly one after another.                                                                                                                                |
| NFR007             | Consistency               | Like-items should always be displayed and act the same way across the entire application (and even between applications).                                                          |
| NFR008             | Scalability               | It is a non-functional property of a system that describes the ability to appropriately handle increasing (and decreasing) workloads. 100users can use this application at a time. |

       7.USE CASE DIAGRAMS

![USE CASE DIAGRAM](http://covidinformation.live/Diagrams/Project_Usecase_Diagram.png)

       8.CLASS DIAGRAM

![CLASS DIAGRAM ](http://covidinformation.live/Diagrams/Project_Class_Diagram.png)

       9.SEQUENCE DIAGRAM
            User:-

![User](http://covidinformation.live/Diagrams/User_Sequence.png)  
![User_2](http://covidinformation.live/Diagrams/User_Sequence_2.png)  
 Admin:-  
![Admin](http://covidinformation.live/Diagrams/Admin_Sequence.png)

       10.USE CASE SCENARIO

> Register

---

| **Use Case ID**     | **UC-1**                                                                                                                                                                                                                                                                                          |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                                                                                                                                                                                                                                            |
| Use Case Name       | Register                                                                                                                                                                                                                                                                                          |
| Pre-Conditions      | The user is not registered and does not have an account.                                                                                                                                                                                                                                          |
| Post-Conditions     | The user is redirected to the log in page after Registration.                                                                                                                                                                                                                                     |
| Triggering event    | The user has clicked on the Sign Up button.                                                                                                                                                                                                                                                       |
| Description         | New customers can register in the application using sign up page.                                                                                                                                                                                                                                 |
| Flow of events      | Users first need to fill the details before login. User must have his full name, password, gender and email.Users can easily login after this.                                                                                                                                                    |
| Alternate Flow      | None                                                                                                                                                                                                                                                                                              |
| Exceptional Flow(s) | Exception: Only unregistered user emails should be used to register. The system will prompt the message &quot;Email has already been registered, enter new email&quot;. The password should be a minimum 8 characters. The system will prompt the message &quot;Email and Password required&quot; |
| Actor(s)            | Primary-Non-Registered-User                                                                                                                                                                                                                                                                       |

> login

---

| **Use Case ID**     | **UC-2**                                                                                                                                                                                                                                                                                                                                                                   |
| ------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                                                                                                                                                                                                                                                                                                                     |
| Use Case Name       | Login                                                                                                                                                                                                                                                                                                                                                                      |
| Pre-Conditions      | The user is registered and has an account.                                                                                                                                                                                                                                                                                                                                 |
| Post-Conditions     | The user is redirected to the home page after logging in                                                                                                                                                                                                                                                                                                                   |
| Triggering event    | User clicks on the login button.                                                                                                                                                                                                                                                                                                                                           |
| Description         | User need to login to get the features of the application user will provide credentials in order to login screen.                                                                                                                                                                                                                                                          |
| Flow of events      | User clicks on the Login button User enters the email and the password in the login credentials. User is directed to the homepage specific to the user.                                                                                                                                                                                                                    |
| Alternate Flow      | 1. User clicks on &quot;forget password. 2. User enters the email used for registration. 3. Email to change password is sent to the given email address. 4. User changes the password and confirms the changes. 5. User is directed to the Login page. 6. User enters the user name and the password in the form. 7. User is directed to the homepage specific to the user |
| Exceptional Flow(s) | Users must be registered to login. The password should be a minimum 8 characters. The system will prompt the message &quot;Email and Password required&quot;                                                                                                                                                                                                               |
| Actor(s)            | Primary-Registered-User                                                                                                                                                                                                                                                                                                                                                    |

> Reset Password

---

| **Use Case ID**     | **UC-3**                                                                                                                                                                                                                                                |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                                                                                                                                                                                                  |
| Use Case Name       | Reset Password                                                                                                                                                                                                                                          |
| Pre-Conditions      | The user is registered and has an account.                                                                                                                                                                                                              |
| Post-Conditions     | The password of the user will be updated in the database.                                                                                                                                                                                               |
| Triggering event    | The user has clicked on the Forgot Password button.                                                                                                                                                                                                     |
| Description         | User has forgotten the password and wants to reset it, so this feature helps the user to reset the password.                                                                                                                                            |
| Flow of events      | User clicks on the Forgot Password link.. User enters the email used for registration. User clicks on a forgotten password. Users will change passwords using links received by email. User is redirected to the Login Page.                            |
| Alternate Flow      | None                                                                                                                                                                                                                                                    |
| Exceptional Flow(s) | Exception: Password should be a minimum 8 character. The system will prompt the message &quot;Password is too short, it should be at least 8 characters&quot;. The system will prompt the message &quot;Email is not registered with application&quot;. |
| Actor(s)            | Primary-Registered-User                                                                                                                                                                                                                                 |

> Notification

---

| **Use Case ID**     | **UC-3**                                   |
| ------------------- | ------------------------------------------ |
| Authors             | Team 4                                     |
| Use Case Name       | Notification                               |
| Pre-Conditions      | User must login                            |
| Post-Conditions     | Users can view the notifications.          |
| Triggering event    | If there is update regarding the instance  |
| Description         | Users can get notification about covid 19. |
| Flow of events      | User pulls the notification bar.           |
| Alternate Flow      | None                                       |
| Exceptional Flow(s) | User must be a registered user             |
| Actor(s)            | Primary-Registered-User                    |

> Edit Profile

---

| **Use Case ID**     | **UC-3**                                                  |
| ------------------- | --------------------------------------------------------- |
| Authors             | Team 4                                                    |
| Use Case Name       | Edit Profile                                              |
| Pre-Conditions      | User must have successfully logged in the application.    |
| Post-Conditions     | User have updated their profiles successfully.            |
| Triggering event    | User can successfully Edit their detail.                  |
| Description         | Logged in user can update their data and manage profile.. |
| Flow of events      | 1. Change Name                                            |
|                     | 2. Change Email                                           |
|                     | 3. Change password                                        |
|                     | 4. Change Country                                         |
|                     | 5. Change Province                                        |
|                     | 6. Mobile Number                                          |
|                     | 7. Click on update profile button                         |
| Alternate Flow      | None                                                      |
| Exceptional Flow(s) | Failed authentication not allowed to Edit the profile     |
| Actor(s)            | Primary-Registered-User                                   |

> Covid Information

---

| **Use Case ID**     | **UC-3**                                                                                               |
| ------------------- | ------------------------------------------------------------------------------------------------------ |
| Authors             | Team 4                                                                                                 |
| Use Case Name       | Covid Information                                                                                      |
| Pre-Conditions      | Users must have successfully logged in the application.                                                |
| Post-Conditions     | Users can view the latest information.                                                                 |
| Triggering event    | Users after successfully logging in can view the updated covid information.                            |
| Description         | Users can get information about COVID-19(cases,deaths etc.).                                           |
| Flow of events      | When the user reaches the home screen of the application, the user can get information about COVID-19. |
| Alternate Flow      | None                                                                                                   |
| Exceptional Flow(s) | Failed authentication not allowed to create the profile                                                |
| Actor(s)            | Primary-Registered-User                                                                                |

> Graphical data

---

| **Use Case ID**     | **UC-3**                                                                                                                                                                       |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Authors             | Team 4                                                                                                                                                                         |
| Use Case Name       | Graphical data                                                                                                                                                                 |
| Pre-Conditions      | Users must have successfully logged in the application.                                                                                                                        |
| Post-Conditions     | Users can view the information about covid19 in the form of graphical data representation.                                                                                     |
| Triggering event    | User in the feed section click on the graphical data (like pie chart, animated map and map) to view the covid information                                                      |
| Description         | Users can view the variety of data regarding spread of COVID-19 in different representations such as pie charts or maps.                                                       |
| Flow of events      | Latest information according to province and country has been shown to the user in the form of graphical data representation.User clicks on the different pie charts and maps. |
| Alternate Flow      | None                                                                                                                                                                           |
| Exceptional Flow(s) | User must verify to gain access to the application                                                                                                                             |
| Actor(s)            | Primary-Registered-User                                                                                                                                                        |

> Self Assessment

---

| **Use Case ID**     | **UC-3**                                                                                                                              |
| ------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                                                                                |
| Use Case Name       | Self assessment                                                                                                                       |
| Pre-Conditions      | Users must have successfully logged in the application.                                                                               |
| Post-Conditions     | Users can perform self assessment through the application.                                                                            |
| Triggering event    | Users can go through the questions related to covid and get the results at the end behalf of his/her answers.                         |
| Description         | By using this, the people can come to conclusion that if they need to take covid test or have any symptoms of coronavirus (COVID-19). |
| Flow of events      | Users continuously follow the Questionnaire which has Yes/No Questions until they come to the conclusion.                             |
| Alternate Flow      | None                                                                                                                                  |
| Exceptional Flow(s) | User must verify to gain access to the application                                                                                    |
| Actor(s)            | Primary-Registered-User                                                                                                               |

> News Feed

---

| **Use Case ID**     | **UC-3**                                                                           |
| ------------------- | ---------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                             |
| Use Case Name       | News Feed                                                                          |
| Pre-Conditions      | Users must have successfully logged in the application.                            |
| Post-Conditions     | User can able to view the latest covid information                                 |
| Triggering event    | User can able to view the news feed                                                |
| Description         | News feed can provide the news of covid cases and centres and updated information. |
| Flow of events      | Users can click on the news feed button to get the updated news about covid19.     |
| Alternate Flow      | None                                                                               |
| Exceptional Flow(s) | User must verify to gain access to the application                                 |
| Actor(s)            | Primary-Registered-User                                                            |

> Logout

---

| **Use Case ID**     | **UC-3**                                                                              |
| ------------------- | ------------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                                |
| Use Case Name       | Logout                                                                                |
| Pre-Conditions      | Users must have successfully logged in the application.                               |
| Post-Conditions     | Users can able to logout the application.                                             |
| Triggering event    | Users can successfully logout the application.                                        |
| Description         | Registered users can logout the application after getting the status regarding covid. |
| Flow of events      | Users click on the logout button to close the application.                            |
| Alternate Flow      | None                                                                                  |
| Exceptional Flow(s) | User must verify to gain access to the application                                    |
| Actor(s)            | Primary-Registered-User                                                               |

> Admin Login

---

| **Use Case ID**     | **UC-3**                                                               |
| ------------------- | ---------------------------------------------------------------------- |
| Authors             | Team 4                                                                 |
| Use Case Name       | Admin Login                                                            |
| Pre-Conditions      | Admin must have credentials assigned.                                  |
| Post-Conditions     | Admin will successfully access to the application                      |
| Triggering event    | Admin can sign in                                                      |
| Description         | Admin can push the information to the covid user application.          |
| Flow of events      | Admin clicks on the sign in button and gets access to the admin panel. |
| Alternate Flow      | None                                                                   |
| Exceptional Flow(s) | Admin must verify to gain access to the application                    |
| Actor(s)            | Admin                                                                  |

> Admin CRUD

---

| **Use Case ID**     | **UC-3**                                                                          |
| ------------------- | --------------------------------------------------------------------------------- |
| Authors             | Team 4                                                                            |
| Use Case Name       | Admin CRUD                                                                        |
| Pre-Conditions      | Admin must have an email id.                                                      |
| Post-Conditions     | Admin can able to register                                                        |
| Triggering event    | Admin will successfully access to the application and can perform CRUD operations |
| Description         | Admin can perform the crud operation in the application.                          |
| Flow of events      | Admin can login the application and perform crud operations.                      |
| Alternate Flow      | None                                                                              |
| Exceptional Flow(s) | Admin must verify to gain access to the application                               |
| Actor(s)            | Admin                                                                             |

       11.USER STORIES

---

| **Sr_No** | **As a** | **I want to**         | **So that**                                                                    |
| --------- | -------- | --------------------- | ------------------------------------------------------------------------------ |
| US-1      | User     | Login                 | I can get COVID-19 information and keep myself safe in this pandemic.          |
| US-2      | User     | Reset my password     | I can change my password easily in case I forget.                              |
| US-3      | User     | Update profile        | I can change my personal information.                                          |
| US-4      | User     | Vaccine centers       | I can search and get the location of Vaccine Centers at any place any time.    |
| US-5      | User     | Get notifications     | I can keep myself aware about new updates about COVID-19.                      |
| US-6      | User     | Graphical Data        | I can get COVID-19 data in the form of graphical representation and Animation. |
| US-7      | User     | News feed             | I can see all the latest covid related news at one place.                      |
| US-8      | User     | Travel guidelines     | I can check the travel guidelines according to my need.                        |
| US-9      | User     | Self assessment       | I can check if I have any symptoms of covid by myself.                         |
| US-10     | User     | Quarantine guidelines | I will have an idea how to quarantine and be safe.                             |
| US-11     | User     | Covid centers         | I can search and get the location of COVID-19 Centers at any place any time.   |
| US-12     | User     | Logout                | I can safely log out and keep my information safe.                             |
| US-13     | Admin    | Login                 | I can gain access to the covid information Admin app.                          |
| US-15     | Admin    | CRUD                  | I can create, read, update and delete data about COVID-19.                     |
| US-16     | Admin    | Logout                | I can logout once I&#39;m done using the application.                          |

    12.ER DIAGRAM

![Admin](http://covidinformation.live/Diagrams/Project_ER_diagram.png)

    13.ER DIAGRAM

![Admin](http://covidinformation.live/Diagrams/Screens/logout_screen.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Admin_NF.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Admin_QG.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Admin_TG.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Admin_VD.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Admin-Dashboard.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Detailed news.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/FAQs.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Log_IN.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/News Feed.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Notifications_Panel.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Profile_after_edit.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Quarantine_Guidliance.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Register.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_1.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_2.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_3.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_4.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_5.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_6.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_7.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Safety_8.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Self_assessment.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/splash.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Travell_Guidliance.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/User_dashboard.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/logout_screen.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/User_profile.svg)
![Admin](http://covidinformation.live/Diagrams/Screens/Vaccine_details.svg)
