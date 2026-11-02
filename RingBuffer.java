/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
    private double[] ringBuffer;
    private int size;
    private int indexStart; 
    private int indexEnd; 

    public RingBuffer(int capacity)
    {
        ringBuffer = new double[capacity];
        size = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size==0;
    }
    
    public boolean isFull()
    {
        return size==ringBuffer.length;
    }
    
    public void add(double value)
    {
    
        ringBuffer[indexEnd] = value;
        indexEnd = (indexEnd + 1) % ringBuffer.length;
        size++;
    }
    public double peek()
    {
        return ringBuffer[indexStart];
    }
    
    public double remove()
    {
        double value = ringBuffer[indexStart];

        indexStart = (indexStart + 1) % ringBuffer.length;
        size--;

        return value;
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
