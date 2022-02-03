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
		
		stage("Pull Latest Image") {
            steps {   
                sh "docker pull amitkumarr99/selenium-docker"
            }
        } 
		stage("Start Grid") {
            steps {   
                sh "docker-compose up -d selenium-hub chrome"
            }
        }
		stage("Run Tests") {
            steps {   
                sh "docker-compose up  smoke-suite regression-suite"
            }
        }
    }
	post{
	    always{
		    archiveArtifacts artifacts: 'output/**'  
            sh "docker-compose down"    
            }
		}
}
