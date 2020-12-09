/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khanghv.testng;

import io.grpc.Context;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;
import net.bytebuddy.build.Plugin;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 *
 * @author USER
 */
public class NewEmptyJUnitTest {

    private static String url = "https://demoqa.com/automation-practice-form";
    private static WebDriver myBrowser;
    private static String testFilePath = "D:\\SWT\\TestAutomation\\TestOne\\";
    private static String testFileName = "TestCase.xlsx";
    private static String sheetName = "Sheet1";

    public NewEmptyJUnitTest() {

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @BeforeMethod
    public void setUpMethod() throws Exception {
        String driver = "chromedriver.exe"; // giống chuỗi kết nối CSDL

        System.setProperty("webdriver.chrome.driver", "D:\\SWT\\TestNG\\chromedriver.exe");

        myBrowser = new ChromeDriver();
        myBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        myBrowser.manage().window().maximize();
        myBrowser.get(url);
    }

    @DataProvider(name = "Authencate")
    public Object[][] getData() throws Exception {
        File file = new File(testFilePath + testFileName);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook testWorkBook = null;
        String fileExtensionName = testFileName.substring(testFileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            testWorkBook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            testWorkBook = new HSSFWorkbook(inputStream);
        }
        Sheet dataSheet = testWorkBook.getSheet(sheetName);
        int rowCount = dataSheet.getLastRowNum() - dataSheet.getFirstRowNum();
        int colCount = 12;
        Object[][] inputData = new String[rowCount][colCount];
        for (int i = 1; i < rowCount + 1; i++) {
            Row row = dataSheet.getRow(i);
            int n = row.getLastCellNum();
            for (int j = 1; j < n; j++) {
                inputData[i - 1][j - 1] = row.getCell(j).toString();
            }
        }
        return (inputData);

    }

    @Test(dataProvider = "Authencate")
    public void testLogin(String fname, String lname, String email, String mobile, String DOB, String address, String image, String ss, String gender, String hobbies, String state, String city) {
        System.out.println("Username: " + fname);
        System.out.println("Pass: " + lname);
        System.out.println("Email: " + email);
        System.out.println("Mobile: " + mobile);
        System.out.println("DOB: " + DOB);
        System.out.println("Add: " + address);
        System.out.println("Image: " + image);
        System.out.println("Subject: " + ss);
        if (!fname.equals("null")) {
            myBrowser.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys(fname);
        }
        if (!lname.equals("null")) {
            myBrowser.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys(lname);
        }
        if (!email.equals("null")) {
            myBrowser.findElement(By.xpath("//*[@id=\"userEmail\"]")).sendKeys(email);
        }
        if (!mobile.equals("null")) {
            myBrowser.findElement(By.xpath("//*[@id=\"userNumber\"]")).sendKeys(mobile);
        }

        if (!image.equals("null")) {
            myBrowser.findElement(By.id("uploadPicture")).sendKeys(image);
        }
        if (!DOB.equals("null")) {
            if (!DOB.trim().equals("Test")) {
                WebElement dobSelect = myBrowser.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
                for (int i = 0; i < 10; i++) {
                    dobSelect.sendKeys(Keys.BACK_SPACE);
                }
                

                dobSelect.sendKeys(DOB);
            } else {
                WebElement dobSelect = myBrowser.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
                for (int i = 0; i < 11; i++) {
                    dobSelect.sendKeys(Keys.BACK_SPACE);
                }
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dobSelect.sendKeys(DOB);
            }

        }
        if (!address.equals("null")) {
            myBrowser.findElement(By.xpath("//*[@id=\"currentAddress\"]")).sendKeys(address);
        }
        if (!gender.equals("null")) {
            if (gender.trim().equals("M")) {
                myBrowser.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")).click();
            } else if (gender.trim().equals("F")) {
                myBrowser.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]/label")).click();
            } else if (gender.trim().equals("O")) {
                myBrowser.findElement(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[3]/label")).click();
            }
        }
        if (!hobbies.equals("null")) {
            WebElement sport = myBrowser.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label"));
            WebElement music = myBrowser.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label"));
            WebElement reading = myBrowser.findElement(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label"));
            switch (hobbies) {
                case "S":
                    sport.click();
                    break;
                case "R":
                    reading.click();
                    break;
                case "M":
                    music.click();
                    break;
                case "SR":
                case "RS":
                    sport.click();
                    reading.click();
                    break;
                case "RM":
                case "MR":
                    reading.click();
                    music.click();
                    break;
                case "SM":
                case "MS":
                    music.click();
                    sport.click();
                    break;
                case "SMR":
                case "SRM":
                case "RMS":
                case "RSM":
                case "MSR":
                case "MRS":
                    music.click();
                    sport.click();
                    reading.click();
                    break;
            }
        }
        if (!ss.equals("null")) {
            if (!ss.trim().equals("Math")) {
                WebElement subj = myBrowser.findElement(By.id("subjectsInput"));
                subj.sendKeys(ss);
                subj.sendKeys(Keys.TAB);
            } else {
                WebElement subj = myBrowser.findElement(By.id("subjectsInput"));
                subj.sendKeys(ss);
                subj.sendKeys(Keys.TAB);
                WebElement cancelBtn = myBrowser.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]/div[1]/div[2]"));
                cancelBtn.click();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (!state.equals("null")) {
            WebElement stt = myBrowser.findElement(By.id("react-select-3-input"));
            stt.sendKeys(state);
            stt.sendKeys(Keys.TAB);
            WebElement stat = myBrowser.findElement(By.id("react-select-3-input"));
            System.out.println("State: " + stat.getText());
            if (stat.getText() != null && !stat.getText().equals("")) {
                if (!city.equals("null")) {
                    WebElement cities = myBrowser.findElement(By.id("react-select-4-input"));
                    if (cities != null) {
                        cities.sendKeys(city);
                        cities.sendKeys(Keys.TAB);
                    }

                }
            }

        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebElement btnSubmit = myBrowser.findElement(By.xpath("//*[@id=\"submit\"]"));

        btnSubmit.click();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!myBrowser.findElement(By.xpath("//*[@id=\"example-modal-sizes-title-lg\"]")).toString().isEmpty()) {

            System.out.println("OK");
        }

    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        myBrowser.close();
    }
}
