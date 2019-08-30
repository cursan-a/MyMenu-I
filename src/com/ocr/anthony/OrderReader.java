package com.ocr.anthony;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class OrderReader {
    public void read() {
        String[] menus = {"Menu Poulet", "Menu Boeuf", "Menu Végétarien"};
        String[] side = {" avec des légumes frais", " avec des frites", " avec du riz"};
        String[] sideVegetarian = {" avec du riz", " sans riz"};
        String[] drink = {" et avec de l'eau plate", " et avec de l'eau gazeuse", " et avec du soda"};
        try {
            Reader in = new FileReader("order.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                int nbMenu = Integer.valueOf(record.get("menu"));
                int nbSide = Integer.valueOf(record.get("accompagnement"));
                int nbDrink = Integer.valueOf(record.get("boisson"));
                String orderLine = menus[nbMenu - 1];
                if (nbMenu == 3) //vegetarian menu
                    orderLine += sideVegetarian[nbSide - 1];
                else
                    orderLine += side[nbSide - 1];
                if (nbDrink == -1)
                    orderLine += " et sans boisson";
                else
                    orderLine += drink[nbDrink - 1];
                System.out.println(orderLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Impossible de trouver le fichier order.csv");
        } catch (IOException e) {
            System.out.println("Erreur durant la lecture du fichier order.csv");
        }
    }

    public static void main(String[] args) {
        OrderReader orderReader = new OrderReader();
        orderReader.read();
    }
}