package com.my131.Outfitcombinationplatform_backend.domain.clothing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ClothingItemController {
    @GetMapping("/test/env")
    public Map<String, String> testEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("DB_HOST", System.getenv("DB_HOST"));
        env.put("DB_PORT", System.getenv("DB_PORT"));
        env.put("DB_NAME", System.getenv("DB_NAME"));
        env.put("DB_USERNAME", System.getenv("DB_USERNAME"));
        return env;
    }
}
