#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "chmod +x mvnw"
        sh "./mvnw clean"
    }

    stage("whoami"){
        sh "whoami"
    }

    stage("env"){
        sh "env"
    }

    stage('package and deploy') {
        sh "./mvnw -Pprod tomcat7:redeploy -DskipTests"
    }

}
