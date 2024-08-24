pipeline {
    agent any

    stages {
        stage('Checkout CI-CD Repository') {
            steps {
                // Checkout the CI-CD repository containing the build.groovy script
                git(
                    url: 'git@github.com:hdrick/CI-CD.git',
                    branch: 'develop',
                    credentialsId: 'ssh_dricks'
                )
            }
        }

        stage('Checkout my-crud-be') {
            steps {
                git(
                    url: 'git@github.com:hdrick/my-crud-be.git',
                    branch: 'develop',
                    credentialsId: 'ssh_dricks'
                )
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('my-crud-be') {
                    sh 'docker build -t my-crud-app .'
                }
            }
        }

        stage('Run Tests') {
            steps {
                dir('my-crud-be') {
                    // Run your application tests here if needed
                    sh 'docker run --rm my-crud-app ./run-tests.sh' // Adjust as needed
                }
            }
        }
    }

    post {
        always {
            // Clean up actions if needed
            echo 'Cleaning up...'
        }
    }
}
