CODE_CHANGES = true
pipeline {
 agent any

 environment {
   NEW_VERSION = '1.4.0'
   //Need the -Credentials Plugin and Credentials Binding plugin for this in Jenkins
   SERVER_CREDENTIALS = credentials('server-credentials')
 }

//  tools {
//   gradle 'Gradle'
//  }
  stages {
    stage("build") {
      when {
        expression {
          BRANCH_NAME == 'feature' && CODE_CHANGES == true
        }
      }
      steps {
        echo 'Building application'
        // Need to use double quoutes since we are using a variable inside the string
        echo "Building version ${NEW_VERSION}"
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
        expression {
          //env.BRANCH_NAME or
          env.BRANCH_NAME == 'feature' || BRANCH_NAME == 'master'

        }
      }
      steps {
        echo 'Testing the application'
        // Without wrapper since we included it in tools at the beginning.
        // echo 'Building with gradle'
        // sh 'ls'
        // sh 'pwd'
        // sh './gradlew -v' 
      }
    }

    stage("deploy") {
      steps {
        echo 'deploying the application...'
        echo "Deploying using the credentials : ${SERVER_CREDENTIALS}"
        // With wrapper it looks like this.
        withCredentials([
          // usernamepassword cause thats the type.. server-credentials is the ID
          usernamepassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PASSWORD)
        ]) {
            sh "some script ${USER} ${PASSWORD}"
        }
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
