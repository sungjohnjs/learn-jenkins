/* groovylint-disable CompileStatic */
pipeline {
  agent any

  environment {
    JAVA_HOME = '/root/.sdkman/candidates/java/current'
    PATH = "${JAVA_HOME}/bin:${env.PATH}"
    PORT = 8019
  }

  stages {
    stage('Setup') {
      steps {
        script {
          echo 'Preparing the environment...'
          // Ensure log and target directories exist
          sh 'mkdir -p log'
          sh 'rm -rf target || true' // Clean previous build artifacts
          echo 'Check Java Version'
          sh 'java --version'
        }
      }
    }

    stage('Build') {
      steps {
        script {
          echo 'Building the project...'
          sh 'mvn clean package -DskipTests' // Package the JAR without running tests
        }
      }
    }

    stage('Test') {
      steps {
        script {
          echo 'Running tests...'
          sh 'mvn test' // Run tests
        }
      }
    }

    stage('Stop Current Service') {
      steps {
        script {
          echo 'Stopping any currently running service...'
          sh '''
          if lsof -i:${PORT} > /dev/null; then
            echo "Port ${PORT} is in use. Stopping service..."
            lsof -t -i:${PORT} | xargs kill -9 || true
          fi
          '''
        }
      }
    }

    stage('Start Application') {
      steps {
        script {
          echo 'Starting the Spring Boot application...'
          sh 'chmod +x ./bin ./bin/*'
          sh 'JENKINS_NODE_COOKIE=dontKillMe && ./bin/start.sh'
        }
      }
    }

    stage('Verify Deployment') {
      steps {
        script {
          echo 'Verifying that the application is running...'
          sh '''
          if ! curl -s http://localhost:${PORT} > /dev/null; then
            echo "Application is not running on port ${PORT}."
            exit 1
          else
            echo "Application is successfully running on port ${PORT}."
          fi
          '''
        }
      }
    }
  }

  post {
    always {
      script {
        echo 'Pipeline completed. Cleaning up temporary files...'
        sh 'rm -f pid.file || true'
      }
    }
    failure {
      script {
        echo 'Pipeline failed. Check logs for details.'
        sh 'cat log/app.log || true' // Output app log on failure for debugging
      }
    }
  }
}
