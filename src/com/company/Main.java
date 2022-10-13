package com.company;

//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;

import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.CopiaPdf.RidimImm;
import static com.company.CopiaPdf.copiapdf;
import static com.company.IO.*;
import static com.company.Merge.CheckPathCod;
import static com.company.Merge.doMerge;
import static com.company.MergeAlternativo.mergealternativo;
import static com.company.OPSAP.opsap;
import static com.company.WriteUtilizzo.writeutilizzo;
import static com.itextpdf.kernel.pdf.PdfName.Page;

public class Main {

    public static void main(String[] args) throws DocumentException, IOException {

        Map<Integer, List<String>> ListaCodici = new HashMap<>();
        Map<Integer, List<String>> ListaCodici_ALL = new HashMap<>();
        Map<Integer, List<String>> ListaUtilizzi = new HashMap<>();
        Map<Integer, List<String>> errpath = new HashMap<>();
        Map<Integer, List<String>> dimPag = new HashMap<>();
        List<InputStream> list;
        OutputStream out;


        try {
            ListaCodici_ALL = input();

         //   ListaCodici = input();
           ListaCodici=creaListaCodici(ListaCodici_ALL);
            System.out.println(ListaCodici.size());
            ListaUtilizzi = input2();

            String stringa1;
            int i;
            int j;
            errpath = CheckPathCod(ListaCodici, dimPag);
            for (i = 0; i < errpath.size(); i++) {
                System.out.println("I codici che danno errore sono i seguenti: " + errpath.get(i).get(0) + errpath.get(i).get(1) + " " + errpath.size());
            }


            // PdfReader reader;
            // PdfStamper stamper;
            for (j = 1; j < ListaCodici.size(); j++) {
                stringa1 = "\\\\SERVER2008\\\\dati2\\\\UFFICIO TECNICO\\\\DRAWING\\\\CASELLA\\\\CARTELLA CASELLA PDF\\\\" + ListaCodici.get(j).get(0) + ".pdf";
                String stringaout = "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Outputprovv.pdf";

                opsap(stringa1, ListaCodici.get(j), stringaout);

                try {
                    if (j < 5000) {
                        list = new ArrayList<InputStream>();
                        // Source pdfs
                        if (j == 1) {
                            list.add(new FileInputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Outputprovv.pdf")));
                            System.out.println("dimensione list " + list.size());

                        } else {
                            list.add(new FileInputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Resultprovv.pdf")));
                            list.add(new FileInputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Outputprovv.pdf")));

                            System.out.println("dimensione list " + list.size());


                        }
                        String stringa2;
                        stringa1 = "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Result.pdf";
                        stringa2 = "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Resultprovv.pdf";

                        mergealternativo(list);
                        // doMerge(list);
                        copiapdf(stringa1, stringa2);
                    }
                    String stringa2;
                    stringa2 = "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Resultprovv.pdf";

                    writeutilizzo(ListaCodici.get(j), ListaUtilizzi);
                    list = new ArrayList<InputStream>();
                    list.add(new FileInputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Resultprovv.pdf")));
                    list.add(new FileInputStream(new File("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\provatabella.pdf")));


                    mergealternativo(list);
                    // doMerge(list);
                    copiapdf(stringa1, stringa2);

                    System.out.println("read e pr2 chius");
                } catch (FileNotFoundException e) {
                    System.out.println("UHeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeIHJIJOJOJOJOIJOIJOJOIJOIJ");
                    e.printStackTrace();
                } catch (DocumentException e) {
                    System.out.println("/////////////////*************************//////////////////");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("******************////////////////////////////////////////////");
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("err");
        }

        // writeutilizzo(ListaCodici.get(1),ListaUtilizzi);
        System.out.println("FattoDDD");


        com.company.Rotate.rotation_270_function("C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Result.pdf", "C:\\\\Users\\\\Magazzino4\\\\Desktop\\\\input java\\\\pdf\\\\Result2.pdf");


    }
}

