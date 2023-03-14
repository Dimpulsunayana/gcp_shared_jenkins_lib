def call(){
pipeline{
   agent any
  parameters {
//            choice(name: 'Infra_env', choices: ['dev', 'prod'], description: 'Pick the env')
            choice(name: 'Action', choices: ['apply', 'destroy'], description: 'Pick the action')
         }
   options {
      ansiColor('xterm')
   }
   environment {
        CLOUDSDK_CORE_PROJECT='sbx-107038-rm0228-bd-3ba40310'
        GOOGLE_APPLICATION_CREDENTIALS = credentials('gcloud-credentials')
      
    }
   stages{
       stage('init'){
           steps{
               script{
                    sh "terraform init"
       }
     }
       }
       stage('apply/destroy'){
           steps{
               script{
                  sh "terraform ${params.Action} --auto-approve"
                  
           }
       }
     }
      
//       or

// stage('apply/destroy'){
//            steps{
//                script{
//                   if (params.Action =="apply"){
//                   sh "terraform apply --auto-approve"
//                   } else {
//                   sh "terraform destroy --auto-approve"
//            }
//                }
//        }
//      }


   }
}

}
