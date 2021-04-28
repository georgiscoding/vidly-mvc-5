pipeline {
 agent any
 
  stages {
    stage("run frontend") {
      steps {
        echo 'Building with yarn'
      }
    }
    stage("run backend") {
      steps {
        echo 'Building with gradle'
      }
    }
  }
}
