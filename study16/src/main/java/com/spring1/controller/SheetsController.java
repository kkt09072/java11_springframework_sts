package com.spring1.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    /*
    1. credentials.json 파일을 프로젝트의 리소스 경로에 배치합니다.
	2. 필요한 권한을 Google API 콘솔에서 설정하고 OAuth 2.0 클라이언트 ID를 생성합니다.
	3. tokens 디렉토리가 올바르게 설정되었는지 확인합니다.
    */
    
    @GetMapping("/sheets")
    public String getSheetsData(Model model) {
        try {
            Sheets sheetsService = googleSheetsService.getSheetsService();
            // Google Sheets API 호출 예시
            String spreadsheetId = "your_spreadsheet_id";
            String range = "Sheet1!A1:D10";
            var response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
            var values = response.getValues();
            
            model.addAttribute("values", values);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
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