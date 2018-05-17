#!/usr/bin/env groovy

node {

    stage('whoami') {
        sh "whoami"
    }
    stage('checkout') {
        checkout scm
    }

    docker.image('openjdk:8').inside('-u root -e MAVEN_OPTS="-Duser.home=./"') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x mvnw"
            sh "./mvnw clean"
        }

        stage('whoami') {
            sh "whoami"
        }

        stage('install tools') {
            sh "./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-yarn -DnodeVersion=v8.9.4 -DyarnVersion=v1.3.2"
        }

        stage('yarn install') {
            sh "./mvnw com.github.eirslett:frontend-maven-plugin:yarn -DskipTests"
        }
    }

    stage('remove docker image'){
      sh "docker-compose -f src/main/docker/app.yml stop"
      sh "docker rmi -f fmp"
    }

    stage('packaging') {
        sh "./mvnw package dockerfile:build -DskipTests"
    }

    stage('deploy') {
      sh "docker-compose -f src/main/docker/app.yml up"
    }
}
