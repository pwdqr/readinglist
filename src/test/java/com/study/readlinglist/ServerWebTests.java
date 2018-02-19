package com.study.readlinglist;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServerWebTests {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerWebTests.class);
	
	private static ChromeDriver brower;
	
	private static final String baseUrl = "http://localhost:";
	
	@Value("${local.server.port}")
	private int port;
	
	@BeforeClass
	public static void openBrower() {
		String CHROMEDRIVER_FILE_PATH = "~file_path\\chromedriver.exe"; // 드라이버 경로.
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_FILE_PATH);

		brower = new ChromeDriver();
		
		// ChromeDriver 설정
		brower.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // 페이지에 있는 요소를 로드하기 위해 최대 10초까지 기다리도록 설정.
	}
	
	@AfterClass
	public static void closeBrower() {
		try {
			Thread.sleep(5000); // 결과확인을 위헤 브라우저 종료 전 잠시 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		brower.quit(); // 웹 브라우저 종료
	}
	
	@Test
	public void addBookToEmptyList() {
		brower.get(baseUrl + port);
		
		// 로그인
		brower.findElementByName("username").sendKeys("james");
		brower.findElementByName("password").sendKeys("password");
		brower.findElementByName("submit").click();
		
		
		assertEquals("You have no books in your book list", brower.findElementByTagName("div").getText()); // 빈 책 목록 검증.
		
		brower.findElementByName("title").sendKeys("BOOK TITLE");
		brower.findElementByName("author").sendKeys("BOOK AUTHOR");
		brower.findElementByName("isbn").sendKeys("1234567890");
		brower.findElementByName("description").sendKeys("BOOK DESCRIPTION");
		brower.findElementsByTagName("form").get(1).submit(); // 폼 데이터 추가 및 전송
		
		WebElement dl = brower.findElementByCssSelector("dt.bookHeadline");
		assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
		WebElement dt = brower.findElementByCssSelector("dd.bookDescription");
		assertEquals("BOOK DESCRIPTION", dt.getText()); // 목록에 추가 되었는지 검증
		
	}
}
