package org.selenide.googleforms.test;

import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selectors.*;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class googleforms {
  @Test
  public void googlefroms_test() {
    open("https://docs.google.com/forms/d/e/1FAIpQLScNx9xK2LM-G3Z3fJXOQapiSK1IAoNXc_67MyS-soTfhDXotA/viewform");
    // ASSERTS
    Random Rand = new Random();

    String FormMonth = Integer.toString(Rand.nextInt(12)+1);
    String FormDay = Integer.toString(Rand.nextInt(12)+1);
    String FormYear = "2019";

    Formatter fmt = new Formatter();
    Calendar cal = Calendar.getInstance();
    fmt = new Formatter();
    String FormCurrentMonthByWords = fmt.format("%tB", cal).toString();
    String[] Movies = {"Terminator I", "Terminator II", "Commando", "Conan", "True Lies"};

    // String BrName = System.getProperty("selenide.browser");

    // FRIST PAGE
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[1]/div/div[2]/div[1]/div/label/div/div[2]/div/span")).click();
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[1]/div/div[2]/div[4]/div/label/div/div[2]/div/span")).click();

    if(System.getProperty("selenide.browser").equals("chrome")) {
      //CHROME
      $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div[1]/div/div[1]/input")).val(FormDay + "/" + FormMonth + "/" + FormYear);
    } else {
      // SAFARI, FIREFOX
      $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div/div[1]/div/div[2]/div[1]/div/div[1]/input")).val(FormDay);
      $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div/div[3]/div/div[2]/div[1]/div/div[1]/input")).val(FormMonth);
    }


    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div")).click();
    $(By.xpath("//*[@id='i.err.1806505028']")).shouldBe(visible);

    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/div[1]/div/div[1]/input")).val(FormCurrentMonthByWords);
    sleep(1000);
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div/div")).click();
    sleep(1000);

    // SECOND PAGE
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[2]/textarea")).sendKeys(Movies[Rand.nextInt(5)] + Keys.RETURN + Movies[Rand.nextInt(5)] + Keys.RETURN + Movies[Rand.nextInt(5)]);
    String TextValueToCheck = $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[2]/textarea")).getValue();

    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/span/div/div[4]/label/div/div[1]/div[3]/div")).click();

    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div/div[1]")).click();
    sleep(1000);

    // FRIST PAGE

    String NormalString = $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/div[1]/div/div[1]/input")).getValue();
    StringBuilder sb = new StringBuilder(NormalString);
    String ReverseString = sb.reverse().toString();
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/div[1]/div/div[1]/input")).val(ReverseString);

    sleep(1000);
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div/div")).click();
    sleep(1000);

    // SECOND PAGE
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/div[2]/textarea")).shouldHave(text(TextValueToCheck));
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/span/div/div[4]/label/div/div[1]")).shouldHave(attribute("aria-checked","true"));


    // THIRD PAGE
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div/div[2]")).click();
    sleep(1000);

    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[2]/div[3]/div/div[2]/div/span/div/div[1]/label/div/div[1]")).click();
    $(By.xpath("//*[@id='mG61Hd']/div/div[2]/div[3]/div[1]/div/div[2]")).click();
    sleep(1000);
    $(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div[2]")).shouldBe(visible);
    sleep(5000);

  }
}
