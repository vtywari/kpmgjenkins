 def jenkinsconnection(hostname, credential,command) 
  {
      try{    
      def remote = [:]
            remote.name = "node"
            remote.host = hostname
            remote.allowAnyHosts = true
            //def command  = command

          

            withCredentials([sshUserPrivateKey(credentialsId: credential, 
                      keyFileVariable: 'JENKINS_PRIVATE_KEY', passphraseVariable: '',
                      usernameVariable: 'USERNAME')]) 
                      {
              remote.user = USERNAME
              remote.identityFile = JENKINS_PRIVATE_KEY
              //sshCommand remote: remote, command: command, failOnError: 'true'
              def fileName= "home/vikas/Desktop/learn/Groovylearn/result.text"
              File myfilename= new File(fileName)
               myfilename.write('hello')
               println myfilename.text
              
                      }

              }
          catch(Exception ex)
          {
    
                      println("Catching the exception " +ex.message);
  
          }
    }
    def manualconection(hostname, username, password, command )
    {  
      try{   
      def remote = [:]
            remote.name = "node"
            remote.host = hostname
            remote.allowAnyHosts = true
            //def command  = command

            
            remote.user = username
            remote.password = password
            sshCommand remote: remote, command: command, failOnError: 'true'
            }

            catch(Exception ex){

              println("Catching the exception " +ex.message)
            }
    }

  pipeline {
    agent any
    stages {
      stage('Deploy Using SSH Steps') {
        steps {
              jenkinsconnection("192.168.50.16","sshuser", "df -h")
              //manualconection("192.168.50.16","root","redhat","ls -lrth")
              
        }
      } 
    }
    }
