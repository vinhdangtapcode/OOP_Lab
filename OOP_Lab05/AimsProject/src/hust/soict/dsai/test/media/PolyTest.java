package hust.soict.dsai.test.media;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;

import java.util.ArrayList;

public class PolyTest {

    public static void main(String[] args) {
        ArrayList<Media> mediaList = new ArrayList<>();

        mediaList.add(new Book("Harry Potter", "Fantasy",20.99f));
        mediaList.add(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        mediaList.add(new CompactDisc("Greatest Hits", "Music", "Artist Name", 50, 15.99f, "Queen"));

        for (Media media : mediaList) {
            System.out.println(media.toString());
        }
    }
}
