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
        findAndReplace = "sed -i.bak \'s/BUILD_ID/${BUILD_ID}/g\'"

        sh "sudo runuser -l vagrant -c \'${findAndReplace} /home/vagrant/deployment.yml\'"
        sh "sudo runuser -l vagrant -c 'ansible-playbook -i /home/vagrant/hosts --extra-vars \"deployment_environment=development\" deployment.yml'"
    }
    stage('Deploy on Test'){
        //sudo runuser -l vagrant -c 'whoami'
    }
    stage('Deploy on Acceptance'){
        //sudo runuser -l vagrant -c 'whoami'
    }
    stage('Deploy on Production'){
        //sudo runuser -l vagrant -c 'whoami'
    }
}