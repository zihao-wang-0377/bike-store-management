package de.pdbm.starter.business.messages.boundary.table;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ForeignKeyExistsValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKeyExists {
    String customerMessage() default "";
    String message() default "das BrandId oder CategorieId,die Sie gegeben haben exsist nicht";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> entity();
}