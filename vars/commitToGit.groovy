#!/user/bin/env groovy

def call(){
    withCredentials([usernamePassword(
            credentialsId: 'github-creds',
            usernameVariable: 'GIT_USER',
            passwordVariable: 'GIT_PASS'
    )]) {
        sh 'git config --global user.email "jenkins@ci.com"' // Sets a git identity label for Jenkins (required to make commits)
        sh 'git config --global user.name "Jenkins"'
        sh "git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/KarenCattie/08-jenkins-exercises.git"
        sh 'git add app/package.json' // Only commits `package.json` — that's the only file that changed (the version bump)
        sh "git commit -m \"ci: bump version to ${IMAGE_VERSION}\""
        sh 'git push origin HEAD:main' // HEAD = "my current local commit", main = "push it to the main branch on GitHub"
    }
}