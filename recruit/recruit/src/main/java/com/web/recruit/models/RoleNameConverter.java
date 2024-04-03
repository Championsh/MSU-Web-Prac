package com.web.recruit.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleNameConverter implements AttributeConverter<RoleName, String> {

    @Override
    public String convertToDatabaseColumn(RoleName attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public RoleName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RoleName.valueOf(dbData);
    }
}