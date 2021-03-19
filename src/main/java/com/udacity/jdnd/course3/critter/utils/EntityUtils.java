package com.udacity.jdnd.course3.critter.utils;

import org.springframework.beans.BeanUtils;

public class EntityUtils {

    public static <T> T convertFromDTOToEntity(Object entityDTO, T entityObject) {
        BeanUtils.copyProperties(entityDTO, entityObject);
        return entityObject;
    }
}
