package com.company;

import com.itextpdf.commons.actions.IEventHandler;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.List;

import static com.company.CopiaPdf.copiapdf;

public class OPSAP {


    public static void opsap(String stringa, List<String> codice, String nomefileout) throws DocumentException, IOException {

        PdfReader reader;

        reader = new PdfReader(stringa); // input PDF
        OutputStream out = new FileOutputStream(nomefileout);

        int number_of_pages = reader.getNumberOfPages();
        PdfStamper stamper = new PdfStamper(reader, out);
        // Get the PdfContentByte type by pdfStamper.


        Document doc = new Document(PageSize.A4_LANDSCAPE);
        PdfWriter writer = PdfWriter.getInstance(doc, out);
        PdfImportedPage page = writer.getImportedPage(reader, 1);

        Image image = Image.getInstance(page);

        float scaleRatio = calculateScaleRatio(doc, image);

        System.out.println("scale ratio OPSAP    " + scaleRatio);

        if (scaleRatio < 0.86F) {
            image.scalePercent(scaleRatio * 100F);
        }
        writer.close();
        Rectangle pageSize = doc.getPageSize();
        float imageHeight = image.getHeight();
        float imageWidth = image.getWidth();
        System.out.println("dimensione PAG codice " + codice.get(0) + " ha altezza " + imageHeight + " e larghezza " + imageWidth);


        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font


        //rotation.setRotation(page);
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            // get object for writing over the existing content;
            PdfContentByte over = stamper.getOverContent(i);
            over.getHorizontalScaling();

            // write text
            over.beginText();
            over.setFontAndSize(bf, 16);    // set font and size
            over.setTextMatrix(50, min(imageHeight,imageWidth) - 18);   // set x,y position MESSAGGIO
            over.showText("LANCIO 2P ANNO 22-23");  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 16);    // set font and size
            over.setTextMatrix(460, min(imageHeight,imageWidth) - 18);   // set x,y position NUMERO OP SAP
            over.showText("ORD PROD SAP " + codice.get(3).toString());  // set text
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 14);    // set font and size
            over.setTextMatrix(300, min(imageHeight,imageWidth) - 18);   // set x,y position PAG
            over.showText("PAGINA " + i);  // PAG
            over.endText();

            over.beginText();
            over.setFontAndSize(bf, 16);    // set font and size
            over.setTextMatrix(680, min(imageHeight,imageWidth) - 18);   // set x,y position QTA OP SAP
            over.showText("QTA " + codice.get(7).toString());  // set text
            over.endText();
        }

        System.out.println("OPSAP");
        stamper.close();
        reader.close();
        out.close();

    }


    public static float calculateScaleRatio(Document doc, Image image) {
        float scaleRatio;
        float imageWidth = image.getWidth(); //image.getWidth();image.getHeight()
        float imageHeight = image.getHeight(); //image.getHeight() image.getWidth()
        if (imageWidth > 0 && imageHeight > 0) {
            // Firstly get the scale ratio required to fit the image width
            Rectangle pageSize = doc.getPageSize();
            float pageWidth = pageSize.getWidth() - doc.leftMargin() - doc.rightMargin();  //- doc.leftMargin() - doc.rightMargin()
            scaleRatio = pageWidth / imageWidth;

            // Get scale ratio required to fit image height - if smaller, use this instead
            float pageHeight = pageSize.getHeight() - doc.topMargin() - doc.bottomMargin();
            float heightScaleRatio = pageHeight / imageHeight;
            if (heightScaleRatio < scaleRatio) {
                scaleRatio = heightScaleRatio;
            }

            // Do not upscale - if the entire image can fit in the page, leave it unscaled.
            if (scaleRatio > 1F) {
                scaleRatio = 1F;
            }
        } else {
            // No scaling if the width or height is zero.
            scaleRatio = 1F;
        }
        return scaleRatio;
    }


    public static float calculateScaleRatio2(Document doc, Image image) {
        float scaleRatio;
        float imageWidth = image.getHeight(); //image.getWidth();image.getHeight()
        float imageHeight = image.getWidth(); //image.getHeight() image.getWidth()
        if (imageWidth > 0 && imageHeight > 0) {
            // Firstly get the scale ratio required to fit the image width
            Rectangle pageSize = doc.getPageSize();
            float pageWidth = pageSize.getWidth() - doc.leftMargin() - doc.rightMargin();  //- doc.leftMargin() - doc.rightMargin()
            scaleRatio = pageWidth / imageWidth;

            // Get scale ratio required to fit image height - if smaller, use this instead
            float pageHeight = pageSize.getHeight() - doc.topMargin() - doc.bottomMargin();
            float heightScaleRatio = pageHeight / imageHeight;
            if (heightScaleRatio < scaleRatio) {
                scaleRatio = heightScaleRatio;
            }

            // Do not upscale - if the entire image can fit in the page, leave it unscaled.
            if (scaleRatio > 1F) {
                scaleRatio = 1F;
            }
        } else {
            // No scaling if the width or height is zero.
            scaleRatio = 1F;
        }
        return scaleRatio;
    }

    public static float min(float n1, float n2) {
        if (n1 < n2) {
            return n1;
        } else {
            return n2;
        }
    }

}


