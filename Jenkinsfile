pipeline {
    // master executor should be set to 0
    agent any
    stages {
        stage('Build Jar') {
            steps {
                //bat
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image') {
            steps {
                  image 'maven:3-alpine'
                  args '-v $HOME/.m2:/root/.m2'
                //bat
                sh "docker build -t='amitkumarr99/selenium-docker' ."
            }
        }
        stage('Push Image') {
            steps {
			    withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
                    //bat
			        sh "docker login --username=${user} --password=${pass}"
			        sh "docker push amitkumarr99/selenium-docker:latest"
			    }                           
            }
        }
    }
}
