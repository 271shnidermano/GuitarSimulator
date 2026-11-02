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

        // Create all 37 guitar strings
        for (int i = 0; i < strings.length; i++)
        {
            double frequency = 440.0 * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(frequency);
        }

        while (true)
        {
            // Check for a key press
            if (StdDraw.hasNextKeyTyped())
            {
                char key = StdDraw.nextKeyTyped();

                int index = keyboard.indexOf(key);

                // Pluck the corresponding string
                if (index != -1)
                {
                    strings[index].pluck();
                }
            }

            // Add together the samples from every string
            double sample = 0.0;

            for (int i = 0; i < strings.length; i++)
            {
                sample += strings[i].sample();
            }

            // Play the combined sample
            StdAudio.play(sample);

            // Advance every string
            for (int i = 0; i < strings.length; i++)
            {
                strings[i].tic();
            }
        }
    }
}