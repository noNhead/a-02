package org.example.service;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.example.data.gen.Bouquet;
import org.example.data.gen.GetBouquetRequest;
import org.example.data.gen.GetBouquetResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BouquetService {
    private static final String address = "http://localhost:8080/ws/GetBouquetResponse";
    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    public List<Bouquet> getAllBouquets() {
        Bouquet bouquet1 = new Bouquet();
        bouquet1.setName("name-1");
        bouquet1.setDescription("description-1");

        Bouquet bouquet2 = new Bouquet();
        bouquet2.setName("name-2");
        bouquet2.setDescription("description-2");

        Bouquet bouquet3 = new Bouquet();
        bouquet3.setName("name-3");
        bouquet3.setDescription("description-3");

        List<Bouquet> bouquets = new ArrayList<>();
        bouquets.add(bouquet1);
        bouquets.add(bouquet2);
        bouquets.add(bouquet3);
        return bouquets;
    }

    public Bouquet getResponseBouquet(String name) throws Exception {
        if (marshaller.getPackagesToScan() == null) {
            marshaller.setPackagesToScan(ClassUtil.getPackageName(GetBouquetRequest.class));
            marshaller.afterPropertiesSet();
        }

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        GetBouquetRequest request = new GetBouquetRequest();
        request.setName(name);

        GetBouquetResponse response = (GetBouquetResponse) webServiceTemplate.marshalSendAndReceive(address, request);

        return response.getBouquet();
    }
}
