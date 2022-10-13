package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;


public class Rotate extends PdfPageEventHelper {
       protected PdfNumber rotation = PdfPage.PORTRAIT;

       public void setRotation(PdfNumber rotation) {
            this.rotation = rotation;
       }

       public static void rotation_270_function (String path, String pathdest){

           try {

               PdfReader pdfReader = new PdfReader(path);
               Document document = new Document();
               PdfWriter pdfWriter = PdfWriter.getInstance(document,new FileOutputStream(pathdest));
               pdfWriter.setStrictImageSequence(false);
               document.setPageSize(PageSize.A4.rotate());
               document.open();

               int pageCount = pdfReader.getNumberOfPages();

               for (int i = 1; i <= pageCount; i++) {

                   document.newPage();
                   PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
                   Image image = Image.getInstance(page);
                   image.setRotationDegrees(180);
                   image.setAbsolutePosition(0, 0);
                   document.add(image);
               }
               document.close();
               pdfReader.close();
               pdfWriter.close();


           } catch (Exception e){
               System.out.println("error rotation");
           }
       }

    }

