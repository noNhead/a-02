package org.example.controller;

import org.example.data.gen.Bouquet;
import org.example.data.gen.GetBouquetRequest;
import org.example.data.gen.GetBouquetResponse;
import com.fasterxml.jackson.databind.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.annotation.PostConstruct;

@RestController
public class BouquetDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BouquetDataController.class);
    private static final String address = "http://localhost:8080/ws/GetBouquetResponse";

    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @GetMapping("bouquet/{name}")
    @PostConstruct
    public String getBouquetFromDatabase(@PathVariable String name) throws Exception {
        if (marshaller.getPackagesToScan() == null) {
            marshaller.setPackagesToScan(ClassUtil.getPackageName(GetBouquetRequest.class));
            marshaller.afterPropertiesSet();
        }

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        GetBouquetRequest request = new GetBouquetRequest();
        request.setName(name);

        GetBouquetResponse response = (GetBouquetResponse) webServiceTemplate.marshalSendAndReceive(address, request);

        Bouquet bouquet = response.getBouquet();
        LOGGER.info("Received bouquet: " + bouquet.getName() + ", with description: " + bouquet.getDescription()+";");
        return "Received bouquet: " + bouquet.getName() + ", with description: " + bouquet.getDescription()+";";
    }
}
