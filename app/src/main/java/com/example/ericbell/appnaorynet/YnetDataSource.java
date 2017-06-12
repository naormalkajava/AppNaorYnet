package com.example.ericbell.appnaorynet;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric.bell on 6/12/2017.
 */

public class YnetDataSource {
    interface OnYnetArivvedListener {
        void onYnetArvvied(List<Ynet> data);
    }

    public static void getYnet(final OnYnetArivvedListener listener) {
        AsyncTask asyncTask = new AsyncTask<Void, Void, List<Ynet>>() {
            @Override
            protected List<Ynet> doInBackground(Void... params) {

                try {
                    String xml = IO.readWebSite("http://www.ynet.co.il/Integration/StoryRss3.xml","Windows-1255");
                    List<Ynet> data = parse(xml);
                    return data;

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }

            @Override
            protected void onPostExecute(List<Ynet> ynets) {
              listener.onYnetArvvied(ynets);
            }
        }.execute();
    }


    public static List<Ynet> parse(String xml) {

        List<Ynet> data = new ArrayList<>();

        Document document = Jsoup.parse(xml);
        Elements elements = document.getElementsByTag("item");
        for (Element element : elements) {
            String title = element.getElementsByTag("title").first().text().replace("<![CDATA[", "").replace("]]>", "");
            String descriptionHTML = element.getElementsByTag("description").first().text();
            Document descriptionDocument = Jsoup.parse(descriptionHTML);
            String link = descriptionDocument.getElementsByTag("a").first().attr("href");
            String thumbnail = descriptionDocument.getElementsByTag("img").first().attr("src");
            String content = descriptionDocument.text();
            Ynet ynet = new Ynet(title, link, thumbnail, content);
            data.add(ynet);
        }
        return data;
    }

    public static class Ynet {

       private String title;
       private String link;
       private String thumble;
       private String description;

        public Ynet(String title, String link, String thumble, String description) {
            this.title = title;
            this.link = link;
            this.thumble = thumble;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Ynet{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", thumble='" + thumble + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getThumble() {
            return thumble;
        }

        public String getDescription() {
            return description;
        }
    }
}
