package com.ct.nightwatch.webapi.service.trimmer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StringTrimmer {

    private static final Logger logger = LoggerFactory.getLogger(StringTrimmer.class);

    public static <T> void trim(T object) {
        trim(object, null);
    }

    public static <T> void trim(T object, Set<String> exclude) {
        if (object == null)
            return;

        exclude = Optional.ofNullable(exclude)
                .orElse(new HashSet<>());

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            Set<String> finalExclude = exclude;
            Arrays.stream(beanInfo.getPropertyDescriptors())
                    .filter(propertyDescriptor -> {
                        return propertyDescriptor.getPropertyType().isAssignableFrom(String.class) &&
                                !finalExclude.contains(propertyDescriptor.getName());
                    })
                    .forEach(propertyDescriptor -> {
                        try {
                            String value = (String) propertyDescriptor.getReadMethod().invoke(object);
                            propertyDescriptor.getWriteMethod().invoke(object, value.trim());
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e) {
            logger.error("An error occurred during trimming", e);
            throw new RuntimeException(e);
        }
    }

}
