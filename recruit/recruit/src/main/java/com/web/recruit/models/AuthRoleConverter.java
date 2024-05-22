package com.web.recruit.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AuthRoleConverter implements AttributeConverter<AuthRole, String> {

    @Override
    public String convertToDatabaseColumn(AuthRole attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AuthRole convertToEntityAttribute(String dbData) {
        return dbData == null ? null : AuthRole.valueOf(dbData);
    }
}
