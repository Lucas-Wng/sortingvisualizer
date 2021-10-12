// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// MidiPlayer Class
// Used for playing sounds that are pitched to the value.

import java.util.ArrayList;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiPlayer {
    private final ArrayList<Integer> pitch;
    private Synthesizer synth;
    private final MidiChannel channel;
    private int inputValueMaximum;

    // Sets the maxValue to the biggest value of the array
    public MidiPlayer(int maxValue) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
        inputValueMaximum = maxValue;
        channel = synth.getChannels()[0];
        // Sets the sound to "Breath Noises" which sounds the best for sorting
        channel.programChange(121);
        // Adds the pitches to an ArrayList. Between 20 and 110 is the best for
        // listening
        pitch = new ArrayList<>();
        for (int i = 20; i <= 110; i++) {
            pitch.add(i);
        }
    }

    private int pitchToValue(int value) {
        // Calculates the pitch in relation to the maximum value and value
        double n = ((double) value / (double) inputValueMaximum);
        int index = (int) (n * (double) pitch.size());
        // The largest indexes in the array will go out of bounds which is fixed by the
        // line below
        index = Math.max(1, Math.min(90, index));
        return pitch.get(index);
    }

    public void makeSound(int value) {
        // Plays the note
        int note = pitchToValue(value);
        channel.noteOn(note, 25);
    }
}
