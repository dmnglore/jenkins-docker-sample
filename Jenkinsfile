pipeline{
	agent any
	tools{
			maven 'maven_3.9.10'
		}
	environment {   
		DOCKER_REGISTRY = "https://index.docker.io/v1/"
		DOCKER_IMAGE_NAME = "dayalathakodagi/devops-integration:1.0"
		DOCKER_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIALS')
		//CHATGPT_API_TOKEN_SERVICE_URL = 'http://localhost:8092/generateResponse'
		}
	options {
        timestamps() // Good practice: log timestamps
        skipStagesAfterUnstable() // Fail fast
    }

	stages{
		stage('Build Maven'){
			steps{
			    checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/dmnglore/jenkins-docker-sample']])
			    bat 'mvn clean install'
			}
		}
		stage('Docker Image to Docker Hub'){
				steps{
					script{
						withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', passwordVariable: 'DOCKER_HUB_CREDENTIALS_PSW', usernameVariable: 'DOCKER_HUB_CREDENTIALS_USR')]) {
							
							bat "docker build -t ${DOCKER_IMAGE_NAME} ."
							bat "docker login -u ${DOCKER_HUB_CREDENTIALS_USR} -p ${DOCKER_HUB_CREDENTIALS_PSW} ${DOCKER_REGISTRY}"
							// Push the Docker image to your container registry
							bat "docker push ${DOCKER_IMAGE_NAME}"
						}
					}
				}
			}
			stage('Deploy to K8S'){
				steps{
					script{
					   withCredentials([file(credentialsId: 'KUBECONFIG_FILE', variable: 'KUBECONFIG')]) {
                       bat "kubectl apply -f k8deployment.yaml --kubeconfig=\"%KUBECONFIG%\""
					}
    	        }
            }
         }
	}
	post {
        success {
            echo '✅ Pipeline executed successfully!'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
        always {
            cleanWs() // Clean workspace after build
        }
    }
    }	
