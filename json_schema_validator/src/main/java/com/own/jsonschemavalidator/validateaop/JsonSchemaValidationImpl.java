package com.own.jsonschemavalidator.validateaop;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import com.own.jsonschemavalidator.domain.MasterSchemaRepository;
import com.own.jsonschemavalidator.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class JsonSchemaValidationImpl {


    private  final ObjectMapper objectMapper;
    private final MasterSchemaRepository masterSchemaRepository;

    @Around("@annotation(JsonSchemaValidate)")
    public Object validateJson(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        JsonSchemaValidate jsonSchemaValidate = method.getAnnotation(JsonSchemaValidate.class);

        String type = jsonSchemaValidate.value();
        String masterSchema =  masterSchemaRepository.getValue(type);

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = objectMapper.readTree(args[0].toString());

        InputStream schemaAsStream = new ByteArrayInputStream(masterSchema.getBytes(StandardCharsets.UTF_8));

       // InputStream schemaAsStream = CoronaPassportRestController.class.getClassLoader().getResourceAsStream("model/coronopassportrequest.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        Set<ValidationMessage> errors = schema.validate(jsonNode);

        if (!errors.isEmpty())
            throw new BadRequestException("Please fix your json! " + errors.toString());

        return joinPoint.proceed();
    }

}
