package de.pdbm.starter.business.messages.boundary.table;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ForeignKeyExistsValidator implements ConstraintValidator<ForeignKeyExists, Integer> {
    @PersistenceContext
    EntityManager entityManager;

    private String defaultMessage;

    private String customMessage;

    private Class<?> entityClass;

    @Override
    public void initialize(ForeignKeyExists constraintAnnotation) {
        this.defaultMessage = constraintAnnotation.message();
        this.customMessage = constraintAnnotation.customerMessage();
        this.entityClass = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) return true;
        boolean isValid = entityManager.find(entityClass, value) != null;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            String effectiveMessage = customMessage.isEmpty() ? defaultMessage : customMessage;
            context.buildConstraintViolationWithTemplate(effectiveMessage)
                    .addConstraintViolation();
        }

        return isValid;
    }
}