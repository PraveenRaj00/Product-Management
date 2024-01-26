
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PraveenRaj00/Product-Management.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    parallel (
                        "API-Gateway": { sh 'mvn clean install -f API-Gateway/pom.xml' },
                        "Order-microservice": { sh 'mvn clean install -f Order-microservice/pom.xml' },
                        "Product-microservice": { sh 'mvn clean install -f Product-microservice/pom.xml' },
                        "Service-Discovery": { sh 'mvn clean install -f Service-Discovery/pom.xml' },
                        
                         
                        // Add more Maven commands for each microservice
                    )
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test -f Order-microservice/pom.xml'
                    sh 'mvn test -f Product-microservice/pom.xml'
                    
                    // Add more Maven commands for running tests in each microservice
                }
            }
        }
    }

    post {
         always {
            // Publish test results
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Microservices build and tests successful!'
        }
        failure {
            echo 'Microservices build and tests failed.'
        }
    }
}
