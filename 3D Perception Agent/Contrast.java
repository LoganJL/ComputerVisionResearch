
import java.awt.image.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;


public class Contrast
{

    public static final short MAX_PIXEL_VALUE = 255;
    public static final short MIN_PIXEL_VALUE = 0;

    public static void main( String args[] )
    {
		// the number of argument is " 2*frame+1 " 
		if (args.length % 2 != 1)
		{
			System.out.println("wrong number of files");
			return;
		}

		int frame, i, j;
		frame = args.length / 2;
		String inputFileName[] = new String[frame];
		String outputFileName[] = new String[frame];

		for (i = 0; i < frame; i++)
		{
			inputFileName[i] = args[i];
			if (!inputFileName[i].endsWith(".jpg"))
				System.out.println("Input file must be a jpg file.");
		}
		for (j = 0; j < frame; j++)
		{
			outputFileName[j] = args[i + j];
			if (!outputFileName[j].endsWith(".jpg"))
				System.out.println("Output file must be a jpg file.");
		}

		String deltaString = args[i + j];
		double delta;

        // check that the third parameter, delta is a real number at least 1.0
        try
        {
            delta = Double.parseDouble( deltaString );
        }
        catch ( NumberFormatException e )
        {
            System.out.println(" The last parameter must be a double.");
            return;
        }
        if ( delta < 1.0 )
        {
            System.out.println("The last parameter, delta, must be at least 1.0.");
            return;
        }


        startEnhancement( inputFileName, outputFileName, delta, frame );

    }

    public static int[][][][] makecopy(int[][][][] imageData)
    {
        int[][][][] rasterValues;
        int numbands, frame, height, width;

		numbands = imageData.length;
        frame = imageData[0].length;
        height = imageData[0][0].length;
        width = imageData[0][0][0].length;
        
		rasterValues = new int [numbands][][][];
		for (int band = 0; band < numbands; band++)
		{
			rasterValues[band] = new int[frame][][];
			for (int f = 0; f < frame; f++)
			{
				rasterValues[band][f] = new int[height][];
				for (int y = 0; y < rasterValues[band][f].length; y++)
					rasterValues[band][f][y] = new int[width];
			}
        }
        
		
		for (int band=0; band < numbands; band++)
			for (int f = 0; f < frame; f++)
			   for ( int x = 0; x < width; x++ )
				   for( int y = 0; y <height; y++ )
					   rasterValues[band][f][y][x] = imageData[band][f][y][x];
                   
        return rasterValues;
    }
    
    public static double[][][] makeStretchedArray(int[][][] imageData)
    {
        double[][][] stretched;
        int frame, height, width;
        
        frame = imageData.length;
        height = imageData[0].length;
		width = imageData[0][0].length;

		stretched = new double[frame][][];
		for (int f = 0; f < frame; f++)
		{
			stretched[f] = new double[height][];
			for (int y = 0; y < height; y++)
				stretched[f][y] = new double[width];
		}

        for ( int f = 0; f < frame; f++ )
	        for ( int x = 0; x < width; x++ )
		        for( int y = 0; y <height; y++ )
			       stretched[f][y][x] = 1.0;
                   
        return stretched;
    }
    
    public static double[][][] makeAdjustedStretchedArray(double[][][] stretched)
    {
        double[][][] newStretched;
        int frame, height, width;
        double currentMax;
        
        frame = stretched.length;
        height = stretched[0].length;
		width = stretched[0][0].length;

		newStretched = new double[frame][][];
		for (int f = 0; f < frame; f++)
		{
			newStretched[f] = new double[height][];
			for (int y = 0; y < height; y++)
				newStretched[f][y] = new double[width];
		}

		for (int f = 0; f < frame; f++)
		{
			for (int x = 0; x < width; x++)
			{
				for (int y = 0; y < height; y++)
				{
					currentMax = stretched[f][y][x];

					if (f > 0 && stretched[f - 1][y][x] > currentMax)
						currentMax = stretched[f - 1][y][x];
					if (f < frame - 1 && stretched[f + 1][y][x] > currentMax)
						currentMax = stretched[f + 1][y][x];
					if (x > 0 && stretched[f][y][x - 1] > currentMax)
						currentMax = stretched[f][y][x - 1];
					if (x < width - 1 && stretched[f][y][x + 1] > currentMax)
						currentMax = stretched[f][y][x + 1];
					if (y > 0 && stretched[f][y - 1][x] > currentMax)
						currentMax = stretched[f][y - 1][x];
					if (y < height - 1 && stretched[f][y + 1][x] > currentMax)
						currentMax = stretched[f][y + 1][x];

					newStretched[f][y][x] = currentMax;
				}
			}
		}
                   
        return newStretched;
    }
    
    public static boolean startEnhancement( String[] inputFileName, String[] outputFileName, double delta, int frame)
    {
        int[][][][] cpdata;
        int[][][][] imageData;
        boolean[][][] criticalMap;
        double[][][] stretched;       // indicates how much a particular pixel has been stretched.
        int band, numbands;

        System.out.println("Reading input file.");


        imageData = MyImageReader.readImagesInto3DArray(inputFileName);
        
        if ( imageData == null )
            return false;
        
        cpdata = makecopy(imageData);
        
        numbands = MyImageReader.numchannels(inputFileName[0]);

        System.out.println("Starting first pass.");
        long starttime = Calendar.getInstance().getTimeInMillis();
	
		for (band = 0; band < numbands; band++)
		{
			stretched = makeStretchedArray(imageData[band]);

			criticalMap = ImageUtilities.makeCriticalMap(imageData[band]);
			System.out.println("Making Critical Map");
			imageData[band] = ContrastEnhancer.enhance(imageData[band], criticalMap, stretched, MAX_PIXEL_VALUE, MIN_PIXEL_VALUE, delta);
/*
			System.out.println("Starting second pass.");

			//Invert image, run again, and invert back
			ImageUtilities.invertImage( imageData[band], MAX_PIXEL_VALUE, MIN_PIXEL_VALUE );
            
			stretched = makeAdjustedStretchedArray( stretched );

			criticalMap = ImageUtilities.makeCriticalMap( imageData[band] );
			imageData[band] = ContrastEnhancer.enhance( imageData[band], criticalMap, stretched, MAX_PIXEL_VALUE, MIN_PIXEL_VALUE, delta );

			//Invert the image back
			ImageUtilities.invertImage( imageData[band], MAX_PIXEL_VALUE, MIN_PIXEL_VALUE );
			//ImageUtilities.printviolations(cpdata[band], imageData[band], delta);
*/
        }

        long endtime = Calendar.getInstance().getTimeInMillis();
        long runningtime = endtime - starttime;
        
        System.out.println("Writing output files.");


        if (!MyImageWriter.writeImages( inputFileName, outputFileName, imageData ))
        	return false;

        System.out.println("DONE in " + runningtime + " millisecs.");

        return true;

    }

}