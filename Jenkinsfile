pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PraveenRaj00/microservice-hotel-system.git'
            }
        }

        stage('Build') {
            steps {
                script {
                        "Service-Discovery": { sh 'mvn clean install -f Service-Discovery/pom.xml' },
                        "Product-microservice": { sh 'mvn clean install -f Product-microservice/pom.xml' },
                        "Order-microservice": { sh 'mvn clean install -f Order-microservice/pom.xml' },
                        "API-Gateway": { sh 'mvn clean install -f API-Gateway/pom.xml' },
                        // Add more Maven commands for each microservice
                    )
                
            }
        }

        stage('Test') {
            steps {
                script {
                    bat 'mvn test -f Product-microservice/pom.xml'
                    bat 'mvn test -f Order-microservice/pom.xml'
                    // Add more Maven commands for running tests in each microservice
                }
            }
        }
    }

    post {
        success {
            echo 'Microservices build and tests successful!'
        }
        failure {
            echo 'Microservices build and tests failed.'
        }
    }
}
