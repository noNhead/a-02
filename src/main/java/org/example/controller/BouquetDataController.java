package org.example.controller;

import org.example.data.gen.Bouquet;
import org.example.service.BouquetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class BouquetDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BouquetDataController.class);

    @GetMapping("bouquet/{name}")
    @PostConstruct
    public String getBouquetFromDatabase(@PathVariable String name) throws Exception {
        Bouquet bouquet = BouquetService.getResponseBouquet(name);
        LOGGER.info("Received bouquet: " + bouquet.getName() + ", with description: " + bouquet.getDescription()+";");
        return "Received bouquet: " + bouquet.getName() + ", with description: " + bouquet.getDescription()+";";
    }
}
