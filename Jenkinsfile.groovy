class Build{
    String description
}

def getRepoURL() {
    sh "git config --get remote.origin.url > .git/remote-url"
    return readFile(".git/remote-url").trim()
}

def getCommitSha() {
    sh "git rev-parse HEAD > .git/current-commit"
    return readFile(".git/current-commit").trim()
}

def updateGithubCommitStatus(build) {
    // workaround https://issues.jenkins-ci.org/browse/JENKINS-38674
    repoURL = getRepoURL()
    commitSha = getCommitSha()

    step([
            $class: 'GitHubCommitStatusSetter',
            reposSource: [$class: "ManuallyEnteredRepositorySource", url: repoURL],

            commitShaSource: [$class: "ManuallyEnteredShaSource", sha: commitSha],
            errorHandlers: [[$class: 'ShallowAnyErrorHandler']],
            statusResultSource: [
                    $class: 'ConditionalStatusResultSource',
                    results: [
//                            [$class: 'AnyBuildResult', result: 'SUCCESS', state: 'SUCCESS', message: build.description]
                            [$class: 'BetterThanOrEqualBuildResult', result: 'SUCCESS', state: 'SUCCESS', message: build.description],
                            [$class: 'BetterThanOrEqualBuildResult', result: 'FAILURE', state: 'FAILURE', message: build.description],
                            [$class: 'AnyBuildResult', state: 'FAILURE', message: 'Loophole']
                    ]
            ]
    ])
}

void setBuildStatus(String message, String state) {
    step([
            $class: "GitHubCommitStatusSetter",
            reposSource: [$class: "ManuallyEnteredRepositorySource", url: "git@github.com:FinhackerTech/SmarketServer.git"],
            contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
            errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
            statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
    ]);
}


node("yxw") {
    def workspace = pwd()

    def git_branch = 'main'
    def git_repository ='git@github.com:FinhackerTech/SmarketServer.git'
    def vm_ip = '60.205.183.238'
    def vm_port = '22'
    def vm_user = 'lyk'


    def IMAGE_NAME = 'smarket_server'
    def IMAGE_NAME_WITH_TAG = 'smarket_server:latest'
    def IMAGE_TO_RUN = 'lyklove/smarket_server:latest'
    def CONTAINER_NAME = 'smarket_server'



//    stage('clone from gitlab into slave\'s workspace') {
//        echo "workspace: ${workspace}"
//        git branch: "${git_branch}", url: "${git_repository}"
//    }
//
//
//    stage('cd to build context') {
//        echo "the context now is:"
//        sh "ls -al"
//        sh "cd ${workspace}"
//        echo "cd to build context, now the context is:"
//        sh "ls -al"
//
//    }
//    stage('build jar on slave machine, jacoco file generated') {
//        echo 'jdk should be 11'
//        sh 'java --version'
//
//        sh 'mvn --version'
//        sh "mvn  clean package jacoco:report -Dmaven.test.failure.ignore=true"
//        echo "build finish on ${vm_ip}"
//
//    }
//
//    stage( 'testing, using jacoco' ) {
//        jacoco (
//                execPattern: '**/target/jacoco.exec',
//                classPattern: '**/classes',
//                sourcePattern: '**/src/main/java',
//                exclusionPattern: '**/src/test*',
////                inclusionPattern: '**/com/hel/auto/service/*.class,**/com/hel/auto/controller/*.class',
//        )
//    }
//
//
//    stage("build docker image"){
//        sh "docker build -t ${IMAGE_NAME} ."
//    }
//
//    stage("login to dockerhub"){
//        withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_KEY', passwordVariable: 'password', usernameVariable: 'username')]) {
//            sh 'docker login -u $username -p $password'
//        }
//    }
//
//    stage("push to dockerhub"){
//        echo "begin push to dockerhub"
//        sh "docker image tag ${IMAGE_NAME_WITH_TAG} lyklove/${IMAGE_NAME_WITH_TAG}"
//        sh "docker image push lyklove/${IMAGE_NAME_WITH_TAG}"
//    }
//    stage("clean previous image and container"){
//        sh "docker container rm -f ${CONTAINER_NAME}"
//        sh "docker image rm ${IMAGE_NAME_WITH_TAG}"
//        sh "docker image rm ${IMAGE_TO_RUN}"
//    }
//    stage( "pull image" ){
//        sh "docker pull  lyklove/${IMAGE_NAME_WITH_TAG}"
//    }
//    stage("run container") {
//        sh "docker image ls"
//        sh "docker container run --name ${CONTAINER_NAME} --net=host  -d ${IMAGE_TO_RUN}"
//    }
    stage("signal github: deployed"){
        def build = new Build(description: "build success!!!")

        updateGithubCommitStatus(build)

//        setBuildStatus("Build complete", "SUCCESS");
    }


}
