/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString
{
    private RingBuffer ringBuffer;
    private int time;

    public GuitarString(double frequency)
    {
        int capacity = (int)(44100 / frequency);

        ringBuffer = new RingBuffer(capacity);
        time = 0;

        for (int i = 0; i < capacity; i++)
        {
            ringBuffer.add(0.0);
        }
    }

    public GuitarString(double[] init)
    {
        ringBuffer = new RingBuffer(init.length);
        time = 0;

        for (int i = 0; i < init.length; i++)
        {
            ringBuffer.add(init[i]);
        }
    }

    public void pluck()
    {
        while (!ringBuffer.isEmpty())
        {
            ringBuffer.remove();
        }

        while (!ringBuffer.isFull())
        {
            double randomValue = Math.random() - 0.5;
            ringBuffer.add(randomValue);
        }
    }

    public void tic()
    {
        double first = ringBuffer.remove();

        double average = (first + ringBuffer.peek()) / 2.0;

        ringBuffer.add(average * 0.994);

        time++;
    }

    public double sample()
    {
        return ringBuffer.peek();
    }

    public int time()
    {
        return time;
    }
}