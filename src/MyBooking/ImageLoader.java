package MyBooking;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A public class that handles the images of the accommodations. Methods for reading images from file and
 * from user are present. So is a method for writing an image to file. No constructor needed.
 * author K.Stafylidis - P.Daratzis
 * @version 1.0
 */
public class ImageLoader {

    /**
     * A method for reading an image from the directory of saved images for a specific Accommodation.
     * @param id The ID of the accommodation
     * @return the BufferedImage associated with the ID of the accommodation.
     */
    public BufferedImage readImageFromFile(String id) {
        File file = new File("PhotosAccommodation/Accommodation"+id+".jpg");
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(file);
        } catch (IOException e) {
            try {
                tempImage = ImageIO.read(new File("PhotosAccommodation/noImage.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        int imageWidth = 100;
        assert tempImage != null;
        int h = tempImage.getHeight();
        int w = tempImage.getWidth();
        double scale = (double) imageWidth/w;

        BufferedImage after = new BufferedImage(imageWidth,(int) (h*scale),BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale,scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return  scaleOp.filter(tempImage, after);

    }

    /**
     * A method for writing the images in the resource's folder of the app. It uses the ID of the
     * accommodation and saves it as jpg.
     * @param id The ID of the Accommodation
     * @param image the BufferedImage associated with the ID of the Accommodation
     */
    public void writeImageOnDisk(String id, BufferedImage image){
        File outputFile = new File("PhotosAccommodation/Accommodation"+id+".jpg");
        try {
            ImageIO.write(image,"JPG",outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method needed to add or update an image of Accommodation. If the image cannot be read the
     * method returns false in order to use it in other cases. If there is no problem with the userFile
     * specified the method uses the method writeImageOnDisk to store it and associate it with Accommodation
     * @param userFile the File to read and then write
     * @param id the ID of the accommodation to associate the image with
     * @return true if the process is carried out correctly of false if there is a problem with the File
     */
    public boolean readFileFromUser(File userFile, String id){
        if (userFile==null){
            return false;
        }
        BufferedImage imageFromUser;
        try {
            imageFromUser = ImageIO.read(userFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if(imageFromUser == null){
            return false;
        }
        writeImageOnDisk(id, imageFromUser);
        return true;
    }
}
