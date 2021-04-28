pipeline {
 agent any
 tools {
  gradle 'Gradle'
 }
  stages {
    stage("run frontend") {
      steps {
        echo 'Building with yarn'
        nodejs('Node-15.14.0') {
          sh 'yarn install'      
        }
      }
    }
    stage("run backend") {
      steps {
        echo 'Building with gradle'
        sh './gradlew -v' 
      }
    }
  }
}
