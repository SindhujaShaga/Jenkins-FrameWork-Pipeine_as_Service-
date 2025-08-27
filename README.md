-------------------------------*PROJECT ON MULTITENANCY IN JENKINS AND SONARQUBE*---------------------

This project demonstrates a complete, end-to-end solution for achieving multi-tenancy in a CI/CD environment using Jenkins and SonarQube. The goal is to onboard multiple users or teams onto a shared infrastructure while ensuring their pipelines, jobs, and analysis reports remain isolated and secure.

*Part 1: Jenkins Multi-Tenancy Setup*

Multi-tenancy in Jenkins is achieved by using plugins to create role-based access control tied to specific folders or job name patterns.  This ensures that tenants can only access and manage their own resources

Required Plugins

* Role-based Authorization Strategy : The core plugin used to create and manage user permissions through roles.
* Folders Plugin : Allows for the organization of jobs into folders, which can then have specific permissions applied.

Configuration Steps
1.Create Global Roles:

* In Jenkins, navigate to 

>Manage Jenkins > Manage and Assign Roles. 

* Under 

>Manage Roles, create a "global role" for each tenant (e.g., tenant1, tenant2). 

* Assign basic 

>Overall/Read permission to these roles so that users can log in and see the Jenkins UI.

 2.Create Item Roles:
 * Create an "item role" for each tenant to control access to specific jobs and folders.
 * Define a unique 

>Pattern for each item role, such as tenant1.*. This pattern means the role will apply to any job or folder that starts with the prefix "tenant1".
* Assign necessary permissions to this role, such as Job (Build, Create, Configure, Read) and Run (Update, Delete).
* It is recommended to use the Folders plugin and apply the pattern to a folder to eliminate the need for jobs to follow a specific prefix naming convention. 

3.Assign Roles to Users:
* Navigate to the
>Assign Roles section.
* Assign each user their corresponding global role (e.g.,
>user1 gets the tenant1 global role).
* Assign each user their corresponding item role (e.g.,
>user1 gets the tenant1 item role). This ensures the user can only access jobs and folders that match the pattern defined in the item role.


*Part 2: SonarQube Multi-Tenancy Setup*

   Multi-tenancy in SonarQube is achieved by creating private projects and using groups to control which users can view and analyze them.

   Configuration Steps
   1.Create Users and Groups:
* From the 
> Administration tab, create a new user for each tenant (e.g., user3).
*Create a new group with a corresponding name (e.g., 
>usergrp3).
*Add the newly created user to their respective group.

3.Create and Secure a Project:

*Create a new project for the tenant (e.g., 

>DemoProject3). 


* Navigate to the project's 

>Project Settings > Permissions. 

* Set the project's visibility from Public to Private. This ensures that only authorized users or groups can see the project.

3.Grant Project Permissions:

* On the same Permissions page, grant the necessary permissions (e.g., Browse, See Source Code, Execute Analysis) to the tenant's group (usergrp3). 

* Revoke permissions from any other general groups like "Anyone" or "sonar-users".

*Part 3: Connecting Jenkins to a Secure SonarQube Project
This final step integrates the two systems, allowing a tenant's Jenkins pipeline to send analysis data to their private, isolated SonarQube project.*

After these steps, when a tenant user logs into SonarQube, they will only be able to see and access the projects for which their group has been granted permissions, successfully achieving multi-tenancy. 

*Configuration Steps*

1.Generate a SonarQube Token:

* In SonarQube, navigate to the tenant's private project.

* Generate a new secret authentication token for the project.  This token will be used by Jenkins to authenticate.

2.Configure the Jenkins Pipeline:

* Create a pipeline job in Jenkins. 

* In the pipeline script, configure the SonarQube scanner step (mvn sonar:sonar). 

* Update the command with the correct SonarQube Project Key and the Authentication Token generated in the previous step. 

Once the pipeline is built, the analysis results will be sent directly to the specified SonarQube project. Because the project is private, the report will only be visible to the tenant user (user3) who has been granted permission, completing the secure, multi-tenant workflow. 













