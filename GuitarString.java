/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer ringBuffer;
    private int ticTimes=0;
    public GuitarString(double frequency) 
    {
        ringBuffer = new RingBuffer((int)(44100/frequency));

    }

    public GuitarString(double[] array) 
    {
        ringBuffer = new RingBuffer(array.length);
        for(double val : array)
        {
            ringBuffer.add(val);
        }
    }

    public void pluck() 
    {
        while(!ringBuffer.isFull())
        {
            ringBuffer.add(((Math.random() * Math.nextUp(1.0)) - 0.5)); //gemini
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double first = ringBuffer.remove();
        ringBuffer.add(((first+ringBuffer.peek())/2.0)*0.994);
        ticTimes++;
    }

    // return the current sample
    public double sample() 
    {
        return ringBuffer.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticTimes;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
