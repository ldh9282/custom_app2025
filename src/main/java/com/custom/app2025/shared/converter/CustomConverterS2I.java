package com.custom.app2025.shared.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * <pre>
 * 클래스명: CustomConverterS2I
 * 설명: String to Integer 컨버터
 * </pre>
 */
@Converter
public class CustomConverterS2I implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        try {
            return Integer.valueOf(attribute);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Integer Format: " + attribute, e);
        }
    }

    @Override
    public String convertToEntityAttribute(Integer dbData) {
        return dbData == null ? "" : String.valueOf(dbData);
    }
}