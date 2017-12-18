import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Plays the background music
 */
public class Music {
    public static final String EMPTY = "";
    public static final String ERROR_SOUND_LOG_MESSAGE = "Error with playing sound.";

    private static ArrayList<String> musicList = new ArrayList<String>(Arrays.asList("Rock.mp3"));
    private static int curr = 0;
    private static String bip;
    private static Media hit;
    private static MediaPlayer mediaPlayer;

    /**
     * start playing the first error invalidCommandSound on the playlist.
     */
    public static void playSound() {
        try {
            createsNewMediaPlayer();
            mediaPlayer.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Generates a new MediaPlayer
     * @throws URISyntaxException if media file cannot be found
     */
    private static void createsNewMediaPlayer() throws URISyntaxException {
        JFXPanel fxPanel = new JFXPanel();
        bip = musicList.get(curr);
        //must be a valid file name before begin searching
        assert bip != EMPTY;
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(bip).toURI().toString());
        hit = new Media(Thread.currentThread().getContextClassLoader().getResource(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
    }
}
