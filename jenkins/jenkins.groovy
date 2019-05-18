node ('master') {
    stage('pull') {
        git 'https://github.com/tomek401273/report-generator.git'
    }
    stage('package-report') {
        sh script: 'mvn package'
    }
    stage('build-report') {
        sh script: 'docker build -t tomek371240/report-service:1.1 .'
    }
    stage('deploy-report') {
        sh script: 'docker stack deploy -c report.yml report-stack'
    }
}
