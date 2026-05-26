package com.example.bfhl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bfhl.dto.BfhlRequest;
import com.example.bfhl.dto.BfhlResponse;
import com.example.bfhl.service.BfhlService;

@SpringBootTest
class BfhlApplicationTests {

    @Autowired
    private BfhlService bfhlService;

    @Test
    void contextLoads() {
        assertNotNull(bfhlService, "BfhlService should be injected successfully.");
    }

    @Test
    void testExampleA() {
        // Request: { "data": ["a", "1", "334", "4", "R", "$"] }
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isIs_success());
        assertEquals("aashi_jain_25052004", response.getUser_id());
        assertEquals("jaashi017@gmail.com", response.getEmail());
        assertEquals("0827IT231002", response.getRoll_number());

        // Check categorizations
        assertEquals(Collections.singletonList("1"), response.getOdd_numbers());
        assertEquals(Arrays.asList("334", "4"), response.getEven_numbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Collections.singletonList("$"), response.getSpecial_characters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcat_string());
    }

    @Test
    void testExampleB() {
        // Request: { "data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"] }
        BfhlRequest request = new BfhlRequest();
        request.setData(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));

        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isIs_success());
        assertEquals("aashi_jain_25052004", response.getUser_id());
        assertEquals("jaashi017@gmail.com", response.getEmail());
        assertEquals("0827IT231002", response.getRoll_number());

        // Check categorizations
        assertEquals(Collections.singletonList("5"), response.getOdd_numbers());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEven_numbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecial_characters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcat_string());
    }

    @Test
    void testEmptyInput() {
        BfhlRequest request = new BfhlRequest();
        request.setData(Collections.emptyList());

        BfhlResponse response = bfhlService.processRequest(request);

        assertTrue(response.isIs_success());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcat_string());
    }

    @Test
    void testNullRequest() {
        BfhlResponse response = bfhlService.processRequest(null);
        assertFalse(response.isIs_success());
        assertTrue(response.getOdd_numbers().isEmpty());
        assertTrue(response.getEven_numbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecial_characters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcat_string());
    }
}
