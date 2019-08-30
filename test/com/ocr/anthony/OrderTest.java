package com.ocr.anthony;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Order order = new Order();

    @Test
    public void Given_Chicken_When_DisplayMenuSelected_Then_DisplayChickenSentence() {
        order.displaySelectedMenu(1);
        assertEquals("Vous avez choisi comme menu : poulet\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Beef_When_DisplayMenuSelected_Then_DisplayBeefSentence() {
        order.displaySelectedMenu(2);
        assertEquals("Vous avez choisi comme menu : boeuf\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Vegetarian_When_DisplayMenuSelected_Then_DisplayVegetarianSentence() {
        order.displaySelectedMenu(3);
        assertEquals("Vous avez choisi comme menu : végétarien\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_TooBigValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(15);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_NegativeValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(-6);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }@Test
    public void Given_ChikenWithFriesAndWaterInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("1\n2\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : poulet", output[5]);
        assertEquals("Vous avez choisi comme accompagnement : frites", output[11]);
        assertEquals("Vous avez choisi comme boisson : soda", output[17]);
    }
    @Test
    public void Given_BeefWithVegetableInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("2\n1\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : boeuf", output[5]);
        assertEquals("Vous avez choisi comme accompagnement : légumes frais", output[11]);
    }
    @Test
    public void Given_VegetarianWithNoRiceAndSparklingWaterInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("3\n2\n2\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : végétarien", output[5]);
        assertEquals("Vous avez choisi comme accompagnement : pas de riz", output[10]);
        assertEquals("Vous avez choisi comme boisson : eau gazeuse", output[16]);
    }
    @Test
    public void Given_VegetablesAndAllSides_When_DisplaySideSelected_Then_DisplayVegetablesSentence() {
        order.displaySelectedSide(1, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : légumes frais\n", output);
    }
    @Test
    public void Given_FriesAndAllSides_When_DisplaySideSelected_Then_DisplayFriesSentence() {
        order.displaySelectedSide(2, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : frites\n", output);
    }
    @Test
    public void Given_RiceAndAllSides_When_DisplaySideSelected_Then_DisplayRiceSentence() {
        order.displaySelectedSide(3, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : riz\n", output);
    }
    @Test
    public void Given_BadValueAndAllSides_When_DisplaySideSelected_Then_DisplayErrorSentence() {
        order.displaySelectedSide(5, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés\n", output);
    }
    @Test
    public void Given_RiceAndNotAllSides_When_DisplaySideSelected_Then_DisplayRiceSentence() {
        order.displaySelectedSide(1, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : riz\n", output);
    }
    @Test
    public void Given_NoRiceAndNotAllSides_When_DisplaySideSelected_Then_DisplayNoRiceSentence() {
        order.displaySelectedSide(2, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : pas de riz\n", output);
    }
    @Test
    public void Given_BadValueAndNotAllSides_When_DisplaySideSelected_Then_DisplayErrorSentence() {
        order.displaySelectedSide(5, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés\n", output);
    }
    @Test
    public void Given_Water_When_DisplayDrinkSelected_Then_DisplayWaterSentence() {
        order.displaySelectedDrink(1);
        assertEquals("Vous avez choisi comme boisson : eau plate\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_SparklingWater_When_DisplayDrinkSelected_Then_DisplaySparklingWaterSentence() {
        order.displaySelectedDrink(2);
        assertEquals("Vous avez choisi comme boisson : eau gazeuse\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Soda_When_DisplayDrinkSelected_Then_DisplaySodaSentence() {
        order.displaySelectedDrink(3);
        assertEquals("Vous avez choisi comme boisson : soda\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_BadValue_When_DisplayDrinkSelected_Then_DisplayErrorSentence() {
        order.displaySelectedDrink(5);
        assertEquals("Vous n'avez pas choisi de boisson parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_OneMenuChikenWithFriesAndWaterInStandardInput_When_MenusIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("1\n1\n2\n3\n".getBytes()));
        order = new Order();
        order.runMenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : poulet", output[6]);
        assertEquals("Vous avez choisi comme accompagnement : frites", output[12]);
        assertEquals("Vous avez choisi comme boisson : soda", output[18]);
    }
    @Test
    public void Given_TwoMenu_BeefWithVegetable_VegetarianWithNoRiceAndSparklingWaterInStandardInput_When_MenusIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("2\n2\n1\n3\n2\n2\n".getBytes()));
        order = new Order();
        order.runMenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : boeuf", output[6]);
        assertEquals("Vous avez choisi comme accompagnement : légumes frais", output[12]);
        assertEquals("Vous avez choisi comme menu : végétarien", output[18]);
        assertEquals("Vous avez choisi comme accompagnement : pas de riz", output[23]);
        assertEquals("Vous avez choisi comme boisson : eau gazeuse", output[29]);
    }
    @Test
    public void Given_BadMenu_When_MenuIsRun_Then_ReAskMenu() {
        System.setIn(new ByteArrayInputStream("4\n1\n2\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés", output[5]);
        assertEquals("Vous avez choisi comme menu : poulet", output[6]);
    }
    @Test
    public void Given_ChikenWithBadSideAndBadDrink_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("1\n4\n2\n-1\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : poulet", output[5]);
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés", output[11]);
        assertEquals("Vous avez choisi comme accompagnement : frites", output[12]);
        assertEquals("Vous n'avez pas choisi de boisson parmi les choix proposés", output[18]);
        assertEquals("Vous avez choisi comme boisson : soda", output[19]);
    }
    @Test
    public void Given_BeefWithBadSide_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("2\n4\n2\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : boeuf", output[5]);
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés", output[11]);
        assertEquals("Vous avez choisi comme accompagnement : frites", output[12]);
    }
    @Test
    public void Given_VegetarianWithBadSideAndBadDrink_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("3\n3\n2\n-1\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : végétarien", output[5]);
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés", output[10]);
        assertEquals("Vous avez choisi comme accompagnement : pas de riz", output[11]);
        assertEquals("Vous n'avez pas choisi de boisson parmi les choix proposés", output[17]);
        assertEquals("Vous avez choisi comme boisson : soda", output[18]);
    }
    @Test
    public void Given_BadResponseAndResponse1_When_AskAboutCarWithThreeResponses_Then_DisplayErrorAndGoodResponse() {
        System.setIn(new ByteArrayInputStream("5\n1\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals(true, output[0].contains("voiture"));
        assertEquals("Vous n'avez pas choisi de voiture parmi les choix proposés", output[5]);
        assertEquals("Vous avez choisi comme voiture : BMW", output[6]);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_DisplayChikenChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme menu : poulet", output[5]);
    }
    @Test
    public void Given_FriesWithAllSidesEnabled_When_AskAboutSides_Then_DisplayFriesChoice() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        order = new Order();
        order.askSide(true);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme accompagnement : frites", output[5]);
    }
    @Test
    public void Given_Water_When_AskAboutDrinks_Then_DisplayWaterChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askDrink();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme boisson : eau plate", output[5]);
    }
    @Test
    public void Given_Response2_When_AskAboutCarWithThreeResponses_Then_ReturnNumber2() {
        System.setIn(new ByteArrayInputStream("5\n2\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        int choice = order.askSomething("voiture", responses);
        assertEquals(2, choice);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_Return1() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        int choice = order.askMenu();
        assertEquals(1, choice);
    }
    @Test
    public void Given_Response_When_CallingAskQuestion_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("1%n").getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        int choice = order.askSomething("voiture", responses);
        assertEquals("Vous avez choisi comme voiture : BMW%n", order.orderSummary);
    }
    @Test
    public void Given_Responses_When_CallingRunMenus_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("2%n1%n1%n1%n2%n2%n").getBytes()));
        order = new Order();
        order.runMenus();
        assertEquals("Résumé de votre commande :%n" +
                "Menu 1:%n" +
                "Vous avez choisi comme menu : poulet%n" +
                "Vous avez choisi comme accompagnement : légumes frais%n" +
                "Vous avez choisi comme boisson : eau plate%n" +
                "Menu 2:%n" +
                "Vous avez choisi comme menu : boeuf%n" +
                "Vous avez choisi comme accompagnement : frites%n" , order.orderSummary);
    }
    @Test
    public void Given_TextResponse_When_CallingAskQuestion_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream(String.format("texte%n1%n").getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de voiture parmi les choix proposés", output[5]);
    }
    @Test
    public void Given_BadMenusQuantityInStandardInput_When_MenusIsRun_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream(String.format("texte%n1%n1%n2%n3%n").getBytes()));
        order = new Order();
        order.runMenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous devez saisir un nombre, correspondant au nombre de menus souhaités", output[1]);
    }
}