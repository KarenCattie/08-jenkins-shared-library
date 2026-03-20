#!/user/bin/env groovy

def call(){
    dir('app'){
        sh 'npm install' // Installs dependencies then runs Jest tests
        sh 'npm test'    // If npm test fails, the entire pipeline stops here — nothing gets built or pushed. This is the safety gate that ensures only working code gets deployed
    }
}