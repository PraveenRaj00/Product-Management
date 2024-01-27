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
                    def services = ["Service-Discovery", "Product-microservice", "Order-microservice", "API-Gateway"]
                    
                    parallel (
                        "Build Microservices": {
                            for (def service in services) {
                                // Check if pom.xml exists before attempting to build
                                if (fileExists("Product-Management/Order-microservice/pom.xml")) {
                                    echo "Building"
                                    sh "mvn clean install -f Product-Management/Order-microservice/pom.xml"
                                } else {
                                    echo "pom.xml not found"
                                }
                            }
                        }
                    )
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    parallel (
                        "Test Order-microservice": {
                            // Check if Order-microservice/pom.xml exists before attempting to test
                            if (fileExists("Order-microservice/pom.xml")) {
                                echo "Testing Order-microservice"
                                sh "mvn test -f Order-microservice/pom.xml"
                            } else {
                                echo "Order-microservice/pom.xml not found"
                            }
                        },
                        "Test Product-microservice": {
                            // Check if Product-microservice/pom.xml exists before attempting to test
                            if (fileExists("Product-microservice/pom.xml")) {
                                echo "Testing Product-microservice"
                                sh "mvn test -f Product-microservice/pom.xml"
                            } else {
                                echo "Product-microservice/pom.xml not found"
                            }
                        }
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
