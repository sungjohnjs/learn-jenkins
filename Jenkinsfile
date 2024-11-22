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
        script {
          sh """
          if lsof -i:8019 > /dev/null; then
              echo "Port 8019 is in use. Stopping existing service..."
              lsof -t -i:8019 | xargs kill -9
              sleep 2 # Ensure the port is freed
          fi
          """
        }
      }
    }
    stage('Start') {
      steps {
        echo('Running the jar file')
        script {
          sh """
          nohup java -jar ./target/learn_jenkins-0.0.1.jar > app.log 2>&1 &
          echo $! > ./pid.file
          """
        }
      }
    }
    stage('Health Check') {
      steps {
        script {
          sh """
          echo "Checking application health..."
          curl -f http://localhost:8019/actuator/health || echo "Application is not healthy"
          """
        }
      }
    }
  }
}
