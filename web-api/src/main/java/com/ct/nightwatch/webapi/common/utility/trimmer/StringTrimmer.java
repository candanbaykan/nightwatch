package com.ct.nightwatch.webapi.common.utility.trimmer;

import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.Trimmable;
import com.ct.nightwatch.webapi.common.utility.trimmer.annotation.NotTrimmable;
import com.ct.nightwatch.webapi.common.utility.trimmer.exception.StringTrimmerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class StringTrimmer {

    private static final Logger logger = LoggerFactory.getLogger(StringTrimmer.class);

    public static <T> T trim(T object) {
        return trim(object, object.getClass());
    }

    private static <T> T trim(T object, Class<?> theClass) {
        try {
            if (object == null || theClass == null || theClass.equals(Object.class))
                return object;

            if (object instanceof String string)
                return (T) string.trim();

            trim(object, theClass.getSuperclass());

            if (!theClass.isAnnotationPresent(Trimmable.class))
                return object;


            for (Field field : theClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(NotTrimmable.class))
                    continue;

                field.setAccessible(true);
                Object fieldValue = field.get(object);

                if (fieldValue == null)
                    continue;

                Class<?> fieldClass = fieldValue.getClass();

                field.set(object, trim(fieldValue, fieldClass));
            }

            return object;
        } catch (Exception e) {
            logger.error("An error occurred during trimming", e);
            throw new StringTrimmerException(e);
        }
    }

}
