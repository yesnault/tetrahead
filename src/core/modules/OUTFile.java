package core.modules;

import core.*;

import gui.ConcreteFactory;

import java.io.*;
import java.nio.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sound.sampled.*;

/**
 * Sortie du son dans un fichier.
 * 
  * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class OUTFile extends Module {

	private final String NAME = "OUTFile";

	public static final String PARAM_STATE = "On/Off";

	public static final String PARAM_STATE_ON = "On";

	public static final String PARAM_STATE_OFF = "Off";

	public static final String PORT_SIGNAL_IN = "In";

	private double signal;

	private AudioFormat audioFormat;

	private AudioInputStream audioInputStream;

	// 1 ou 2 : Mono ou Stereo
	int channels = 1;

	// True ou False
	boolean signed = true;

	// True ou False
	boolean bigEndian = true;

	// 8000,11025,16000,22050,44100
	float sampleRate = 44000.0F;

	int sampleSizeInBits = 16;

	int sampleSize = sampleSizeInBits * 500;

	InputStream byteArrayInputStream;

	ByteBuffer byteBuffer;

	ShortBuffer shortBuffer;

	// Buffer pour avoir 100 secondes en mono, 16000 sample / sec
	private byte audioData[] = new byte[sampleSize * 500];

	private String fileOut = "sorties/wav_temp/sortie";

	boolean threadLance = false;

	private int compteur;

	private boolean isPlaying;

	public OUTFile() {
		/* Port d'entr�e */
		usine = ConcreteFactory.getFactory();
		this.addPortsIn(OUTFile.PORT_SIGNAL_IN, usine
				.newPortIn(OUTFile.PORT_SIGNAL_IN));

		/* Bouton pour activer ou non le module de sortie */
		Collection<ParameterValue> pv = new ArrayList<ParameterValue>();
		pv.add(new ParameterValue(OUTFile.PARAM_STATE_OFF));
		pv.add(new ParameterValue(OUTFile.PARAM_STATE_ON));
		this.addDiscreteParameter(OUTFile.PARAM_STATE, usine
				.newDiscreteParameter(OUTFile.PARAM_STATE, "", pv));

		setPortOut(null);
		isPlaying = false;

		/*
		 * On met un nom de fichier sp�cifique pour la sortie mais l'utilisateur
		 * doit pouvoir le modifier �galement avec l'interface. L�, c'est juste
		 * pour avoir un nom par d�faut g�n�rique.
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyymmdd_hhmmssSS");
		setFileOut(getFileOut() + "_" + dateFormat.format(new Date()));
	}

	@Override
	public void compute() throws Exception {

		signal = getPortIn(PORT_SIGNAL_IN).read();

		if (getDiscreteParameter(OUTDirect.PARAM_STATE).getCurrentValue()
				.toString() == OUTDirect.PARAM_STATE_ON
				&& !isPlaying) {
			initSoundSystem();

		} else if (getDiscreteParameter(OUTDirect.PARAM_STATE)
				.getCurrentValue().toString() == OUTDirect.PARAM_STATE_OFF
				&& isPlaying) {

			/*
			 * Appel � la m�thode de fermeture du syst�me de son �criture dans
			 * le fichier
			 */
			stop();
		}

		if (isPlaying) {
			if (shortBuffer.limit() > compteur) {
				shortBuffer.put(((short) (signal * 3200))) ;
				compteur++;
			} else {
				compteur = 0;
				//shortBuffer.put(tabShort);
				/* Ecriture dans le fichier */
				//Et l'on stoppe d�s que l'on a �crit le fichier
				stop();
				
				shortBuffer.clear();
				shortBuffer.rewind();
				// 
				
			}
		}
	}
	
	public String getName() {
		return this.NAME;
	}

	@Override
	/**
	 * Lib�ration du syst�me de son
	 */
	public synchronized  void stop() throws IOException {
		playToFile();
		stopSoundSystem();
	}

	@Override
	/**
	 * Fait appel � la m�thode reset de la classe Module et vide en plus les
	 * diff�rents buffer
	 */
	public void reset() {
		super.reset();
		compteur = 0;
		shortBuffer.clear();
		shortBuffer.rewind(); // facultatif
	}
	
	
	/***************************************************************************
	 * * Private Method
	 * 
	 * @throws IOException
	 */
	private void playToFile() throws IOException {
		if (isPlaying) {
			System.out.println(" OUTFile::playToFile : Ecriture du fichier "
					+ fileOut + ".wav");
			AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE,
					new File(fileOut + ".wav"));			
		}

	}

	/**
	 * Indique le nom du fichier de sortie. Par exemple :
	 * sorties/wav_temp/sortie (sans l'extension .wav)
	 * 
	 * @param string :
	 *            nom du fichier de sortie
	 */
	private String getFileOut() {
		return fileOut;
	}

	/**
	 * Indique le nom du fichier de sortie. Par exemple :
	 * sorties/wav_temp/sortie (sans l'extension .wav)
	 * 
	 * @param string :
	 *            nom du fichier de sortie
	 */
	private void setFileOut(String string) {
		fileOut = string;
	}
	
	
	/**
	 * Initialisation du syst�me de son.
	 * 
	 * @throws LineUnavailableException
	 */
	private void initSoundSystem() throws LineUnavailableException {

		for (int i = 0; i<audioData.length; i++ ) {
			audioData[i] = 0;	
		}
		
		byteBuffer = ByteBuffer.wrap(audioData);
		shortBuffer = byteBuffer.asShortBuffer();
		
		byteArrayInputStream = new ByteArrayInputStream(audioData);

		audioFormat = new AudioFormat(freqEch, sampleSizeInBits, channels,
				signed, bigEndian);

		audioInputStream = new AudioInputStream(byteArrayInputStream,
				audioFormat, audioData.length / audioFormat.getFrameSize());

		isPlaying = true;
		compteur = 0;
	}

	/**
	 * On stop le syst�me de son, le Thread doit s'arr�ter apr�s l'appel � cette
	 * m�thode : initPlaying = false
	 * 
	 */
	private  synchronized void stopSoundSystem() {
		if (isPlaying) {
			isPlaying = false;
			getDiscreteParameter(OUTDirect.PARAM_STATE).setCurrentValue(new ParameterValue(OUTDirect.PARAM_STATE_OFF));			
		}

	}
}
