/* 
 * Some useful methods to operate on images.
 * 
 */
public class ImageUtilities
{
    /* 
     * This methods takes in a 2D array fo shorts representing an image
     * and returns a 2D array of booleans with the same dimensions.
     * The booleans indicate whether a pixel is a critical point.
     * Right now, only minima are identified as critical points.
     */
    public static boolean[][][] makeCriticalMap( int[][][] imageData )
    {
        boolean criticalMap[][][];
		int frame = imageData.length;
        int height = imageData[0].length;
        int width = imageData[0][0].length;
        
        criticalMap = new boolean[frame][][];
		for (int f = 0; f < criticalMap.length; f++)
		{
			criticalMap[f] = new boolean[width][];
			for (int y = 0; y < criticalMap[0].length; y++)
				criticalMap[f][y] = new boolean[width];
		}
         
		boolean test;

		for ( int f = 0; f < frame; f++)
			for ( int y = 0; y < height; y++ )
				for ( int x = 0; x < width; x++ )
				{
					test = true;
					for ( int fInc = -1; fInc <= 1 && test; fInc++ )
						for ( int xInc = -1; xInc <= 1 && test; xInc++ )
							for ( int yInc = -1; yInc <=1 && test; yInc++ )
								if ( fInc+f >= 0 && fInc+f < frame &&
									 xInc+x >= 0 && xInc+x < width &&
									 yInc+y >= 0 && yInc+y < height &&
									imageData[f+fInc][y+yInc][x+xInc] < imageData[f][y][x] )
									test = false;
					criticalMap[f][y][x] = test;
				}
         return( criticalMap );
    }
    
    // This method inverts each individual pixel within the [minValue,maxValue] range
    public static int[][][] invertImage( int[][][] imageData, int maxValue, int minValue )
    {
        int frame = imageData.length;
        int height = imageData[0].length;
        int width = imageData[0][0].length;
        
		for ( int f = 0; f < frame; f++)
	        for ( int y = 0; y < height; y++ )
		        for ( int x = 0; x < width; x++ )
			    {
				    imageData[f][y][x] = ( maxValue - imageData[f][y][x] + minValue );
				}
        return imageData;
    }
    
    public static void printviolations(int[][][] origData, int[][][] enhData, double delta)
    {
		int frame = origData.length;
        int height = origData[0].length;
		int width = origData[0][0].length;
        
		for (int f=1; f<frame-1; f++)
        for (int y=1; y<height-1; y++)
            for(int x=1; x<width-1; x++)
            {
				if ((Math.abs(enhData[f][y][x] - enhData[f-1][y][x])) > delta*(Math.abs(origData[f][y][x] - origData[f-1][y][x]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor ( " + (f-1) + " , " + y + " , " + x + " ) " );
                        System.out.println("Up: Original Values: " + origData[f][y][x] + " " + origData[f-1][y][x]);
                        System.out.println("Up: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f-1][y][x]);                        
                    }
                 if ((Math.abs(enhData[f][y][x] - enhData[f+1][y][x])) > delta*(Math.abs(origData[f][y][x] - origData[f+1][y][x]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor (" + (f+1) + " , " + y + " , " + x + " ) " );
                        System.out.println("Bottom: Original Values: " + origData[f][y][x] + " " + origData[f+1][y][x]);
                        System.out.println("Bottom: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f+1][y][x]);                        
                    }
                if ((Math.abs(enhData[f][y][x] - enhData[f][y-1][x])) > delta*(Math.abs(origData[f][y][x] - origData[f][y-1][x]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor ( " + f + " , " +  (y-1) + " , " + x + " ) " );
                        System.out.println("Up: Original Values: " + origData[f][y][x] + " " + origData[f][y-1][x]);
                        System.out.println("Up: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f][y-1][x]);                        
                    }
                    
                 if ((Math.abs(enhData[f][y][x] - enhData[f][y+1][x])) > delta*(Math.abs(origData[f][y][x] - origData[f][y+1][x]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor ( " + f + " , " +  (y+1) + " , " + x + " ) " );
                        System.out.println("Bottom: Original Values: " + origData[f][y][x] + " " + origData[f][y+1][x]);
                        System.out.println("Bottom: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f][y+1][x]);                        
                    }
                    
                 if ((Math.abs(enhData[f][y][x] - enhData[f][y][x-1])) > delta*(Math.abs(origData[f][y][x] - origData[f][y][x-1]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor ( " + f + " , " +  y + " , " + (x-1) + " ) " );
                        System.out.println("Left: Original Values: " + origData[f][y][x] + " " + origData[f][y][x-1]);
                        System.out.println("Left: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f][y][x-1]);                        
                    }
                    
                  if ((Math.abs(enhData[f][y][x] - enhData[f][y][x+1])) > delta*(Math.abs(origData[f][y][x] - origData[f][y][x+1]))+1)
                    {
                        System.out.println("Violates at " + "( " + f + " , " + y + " , " + x + " ) for neighbor ( " + f + " , " +  y + " , " + (x+1) + " ) " );
                        System.out.println("Right: Original Values: " + origData[f][y][x] + " " + origData[f][y][x+1]);
                        System.out.println("Right: Enhanced Values: " + enhData[f][y][x] + " " + enhData[f][y][x+1]);                        
                    }
            }
            
    }
}