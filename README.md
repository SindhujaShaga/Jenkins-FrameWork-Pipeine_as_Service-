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




