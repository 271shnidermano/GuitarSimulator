/**
 * This GuitarSimulator object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarSimulator
{
    public static void main(String[] args)
    {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] strings = new GuitarString[37];

        for (int i = 0; i < strings.length; i++)
        {
            double frequency = 440.0 * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(frequency);
        }
        while (true)
        {
            if (StdDraw.hasNextKeyTyped())
            {
                char key = StdDraw.nextKeyTyped();

                int index = keyboard.indexOf(key);
                if (index != -1)
                {
                    strings[index].pluck();
                }
            }
            double sample = 0.0;

            for (int i = 0; i < strings.length; i++)
            {
                sample += strings[i].sample();
            }
            StdAudio.play(sample);
            for (int i = 0; i < strings.length; i++)
            {
                strings[i].tic();
            }
        }
    }
}