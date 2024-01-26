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
                    parallel(
                        "Service-Discovery": { sh 'mvn clean install -f Service-Discovery/pom.xml' },
                        "Product-microservice": { 
                            if (fileExists('Product-microservice/pom.xml')) {
                                sh 'mvn clean install -f Product-microservice/pom.xml' 
                            } else {
                                echo 'Product-microservice/pom.xml not found'
                            }
                        },
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
                    sh 'mvn test -f Product-microservice/pom.xml'
                    sh 'mvn test -f Order-microservice/pom.xml'
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
