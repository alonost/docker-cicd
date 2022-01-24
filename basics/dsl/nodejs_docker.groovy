job('NodeJS Docker example') {
    scm {
        git('git://github.com/yanivomc/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('alonost/amdocsapp')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('f7f1cdd5-5370-4578-b639-c478e6cb2ffc')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

