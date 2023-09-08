import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
		private Clip clip;
		
		public Musica(MainJuego mainJuego) {
		}

		public void setFile(URL soundFileName){
			
			try{
				AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);	
				clip = AudioSystem.getClip();
				clip.open(sound);
				clip.loop(Clip.LOOP_CONTINUOUSLY);

			}
			catch(Exception e){
				
			}
		}
		
		public void play(){
			
			clip.setFramePosition(0);
			clip.start();
		}

	}