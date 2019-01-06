import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound extends Thread{

	protected volatile boolean cancelled = false;
	protected boolean finish = false;
	AudioClip audioClip; // declares an AudioClip object
	URL urlclip ; // object which is going to define the url of the file
	
	public Sound(String URL){
		try {
			urlclip= new URL("file:" + URL); // new URL object
			audioClip = Applet.newAudioClip(urlclip); // new AudioClip object
			audioClip.play(); // reads the audio file
		} catch (MalformedURLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void playTempSound(String URL){
		Thread s = new Sound(URL);
		s.start();
	}
}