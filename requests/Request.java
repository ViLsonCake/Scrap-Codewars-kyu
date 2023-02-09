package project.files.requests;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import project.files.UrlConstructor;
import project.files.consts.ScrapConsts;

import java.io.IOException;

@Component
public class Request {

    // Request url
    String url;

    // Setter
    @Autowired
    @Qualifier("urlConstructor")
    public void setUrl(UrlConstructor urlConstructor) {
        this.url = urlConstructor.getUrl();
    }

    public void getRequest() throws IOException {

        if (url == null) {
            return;
        }

        // Create parser if url is valid
        Document document = Jsoup.connect(url).get();

        // Parse all kyu
        Elements fields = document.getElementsByClass(ScrapConsts.KYU_FIELD);

        // Iteration for kyu
        for (Element field : fields) {
            // Get value from page
            String nameKyu = field.getElementsByClass(ScrapConsts.KYU_NAME_AND_LINK).text();
            String link = field.getElementsByClass(ScrapConsts.KYU_NAME_AND_LINK).attr(ScrapConsts.LINK_ATTR);
            String authorLink = field.getElementsByAttributeValue(ScrapConsts.AUTHOR_ATTRIBUTE,
                    ScrapConsts.AUTHOR_ATTR_VALUE).attr(ScrapConsts.LINK_ATTR);
            String author;
            String difficulty = field.getElementsByClass(ScrapConsts.DIFFICULTY_CLASS).text();

            // Remove "/users/" from author
            if (authorLink.startsWith("/users/")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(authorLink);
                stringBuilder.delete(0, 7);

                author = stringBuilder.toString();
            } else {
                author = "";
            }

            // If values not valid
            if (nameKyu.isEmpty() || authorLink.isEmpty() || link.isEmpty() || difficulty.isEmpty())
                continue;

            // Separator
            System.out.println("=================================================================");

            // Show data
            System.out.println("Name: " + nameKyu);
            System.out.println("Difficulty: " + difficulty);
            System.out.println("Author: " + author + " " + "https://codewars.com" + authorLink);
            System.out.println("Link: " + "https://codewars.com" + link);

        }

    }
}
