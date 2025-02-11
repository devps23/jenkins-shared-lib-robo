def call() {
    node('node1') {
        stage('CodeCheckout') {
            sh "find ."
            sh "find . | sed -e '1d' | xargs rm -rf"
            if (env.TAG_NAME ==~ '.*') {
                env.branch_name = "refs/tags/${env.TAG_NAME}"
            } else {
                env.branch_name = "${env.BRANCH_NAME}"
            }
            checkout([$class: 'GitSCM',
                    branches: [[name: "${branch_name}"]],
                    userRemoteConfigs: [[url: "https://github.com/devps23/expense-${component}"]]]
            )
        }
    }
    if (env.TAG_NAME ==~ '.*') {
        stage('Build Code') {
            sh "env"
            print 'OK'
        }
        stage('Release Software') {
            print 'OK'
        }
    } else {
        stage('Lint code') {
            print 'OK'
        }
    }
    if (env.BRANCH_NAME != 'main') {
        stage('Run unit tests') {
            print 'OK'
        }
        stage('Run integration tests') {
            print 'OK'
        }
    } else {
        stage('Sonar Scan Code Review') {
            print 'OK'
        }
    }
}
