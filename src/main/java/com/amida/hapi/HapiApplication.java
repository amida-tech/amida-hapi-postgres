package com.amida.hapi;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
import org.apache.lucene.document.Field;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ResourceCondition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileReader;

@SpringBootApplication
public class HapiApplication {

    private FhirContext dstu2 = FhirContext.forDstu2();
    private IParser jsonParser = dstu2.newJsonParser();
    private FhirValidator validator = dstu2.newValidator();
    private String serverBase = "http://localhost:8087/fhir";
    private IGenericClient client = dstu2.newRestfulGenericClient(serverBase);

    public static void main(String[] args) {

        SpringApplication.run(HapiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            File dir = new File(ctx.getClassLoader().getResource("samples").getFile());

            if (dir.isDirectory()){
                File[] files = dir.listFiles();

                for(File f: files){
                    if (f.getName().endsWith(".json")){
                        if (f.getName().startsWith("0")){

                            System.out.println(f.getName());
                            try{
                                FileReader reader = new FileReader(f);
                                IBaseResource resource = jsonParser.parseResource(reader);
                                ValidationResult validation = validator.validateWithResult(resource);

                                if (validation.isSuccessful()){
                                    MethodOutcome outcome = client.create()
                                            .resource(resource)
                                            .execute();
                                    System.out.println(outcome.getCreated());
                                    String id = outcome.getId().getIdPart();
                                    System.out.println(id);

                                }
                            }catch (Exception e){

                                System.out.println(f.getName());
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }

        };
    }


}
