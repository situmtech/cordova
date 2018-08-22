node('ios') {
    stage('Checkout SCM') {
        checkout scm
    }
    
    stage ('iOS test') {
        sh "cd src/ios && xcodebuild test -project SitumCordovaPlugin.xcodeproj -scheme CordovaLib -destination 'platform=iOS Simulator,name=iPhone 8,OS=11.4'"
    }
}

node('androidci') {
    stage('Checkout SCM') {
        checkout scm
    }

    stage('Clean Android'){
        sh "cd src/android && ./gradlew clean"
    }

    try {
        stage('Android test') {
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

    stage('JS test') {
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
      kubectl.inside() {
          sh "npm run jsdoc"
      }
    }

    stage('Archive artifacts'){
      def kubectl = docker.image('node:10.6-slim')
      kubectl.inside("-u 0") {
        sh "apt-get update && apt-get --assume-yes install zip"
        sh "zip -r JSDoc ./docs/JSDoc/*"
        archiveArtifacts "JSDoc.zip"
      }
    }
}
