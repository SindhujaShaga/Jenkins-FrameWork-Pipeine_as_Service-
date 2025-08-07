pipeline {
    agent any

    // This section defines the selectable checkboxes for the user.
    [cite_start]// Each boolean parameter corresponds to a stage and is set to true by default[cite: 5, 10].
    parameters {
        booleanParam(name: 'Checkout', defaultValue: true, description: '')
        booleanParam(name: 'Unit', defaultValue: true, description: '')
        booleanParam(name: 'Build', defaultValue: true, description: '')
        booleanParam(name: 'Sonar', defaultValue: true, description: '')
        booleanParam(name: 'Static', defaultValue: true, description: '')
    }

    stages {
        [cite_start]// The "AOB Pipeline consists of 5 stages"[cite: 17].

        stage('Checkout') {
            [cite_start]// This 'when' expression makes the stage conditional[cite: 11].
            // It runs only if the 'Checkout' parameter is selected.
            when {
                expression { params.Checkout == true }
            }
            steps {
                [cite_start]// This stage triggers a separate "mod_checkout" pipeline[cite: 25].
                build 'mod_checkout'
            }
        }

        stage('Unit Test Case') {
            when {
                expression { params.Unit == true }
            }
            steps {
                [cite_start]// This stage triggers a separate "Unit Test" pipeline[cite: 26].
                build 'Unit Test'
            }
        }

        stage('Build The Code') {
            when {
                expression { params.Build == true }
            }
            steps {
                [cite_start]// This stage triggers a separate "mod_build" pipeline[cite: 27].
                build 'mod_build'
            }
        }

        stage('Sonarqube Code Analysis') {
            when {
                expression { params.Sonar == true }
            }
            steps {
                // As shown in the document, this stage loads variables from an external file
                [cite_start]// before triggering the next job[cite: 35, 36].
                script {
                    load "/var/lib/Jenkins/.evars/variables.groovy"

                    [cite_start]// The "mod_sonarqube" pipeline is triggered from this stage[cite: 28].
                    build 'mod_sonarqube'
                }
            }
        }

        stage('Static Scan with ASoD') {
            when {
                expression { params.Static == true }
            }
            steps {
                [cite_start]// This stage also loads the same variable file to map the variables[cite: 33, 34].
                script {
                    load "/var/lib/Jenkins/.evars/variables.groovy"

                    [cite_start]// The "mod_asod" pipeline is triggered from this stage[cite: 29].
                    build 'mod_asod'
                }
            }
        }
    }
}