pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/heurit-refactoring/heurit-refactoring.git', branch: 'main')
      }
    }

    stage('build') {
      steps {
        tool 'gradle'
        sh './gradlew build'
      }
    }

  }
}