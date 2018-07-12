import java.nio.file.NoSuchFileException

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