pipeline {
    agent any

       stages {
        stage('Sonar Analysis') {
            steps {
                echo 'Analyze Code..'
                sh 'cd LMS-BE && sudo docker run  --rm -e -Dsonar.host.url="http://3.25.197.31:9000" -e  -Dsonar.token=="sqp_d5ee0e9d5d226da64ab03c39a3e770c892c79dad"  -v ".:/usr/src" sonarsource/sonar-scanner-cli -Dsonar.projectKey=lms-java'
                }
             }
	   }
	 }  
