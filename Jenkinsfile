pipeline {
    agent {
        docker {
            image 'maven:3.6.2-jdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deliver') {
            steps{
                sh "ssh -o StrictHostKeyChecking=no j@172.17.0.1 rm -rf /home/j/Documents/exe"

                sh "ssh -o StrictHostKeyChecking=no j@172.17.0.1 mkdir -p /home/j/Documents/exe"

                sh "scp -r target j@172.17.0.1:/home/j/Documents/exe/"
            }
        }
    }
}