def buildApp() {
    echo 'Building the application...'
}

def testApp() {
    echo 'Testing the application...'
}

def deployApp() {
    echo 'Deploying the application'
    echo "Deploying the version: ${params.VERSION}"
    echo "Deploying using the credentials : ${SERVER_CREDENTIALS}"

    // usernamepassword cause thats the type.. server-credentials is the ID
    withCredentials([
        usernamePassword(credentialsId: 'server-credentials', usernameVariable: 'USER', passwordVariable: 'PWD')
        ]) {
            echo "some script ${USER} ${PWD}"
        }
}

return this