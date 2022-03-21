import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Test_To_LC_Waikiki_Page extends BaseTest{


    @Test
    @Order(1)// Test aşamasında test sıralaması random olduğundan dolayı Order kullanılarak sıralama yapılabilir
    // Bu aşamada giriş yap sayfası test ediliyor.
    public void Login(){
        //Siteye ilk girdiğimizde karşımıza çıkan çerezlerin kabul edilmesi için kullanılıyor.
        driver.findElement(By.cssSelector("#footer__container > div.cookie__policy__container.show > div > p.cookie__button > button")).click();

        //Giriş yap butonunda class ya da id ile erişim sağlayamadığımdan dolayı
        //linkText elementini kullandım
        driver.findElement(By.linkText("Giriş Yap")).click();

        //Email textbox'ına erişiliyor.
        WebElement mailbox=driver.findElement(By.id("LoginEmail"));
        mailbox.click();
        mailbox.sendKeys("furkanasan06@gmail.com");

        //Şifre textbox'ına erişiliyor.
        WebElement pswBox = driver.findElement(By.id("Password"));
        pswBox.click();
        pswBox.sendKeys("Furkan06");

        //Giriş butonuna tıklıyor ve giriş yapılıyor.
        driver.findElement(By.id("loginLink")).click();
    }
    @Test
    @Order(2)//Bu aşamada pantolan kelimesi ile ürün araması yapılıyor.
    public void searchProduct(){
        WebElement searchBox= driver.findElement(By.id("search_input"));
        searchBox.click();
        searchBox.sendKeys("pantolan");
        WebElement searchButton = driver.findElement(By.xpath("//div/button[text()='Ara'][1]"));
        searchButton.click();
    }
    @Test
    @Order(3)//Bu aşamada geriye kalan işlemler yapılıyor.
    public void nextPage() {

        //Sayfa sonuna scroll olur ve Daha fazla ürün gör butonuna tıklar.
        driver.findElement(By.xpath("//*[@id=\"divModels\"]/div[7]/div/div[4]/a")).click();

        //Ürün Seçim işlemi yapılır.
        driver.findElement(By.xpath("//*[@id=\"model_1799958_5180047\"]")).click();

        //Sepete Ürünün eklenebilmesi için gerekli olan bedeninin seçim işlemi yapılır.
        driver.findElement(By.xpath("//*[@id=\"option-size\"]/a[6]")).click();

        //Ürün sepete ekleme işlemi yapılır..
        driver.findElement(By.id("pd_add_to_cart")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//Bir sonraki işlem için bekleme süresi

        //Sepete gitme işlemi yapılır.
        driver.findElement(By.id("spanCart")).click();

        //Ürünün fiyatı sepet fiyatı ile karşılaştırılmak için değişkene atılır.
        WebElement price = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/span"));
        String txtPrice=price.getText();

        //Ürün sayfasındaki fiyat ve sepetteki fiyat eşleyor mu kontrol işlemi yapılır.
        WebElement cartPrice = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[1]/div[5]/div/span[2]"));
        String txtPriceCart=cartPrice.getText();
        if(txtPrice.compareTo(txtPriceCart)>0){
            driver.findElement(By.cssSelector("#Cart_AddQuantity_697830801")).click();
        }

        //Sepetteki ürünlerin silinirek sepet boşaltılması işlemi
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.className("fa-trash-o")).click();
        driver.findElement(By.className("sc-fav-delete")).click();


    }




}
