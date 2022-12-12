package model;

import java.net.URI;

import javafx.scene.media.AudioClip;

public class CrashDodgerSounds {
	
	private static double VOL = 0.9;
	
	public static void playSound(URI soundURI) {
		AudioClip clip = new AudioClip(soundURI.toString());
		clip.setVolume(VOL);
		clip.play();
	}
}
