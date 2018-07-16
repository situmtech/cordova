import java.nio.file.NoSuchFileException

PROPERTIES_FILE = 'SitumService/gradle.properties'

VERSION_NAME = ""

ARTIFACTORY_INTERNAL_ID = "-1505608236@1461239289843"
ARTIFACTORY_PUBLIC_ID = "-1505608236@1461239289844"

/**
 * Organización de los diferentes productos
 * ----------------------------------------
 * - Rama master
 *  Publica el plugin en npm
 * - Resto de ramas
 +  No se publican
 */

node('androidci') {

    stage('Checkout SCM') {
        checkout scm
    }

    stage('Clean Android'){
        sh "ls"
        sh "cd src/android && ./gradlew clean"
    }

    try {
        stage('Test Android') {
            sh "cd src/android && ./gradlew test --continue"
        }
    } finally {
        stage('Publish tests') {
            junit 'src/android/app/build/test-results/*/*.xml'
        }
    }
}

node('vm1-docker') {

    stage('Checkout SCM') {
        checkout scm
    }

    stage('Test JS') {
        def kubectl = docker.image('node:10.6-slim')
        kubectl.pull()
        kubectl.inside("-u 0") {
            sh "npm install"
            sh "npm test"
        }
    }
}
