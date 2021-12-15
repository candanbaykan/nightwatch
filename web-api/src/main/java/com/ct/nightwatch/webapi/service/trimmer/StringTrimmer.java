package com.ct.nightwatch.webapi.service.trimmer;

import com.ct.nightwatch.webapi.service.trimmer.annotation.NotTrimmable;
import com.ct.nightwatch.webapi.service.trimmer.annotation.Trimmable;
import com.ct.nightwatch.webapi.service.trimmer.exception.StringTrimmerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class StringTrimmer {

    private static final Logger logger = LoggerFactory.getLogger(StringTrimmer.class);

    public static <T> void trim(T object) {
        trim(object, object.getClass());
    }

    private static <T> void trim(T object, Class<?> theClass) {
        try {
            if (object == null || theClass == null || theClass.equals(Object.class))
                return;

            trim(object, theClass.getSuperclass());

            if (!theClass.isAnnotationPresent(Trimmable.class))
                return;


            for (Field field : theClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(NotTrimmable.class))
                    continue;

                field.setAccessible(true);
                Object fieldValue = field.get(object);

                if (fieldValue == null)
                    continue;

                Class<?> fieldClass = fieldValue.getClass();

                if (fieldClass.equals(String.class))
                    field.set(object, ((String) fieldValue).trim());
                else
                    trim(fieldValue, fieldClass);

            }
        } catch (Exception e) {
            logger.error("An error occurred during trimming", e);
            throw new StringTrimmerException(e);
        }
    }

}
