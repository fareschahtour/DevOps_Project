pipeline {
     agent any;
      stages {
            stage("GIT") {
              steps {
                sh 'git checkout master'
                sh 'git pull origin master'
              }
            }
            stage("MAVEN BUILD") {
              steps {
                sh 'mvn clean install'
              }
            }
           stage("MOCKITO") {
                          steps {
                            sh "mvn test"
                          }
           }
            stage('MVN NEXUS') {
                          steps {
                            sh 'mvn  deploy';
                          }
            }
             stage("SONARQUBE") {
                  steps {
                   sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarqube"
                  }
                }
                stage("DOCKER IMAGE") {
                                steps {
                                  sh 'docker build -t fareschahtour/devops:latest .'
                                }
                   }

                        stage("Docker COMPOSEE") {
                             steps {
                                   sh 'docker compose up -d'
                             }
                        }
                    stage('DOCKER HUB') {
                                           steps {
                                                    withCredentials([string(credentialsId: 'pass', variable: 'DOCKER_PASSWORD')]) {
                                                      sh '''
                                                        sudo chmod 666 /var/run/docker.sock
                                                        docker login -u chahinemahfoudh -p 191JMT4619
                                                        docker push docker push chahinemahfoudh/alpine
                                                      '''
                                                    }
                                                  }
                                        }
      }
}