package MyBooking;

import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

public class ImageLoaderTest {

    @Test
    public void readImageFromFile() {
        BufferedImage testImage = new ImageLoader().readImageFromFile("1");
        assertEquals(testImage.getHeight(), (int) (683*100/1024));
        assertEquals(testImage.getWidth(), 100);
        BufferedImage testImage1 = new ImageLoader().readImageFromFile("4");
        assertEquals(testImage1.getHeight(), (int) (930*100/1800));
        assertEquals(testImage1.getWidth(), 100);
    }
}