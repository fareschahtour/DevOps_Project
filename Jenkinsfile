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
                            sh 'mvn  deploy ';
                          }
            }
             stage("SONARQUBE") {
                  steps {
                   sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarqube"
                  }
                }
      }
}