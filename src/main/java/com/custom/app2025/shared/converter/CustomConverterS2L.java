package com.custom.app2025.shared.converter;

import com.custom.app2025.shared.exception.CustomException;
import com.custom.app2025.shared.exception.CustomExceptionCode;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * <pre>
 * 클래스명: CustomConverterS2L
 * 설명: String to Long 컨버터
 * </pre>
 */
@Converter
public class CustomConverterS2L implements AttributeConverter<String, Long> {

    @Override
    public Long convertToDatabaseColumn(String attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        try {
            return Long.valueOf(attribute);
        } catch (NumberFormatException e) {
        	throw new CustomException(CustomExceptionCode.ERR602, new String[] { attribute }, e);
        } catch (Exception e) {
        	throw new CustomException(CustomExceptionCode.ERR602, new String[] { attribute }, e);
		}
    }

    @Override
    public String convertToEntityAttribute(Long dbData) {
        return dbData == null ? "" : String.valueOf(dbData);
    }
}