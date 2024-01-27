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
                    parallel(
                        "Service-Discovery": { sh 'mvn clean install -f Service-Discovery/pom.xml' },
                        "Product-microservice": { sh 'mvn clean install -f Product-microservice/pom.xml' },
                        "Order-microservice": { sh 'mvn clean install -f Order-microservice/pom.xml' },
                        "API-Gateway": { sh 'mvn clean install -f API-Gateway/pom.xml' }
                        // Add more Maven commands for each microservice
                    )
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    parallel(
                        "Test-Product-microservice": { sh 'mvn test -f Product-microservice/pom.xml' },
                        "Test-Order-microservice": { sh 'mvn test -f Order-microservice/pom.xml' }
                        // Add more Maven test commands for each microservice
                    )
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
