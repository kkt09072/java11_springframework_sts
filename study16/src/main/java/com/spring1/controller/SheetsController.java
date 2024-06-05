package com.spring1.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.spring1.service.GoogleSheetsService;

@Controller
public class SheetsController {

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @GetMapping("/sheets")
    public String sheets(Model model) {
        return "sheet/sheets";
    }

    @PostMapping("/sheets/save")
    public String saveData(@RequestParam("spreadsheetId") String spreadsheetId,
                           @RequestParam("range") String range,
                           @RequestParam("values") String values) throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleSheetsService.getSheetsService();
        ValueRange body = new ValueRange().setValues(Collections.singletonList(
                Arrays.asList(values.split(","))));
        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
        return "redirect:/sheets";
    }

    @PostMapping("/sheets/load")
    public String loadData(@RequestParam("spreadsheetId") String spreadsheetId,
                           @RequestParam("range") String range,
                           Model model) throws IOException, GeneralSecurityException {
        Sheets sheetsService = googleSheetsService.getSheetsService();
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        model.addAttribute("values", values);
        return "sheet/sheets";
    }
}