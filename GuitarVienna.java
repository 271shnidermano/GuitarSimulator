public class GuitarVienna
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

        String[] vienna =
        {
            "g", "n", "j", "m", "9[d", "9[d",
            "m", "j", "n", "8=d", ".", "8=d",
            "n", "i=d", "i=d", "n", "m", "n",
            "g", "9=c", "g", "c", "f", "v",
            "g", "7p=", "7p=", "g", "n", "v",
            "g", "yp=", "m", "9[xg", "9[xg", "9[xg"
        };

        int count = 0;

        while (count < vienna.length)
        {
            if (StdDraw.hasNextKeyTyped())
            {
                StdDraw.nextKeyTyped();
                for (int i = 0; i < vienna[count].length(); i++)
                {
                    char key = vienna[count].charAt(i);

                    int index = keyboard.indexOf(key);

                    if (index != -1)
                    {
                        strings[index].pluck();
                    }
                }

                count++;
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

        while (true)
        {
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