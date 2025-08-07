*Project: Multi-Tenancy in Jenkins and SonarQube*

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






