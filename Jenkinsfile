pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'raphacbs/api-expense-track:latest'
        CONTAINER_NAME = 'expense-tracker-dev'
        NETWORK_NAME = 'services'
        SPRING_DATASOURCE_URL = '"jdbc:postgresql://diycompany.online/rds-expensetrack-dev"'
        SPRING_DATASOURCE_USERNAME = '"postgres"'
        SPRING_DATASOURCE_PASSWORD = '"h7#gT@9W&4K2yUj!"'
        SPRING_JPA_SHOW_SQL = '"false"'
        SPRING_JPA_GENERATE_DDL = '"false"'
        SPRING_JPA_HIBERNATE_DDL_AUTO = '"update"'
        SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT = '"org.hibernate.dialect.PostgreSQLDialect"'
        SPRING_TASK_SCHEDULING_POOL_SIZE = '"1"'
        SERVER_PORT = '"8082"'
        EXPENSE_TRACK_JWT_SECRET = '"expensetrack"'
        SPRING_KAFKA_BOOTSTRAP_SERVERS = '"diycompany.online:9091,diycompany.online:9092"'
        SPRING_KAFKA_CONSUMER_GROUP_ID = '"api-expense-track"'
        SPRING_KAFKA_CONSUMER_AUTO_OFFSET_RESET = '"earliest"'
        TOPIC_TRANSACAO_MENSAL_A_SALVAR = '"TRANSACAO_MENSAL_A_SALVAR"'
        TOPIC_TRANSACAO_MENSAL_A_CRIAR = '"TRANSACAO_MENSAL_A_CRIAR"'
        SERVER_SSL_KEY_STORE='"classpath:diycompany.keystore.p12"'
        SERVER_SSL_KEY_STORE_PASSWORD='"qwe01poi"'
        SERVER_SSL_KEY_PASSWORD='"qwe01poi"'
    }

    stages {
        stage('Limpar container existente') {
            steps {
                script {
                    // Verifica se o container existe
                    def existingContainer = sh(script: "docker ps -aqf name=${CONTAINER_NAME}", returnStdout: true).trim()
                    if (existingContainer) {
                        // Para e remove o container existente
                        sh "docker stop ${CONTAINER_NAME}"
                        sh "docker rm ${CONTAINER_NAME}"
                    }
                }
            }
        }

        stage('Baixar e executar nova imagem') {
            steps {
                script {
                    def dockerRunCommand = "docker run -d --name ${CONTAINER_NAME} --network ${NETWORK_NAME} -p ${SERVER_PORT}:${SERVER_PORT}"

                    // Adicionando as variáveis de ambiente no comando docker run
                    dockerRunCommand += " -e spring.datasource.url=${SPRING_DATASOURCE_URL}"
                    dockerRunCommand += " -e spring.datasource.username=${SPRING_DATASOURCE_USERNAME}"
                    dockerRunCommand += " -e spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}"
                    dockerRunCommand += " -e spring.jpa.show-sql=${SPRING_JPA_SHOW_SQL}"
                    dockerRunCommand += " -e spring.jpa.generate-ddl=${SPRING_JPA_GENERATE_DDL}"
                    dockerRunCommand += " -e spring.jpa.hibernate.ddl-auto=${SPRING_JPA_HIBERNATE_DDL_AUTO}"
                    dockerRunCommand += " -e spring.jpa.properties.hibernate.dialect=${SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT}"
                    dockerRunCommand += " -e spring.task.scheduling.pool.size=${SPRING_TASK_SCHEDULING_POOL_SIZE}"
                    dockerRunCommand += " -e server.port=${SERVER_PORT}"
                    dockerRunCommand += " -e expense.track.jwt.secret=${EXPENSE_TRACK_JWT_SECRET}"
                    dockerRunCommand += " -e spring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS}"
                    dockerRunCommand += " -e spring.kafka.consumer.group-id=${SPRING_KAFKA_CONSUMER_GROUP_ID}"
                    dockerRunCommand += " -e spring.kafka.consumer.auto-offset-reset=${SPRING_KAFKA_CONSUMER_AUTO_OFFSET_RESET}"
                    dockerRunCommand += " -e topic.transacao.mensal.a.salvar=${TOPIC_TRANSACAO_MENSAL_A_SALVAR}"
                    dockerRunCommand += " -e topic.transacao.mensal.a.criar=${TOPIC_TRANSACAO_MENSAL_A_CRIAR}"
                    dockerRunCommand += " -e server.ssl.key-store=${SERVER_SSL_KEY_STORE}"
                    dockerRunCommand += " -e server.ssl.key-store-password=${SERVER_SSL_KEY_STORE_PASSWORD}"
                    dockerRunCommand += " -e server.ssl.key-password=${SERVER_SSL_KEY_PASSWORD}"

                    // Adicionando a imagem Docker ao comando docker run
                    dockerRunCommand += " ${DOCKER_IMAGE}"

                    // Atualizando a imagem Docker
                    sh "docker pull ${DOCKER_IMAGE}"

                    // Executando o comando docker run
                    sh dockerRunCommand
                }
            }
        }
    }
}
