CODE_CHANGES = true
pipeline {
 agent any

 environment {
   NEW_VERSION = '1.4.0'
   //Need the -Credentials Plugin and Credentials Binding plugin for this in Jenkins
   SERVER_CREDENTIALS = credentials('server-credentials')
 }

  // May be some external config you want to provide to your build. May be build you want to deploy to 
  // staging server and you want to be able to choose a VERSION you want o deploy
 parameters {
   //string(name: 'VERSION', defaultValue: '', description: 'Version to deploy on staging server')
   booleanParam(name: 'executeTests', defaultValue: true, description: 'Version to deploy on staging server')
   choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: 'Version to deploy on staging server')
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
          //params.executeTests == true
          params.executeTests

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
          usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')
        ]) {
            echo "some script ${USER} ${PWD}"
        }
        echo "Deploying the version: ${params.VERSION}"
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
