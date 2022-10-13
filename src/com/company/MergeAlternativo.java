package com.company;


import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeAlternativo {

    public static void mergealternativo(java.util.List<InputStream> list) throws Exception {


        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, new FileOutputStream("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Result.pdf"));

        document.open();



        if (list.size() >0) {

            for (InputStream in : list) {
                PdfReader reader = new PdfReader(in);
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    // optionally write an if statement to include the page
                    copy.addPage(copy.getImportedPage(reader, i));

                }
                copy.freeReader(reader);
                reader.close();
            }


        } else {


        }
        document.close();
    }


}