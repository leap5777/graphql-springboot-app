pipeline {
    agent any

    environment {
        APP_NAME = "springboot-graphql-app"
        DOCKER_IMAGE = "karan77777/springboot-graphql-app"
        KUBE_NAMESPACE = "default"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Clone the repo into a workspace directory
                git branch: 'main',
                    url: 'https://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@github.com/leap5777/graphql-springboot-app.git'

                // Copy code to a local directory if needed
                bat "xcopy /E /I /Y \"%WORKSPACE%\" C:\\jenkins_workspace\\%APP_NAME%"
            }
        }

        stage('Build JAR') {
            steps {
                // Change to local workspace and build using Maven Wrapper
                dir("C:\\jenkins_workspace\\${APP_NAME}") {
                    bat ".\\mvnw clean package -DskipTests"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("C:\\jenkins_workspace\\${APP_NAME}") {
                    bat "docker build -t %DOCKER_IMAGE%:%BUILD_NUMBER% ."
                    bat "docker push %DOCKER_IMAGE%:%BUILD_NUMBER%"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Deploy the new Docker image to Kubernetes
                bat "kubectl set image deployment/%APP_NAME%-deployment %APP_NAME%=%DOCKER_IMAGE%:%BUILD_NUMBER% --namespace=%KUBE_NAMESPACE%"
            }
        }
    }

    post {
        success {
            echo "Deployment successful! Docker image %DOCKER_IMAGE%:%BUILD_NUMBER% deployed to Kubernetes."
        }
        failure {
            echo "Deployment failed. Check logs for errors."
        }
    }
}
