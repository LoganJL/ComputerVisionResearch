/*
 * This class contains all the pixels for a hillock. Most of the operations
 * reside here because the primary operation is to raise the threshold value
 * and compute new connected components (i.e. hillocks) from the subset of pixels
 * whose value is at least as high as the threshold.
 */
public class Hillock
{
    final int UNMARKED = -1;
    final int BELOW_THRESHOLD = -2;
    final int MIN_STEP = 10;    // The minimum step between consecutive thresholds
    final int INIT_NUM_COMPONENTS = 10;
    final int MAX_NUM_NEIGHBORS = 6;
    final double EPSILON = .0001;
    
    int numPixels;
    int maxPixelValue, minPixelValue;
    double maxStretched, minStretched;
    int[] fCoords, xCoords, yCoords;
    int[][] neighbors; // Each vertex has an array for six pixels, denoting its N, E, S, W, P, F neighbor.
                       // If the corresponding neighbor is not in the hillock, the value is -1
                       // otherwise, the value indicates the index of the neighbor
    int[] marked;
    int threshold;
    double[][][] stretched;   // map showing how much each pixel has been stretched.
    int[][][] imageData;
    boolean[][][] criticalMap; // 3D array of booleans. Same dimension as the image.
                            // indicates whether the pixel is a critical value
    int saturationValue;    // the maximum value any pixel can be
    double delta;           // the maximum stretch that can be achieved

    // this constructor is used for all hillocks, except the first.
    // pixels and edges are added to the image one at a time from the old hillock
    public Hillock( int size, int[][][] inputImageData, boolean[][][] crit, double[][][] stretchedArray, int sat, double del)
    {
        numPixels = size;
        imageData = inputImageData;
        criticalMap = crit;
        saturationValue = sat;
        stretched = stretchedArray;
        delta = del;
		fCoords = new int[ numPixels ];
        xCoords = new int[ numPixels ];
        yCoords = new int[ numPixels ];
        marked = new int[ numPixels ];
        
        //System.out.println(numPixels);
        //System.out.println(MAX_NUM_NEIGHBORS);
        
        neighbors = new int[ numPixels ][MAX_NUM_NEIGHBORS];
        for ( int i = 0; i < numPixels; i++ )
        {
        	//System.out.println("Outer loop itr");
            for ( int j = 0; j < MAX_NUM_NEIGHBORS; j++ )
            {
            	//System.out.println("inner loop itr");
                neighbors[i][j] = -1;
            }
        }
    }
    
    // this constructor is only used the first time a hillock is created
    // and makes a hillock consisting of the entire image.
    public Hillock( int[][][] inputImageData, double[][][] stretchedArray, boolean[][][] crit, int sat, double del  )
    {
		int frame = inputImageData.length;
        int height = inputImageData[0].length;
        int width = inputImageData[0][0].length;
        numPixels = frame * height * width;
		fCoords = new int[ numPixels ];
        xCoords = new int[ numPixels ];
        yCoords = new int[ numPixels ];
        marked = new int[ numPixels ];
        
         neighbors = new int[ numPixels ][];
        for ( int i = 0; i < numPixels; i++ )
        {
            neighbors[i] = new int[ MAX_NUM_NEIGHBORS ];
            for ( int j = 0; j < MAX_NUM_NEIGHBORS; j++ )
                neighbors[i][j] = -1;
        }
        
 
        int index = 0;
		for ( int f = 0; f < frame; f++ )
        for ( int y = 0; y < height; y++ )
            for ( int x = 0; x < width; x++ )
                {
					fCoords[ index ] = f;
                    xCoords[ index ] = x;
                    yCoords[ index ] = y;
				    
				    // Initialize north neighbor
                    if ( y > 0 )
                        neighbors[ index ][0] = (index - width);
                    // Initialize south neighbor
				    if ( y < height-1 )
                        neighbors[ index ][2] = (index + width);
                    // Initialize west neighbor
                    if ( x > 0 )
                        neighbors[ index ][3] = (index - 1);
                    // Initialize east neighbor
                    if ( x < width-1 )
                        neighbors[ index ][1] = (index + 1);
					// Initialize previous neighbor
					if ( f > 0 )
						neighbors[index][4] = (index - width*height);
					// Initialize following neighbor
					if ( f < frame-1 )
						neighbors[index][5] = (index + width*height);    
    
                    index++;
                }
        
            
        threshold = 0;
        stretched = stretchedArray;    
        delta = del;
        saturationValue = sat;
        criticalMap = crit;
        imageData = inputImageData;
        setMaxValue();
        setMinValue();
        if ( minPixelValue < maxPixelValue )
            stretch( threshold );
        setMaxValue();
        setMinValue();
        setMaxStretched();
        setMinStretched();
    }
    
    public void addPixel( int index, int f, int x, int y )
    {
		fCoords[index] = f;
        xCoords[index] = x;
        yCoords[index] = y;
    }
    
    public void addEdge( int fromIndex, int toIndex, int edgeIndex )
    {
        neighbors[fromIndex][edgeIndex] = (int)toIndex;
    }
    
    public int setMaxValue()
    {
        maxPixelValue = Short.MIN_VALUE;
        int pixelValue;
        
        for ( int i = 0; i < numPixels; i++ )
        {
            pixelValue = imageData[ fCoords[i] ][ yCoords[i] ][ xCoords[i] ];
            if ( pixelValue > maxPixelValue )
                maxPixelValue = (int)pixelValue;
        }
        return maxPixelValue;
    }
    
    public int setMinValue()
    {
        minPixelValue = Short.MAX_VALUE;
        int pixelValue;
        
        for ( int i = 0; i < numPixels; i++ )
        {
			pixelValue = imageData[fCoords[i]][yCoords[i]][xCoords[i]];
            if ( pixelValue < minPixelValue )
                minPixelValue = (int)pixelValue;
        }
        return minPixelValue;
    }
    
    
    public double setMinStretched()
    {
        minStretched = Double.MAX_VALUE;
        double stretchedValue;
        
        for ( int i = 0; i < numPixels; i++ )
        {
			stretchedValue = stretched[fCoords[i]][yCoords[i]][xCoords[i]];
            if ( stretchedValue < minStretched )
                minStretched = stretchedValue;
        }
        return minStretched;
    }
    
    public double setMaxStretched()
    {
        maxStretched = Double.MIN_VALUE;
        double stretchedValue;
        
        for ( int i = 0; i < numPixels; i++ )
        {
			stretchedValue = stretched[fCoords[i]][yCoords[i]][xCoords[i]];
            if ( stretchedValue > maxStretched )
                maxStretched = stretchedValue;
        }
        return maxStretched;
    }
    // count number at or above threshold.
    // mark those pixels that are below threshold
    public int findTarget()
    {
        int target = 0;
        int pixelValue;
        
        //System.out.println("Threshold is " + threshold);
        
        for ( int i = 0; i < numPixels; i++ )
        {
			pixelValue = imageData[fCoords[i]][yCoords[i]][xCoords[i]];
            if ( pixelValue >= threshold )
                target++;
            else
                marked[i] = BELOW_THRESHOLD;
        }
        return target;
    }
    
    public void clearMarks()
    {
        for ( int i = 0; i < numPixels; i++ )
            marked[i] = UNMARKED;
    }
    
    // Find the first unmarked pixel
    public int findFirstUnmarked()
    {
        int index = 0;
        for ( index = 0; index < numPixels; index++ )
            if ( marked[ index ] == UNMARKED )
                return index;
        //System.out.println("There is no unmarked pixel.");
        return -1;
    }
    
    // performs DFS until all pixels above the treshold have been found.
    // returns an array with the sizes of the different components
    public int[] findComponents()
    {
        clearMarks();
        int componentSizes[] = new int[ INIT_NUM_COMPONENTS ];
        
        // find out how many pixels are at or above the threshold.
        // this will be the sum of the sizes of the resulting hillocks
        // this also marks pixels that are below threshold in "marked" array
        int target = findTarget();
        
        int numComponents = 0;
        int totalReached = 0;
        int numReached = 0;
        
        while ( totalReached < target )
        {
            int start = findFirstUnmarked();
            if ( start < 0 )
            {
                System.out.println("There should be an unmarked node.");
                return null;
            }
            // DFS returns the number of pixels reached from the start vertex
            numReached = DFS( start, numComponents );
            totalReached += numReached;
            
            numComponents++;    
            
            // we don't know in advance the number of components, so may need to make room for more
            if ( numComponents > componentSizes.length )
                componentSizes = resizeComponentSizes( componentSizes );
            // will need to know the size of each component in order to create a new hillock for it
            componentSizes[ numComponents-1 ] = numReached;
        }
        //System.out.println(" Number of components is " + numComponents );
        return componentSizes;
        
    }
    
    // increase size of array by a factor of 2
    public int[] resizeComponentSizes( int[] sizes )
    {
        int[] newSizes = new int[ sizes.length * 2 ];
        for ( int i = 0; i < sizes.length; i++ )
            newSizes[i] = sizes[i];
        return newSizes;
    }
    
    // for debugging
    public void printPixelStats(int i )
    {
        if ( i < 0 || i >= numPixels )
            return;
        System.out.println(" Pixel index = " + i );
        System.out.println(" F coord = " + fCoords[i] );
		System.out.println(" X coord = " + xCoords[i]);
        System.out.println(" Y coord = " + yCoords[i] );
		System.out.println(" Value = " + imageData[fCoords[i]][yCoords[i]][xCoords[i]]);
        System.out.println("Neighbors = ");
        for ( int j = 0; j < MAX_NUM_NEIGHBORS; j++ )
            System.out.println(" " + neighbors[i][j] );
    }
    /*
     * The main routine.
     * Find pixels above the threshold. Compute the connected components.
     * If there is more than one component, create a new hillock for each component.
     * If there is only one component return "this".
     */
    public Hillock[] processHillock()
    {
        // find the components
        // the new component for each pixel is stored in the "marked" array
        // componentSizes indicates the number of pixels in each component
        
        int[] componentSizes = findComponents();
        int numComponents = 0;
        
        /*
        if (  threshold == 30 ||  threshold == 40)
        {
            System.out.println("BEFOERE resetting EDGES");
            printPixelStats( 0 );
            System.out.println("Component = " + marked[ 0 ] );
            printPixelStats( 400 );
            System.out.println("Component = " + marked[ 400 ] );
        }
        */
       
        
        // find number of Components.
        while ( numComponents < componentSizes.length && componentSizes[ numComponents ] > 0 )
            numComponents++;
        
        Hillock[] hillockList = new Hillock[ numComponents ];
        // If there are no new connected components, the hillock is still stretched to the
        // saturation level. Compute the new threshold value and return the same hillock.
        if ( numComponents == 1 )
        {
            hillockList[0] = this;
            hillockList[0].setNewThreshold( threshold );
            return hillockList;   
        }
        
        // create a new hillock for each component
        for ( int i = 0; i < numComponents; i++ )
        {
        	//Jeff: System.out.println("numComponents loop itr");
            hillockList[i] = new Hillock( componentSizes[i], imageData, criticalMap, stretched, saturationValue, delta);
        }
        // For each pixel, we will need to know its new index in the new hillock
        int[] newLocation = new int[ numPixels ];
        // This keeps track of how many pixels have been already added to each hillock
        int[] currentLocation = new int[ numComponents ];
        
        int component, neighborIndex;
        // Go through each pixel and add it to its new hillock
        for ( int i = 0; i < numPixels; i++ )
        {
            if ( marked[i] != BELOW_THRESHOLD )
            {
                component = marked[i]; 
                hillockList[ component ].addPixel( currentLocation[ component ], fCoords[i], xCoords[i], yCoords[i] );
                newLocation[i] = (int)currentLocation[ component ];
                // add edges incident to the i-th pixel if the other endpoint has already been added
                for ( int j = 0; j < MAX_NUM_NEIGHBORS; j++ )
                {
                    /*
                    if ( threshold == 30 && i == 400 )
                        System.out.println("STOP here");
                        */
                       
                    // find index of the neighbor in the old hillock
                    neighborIndex = neighbors[i][j];
                    // if the neighbor is above the threshold, in the same component and has already been
                    // added to the new hillock, add the edge\
                    //NEW LINE
                    if ( neighborIndex >= 0 && marked[neighborIndex] == component && neighborIndex < i )
                    //OLD LINE
                    //if ( neighborIndex > 0 && marked[neighborIndex] == component && neighborIndex < i )
                    {
                        int endPoint1 = newLocation[ i ];
                        int endPoint2 = newLocation[ neighborIndex ];
                        // need to add edges in each direction
                        hillockList[ component ].addEdge( endPoint1, endPoint2, j );
                        hillockList[ component ].addEdge( endPoint2, endPoint1, (j+2)%4 );
                    }
                }
                currentLocation[ component ]++;
            }
        }
        
        /*
        if (  threshold == 30 ||  threshold == 40 )
        {
            System.out.println("AFTER resetting EDGES");
            hillockList[ marked[0] ].printPixelStats( 0 );
            System.out.println("Component = " + marked[ 0 ] );
            hillockList[ marked[400] ].printPixelStats( 400 );
            System.out.println("Component = " + marked[ 400 ] );
        }
        */
       
        // now stretch each new hillock and set new threshold
        for ( int i = 0; i < numComponents; i++ )
        {
            hillockList[i].setMaxValue();
            hillockList[i].setMinValue();
            
            if ( hillockList[i].minPixelValue < hillockList[i].maxPixelValue )
            {
                hillockList[i].setMaxStretched();
                
                //System.out.println(" Min = " + minPixelValue );
                //System.out.println(" Max = " + maxPixelValue );
                //System.out.println(" About to stretch. Threshold = " + threshold );
                hillockList[i].stretch( threshold );
                
                hillockList[i].setNewThreshold( threshold );
                
                // if all pixels have been stretched as much as possible, process no further
                if ( hillockList[i].setMinStretched() > delta - EPSILON )
                    hillockList[i] = null;
                
                // if threshold is higher than the saturation value, no need to process further
                if ( threshold >= saturationValue )
                    hillockList[i] = null;
            }
            else
                hillockList[i] = null;
        }
        
        
        
        return hillockList;
    }
    
    // returns true of a stretch of delta is achieved.
    // returns false if saturation level is reached.
    public void stretch( int oldThreshold )
    {
        //if ( maxPixelValue == oldThreshold )
        //{
        //    System.out.println(" maxPixelValue and threshold are the same: " + maxPixelValue );
        //    System.out.println(" numPixels = " + numPixels );
        //}
            
        double toBeStretched = Math.min( delta/maxStretched, ( saturationValue - oldThreshold )/( maxPixelValue - oldThreshold ) );
        
        int f, y, x;
        int pixelValue;
        
        for ( int i = 0; i < numPixels; i++ )
        {
			f = fCoords[i];
            y = yCoords[i];
            x = xCoords[i];
            pixelValue = imageData[f][y][x];
            imageData[f][y][x] = (int)(oldThreshold + (pixelValue - oldThreshold)*toBeStretched);
            stretched[f][y][x] *= toBeStretched;
            /*
            if  ((x==1) && ((y==1) || (y==0)) )
            {
                System.out.println("New Value: " + imageData[y][x]);
                System.out.println("x = " + x + " y = " + y + " Index = " + i );
            }
            */
           
        }
        return;
        
    }
    
    public void setNewThreshold( int oldThreshold )
    {
        int minCriticalValue = Integer.MAX_VALUE;
        int pixelValue;
        
        // find lowest critical point that is above the threshold
        for ( int i = 0; i < numPixels; i++ )
        {
			int f = fCoords[i];
			int x = xCoords[i];
			int y = yCoords[i];
            if( criticalMap[f][y][x] )
            {
                if ( imageData[f][y][x] < minCriticalValue && imageData[f][y][x] > threshold )
                    minCriticalValue = imageData[f][y][x];
            }
        }
        
        // if there is no critical point above the threshold, just increment by MIN_STEP
        if ( minCriticalValue == Integer.MAX_VALUE ) 
            threshold = oldThreshold + MIN_STEP;
        else
            threshold = Math.max( oldThreshold + MIN_STEP, minCriticalValue );
            
        return;
    }
    
    // Depth first search, starting at pixel start.
    // Each pixel that is reached is marked as being in "component".
    public int DFS( int start, int component )
    {
        StackOfInts nodeStack = new StackOfInts();
        // this stack stores a number from 0 to 3 and indicates the next edge out of the
        // node to be traversed
        StackOfInts edgeStack = new StackOfInts();
        
        nodeStack.push(start);
        edgeStack.push(0);
        
        int numReached = 1;
        marked[start] = component;
        
        while( !nodeStack.isEmpty() )
        {
            int topNode = nodeStack.pop();
            int topEdge = edgeStack.pop();
            
            // if there are edges out of the node that have not been traveresed...
            if ( topEdge < MAX_NUM_NEIGHBORS )
            {
                // put topNode back and go on to next edge.
                nodeStack.push( topNode );
                edgeStack.push( (topEdge + 1) );
                // if the edge to be traversed leads to an unmarked node, 
                // mark it and push it on the stack
                int neighborIndex = neighbors[ topNode ][ topEdge ];
                if ( neighborIndex >= 0 && marked[ neighborIndex ]==UNMARKED )
                {
                    marked[neighborIndex] = component;
                    numReached++;
                    nodeStack.push( neighborIndex );
                    edgeStack.push( 0 );
                }
            }
        }
        return numReached;
        
        
    }

}