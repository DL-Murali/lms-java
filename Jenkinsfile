pipeline {
    agent any

       stages {
        stage('Sonar Analysis') {
            steps {
                echo 'Analyze Code..'
                sh 'cd LMS-BE && sudo docker run  --rm -e  sh "mvn sonar:sonar -Dsonar.host.url=${http://13.210.238.114:9000} -Dsonar.login=${sqp_d7d2985cc79fe64296845c9e290b2a54a022051b}"
             }
	  }
      }
      }  
