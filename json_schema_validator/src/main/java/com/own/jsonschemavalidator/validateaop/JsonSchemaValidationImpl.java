package com.own.jsonschemavalidator.validateaop;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.own.jsonschemavalidator.CoronaPassportRequest;
import com.own.jsonschemavalidator.CoronaPassportRestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class JsonSchemaValidationImpl {


    private  final ObjectMapper objectMapper;

    @Around("@annotation(JsonSchemaValidate)")
    public Object validateJson(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        JsonSchemaValidate jsonSchemaValidate = method.getAnnotation(JsonSchemaValidate.class);

        String type = jsonSchemaValidate.value();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = objectMapper.readTree(args[0].toString());

        InputStream schemaAsStream = CoronaPassportRestController.class.getClassLoader().getResourceAsStream("model/coronopassportrequest.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        if (!errors.isEmpty())
            throw new RuntimeException("Please fix your json! " + errors.toString());

        return joinPoint.proceed();

    }

}
