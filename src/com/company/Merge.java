package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.OPSAP.calculateScaleRatio;
import static com.company.OPSAP.calculateScaleRatio2;

public class Merge {

    private PdfNumber rotation;

    public static void doMerge(List<InputStream> list) throws DocumentException, IOException {
        OutputStream outputStream;
        outputStream = new FileOutputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Result.pdf"));
        Document document = new Document(PageSize.A4_LANDSCAPE);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        PdfReader reader2;

        if (list.size() == 1) {

            for (InputStream in : list) {
                reader2 = new PdfReader(in);

                for (int i = 1; i <= reader2.getNumberOfPages(); i++) {
                    document.newPage();
                    //import the page from source pdf
                    PdfImportedPage page = writer.getImportedPage(reader2, i);

                    document.setPageSize(PageSize.A4_LANDSCAPE);
                    document.setPageSize(PageSize.A4_LANDSCAPE.rotate());
                    System.out.println("Merge ");
                    Image image = Image.getInstance(page);

                    float imageWidth = image.getWidth();
                    float imageHeight = image.getHeight();

                    if (imageWidth > imageHeight) {
                         image.setRotationDegrees(90);
                        float scaleRatio = calculateScaleRatio2(document, image);
                        if (scaleRatio < 0.86F) {
                            image.scalePercent(scaleRatio * 150F);
                        }
                        System.out.println("passo di qua 1");


                    } else {
                        // image.setRotationDegrees(90);
                        float scaleRatio = calculateScaleRatio(document, image);
                        if (scaleRatio < 0.86F) {
                            image.scalePercent(scaleRatio * 150F);
                        }
                        System.out.println("passo di qua 2");
                        //image.setRotationDegrees(90);
                    }
                    image.setAbsolutePosition(0, 0);
                    document.add(image);

                }

            }


        } else {


            int p = 1;
            for (InputStream in : list) {
                reader2 = new PdfReader(in);
                if (p > 1) {
                    for (int i = 1; i <= reader2.getNumberOfPages(); i++) {
                        document.newPage();
                        //import the page from source pdf
                        PdfImportedPage page = writer.getImportedPage(reader2, i);
                        if (list.size() > 0) {
                            document.setPageSize(PageSize.A4_LANDSCAPE);
                            // document.setPageSize(PageSize.A4_LANDSCAPE.rotate());
                            System.out.println("Merge ");
                            Image image = Image.getInstance(page);


                            float imageWidth = image.getWidth();
                            float imageHeight = image.getHeight();

                            if (imageWidth > imageHeight) {
                                image.setRotationDegrees(270);
                            float scaleRatio = calculateScaleRatio2(document, image);
                            if (scaleRatio < 0.86F) {
                                image.scalePercent(scaleRatio * 150F);
                            }


                            }else{
                                float scaleRatio = calculateScaleRatio(document, image);
                                image.setRotationDegrees(270);
                                if (scaleRatio < 0.86F) {
                                    image.scalePercent(scaleRatio * 150F);
                                }

                            }




                            image.setAbsolutePosition(0, 0);
                            document.add(image);
                        }
                    }
                } else {  ///////////////////////////////////////////////////////////////guardarci dentro
                    for (int i = 1; i <= reader2.getNumberOfPages(); i++) {
                        document.newPage();
                        //import the page from source pdf
                        PdfImportedPage page = writer.getImportedPage(reader2, i);

                        document.setPageSize(PageSize.A4_LANDSCAPE);
                        // document.setPageSize(PageSize.A4_LANDSCAPE.rotate());
                        System.out.println("Merge lllllllllllllllllllllllllll");
                        Image image = Image.getInstance(page);
                        float imageWidth = image.getWidth();
                        float imageHeight = image.getHeight();
                        System.out.println("Larghezza "+imageWidth+ " altezza "+imageHeight);

                        if (imageWidth < imageHeight) {
                            image.setRotationDegrees(0);
                            float scaleRatio = calculateScaleRatio2(document, image);
                            if (scaleRatio < 0.86F) {
                                image.scalePercent(scaleRatio * 150F);
                            }

                            System.out.println("passo qui 3 ");


                        }else{
                            float scaleRatio = calculateScaleRatio(document, image);
                            image.setRotationDegrees(90);
                            if (scaleRatio < 0.86F) {
                                image.scalePercent(scaleRatio * 150F);
                            }

                            System.out.println("passo qui 4 ");
                        }
                        //  image.setRotationDegrees(180);
                        image.setAbsolutePosition(0, 0);
                        document.add(image);


                    }

                    p++;
                }
            }

        }
        outputStream.flush();
        document.close();
        outputStream.close();

    }


    public static Map<Integer, List<String>> CheckPathCod(Map<Integer, List<String>> ListaCod, Map<Integer, List<String>> dimpag) throws DocumentException, IOException {
        int i;
        int k = 0;
        String stringa;
        Map<Integer, List<String>> patherr = new HashMap<>();
        int z = 0;
        for (i = 1; i < ListaCod.size(); i++) {
            try {
                stringa = "\\\\SERVER2008\\\\dati2\\\\UFFICIO TECNICO\\\\DRAWING\\\\CASELLA\\\\CARTELLA CASELLA PDF\\\\" + ListaCod.get(i).get(0).toString();
                System.out.println("QUI"+ListaCod.get(i).get(0));
                PdfReader reader = new PdfReader(stringa + ".pdf");
                dimpag.put(z, new ArrayList<String>());
                dimpag.get(z).add(reader.getPageSize(1).toString());
                reader.close();
                z++;


            } catch (Exception e) {
                patherr.put(k, new ArrayList<String>());
                System.out.println("QUI"+ListaCod.get(i).get(0));
                patherr.get(k).add(ListaCod.get(i).get(0));
                patherr.get(k).add(ListaCod.get(i).get(1));
                k++;
                e.printStackTrace();
            }
        }
        System.out.println("kkkkk" + k);
        if (k == 0) {
            patherr.put(k, new ArrayList<String>());
            patherr.get(k).add("Tutto ");
            patherr.get(k).add("ok");
        }


        return patherr;
    }

}
