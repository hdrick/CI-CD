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
    }

}


