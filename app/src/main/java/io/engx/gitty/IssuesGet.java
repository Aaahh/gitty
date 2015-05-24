package io.engx.gitty;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by aaahh on 5/24/15.
 */
class IssuesGet {
    public static final ArrayList<String[]> cards = new ArrayList<>();

    public static String[][] getArticles(String feedUrl) {
//        Log.d("geturl", feedUrl);  //USe ISsue URl and class discussion-timeline js-quote-selection-container
        cards.clear();
        try {
            Document doc = Jsoup.connect(feedUrl).timeout(5000).get();
            for (int x = 0; x < doc.select("item").size(); x++) {
                Element item = doc.select("item").get(x);
                String title = item.select("title").first().text();
                String date = item.select("pubDate").first().text();
                date = date.substring(0, date.length() - 15).replaceAll(" 0(\\d) ", " $1 ")
                        .replaceAll("( )+", " ") + " | By: " + item.select("pubDate")
                        .first().nextElementSibling().text();
                String link = item.select("link").first().nextSibling().toString().trim();
                String desc = item.select("description").first().text().replace(" &#160; ", " ")
                        .replace(" [&#8230;]", "…");
                String[] text = new String[4];
                desc = Html.fromHtml(desc).toString();
                text[0] = title.trim();
                text[1] = date.trim();
                text[2] = desc.trim();
                text[3] = link.trim();
                cards.add(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[][] car = new String[cards.size()][5];
        for (int x = 0; x < cards.size(); x++) {
            car[x] = cards.get(x);
        }
        return car;
    }

    public static boolean isNetworkAvailable(Context ct) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
