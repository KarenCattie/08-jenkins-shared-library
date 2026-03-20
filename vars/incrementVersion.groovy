#!/user/bin/env groovy

def call() {
    dir('app'){ // runs the command inside the app/ folder
        sh 'npm version minor --no-git-tag-version' // npm version minor - bumps the middle number in package.json e.g. 1.0.0 → 1.1.0
                                                    // --no-git-tag-version — prevents npm from trying to create a git tag (Jenkins handles git itself, so this would cause a conflict)
    }
        def version = sh(
                script: "cd app && node -p \"require('./package.json').version\"",
                returnStdout:true // runs the command AND captures its output as a string
        ).trim() // removes any trailing newline from the output
        env.IMAGE_VERSION = "$version-$BUILD_NUMBER" // stores it as a pipeline environment variable so later stages can use ${IMAGE_VERSION}
}