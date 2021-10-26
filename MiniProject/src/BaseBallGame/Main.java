package BaseBallGame;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

	public static void main(String[] args) {

		   AudioInputStream ais;
		try {
			ais = AudioSystem.getAudioInputStream(new File("file(bgm)/music shaker (prod Letea).mp3"));
			 Clip clip;
				clip = AudioSystem.getClip();
			
		        
	           clip.open(ais);
	           clip.start();			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
		
		UserInterFace u = new UserInterFace();
		u.start();
		

			
	}

}
