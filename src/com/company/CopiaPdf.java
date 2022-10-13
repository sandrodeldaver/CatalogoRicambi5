package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CopiaPdf {

    public static void copiapdf(String pdfdacopiare, String pdfcopia) throws DocumentException, IOException {

        List<InputStream> list;
        Document document = new Document(PageSize.A4);

        PdfCopy copy;
       // System.out.println("QUIIIIIIIIIIIIIIII " + pdfcopia + " " + pdfdacopiare);
        OutputStream out = new FileOutputStream(pdfcopia);
        copy = new PdfCopy(document, out);

        document.open();


        int n;
        PdfReader reader3;
        list = new ArrayList<InputStream>();
        list.add(new FileInputStream(pdfdacopiare));
        reader3 = new PdfReader(list.get(0));
        // loop over the pages in that document
        n = reader3.getNumberOfPages();
        for (int page = 0; page < n; ) {
            copy.addPage(copy.getImportedPage(reader3, ++page));
        }
        list.get(0).close();
        copy.freeReader(reader3);
        reader3.close();
        document.close();
        copy.close();
        out.close();

      //  System.out.println("oioooooooooooooooooooooooooooooooooooooooooooo");

    }


    public static void RidimImm(String pdfdacopiare, String pdfoutput) throws DocumentException, IOException {


        Document document = new Document();

        PdfReader reader = new PdfReader(pdfdacopiare);
        int number_of_pages = reader.getNumberOfPages();
        PdfStamper pdfStamper = new PdfStamper(reader, new FileOutputStream(pdfoutput));
        // Get the PdfContentByte type by pdfStamper.
        Image watermark_image = Image.getInstance("abstract(0307).bmp");
        int i = 0;
        watermark_image.setAbsolutePosition(0, 0);
        watermark_image.scaleToFit(826, 1100);
        System.out.println(watermark_image.getScaledWidth());
        System.out.println(watermark_image.getScaledHeight());
        PdfContentByte add_watermark;
        while (i < number_of_pages) {
            i++;
            add_watermark = pdfStamper.getUnderContent(i);
            add_watermark.addImage(watermark_image);
        }
        pdfStamper.close();
        reader.close();
        document.close();
    }
}
