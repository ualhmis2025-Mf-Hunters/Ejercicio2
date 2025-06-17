pipeline {
  agent any

  tools {
    // Nombre dado a la instalación de Maven en "Tools configuration"
    maven "maven default"
  }
  environment {
    MAVEN_OPTS = '--add-opens java.base/java.lang=ALL-UNNAMED'
  }

  stages {
    stage('Git fetch') {
      steps {
        // Get some code from a GitHub repository
        git branch: 'main', url: 'https://github.com/ualhmis2025-Mf-Hunters/Ejercicio2.git',
            credentialsId: 'jgm847TokenBien'

      }
    }
    stage('Compile, Test, Package') {
      steps {
        // When necessary, use '-f path-to/pom.xml' to give the path to pom.xml
        // Run goal 'package'. It includes compile, test and package.
        sh "mvn  -f pom.xml clean package"
      }
      
      post {
        // Record the test results and archive the jar file.
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts '**/target/*.jar'
          jacoco( 
            execPattern: '**/target/jacoco.exec',
            classPattern: '**/target/classes',
            sourcePattern: '**/src/main/java/',
            exclusionPattern: '**/src/test/java/'
          )
          publishCoverage adapters: [jacocoAdapter('**/target/site/jacoco/jacoco.xml')] 
        }
      }
    }
      stage ('Analysis') {
    steps {
      // Warnings next generation plugin required
      sh "mvn -f pom.xml checkstyle:checkstyle site -DgenerateReports=false"
      sh "mvn -f pom.xml site"
    }
    post {
      success {
        dependencyCheckPublisher pattern: '**/target/site/es/dependency-check-report.xml'
        recordIssues enabledForFailure: true, tool: checkStyle()
        recordIssues enabledForFailure: true, tool: pmdParser()
        recordIssues enabledForFailure: true, tool: cpd()
        recordIssues enabledForFailure: true, tool: findBugs()
        recordIssues enabledForFailure: true, tool: spotBugs()
      }
    }
  }
      stage ('Documentation') {
      steps {
	    sh "mvn -f pom.xml javadoc:javadoc javadoc:aggregate"
      }
      post{
        success {
          step $class: 'JavadocArchiver', javadocDir: 'target/reports/apidocs', keepAll: false
          publishHTML(target: [reportName: 'Maven Site', reportDir: 'target/reports', reportFiles: 'index.html', keepAll: false])
        }
      }
    }
	    stage('SonarQube analysis') {
    	steps {
      	withSonarQubeEnv(credentialsId: 'sonar_server', installationName: 'servidor_sonarqube') { 
        sh 'mvn -f pom.xml sonar:sonar' 
      }
    }
  }
  }
}