pipeline {
    agent { label 'Jenkins-Agent' }

    tools {
        jdk 'java17'
        maven 'Maven3'
    }

    environment {
        APP_NAME = 'register-app'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        DOCKERHUB_REPO = 'youssraguira/register-app'
        DOCKER_IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'github',
                    url: 'https://github.com/yguira-hub/register-app.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarQube Scanner'
                    withSonarQubeEnv('SonarQube') {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Construire l'image
                    def dockerImage = docker.build("$DOCKERHUB_REPO:$DOCKER_IMAGE_TAG")
                    // Scanner l'image avant de la pousser
                    sh "trivy image --exit-code 0 --no-progress $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG"
                    // Pousser l'image vers Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        dockerImage.push("$DOCKER_IMAGE_TAG")
                        dockerImage.push('latest')
                    }
                }
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
