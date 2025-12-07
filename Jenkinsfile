pipeline {
agent any
tools {
maven 'Maven3'
jdk 'Java11'
}
environment {
DOCKER_IMAGE = "YOUR-DOCKERHUB/project1-cicd"
}
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Upload to Nexus') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: 'NODE1-IP:8081',
                    repository: 'maven-releases',
                    credentialsId: 'nexus-credentials',
                    artifacts: [[
                        artifactId: 'app',
                        file: 'target/app-1.0.0.jar',
                        type: 'jar'
                    ]]
                )
            }
        }
        
        stage('Docker Build') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .'
                sh 'docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest'
}
}
stage('Docker Push') {
steps {
withCredentials([usernamePassword(
credentialsId: 'dockerhub-credentials',
usernameVariable: 'USER',
passwordVariable: 'PASS'
)]) {
sh 'echo $PASS | docker login -u $USER --password-stdin'
sh 'docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}'
sh 'docker push ${DOCKER_IMAGE}:latest'
}
}
}
stage('Deploy to K8s') {
steps {
sh 'kubectl apply -f k8s-deployment.yaml'
}
}
}
post {
success {
echo 'Pipeline completed successfully!'
}
failure {
echo 'Pipeline failed!'
}
}
}
