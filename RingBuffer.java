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
        if (size == ringBuffer.length)
        {
            throw new RingBufferOverflowException("Ring Buffer is Full");
        }
        ringBuffer[indexEnd]=value;
        if (indexEnd == ringBuffer.length - 1)
        {
            indexEnd = 0;
        }
        else
        {
            indexEnd++;
        }
        size++;
    }
    
    public double peek()
    {
        if (isEmpty())
        {
            throw new RingBufferOverflowException("Queue is empty");
        }
        else
        {
            return ringBuffer[indexStart];
        }
    }
    
    public double remove()
    {
        if(isEmpty())
        {
            throw new RingBufferUnderflowException("Queue is Empty");
        }
        else
        {
            double pushed = ringBuffer[indexStart];
            ringBuffer[indexStart] = 0.0;
            size--;

            if (size > 0)
            {
                if (indexStart == ringBuffer.length - 1)
                {
                    indexStart = 0;
                }
                else
                {
                    indexStart++;
                }
            }
            else
            {
                indexStart = 0;
                indexEnd = 0;
            }
            return pushed;
        }
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
