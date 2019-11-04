package App_Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import App_Lib.RedditMainActivityFooter;
import App_Lib.RedditMainActivityHeader;
import BaseTest.*;

public class RedditMainActivityTabTest extends BaseTest {
	
	RedditResetStartPoint resetStartPoint = new RedditResetStartPoint();
	
	@BeforeMethod(alwaysRun=true)
	public void setStartPoint() {
		resetStartPoint.resetStartPointMainActivityPage(TLDriver.getTLDriver());
	}
	
	@Test
	public void tappingFooterTabs() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.selectAllFooterTabs();
	}
	
	@Test
	public void headerTestNavTitle() {
		RedditMainActivityHeader header = new RedditMainActivityHeader(TLDriver.getTLDriver());
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.goToHomePopularTab();
		System.out.println(header.navTitle.getAttribute("resource-id"));
		footer.goToCommunitiesTab();
		System.out.println(header.navTitle.getAttribute("resource-id"));
		footer.openPostOptions();
		System.out.println(header.navTitle.getAttribute("resource-id"));
		footer.goToCommentSection();
		System.out.println(header.navTitle.getAttribute("resource-id"));
		footer.goToEmails();
		System.out.println(header.navTitle.getAttribute("resource-id"));
	}
	
	@Test
	public void swipingBetweenHomePopularTabs() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.goToHomePopularTab();
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.1, 0.9, 150);
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
	}
	
	@Test
	public void swipingBetweenEmailTabs() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.goToEmails();
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.1, 0.9, 150);
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.1, 0.9, 150);
		footer.swipeHorizontal(0.1, 0.9, 150);
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.9, 0.1, 150);
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.05, 150);
		footer.swipeHorizontal(0.1, 0.9, 150);
	}
	
	@Test
	public void openAndCloseProfileTabInChatTab() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.goToCommentSection();
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.01, 150);
		
	}
	
	@Test
	public void openAndCloseProfileTabInCommunitiesTab() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		footer.goToCommunitiesTab();
		footer.swipeHorizontal(0.05, 0.9, 150);
		footer.swipeHorizontal(0.9, 0.01, 150);
	}
	
	
}
