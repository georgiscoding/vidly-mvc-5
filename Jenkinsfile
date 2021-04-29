CODE_CHANGES = true
pipeline {
 agent any
 tools {
  gradle 'Gradle'
 }
  stages {
    stage("build") {
      when {
        expressions {
          env.BRANCH_NAME == 'dev' && CODE_CHANGES == true
        }
      }
      steps {
        echo 'Building application'
      }
    }


    stage("run frontend") {
      steps {
        echo 'Building with yarn'
        nodejs('Node-15.14.0') {
          sh 'yarn install'
          sh 'npm install'
        }
      }
    }
    stage("test mixed up build with gradle") {
      when {
        expressions {
          //env.BRANCH_NAME or
          env.BRANCH_NAME == 'feature' || BRANCH_NAME == 'master'

        }
      }
      steps {
        echo 'Testing the application'
        // echo 'Building with gradle'
        // sh 'ls'
        // sh 'pwd'
        // sh './gradlew -v' 
      }
    }
  }
  post {
    always {
      sh 'pwd'
    }
    success {
      sh 'ls'
    }
    failure {
      sh 'pwd'
    }
  }
}
