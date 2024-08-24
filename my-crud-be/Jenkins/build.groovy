pipeline {
    agent any  // Specifies where the pipeline should run (any available agent)

    environment {
        // Define environment variables here
        MY_ENV_VAR = 'value'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Commands to build your application
                    echo 'Building...'
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Commands to test your application
                    echo 'Testing...'
                    sh 'mvn test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Commands to deploy your application
                    echo 'Deploying...'
                    sh 'deploy.sh'
                }
            }
        }
    }

    post {
        always {
            // Commands to run after the pipeline finishes
            echo 'Cleaning up...'
            cleanWs() // Clean workspace
        }
        success {
            // Actions to take if the pipeline succeeds
            echo 'Pipeline succeeded!'
        }
        failure {
            // Actions to take if the pipeline fails
            echo 'Pipeline failed!'
        }
    }
}
