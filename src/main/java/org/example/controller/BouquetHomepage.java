package org.example.controller;

import org.example.service.BouquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BouquetHomepage {
    private final BouquetService bouquetService;

    @Autowired
    public BouquetHomepage(BouquetService bouquetService){
        this.bouquetService = bouquetService;
    }
    public void process(final HttpServletRequest request,
                        final HttpServletResponse response,
                        final ServletContext servletContext,
                        final ITemplateEngine templateEngine) throws IOException {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("bouquets", bouquetService.getAllBouquets());

        templateEngine.process("home", ctx, response.getWriter());
    }

    @GetMapping("/home")
    public String homepage(Model model, @RequestParam(required = false) String error){
        return "home";
    }

}
