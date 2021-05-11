package org.example.service;

import org.example.data.gen.Bouquet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BouquetService {
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
}
