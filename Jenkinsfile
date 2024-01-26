pipeline {
    agent any

    stages {
        stage('Preparation') {
            steps {
                git 'https://github.com/PraveenRaj00/microservice-hotel-system.git'
                mvnHome = tool 'M3'
            }
        }

       stage('Build') {
    steps {
        script {
            withEnv(["MVN_HOME=$mvnHome"]) {
                if (isUnix()) {
                    sh '''
                        $MVN_HOME/bin/mvn clean install -f Service-Discovery/pom.xml
                        $MVN_HOME/bin/mvn clean install -f Product-microservice/pom.xml
                        $MVN_HOME/bin/mvn clean install -f Order-microservice/pom.xml
                        $MVN_HOME/bin/mvn clean install -f API-Gateway/pom.xml
                        # Add more Maven commands for each microservice
                    '''
                } else {
                    bat '''
                        "%MVN_HOME%\\bin\\mvn" clean install -f Service-Discovery/pom.xml
                        "%MVN_HOME%\\bin\\mvn" clean install -f Product-microservice/pom.xml
                        "%MVN_HOME%\\bin\\mvn" clean install -f Order-microservice/pom.xml
                        "%MVN_HOME%\\bin\\mvn" clean install -f API-Gateway/pom.xml
                        rem Add more Maven commands for each microservice
                    '''
                }
            }
        }
    }
}



        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh '"$MVN_HOME/bin/mvn" test -f Product-microservice/pom.xml'
                        sh '"$MVN_HOME/bin/mvn" test -f Order-microservice/pom.xml'
                        // Add more Maven commands for running tests in each microservice
                    } else {
                        bat(/"%MVN_HOME%\bin\mvn" test -f Product-microservice/pom.xml/)
                        bat(/"%MVN_HOME%\bin\mvn" test -f Order-microservice/pom.xml/)
                        // Add more Maven commands for running tests in each microservice
                    }
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
