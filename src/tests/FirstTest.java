package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class FirstTest extends CoreTestCase {

    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test //Ex3: Тест: отмена поиска
    public void testSearchCancellation() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkSearchResultsCount(1);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForSearchResultsToDisapper();
    }

    @Test //Ex5: Тест: сохранение двух статей
    public void testDeleteArticleFromReadingList() throws InterruptedException {

        String textToSearch = "Java";
        String listName = "My test list";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        //Adding article to the list
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToMyListFisrtTime(listName);
        //Close article
        ArticlePageObject.closeArticle();
        //Adding second article to the list
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring("Island of Indonesia");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistingList(listName);
        //Close article
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickMyLists();
        //Open lists
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(listName);
        MyListsPageObject.swipeByArticleToDelete("island of Indonesia");
        MyListsPageObject.openArticle("object-oriented programming language");

        String actual_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Unexpected article title",
                "Java (programming language)",
                actual_title);

    }

    @Test //Ex6: Тест: assert title
    public void testCheckArticleTitlePresent() {
        String textToSearch = "Java";
        String articleToOpen = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring(articleToOpen);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.checkTitlePresentWithAssert();


    }

}