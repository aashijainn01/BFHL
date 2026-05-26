package com.example.bfhl.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;

@Service
public class BfhlServiceImpl implements BfhlService {

    @Override
    public BfhlResponse processRequest(BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();
        
        // Default values for standard details (aligned with user's existing details)
        response.setUser_id("aashi_jain_25052004");
        response.setEmail("jaashi017@gmail.com");
        response.setRoll_number("0827IT231002");

        if (request == null || request.getData() == null) {
            response.setIs_success(false);
            response.setEven_numbers(new ArrayList<>());
            response.setOdd_numbers(new ArrayList<>());
            response.setAlphabets(new ArrayList<>());
            response.setSpecial_characters(new ArrayList<>());
            response.setSum("0");
            response.setConcat_string("");
            return response;
        }

        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        List<String> alphabetsToReverse = new ArrayList<>();
        
        long sum = 0;

        for (String item : request.getData()) {
            if (item == null) {
                continue;
            }
            // 1. Check if the item is a number (integer)
            if (item.matches("^-?\\d+$")) {
                try {
                    long val = Long.parseLong(item);
                    sum += val;
                    if (val % 2 == 0) {
                        evenNumbers.add(item);
                    } else {
                        oddNumbers.add(item);
                    }
                } catch (NumberFormatException e) {
                    // Fallback for extremely large numbers that exceed Long.MAX_VALUE
                    specialCharacters.add(item);
                }
            } 
            // 2. Check if the item is purely alphabetical characters
            else if (item.matches("^[a-zA-Z]+$")) {
                alphabets.add(item.toUpperCase());
                alphabetsToReverse.add(item);
            } 
            // 3. Otherwise, treat as special characters or mixed values
            else {
                specialCharacters.add(item);
            }
        }

        // 4. Calculate reversed alternating caps string
        StringBuilder rawConcat = new StringBuilder();
        for (int i = alphabetsToReverse.size() - 1; i >= 0; i--) {
            rawConcat.append(alphabetsToReverse.get(i));
        }
        String reversedString = rawConcat.toString();
        
        StringBuilder alternating = new StringBuilder();
        for (int i = 0; i < reversedString.length(); i++) {
            char c = reversedString.charAt(i);
            if (i % 2 == 0) {
                alternating.append(Character.toUpperCase(c));
            } else {
                alternating.append(Character.toLowerCase(c));
            }
        }

        // Populating the response object
        response.setIs_success(true);
        response.setEven_numbers(evenNumbers);
        response.setOdd_numbers(oddNumbers);
        response.setAlphabets(alphabets);
        response.setSpecial_characters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(alternating.toString());

        return response;
    }
}
