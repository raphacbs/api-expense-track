package com.coelho.brasileiro.expensetrack.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Component
public class PropertiesConfig {

    @NotEmpty
    @NotNull
    @Value("${topic.transacao.mensal.a.salvar}")
    private  String topicTransacaoMensalASalvar;

    @NotEmpty
    @NotNull
    @Value("${topic.transacao.mensal.a.criar}")
    private String topicTransacaoMensalACriar;

    @NotEmpty
    @NotNull
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @NotEmpty
    @NotNull
    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @NotEmpty
    @NotNull
    @Value("${spring.datasource.password}")
    private String dataSourcePassword;
}
