package Backend.BackendINF281.Swigger;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@Configuration
@OpenAPIDefinition(
	info=@Info(
		title="Crud del proyecto de INF281",
		description="Este es la documentacion de los endpoint del proyecto de INF281"
	),
	security = {
		@SecurityRequirement(
				name = "BearerAuthentication"
			)
	}
)
@SecurityScheme(
  name = "BearerAuthentication",
  description="Jwt AUTH description",
  scheme = "Bearer", 
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  in = SecuritySchemeIn.HEADER
)

public class SwiggerConguration {

    

}
