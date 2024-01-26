
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
                         
                        // Add more Maven commands for each microservice
                    )
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test -f Config-Server/pom.xml'
                    sh 'mvn test -f Gateway-Service/pom.xml'
                    sh 'mvn test -f HotelService/pom.xml'
                    sh 'mvn test -f RatingService/pom.xml'
                    sh 'mvn test -f Service-Registry/pom.xml'
                    sh 'mvn test -f User-Service/pom.xml'
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
