import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    public static void main(String[] args) {
        GuitarString[] rail = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            rail[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
        }

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                String keyfromchar = String.valueOf(key);
                if (keyboard.contains(keyfromchar)) {
                    rail[keyboard.indexOf(key)].pluck();
                }
            }

            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample += rail[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < 37; i++) {
                rail[i].tic();
            }
        }
    }
}
