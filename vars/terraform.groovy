def call(){
    pipeline{
        options {
            ansiColor('xterm')
        }
       agent any
//      {
//             node{
//                 label 'dimpul'
//             }
//         }

//         parameters {
//             choice(name: 'Infra_env', choices: ['dev', 'prod'], description: 'Pick the env')
//             choice(name: 'Action', choices: ['apply', 'destroy'], description: 'Pick the action')
//         }

        stages{

            stage('Terraform init'){
                steps{
                    sh "terraform init -backend-config=env-${Infra_env}/state.tfvars"
                }
            }

            stage('Terraform apply/destroy'){
                steps{
                    sh "terraform ${ACTION} --auto-approve -var-file=env-${Infra_env}/main.tfvars"
                }
            }
        }

        post{
            always{
                cleanWs()
            }
        }
    }

}
