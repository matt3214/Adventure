import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class AudioSystem {
	AudioPlayer MGP;

	public void AudioSystem() {
		MGP = AudioPlayer.player;
	}

	public void playSound(String location) {
		AudioStream BGM;
		AudioData MD;

		ContinuousAudioDataStream loop = null;

		try {
			InputStream test = new FileInputStream(location);
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
			// MD = BGM.getData();
			// loop = new ContinuousAudioDataStream(MD);

		} catch (FileNotFoundException e) {
			System.out.print(e.toString());
		} catch (IOException error) {
			System.out.print(error.toString());
		}
	}
}
