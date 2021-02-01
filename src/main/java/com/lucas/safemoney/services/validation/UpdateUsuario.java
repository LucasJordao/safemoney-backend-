package com.lucas.safemoney.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lucas.safemoney.services.validation.constraints.UsuarioUpdateValidator;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioUpdateValidator.class)
public @interface UpdateUsuario {

	String message() default "Erro de validação";
	
	Class<?> [] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String value() default "";
	
}