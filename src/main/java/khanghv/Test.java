/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanghv;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author ThinhTPT
 */
public class Test {

    public static void main(String[] args) {
        // playWithGoogleSearch();
         //checkUIFAPLoginPage();
        checkPHPTravelsRegistration();
    }

    // tự động hoá Test Case #1 ở trên TestRail thay vì test bằng tay
    // tự dộng hoá trước, báo bảng màu sắc sau
    public static void checkUIFAPLoginPage() {
        String driver = "chromedriver.exe"; // giống chuỗi kết nối CSDL

        System.setProperty("webdriver.chrome.drive", driver);

        WebDriver myBrowser = new ChromeDriver();

        myBrowser.manage().window().maximize();

        myBrowser.get("http://fap.fpt.edu.vn/");
        
        WebElement header = myBrowser.findElement(By.xpath("/html/body/div/div[1]/h1/span"));
        System.out.println("Header: " + header.getText());
        
        
        WebElement campusCtl = myBrowser.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddlCampus\"]"));
        Select obj = new Select(campusCtl);
        List<WebElement> campusess = obj.getOptions();
        System.out.println("The list of Campuses: ");
        for (WebElement camp : campusess) {
            System.out.println(camp.getText());
        }
        /*
        // trang  chủ phải xuất hiện rồi, mình check các thành phần
        // đúng như thiết kế hay ko: title, combo box, footer
        // tìm, định vị các thẻ trên trang FAP login được tải về
        // định vị được thẻ, mới lấy được value bên trong, mới assert() được
        // 1. ta lấy ra Header của trang
        WebElement header
                = myBrowser.findElement(By.xpath("/html/body/div/div[1]/h1/span"));
        System.out.println("Header text: " + header.getText());

        // 2. ta lấy footer
        WebElement footer
                = myBrowser.findElement(By.xpath("//*[@id=\"cssTable\"]/tbody/tr[2]/td/p"));
        System.out.println("Footer text: " + footer.getText());

        // 3. tui muon kiem tra coi drop-down campus có đủ HN, HCM, DN, CT không
        WebElement cboCampus = myBrowser.findElement(By.cssSelector("#ctl00_mainContent_ddlCampus"));
        //tìm theo name/ css/ xpath đều được (Locator in Web page)
        WebElement campusCtl = myBrowser.findElement(By.xpath("//*[@id=\"ctl00_mainContent_ddlCampus\"]"));
        //css: #ctl00_mainContent_ddlCampus
        Select obj = new Select(campusCtl);
        //chuẩn bị lấy danh sách các option về đưa vào ArrayList
        List<WebElement> campusess = obj.getOptions();
        System.out.println("The list of Campuses: ");
        for (WebElement camp : campusess) {
            System.out.println(camp.getText());
        }
        // lấy từng thành phần bên trong dropdown
        Select item = new Select(cboCampus);
        // ta cần 1 object chứa list item trả vè
        List<WebElement> campuses = item.getOptions();
        for (WebElement x : campuses) {
            System.out.println(x.getText());
        }
        */
    }

    // 1. Test case #1 - Test Google search function by typing a keyword
    // * Test Procedures/test steps
    //   *1. Open a browser, e.g. Chrome
    //   *2. Type google.com, enter     | Expected: Google search page appears
    //   *3. Type a keyword you want to search, e.g ahihi đồ ngok
    //                                  | Expected: all of the links that contain
    //                                      the keyword will be displayed
    // * Status:
    //   Chaỵ theo đúng trình tự ở trên và thấy kết quả (actual)
    //   so sánh với Expected dể báo 2 trạng thái: PASSED | FAILED
    public static void playWithGoogleSearch() {
        // dùng các class có sẵn của thư viện Selenium (~ jdbc)
        // để kết nối và điều khiển trình duyệt
        // Ta sẽ mô phỏng lại toàn bộ các thao tác/steps đã mô tả ở trên qua code,
        // thay người làm
        // tự động hoá quá trình test, TEST AUTOMATION

        // 1. Triệu gọi file .exe để làm cầu nối với Chrome
        // giống Class.forName(...)
        String driver = "chromedriver.exe"; // giống chuỗi kết nối CSDL
        System.setProperty("webdriver.chrome.drive", driver);

        // 2. Ánh xạ object trình duyệt vào trong code Java
        // tức là ta có một dối tượng trình duyệt để điều khiển
        WebDriver myBrowser = new ChromeDriver();
        // trình duyệt xuất hiện, một cửa sổ mới hoàn toàn, không ảnh hưởng 
        // cửa sổ đang mở

        // 3. Bắt đầu tương tác với trình duyệt, vào url gì đó
//        myBrowser.get("https://www.google.com/");
        // 4. Tìm mỗi cái thẻ mà ô nhập keyword: name, id, class, xPath
        // mỗi thẻ tag trong trang thuộc về một object tên là WebElement
//        WebElement searchBox = myBrowser.findElement(By.name("q"));
//        searchBox.sendKeys("Ahihi đồ ngok");
//        searchBox.submit(); // nhấn Enter
        myBrowser.manage().window().maximize();

        myBrowser.get("http://fap.fpt.edu.vn/");
        String mainWindow = myBrowser.getWindowHandle();

        WebElement selectCampus = myBrowser.findElement(By.id("ctl00_mainContent_ddlCampus"));
        selectCampus.click();

        myBrowser.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> listOptions = selectCampus.findElements(By.tagName("option"));
        if (listOptions != null) {
            for (WebElement option : listOptions) {
                if (option.getText().equals("FU-Hồ Chí Minh")) {
                    option.click();
                    break;
                }
            }

            myBrowser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Cookie cookie = new Cookie("ASP.NET_SessionId", "qpnmsyd5xxudxwcshvywzdta");
            myBrowser.manage().addCookie(cookie);

            myBrowser.get("http://fap.fpt.edu.vn/Student.aspx");

            /*
            WebElement loginButton = myBrowser.findElement(By.className("abcRioButtonContentWrapper"));
            loginButton.click();

            Set<String> setOfWindows = myBrowser.getWindowHandles();
            // Now iterate using Iterator
            Iterator<String> it = setOfWindows.iterator();

            while (it.hasNext()) {
                String childWindow = it.next();
                if (!mainWindow.equals(childWindow)) {
                    myBrowser.switchTo().window(childWindow);
                }
            }

            myBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement emailInput = myBrowser.findElement(By.id("identifierId"));
            emailInput.sendKeys("EMAIL_HERE");

            WebElement nextButton = myBrowser.findElement(By.className("VfPpkd-RLmnJb"));
            nextButton.click();

            myBrowser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement passwordContainer = myBrowser.findElement(By.id("password"));
            WebElement passwordInput = passwordContainer.findElement(By.className("whsOnd"));
            passwordInput.sendKeys("PASSWOR_HERE");
            
            nextButton = myBrowser.findElement(By.id("passwordNext"));
            nextButton.click();
             */
        }
        // myBrowser.close();
    }

    //Test account registration of phptravels.net
    //Test procedures
    //1.Open URL https://phptravels.net/register
    //2. Fill account info:....
    //3. Click button sign up
    //Expected result
    //3.1. A dashboard/ main menu appears
    //3.2 Hi Lastname + FirstName is shown as a Welcome Message
    public static void checkPHPTravelsRegistration() {
        String driver = "chromedriver.exe"; // giống chuỗi kết nối CSDL

        System.setProperty("webdriver.chrome.drive", driver);

        WebDriver myBrowser = new ChromeDriver();

        myBrowser.manage().window().maximize();
//        
//        myBrowser.get("https://phptravels.net/register");
//        
//        
//
//        //tìm ô Firstname + tìm data
//        WebElement firstName = myBrowser.findElement(By.name(("firstname")));
//        firstName.sendKeys("Trung");
//        
//        WebElement lastName = myBrowser.findElement(By.name(("lastname")));
//        lastName.sendKeys("Nguyen Duc");
//        
//        WebElement phone = myBrowser.findElement(By.name(("phone")));
//        phone.sendKeys("0612 123 458");
//        
//        WebElement email = myBrowser.findElement(By.name(("email")));
//        email.sendKeys("khoilda@gmail.com");
//        
//        WebElement password = myBrowser.findElement(By.name(("password")));
//        password.sendKeys("123456789");
//        
//        WebElement confirm = myBrowser.findElement(By.name(("confirmpassword")));
//        confirm.sendKeys("123456789");
//        
//        WebElement btnSubmit = myBrowser.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[8]/button"));
//        btnSubmit.click();
//        WebElement btnSubmit = myBrowser.findElement(By.xpath(("//*[@id=\"headersignupform\"]/div[8]/button")));
//        btnSubmit.click();
        




        int i = 1;
        boolean isGood = true;
        int countEnd = 0;
        do {

            try {
                i++;

                String sbd = "02" + String.format("%06d", i);
                myBrowser.get("http://diemthi.hcm.edu.vn/Home/Show?SoBaoDanh=" + sbd);
                

                WebElement name = myBrowser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[1]"));
                System.out.println("Name: " + name.getText());
                WebElement result = myBrowser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[3]"));
                System.out.println("Result: " + result.getText());
                countEnd = 0;
            } catch (NoSuchElementException e) {
                i++;
                countEnd++;
                
            }

        } while (countEnd != 5);

    }
}
