package com.example.bfhl.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bfhl.model.RequestData;

@RestController
@RequestMapping("/bfhl")
public class bfhlController {

    @PostMapping
    public Map<String, Object> processData(@RequestBody RequestData request) {

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();

        for (String item : request.getData()) {

            if (item.matches("\\d+")) {
                numbers.add(item);
            } else {
                alphabets.add(item);
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("is_success", true);
        response.put("user_id", "aashi_jain_25052004");
        response.put("email", "jaashi017@gmail.com");
        response.put("roll_number", "0827IT231002");
        response.put("numbers", numbers);
        response.put("alphabets", alphabets);

        return response;
    }
}