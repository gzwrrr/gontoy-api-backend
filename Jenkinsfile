#!groovy
pipeline {

    agent any

    parameters {
        string(name: 'TAG_NAME', defaultValue: '', description: '')
    }

    environment {
        // DockerHub 凭证 ID(登录您的 DockerHub)
        DOCKER_CREDENTIAL_ID = 'dckr_pat_XPAeaJxg0ozTcplVXT_r88wOxS4'
        //  GitHub 凭证 ID (推送 tag 到 GitHub 仓库)
        GITHUB_CREDENTIAL_ID = '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'
        // kubeconfig 凭证 ID (访问接入正在运行的 Kubernetes 集群)
        KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
        // 镜像的推送
        REGISTRY = 'docker.io'
        //  DockerHub 账号名
        DOCKERHUB_NAMESPACE = 'gzwrrr'
        // GitHub 账号名
        GITHUB_ACCOUNT = 'https://github.com/gzwrrr/gontoy-api-backend'
        // 应用名称
        APP_NAME = 'gapi-server'
        // 应用部署路径
        // APP_DEPLOY_BASE_DIR = '/work/projects/'
    }

    stages {
        stage('检出') {
            steps {
                git url: "https://github.com/gzwrrr/gontoy-api-backend.git",
                        branch: "master"
            }
        }

        stage('构建') {
            steps {
                // TODO 解决多环境链接、密码不同配置临时方案
                sh 'if [ ! -d "' + "${env.HOME}" + '/resources" ];then\n' +
                        '  echo "配置文件不存在无需修改"\n' +
                        'else\n' +
                        '  cp  -rf  ' + "${env.HOME}" + '/resources/*.yaml ' + "${env.APP_NAME}" + '/src/main/resources\n' +
                        '  echo "配置文件替换"\n' +
                        'fi'
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }

        stage('部署') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'Tunxunyun-1', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/usr/local/api/backend', remoteDirectorySDF: false, removePrefix: 'gapi-server/', sourceFiles: 'gapi-server/target/gapi-server.jar'), sshTransfer(cleanRemote: false, excludes: '', execCommand: 'chmod +x /usr/local/api/backend/deploy.sh && /usr/local/api/backend/deploy.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/usr/local/api/backend', remoteDirectorySDF: false, removePrefix: 'bin/', sourceFiles: 'bin/deploy.sh')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
}
