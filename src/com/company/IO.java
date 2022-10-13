package com.company;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IO {


    public static Map<Integer, List<String>> input() throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\Magazzino4\\Desktop\\input java\\Pdf\\Input\\ListaCod.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> CodiciLancio = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            CodiciLancio.put(i, new ArrayList<String>());

            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        CodiciLancio.get(i).add(cell.getStringCellValue());
                        break;

                    default:
                        System.out.println("Errore: rilevato input1 che non è formato testo");
                        CodiciLancio.get(i).add(";");
                }
            }
            i++;
        }
        file.close();

        return CodiciLancio;
    }


    public static Map<Integer, List<String>> input2() throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\Magazzino4\\Desktop\\input java\\Pdf\\Input\\ListaUtilizzi.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> listautilizzi = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            listautilizzi.put(i, new ArrayList<String>());

            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        listautilizzi.get(i).add(cell.getStringCellValue());
                        break;

                    default:
                        System.out.println("Errore: rilevato input2 che non è formato testo");
                        listautilizzi.get(i).add(";");
                }
            }
            i++;
        }
        file.close();

        return listautilizzi;
    }

    public static Map<Integer, List<String>> creaListaCodici(Map<Integer, List<String>> ListaCodici_ALL) throws IOException {
        Map<Integer, List<String>> ListaCodici = new HashMap<>();
        int i;
        int j=0;
        for (i = 0; i < ListaCodici_ALL.size(); i++) {
            if (i == 0) {
                ListaCodici.put(i, new ArrayList<String>());
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(0));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(1));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(2));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(3));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(4));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(5));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(6));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(7));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(8));
            } else {
                if (Float.parseFloat(ListaCodici_ALL.get(i).get(7).toString()) > 0) {
                    j++;
                    ListaCodici.put(j, new ArrayList<String>());
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(0));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(1));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(2));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(3));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(4));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(5));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(6));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(7));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(8));
                }
            }
        }
        return ListaCodici;
    }

    public static Map<Integer, List<String>> creaListaCodici2(Map<Integer, List<String>> ListaCodici_ALL) throws IOException {
        Map<Integer, List<String>> ListaCodici = new HashMap<>();
        int i;
        int j=0;
        for (i = 0; i < ListaCodici_ALL.size(); i++) {
            if (i == 0) {
                ListaCodici.put(i, new ArrayList<String>());
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(0));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(1));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(2));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(3));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(4));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(5));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(6));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(7));
                ListaCodici.get(i).add(ListaCodici_ALL.get(i).get(8));
            } else {
                if (Float.parseFloat(ListaCodici_ALL.get(i).get(7)) == 0) {
                    j++;
                    ListaCodici.put(j, new ArrayList<String>());
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(0));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(1));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(2));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(3));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(4));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(5));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(6));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(7));
                    ListaCodici.get(j).add(ListaCodici_ALL.get(i).get(8));
                }
            }
        }
        return ListaCodici;
    }

}
