package com.company;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.batik.css.engine.value.StringValue;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.TIMES_ROMAN;

public class WriteUtilizzo {

    public static void writeutilizzo(List<String> codice, Map<Integer, List<String>> tabinput) throws DocumentException, IOException {
        Map<Integer, List<String>> listadiappoggio = new HashMap<>();

        System.out.println("Write Utilizzo    " + codice.get(0));
        listadiappoggio.put(0, new ArrayList<String>());
        listadiappoggio.get(0).add("Cod Pad");
        listadiappoggio.get(0).add("Desc Pad");
        listadiappoggio.get(0).add("QTA Pad Prod");
        listadiappoggio.get(0).add("QTA Pad Teor");
        listadiappoggio.get(0).add("MAG Pad");
        listadiappoggio.get(0).add("QTA Pad (RIC)");
       // listadiappoggio.get(0).add("QTA Pad Prod");
        listadiappoggio.get(0).add("QTA Parz");

        int k = 1;
        for (int i = 1; i < tabinput.size(); i++) {
            if (codice.get(0).compareTo(tabinput.get(i).get(8).toString()) == 0) {
                listadiappoggio.put(k, new ArrayList<String>());
                listadiappoggio.get(k).add(tabinput.get(i).get(0));
                listadiappoggio.get(k).add(tabinput.get(i).get(1));
                listadiappoggio.get(k).add(tabinput.get(i).get(2));
                listadiappoggio.get(k).add(tabinput.get(i).get(4));
                listadiappoggio.get(k).add(tabinput.get(i).get(6));
                listadiappoggio.get(k).add(tabinput.get(i).get(13));
                listadiappoggio.get(k).add(tabinput.get(i).get(5));
                k++;
            }
        }


        String path = "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\provatabella.pdf";
        OutputStream outputStream;
        outputStream = new FileOutputStream(new File(path));
        Document pdfDocument = new Document(PageSize.A4.rotate(), 10, 10, 20, 20 + 10);
        PdfWriter pdfWriter;

        pdfWriter = PdfWriter.getInstance(pdfDocument, outputStream);
        pdfDocument.open();


        ////tabella1
        Paragraph testo = new Paragraph("testo2 da inserire");//Inserisco il testo e poi lo aggiungo al document

        float columnT1[] = {10f, 50f, 10f,10f, 10f,10f,10f,10f,16f};
        PdfPTable table1;
        table1 = new PdfPTable(columnT1);

        PdfPCell cel1 = new PdfPCell(new Paragraph("Cod"));
        cel1.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel2 = new PdfPCell(new Paragraph("Desc"));
        cel2.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel3 = new PdfPCell(new Paragraph("QTA Prod"));
        cel3.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel4 = new PdfPCell(new Paragraph("QTA TEOR"));
        cel4.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel5 = new PdfPCell(new Paragraph("QTA MAG"));
        cel5.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel9 = new PdfPCell(new Paragraph("QTA (RIC)"));
        cel9.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel6 = new PdfPCell(new Paragraph("UB1"));
        cel6.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel7 = new PdfPCell(new Paragraph("UB2"));
        cel7.setBackgroundColor(BaseColor.ORANGE);
        PdfPCell cel8 = new PdfPCell(new Paragraph("Rif.OP SAP"));
        cel8.setBackgroundColor(BaseColor.ORANGE);
        table1.addCell(cel1);
        table1.addCell(cel2);
        table1.addCell(cel3);
        table1.addCell(cel4);
        table1.addCell(cel5);
        table1.addCell(cel9);
        table1.addCell(cel6);
        table1.addCell(cel7);
        table1.addCell(cel8);

        cel1=new PdfPCell(new Paragraph(codice.get(0)));
        cel2=new PdfPCell(new Paragraph(codice.get(1)));
        cel3=new PdfPCell(new Paragraph(codice.get(7)));
        cel4=new PdfPCell(new Paragraph(codice.get(2)));
        cel5=new PdfPCell(new Paragraph(codice.get(6)));
        cel9=new PdfPCell(new Paragraph(codice.get(8)));
        cel6=new PdfPCell(new Paragraph(codice.get(4)));
        cel7=new PdfPCell(new Paragraph(codice.get(5)));
        cel8=new PdfPCell(new Paragraph(codice.get(3)));

        table1.addCell(cel1);
        table1.addCell(cel2);
        table1.addCell(cel3);
        table1.addCell(cel4);
        table1.addCell(cel5);
        table1.addCell(cel9);
        table1.addCell(cel6);
        table1.addCell(cel7);
        table1.addCell(cel8);

        ///fine tabella1

        ///inizio tabella2

        Paragraph testo2 = new Paragraph("Lancio Produzione 2P anno 2022-2023");//Inserisco il testo e poi lo aggiungo al documento
        //PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN.getBaseFont());

        //testo2.setFont(font.);
        pdfDocument.add(testo2);

        float columnWidth[] = {13f, 80f, 10f, 10f,10f, 10f, 13f};
        PdfPTable table2;
        table2 = new PdfPTable(columnWidth);


        PdfPCell cell1 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(0).toString()));
        cell1.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell2 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(1).toString()));
        cell2.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell3 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(2).toString()));
        cell3.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell4 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(3).toString()));
        cell4.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell7 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(4).toString()));
        cell7.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell5 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(5).toString()));
        cell5.setBackgroundColor(BaseColor.CYAN);
        PdfPCell cell6 = new PdfPCell(new Paragraph(listadiappoggio.get(0).get(6).toString()));
        cell6.setBackgroundColor(BaseColor.CYAN);

    //    System.out.println("fin qui ci arrivooooooooooo");
        table2.addCell(cell1);
        table2.addCell(cell2);
        table2.addCell(cell3);
        table2.addCell(cell4);
        table2.addCell(cell7);
        table2.addCell(cell5);
        table2.addCell(cell6);



        // PdfContentByte canvas = pdfWriter.getDirectContent();
        //table.writeSelectedRows(0, -1,-1, pdfDocument.bottomMargin(),canvas);



        for (int j = 1; j < listadiappoggio.size(); j++) {
            for (int i = 0; i < 7; i++) {

                cell1 = new PdfPCell(new Paragraph(listadiappoggio.get(j).get(i).toString()));
                table2.addCell(cell1);
            }

        }
        table1.setSpacingBefore(80f);
        pdfDocument.add(table1);
        table2.setSpacingBefore(80f);
        pdfDocument.add(table2);


        outputStream.flush();
        pdfDocument.close();
        // writer.close();
        outputStream.close();

    }


}



