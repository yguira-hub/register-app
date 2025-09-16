pipeline {
    agent { label 'Jenkins-Agent' }

    tools {
        jdk 'java17'
        maven 'Maven3'
    }

    environment {
        APP_NAME = 'register-app'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')  // ID du credential
        DOCKERHUB_REPO = 'youssraguira/register-app'  // Ton username/repo Docker Hub
        DOCKER_IMAGE_TAG = "${BUILD_NUMBER}"  // Tag avec le numéro de build
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
        stage('Scan Docker Image') {
            steps {
                sh 'trivy image --exit-code 0 --no-progress $DOCKERHUB_REPO:$DOCKER_IMAGE_TAG'
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    def dockerImage = docker.build("$DOCKERHUB_REPO:$DOCKER_IMAGE_TAG")
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
