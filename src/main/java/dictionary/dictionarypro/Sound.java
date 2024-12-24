package dictionary.dictionarypro;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * This is a public Sound class represents the Speech pronunciation feature of the dictionary UI.
 */

public class Sound {

    /**
     * This is a public static void speak method represents the speak method of the Sound class.
     * @param target is the word we want to speak.
     */

    public static void speak(String target) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(190);
            voice.setPitch(150);
            voice.setVolume(10);
            voice.speak(target);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
