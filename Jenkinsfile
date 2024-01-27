pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your Git repository
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/PraveenRaj00/Product-Management.git']]])
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def allServices = ['Service-Discovery', 'Product-microservice', 'Order-microservice', 'API-Gateway']
                    def selectedServices = ['Product-microservice', 'Order-microservice']

                    parallel (
                        "Build-All-Services": {
                            for (service in allServices) {
                                dir(service) {
                                    if (isUnix()) {
                                        sh "'${mvnHome}/bin/mvn' clean install"
                                    } else {
                                        bat(/"${mvnHome}\bin\mvn" clean install/)
                                    }
                                }
                            }
                        },
                        "Build-Selected-Services": {
                            for (service in selectedServices) {
                                dir(service) {
                                    if (isUnix()) {
                                        sh "'${mvnHome}/bin/mvn' clean install"
                                    } else {
                                        bat(/"${mvnHome}\bin\mvn" clean install/)
                                    }
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
                    def mvnHome = tool 'Maven'
                    def selectedServices = ['Product-microservice', 'Order-microservice']

                    for (service in selectedServices) {
                        dir(service) {
                            if (isUnix()) {
                                sh "'${mvnHome}/bin/mvn' test"
                            } else {
                                bat(/"${mvnHome}\bin\mvn" test/)
                            }
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            // Archive the JUnit test results
            junit '**/target/surefire-reports/*.xml'

            // Clean up or perform any other actions after the build
            cleanWs()
        }
    }
}
