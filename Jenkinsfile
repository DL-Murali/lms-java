pipeline {
    agent any

       stages {
        stage('Sonar Analysis') {
            steps {
                echo 'Analyze Code..'
                sh 'cd LMS-BE && sudo docker run  --rm -e SONAR_HOST_URL=http://13.210.238.114:9000 -e SONAR_LOGIN=sqp_d7d2985cc79fe64296845c9e290b2a54a022051b maven mvn sonar:sonar'
             }
	  }
      }
        }  
