pipeline {
  agent any

  environment {
    JAVA_HOME = '/root/.sdkman/candidates/java/current'
    PATH = "${JAVA_HOME}/bin:${env.PATH}"
  }

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

        script {
          sh """
          if lsof -i:8019 > /dev/null; then
              echo "Port 8019 is in use. Stopping existing service..."
              lsof -t -i:8019 | xargs kill -9
          fi
          """
        }

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
