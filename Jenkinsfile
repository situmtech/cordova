
node('androidci') {
    stage('Checkout SCM') {
        checkout scm
    }

    stage('Clean Android'){
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

    stage('Generate JSDoc') {
      def kubectl = docker.image('node:10.6-slim')
      kubectl.pull()
      kubectl.inside("-u 0") {
          sh "cp ./docs/conf.json ./node_modules/jsdoc/"
      }
      kubectl.inside() {
          sh "npm run jsdoc"
      }
    }

    stage('Archive artifacts'){
      def kubectl = docker.image('node:10.6-slim')
      kubectl.inside("-u 0") {
        sh "apt-get update && apt-get --assume-yes install zip"
        sh "zip JSDoc ./docs/JSDoc/*"
        archiveArtifacts "JSDoc.zip"
      }
    }
}
