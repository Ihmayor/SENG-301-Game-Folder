package managers;
	import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;


///////////////////////////////////////////
//SoundManager							 //
//Purpose: Manages the sounds in the game//
//Limit: Limited to Slick2dAPI			 //
//Will crash if sound file not found     //
///////////////////////////////////////////


public class SoundManager {
	 
		/** The wav sound effect */
		private static Audio wavEffect;
		
		//Loads initial Backgroud music
	    public SoundManager(String filename) {
	 
	        try {
	 
		    //load Wav sound
		    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
	        } catch (IOException e) {
		    e.printStackTrace();
		}
	    	SoundStore.get().poll(0);
			
	    }
	 
	    //Updates music and keeps it playing
		public void update() {
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			SoundStore.get().poll(0);
		}
		
		//Changes music playing in the background
		public static void changeSound (String filename){
			try{
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
			wavEffect.playAsMusic(1.0f,1.0f, true);  
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}

		//Plays sound once
		public static void playSoundEffect(String filename){
			try{
				wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(filename));
				wavEffect.playAsSoundEffect(1.0f,1.0f, false);  	
			}
			catch (IOException e){
				e.printStackTrace();
			}

		}
	 
}
