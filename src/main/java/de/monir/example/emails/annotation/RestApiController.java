package de.monir.example.emails.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping
public @interface RestApiController {
    /**
     * The value of the RestApiController, default "/emails".
     *
     * @return a string value
     */
    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value() default "/emails";
}

