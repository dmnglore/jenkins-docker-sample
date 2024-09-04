pipeline{
	agent any
	tools{
			maven 'Maven_3.9.6'
		}
	environment {   
		DOCKER_REGISTRY = "https://index.docker.io/v1/"
		DOCKER_IMAGE_NAME = "dayalathakodagi/devops-integration:1.0"
		DOCKER_CREDENTAILS = credentials('DOCKER_HUB_CREDENTIALS')
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
					    withKubeConfig([credentialsId: 'k8configpwd']) {
                        bat 'kubectl apply -f k8deployment.yaml'
                }
					}
    	        }
            }
         }
    }	
