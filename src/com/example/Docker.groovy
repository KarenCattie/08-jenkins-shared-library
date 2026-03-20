#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t ${imageName}:${script.IMAGE_VERSION} ."
        }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(
                credentialsId: 'docker-hub-creds',
                passwordVariable: 'DOCKER_PASS',
                usernameVariable: 'DOCKER_USER'
        )]) {
            script.sh "echo '${script.DOCKER_PASS}' | docker login -u '${script.DOCKER_USER}' --password-stdin"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push $imageName:${script.IMAGE_VERSION}"
    }
}