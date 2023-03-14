def call(){
pipeline{
   agent any
//   parameters {
//            choice(name: 'Infra_env', choices: ['dev', 'prod'], description: 'Pick the env')
//             choice(name: 'Action', choices: ['apply', 'destroy'], description: 'Pick the action')
//         }
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
                //  withCredentials([file(credentialsId: 'gcloud-credentials' , variable: 'GCLOUD_CREDS')]){
                    sh "terraform init"
                  //-backend-config=env-${Infra_env}/state.tfvars
          // }
       }
     }
       }
       stage('apply/destroy'){
           steps{
               script{
                 // withCredentials([file(credentialsId: 'gcloud-credentials' , variable: 'GCLOUD_CREDS')]){
                  sh "terraform apply --auto-approve"
                  //-var-file=env-${Infra_env}/main.tfvars"
               //}
           }
       }
     }
   }
}

}
