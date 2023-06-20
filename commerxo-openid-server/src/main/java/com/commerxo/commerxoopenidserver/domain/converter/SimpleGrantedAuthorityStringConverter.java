package com.commerxo.commerxoopenidserver.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Converter
public class SimpleGrantedAuthorityStringConverter implements AttributeConverter<GrantedAuthority, String> {

    @Override
    public String convertToDatabaseColumn(GrantedAuthority attribute) {
        return attribute == null ? null : attribute.getAuthority();
    }

    @Override
    public GrantedAuthority convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new SimpleGrantedAuthority(dbData);
    }
}