pipeline {
    agent any

    stages {
        stage('compile') {
            steps {
                sh 'chmod +x ./gradlew && ./gradlew build -x test'
            }
        }
        stage('unit test') {
            steps {
                sh './gradlew test'
                publishHTML(target: [
                    reportDir: 'build/reports/tests/test/',
                    reportFiles: 'index.html',
                    reportName: 'Unit Test Report'
                ])
            }
        }
        stage('Docker build') {
            steps {
                sh 'docker build -t 172.17.0.1:5000/booklibrary .'
            }
        }
        stage('Docker push') {
            steps {
                sh 'docker push 172.17.0.1:5000/booklibrary'
            }
        }
        stage('Deploy UAT') {
            steps {
                sh 'docker run -d --rm -p 172.17.0.1:8765:8082 --name booklibrary 172.17.0.1:5000/booklibrary'
            }
        }
        stage('Acceptance Test') {
            steps {
                sh './gradlew cucumberCli -Dbooklibrary.url=http://172.17.0.1:8765/books'
                publishHTML(target: [
                    reportDir: 'build/reports/tests/cucumber/',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber Report'
                ])
            }
        }

    }

    post {
        always {
            sh 'docker stop booklibrary'
        }
    }
}
