package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test //Ex6: Тест: assert title
    public void testCheckArticleTitlePresent() {
        String textToSearch = "Java";
        String articleToOpen = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring(articleToOpen);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.checkTitlePresentWithAssert();
    }
}