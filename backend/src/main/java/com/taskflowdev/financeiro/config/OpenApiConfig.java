package com.taskflowdev.financeiro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Sistema Financeiro Pessoal API", version = "v1"))
public class OpenApiConfig {}
