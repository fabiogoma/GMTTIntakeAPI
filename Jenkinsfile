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

    try {
        timeout(time: 15, unit: 'SECONDS') { // change to a convenient timeout for you
            userInput = input(
                    id: 'Proceed1', message: 'Deploy this app right now?', parameters: [
                    [$class: 'BooleanParameterDefinition', defaultValue: true, description: '', name: 'Please confirm']
            ])
        }
    } catch(err) {
        echo err.getCause().toString()
        currentBuild.result = 'ABORTED'
        userInput = false
    }

    if (userInput) {
        sh "sudo runuser -l vagrant -c 'ansible-playbook -i /home/vagrant/hosts --extra-vars \"deployment_environment=${environment} build_id=${env.BUILD_ID}\" deployment.yml'"
    }

}