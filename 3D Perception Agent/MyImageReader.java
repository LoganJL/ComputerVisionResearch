
/**
 * Write a description of class MyImageReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.imageio.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;

public class MyImageReader
{
    public static BufferedImage readImageIntoBufferedImage(String fileName)
    {
        BufferedImage image = null;

        //Check that the file name indicates we have a jpg file
        if ( !fileName.endsWith(".jpg") )
        {
            System.out.println("This is not a jpg file.");
            return null;
        }

        try {
            // Read from a file
            File file = new File(fileName);
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Could not open file.");
            return null;
        }
        return image;
    }

    /* This method gives the number of bands (number of color channels) in for an input image */

    public static int numchannels(String fileName)
    {
		BufferedImage image = null;

		image = readImageIntoBufferedImage(fileName);
		return image.getRaster().getNumBands();
	}

    /* This method opens the jpg mage file given by the input string, and decodes the jpg,
     * putting the relevant values into a two dimensional array of ints. For the time being,
     * we are only dealing with grayscale. However, in the future, we will need to specify
     * on which "band" we are doing the constrast enhancement.
     * If any of the values are too large to fit in a int, the method returns null.
     */
    public static int[][][][] readImagesInto3DArray(String[] fileNames)
    {
        BufferedImage image[] = new BufferedImage[fileNames.length];
        
        int frames = fileNames.length, height, width, band, numbands;
        WritableRaster[] rasters = new WritableRaster[frames];

        for(int i = 0; i < frames; i++)
        {
        	image[i] = readImageIntoBufferedImage( fileNames[i] );
        	rasters[i] = image[i].getRaster();
        }


        //GRAY: For now, the number of bands should be 1.
        int[] pixelValues = new int[ rasters[0].getNumBands() ];
        height = rasters[0].getHeight();
        width = rasters[0].getWidth();
        //ADITI: Accomodates color image
        numbands = rasters[0].getNumBands();

        // GRAY: This may be specified differently for color images
        //ADITI: This is not needed any longer
        //band = 0;

        //allocate our two dimensional array.
        //ADITI: Changed to accomodate color. It is better to have the channel as the first
        //dimension, since it can allow us to work with each channel independently.
        int rasterValues[][][][];
        rasterValues = new int[numbands][frames][height][width];

		long countval = 0;

        for (int f = 0; f < frames; f++)
        {
	        for ( int x = 0; x < width; x++ )
	        {
	        	for( int y = 0; y <height; y++ )
	        	{
					pixelValues = rasters[f].getPixel( x, y, pixelValues);
					for (band = 0; band < numbands; band++)
					{
	        	    	if ( pixelValues[band] > Integer.MAX_VALUE )
	        	    	{
							System.out.println(" Pixel value too big for int.");
	        	    	    return null;
	        	    	}
	        	    	else
	        	    	{
	        	            rasterValues[band][f][y][x] = pixelValues[band];
	        	            
	        	            //count the total value of pixels
	        	            countval += pixelValues[band];
	        	            //System.out.println("total pixel value is " + countval);
	        	        }
	        	    }
				}
			}
			//show average grayscale value of each frame
			countval = countval/width/height/numbands;
			System.out.println("average pixel value of frame " + f + " is " + countval);
        }

        return rasterValues;

    }
}
