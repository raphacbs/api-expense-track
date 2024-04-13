package com.coelho.brasileiro.expensetrack.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Getter
@Component
public class PropertiesConfig {

    @NotEmpty
    @Value("${topic.transacao.mensal.a.salvar}")
    private  String topicTransacaoMensalASalvar;

    @NotEmpty
    @Value("${topic.transacao.mensal.a.criar}")
    private String topicTransacaoMensalACriar;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;
}
