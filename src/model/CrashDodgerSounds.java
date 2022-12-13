package model;

import java.net.URI;

import javafx.scene.media.AudioClip;

public class CrashDodgerSounds {
	
	public static void playSound(URI soundURI) {
		AudioClip clip = new AudioClip(soundURI.toString());
		clip.setVolume(0.9);
		clip.play();
	}
}
