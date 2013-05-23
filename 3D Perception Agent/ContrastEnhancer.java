import java.util.Stack;

public class ContrastEnhancer
{
    
    /* This is the primary method call. 
     * Given a two dimensional array, max and min values, as well as delta,
     * this will return a new image with thye contrast enhanced.
     */
    public static int[][][] enhance(int[][][] imageData, boolean[][][] criticalMap, double[][][] stretched,
                                    int maxValue, int minValue, double delta )
    {
		int frame = imageData.length;
        int height = imageData[0].length;
        int width = imageData[0][0].length;
        int minPixelValue = Integer.MAX_VALUE;
        Stack hillockStack = new Stack();
        
        // find minimum pixel value
		for ( int f = 0; f < frame; f++ )
        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
                if ( imageData[f][y][x] < minPixelValue )
                    minPixelValue = imageData[f][y][x];
                    
        // translate entire image downwards so that min = 0
		for ( int f = 0; f < frame; f++ )
        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
                imageData[f][y][x] = (int)(imageData[f][y][x] - minPixelValue);
                
        // create a new hillock consisting of entire image and push it onto the stack
        hillockStack.push( new Hillock( imageData, stretched, criticalMap, maxValue, delta ) );
        
        // keep processing hillocks until there are non left
        while ( !hillockStack.isEmpty() )
        {
            Hillock nextHillock = (Hillock)hillockStack.pop();
            Hillock[] newHillocks = nextHillock.processHillock();
           
                for ( int i = 0; i < newHillocks.length; i++ )
                    if ( newHillocks[i] != null )
                        hillockStack.push( newHillocks[i] );
          
            //System.out.println("Number of hillocks is " + hillockStack.size());
        }
                
        
        System.out.println("END");
        return imageData;
    }
}