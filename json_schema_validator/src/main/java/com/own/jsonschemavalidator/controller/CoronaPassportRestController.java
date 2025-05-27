package com.own.jsonschemavalidator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.own.jsonschemavalidator.Dto.CoronaPassportRequest;
import com.own.jsonschemavalidator.validateaop.JsonSchemaValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/corona")
@Slf4j
public class CoronaPassportRestController {

    @PostMapping("/novalidation")
    public CoronaPassportRequest createPassport(@RequestBody CoronaPassportRequest request){
        log.info("Request: " + request);
        return request;
    }

    // localhost:8080/api/corona/withvalidation

//    {
//        "person": {
//        "name": 7,
//                "age": 0,
//                "social-security-number": "ABCDEFGHIJKLMNOPQR"
//    },
//        "passport": {
//        "vaccination-process-begun": false,
//                "vaccination-process-ended": true,
//                "tested-negative": false,
//                "has-been-sick-with-corona": false,
//                "test-date": "ABCD"
//    }
//    }

    @PostMapping("/withvalidation")
    @JsonSchemaValidate("validate")
    public void createPassportValidation(@RequestBody String requestStr) throws JsonProcessingException {
     //   log.info("Request Json String: " + requestStr);
       /* InputStream schemaAsStream = CoronaPassportRestController.class.getClassLoader().getResourceAsStream("model/coronopassportrequest.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(schemaAsStream);

        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(requestStr);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        for (ValidationMessage error : errors) {
            log.error("Validation Error: {}", error);
            errorsCombined += error.toString() + "\n";
        }

        if (errors.size() > 0)
            throw new RuntimeException("Please fix your json! " + errorsCombined);

        CoronaPassportRequest request = om.readValue(requestStr, CoronaPassportRequest.class);
        log.info("Return this request: {}", request);
        return request;*/
    }
}
