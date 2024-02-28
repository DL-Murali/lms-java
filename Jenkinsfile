pipeline {
    agent any

       stages {
        stage('Sonar Analysis') {
            steps {
                echo 'Analyze Code..'
                sh 'cd LMS-BE && sudo docker run  --rm -e  mvn clean verify sonar:sonar -Dsonar.projectKey=lms-java -Dsonar.projectName='lms-java' -Dsonar.host.url="http://13.210.238.114:9000" -Dsonar.token="sqp_d7d2985cc79fe64296845c9e290b2a54a022051b"
                }
             }
	   }
	 }  
