
import javax.imageio.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.*;
import java.awt.Point;

public class MyImageWriter
{


    public static boolean writeImages(String[] inputFileName, String[] outputFileName, int[][][][] imageData)
    {
        BufferedImage[] inputImage = new BufferedImage[inputFileName.length];
        BufferedImage[] outputImage = new BufferedImage[inputImage.length];
        for (int i = 0; i < inputImage.length; i++)
        {
        	inputImage[i] = MyImageReader.readImageIntoBufferedImage( inputFileName[i] );
        	if ( inputImage[i] == null )
        	{
        		System.out.println(" Could not open input image.");
        		return false;
        	}
        	outputImage[i] = new BufferedImage( inputImage[i].getWidth(), inputImage[i].getHeight(),
                    inputImage[i].getType() );
        }
        
		
            
        
        for (int f = 0; f < inputImage.length; f++)
	    {
	        Point origin = new Point();
	        WritableRaster outputRaster, inputRaster;
	        inputRaster = inputImage[f].getRaster();
	        outputRaster = inputRaster.createCompatibleWritableRaster();
	
	        // GRAY
	        // when writing color files, we will have to handle multible bands
	        int[] pixelData = new int[ outputRaster.getNumBands() ];
	        int band = 0;
	        int numbands = outputRaster.getNumBands();
	
	        int height, width;
	        height = outputRaster.getHeight();
	        width = outputRaster.getWidth();
	        
	        long countval = 0;
	
	        for ( int y = 0; y < height; y++ )
	            for ( int x = 0; x < width; x++ )
	            {
	                for ( band = 0; band < numbands; band++ )
	                {
	                    pixelData[ band ] = imageData[band][f][y][x];
	                    
	                    //count the total value of pixels
	        	        countval += pixelData[band];
	        	        //System.out.println("total pixel value is " + countval);
	                }
	                outputRaster.setPixel(x, y, pixelData );
	            }
	        //show average grayscale value of each frame
			countval = countval/width/height/numbands;
			System.out.println("average pixel value of frame " + f + " is " + countval);
	            
	        outputImage[f].setData( outputRaster );
	
	        File outputFile = new File( outputFileName[f] );
	        try
	        {
	            if ( !ImageIO.write( outputImage[f], "jpg", outputFile ))
	            {
	                System.out.println("Could not find image format for output image.");
	                return false;
	            }
	        }
	        catch ( Exception e )
	        {
	            System.out.println("Could not write output file.");
	            return false;
	        }
	    }
        
        return true;
    }
}
