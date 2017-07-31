node{
    stage('Checkout'){
        git branch: env.BRANCH_NAME, credentialsId: 'fabiogoma', url: 'https://github.com/fabiogoma/GMTTIntakeAPI.git'
    }
    stage('Build'){
        def mvnHome = tool 'M3'
        sh "${mvnHome}/bin/mvn clean package"
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }
    stage('Deploy on Development'){
        deployApp('development')
    }
    stage('Deploy on Test'){
        deployApp('test')
    }
    stage('Deploy on Acceptance'){
        deployApp('acceptance')
    }
    stage('Deploy on Production'){
        deployApp('production')
    }
}

def deployApp( environment ) {
    def userInput = true
    def didTimeout = false
    try {
        timeout(time: 15, unit: 'SECONDS') { // change to a convenient timeout for you
            userInput = input(
                    id: 'Proceed1', message: 'Deploy this app right now?', parameters: [
                    [$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm']
            ])
        }
    } catch(err) { // timeout reached or input false
        def build = currentBuild.rawBuild
        def cause = build.getCause(hudson.model.Cause.UserIdCause.class)
        def name = cause.getUserName()
        echo "User: " + name
        if('SYSTEM' == user.toString()) { // SYSTEM means timeout.
            didTimeout = true
        } else {
            userInput = false
            echo "Aborted by: [${name}]"
        }
        err.printStackTrace()
    }

    if (didTimeout) {
        currentBuild.result = 'ABORT'
        echo "no input was received before timeout"
    } else if (userInput) {
        sh "sudo runuser -l vagrant -c 'ansible-playbook -i /home/vagrant/hosts --extra-vars \"deployment_environment=${environment} build_id=${env.BUILD_ID}\" deployment.yml'"
    } else {
        echo "this was not successful"
        currentBuild.result = 'FAILURE'
    }

}