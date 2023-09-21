package com.teste.mobi7.config


import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition
@Configuration
class SpringDocConfiguration {

	@Bean
	fun api(): OpenAPI {
		return OpenAPI().info(
			Info()
				.title("mobi7-teste")
				.description("Projeto para teste de conhecimentos e resolução de problemas")
				.contact(
					Contact().email("luan@gmail.com").name("Luan V.S.Feo")
				)
		)
	}
}