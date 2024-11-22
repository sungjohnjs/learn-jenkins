pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        echo('Packaging Code Into JAR File')
        sh('mvn clean package')
      }
    }
    stage('Test') {
      steps {
        echo('Test Script Has not been made')
      }
    }
    stage('Stop Current Running') {
      steps {
        echo('Stop Current Service If Exist')
        sh('kill $(cat ./pid.file)')
      }
    }
    stage('Start') {
      steps {
        echo('Running the jar file')
        sh('nohup java -jar ./target/learn_jenkins-0.0.1.jar > log/other.log 2>&1 & echo $! > ./pid.file')
      }
    }
  }
}
