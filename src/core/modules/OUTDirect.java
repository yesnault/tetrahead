package core.modules;

import core.*;

import gui.ConcreteFactory;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Sortie du son sur les Hauts Parleurs.
 * 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Leli�vre, Vincent Mah�
 * 
 *  Ce module est un logiciel libre distribue sous licence GNU/LGPL. 
 *  Pour plus de details voir le fichier COPYING2.txt.
 */
public class OUTDirect extends Module {

	private final String NAME = "OUTDirect";

	public static final String PARAM_STATE = "On/Off";

	public static final String PARAM_STATE_ON = "On";

	public static final String PARAM_STATE_OFF = "Off";

	public static final String PORT_SIGNAL_IN = "In";

	private boolean isPlaying = false;

	private int compteur;

	private short[] tabShort;

	protected ShortBuffer shortBuffer;

	byte playBuffer[], playBuffer2[];

	private ByteBuffer byteBuffer;

	SourceDataLine line;

	boolean bigEndian = true;

	int sampleSizeInBits = 16;

	int channels = 1; // Mono

	int frameSize = channels * sampleSizeInBits / 8;

	ListenThread thread = null;

	public String getName() {
		return this.NAME;
	}

	/**
	 * Initialisation du module OUTDirect.
	 * 
	 */
	public OUTDirect() {
		/* Port d'entre */
		usine = ConcreteFactory.getFactory();
		this.addPortsIn(OUTDirect.PORT_SIGNAL_IN, usine
				.newPortIn(OUTDirect.PORT_SIGNAL_IN));

		/* Bouton pour activer ou non le module de sortie */
		Collection<ParameterValue> pv = new ArrayList<ParameterValue>();
		pv.add(new ParameterValue(OUTDirect.PARAM_STATE_ON));
		pv.add(new ParameterValue(OUTDirect.PARAM_STATE_OFF));
		this.addDiscreteParameter(OUTDirect.PARAM_STATE, usine
				.newDiscreteParameter(OUTDirect.PARAM_STATE, "", pv));

		setPortOut(null);

		/*
		 * Initialisation des diff�rents buffers
		 */
		tabShort = new short[32000];
		playBuffer = new byte[64000];
		playBuffer2 = new byte[64000];
		byteBuffer = ByteBuffer.wrap(playBuffer);
		shortBuffer = byteBuffer.asShortBuffer();

	}

	@Override
	/**
	 * Compute du OUT : - Initialisation ou Stop du syst�me de son si besoin -
	 * remplissage d'un buffer pour que le thread de lecture (lanc� lors de
	 * l'initialisation du syst�me de son) puisse le lire ensuite
	 */
	public void compute() throws SyntheCoreException, LineUnavailableException {

		/*
		 * Allumage du syst�me de son (Bouton ON/OFF du module ==> Si le bouton
		 * est � l'�tat ON et que le syst�me n'est pas d�j� initialis�
		 */
		if (getDiscreteParameter(OUTDirect.PARAM_STATE).getCurrentValue()
				.toString() == OUTDirect.PARAM_STATE_ON
				&& !isPlaying) {
			/* Appel � la m�thode d'initialisation */
			initSoundSystem();

			/*
			 * Arr�t du syst�me de son (Bouton ON/OFF du module) ==> Si le
			 * bouton est � l'�tat OFF et que le syst�me de son est initialis�
			 */
		} else if (getDiscreteParameter(OUTDirect.PARAM_STATE)
				.getCurrentValue().toString() == OUTDirect.PARAM_STATE_OFF
				&& isPlaying) {

			/* Appel � la m�thode de fermeture du syst�me de son */
			stopSoundSystem();
		}

		/*
		 * Si l'on est en cours de playing on r�cup�re le signal sur le port
		 * d'entr�e et on le met dans le buffer tabShort
		 */
		if (isPlaying) {

			double signal = getPortIn(PORT_SIGNAL_IN).read();
			tabShort[compteur] = ((short) (signal * 3200));
			/*
			 * On ajoute dans le buffer tabShort s'il est pas remplit le +1 est
			 * pour passer dans le put() � la taille n-1 du buffer
			 */
			if (tabShort.length > compteur + 1) {
				compteur++;

				/*
				 * Si le buffer est remplit, on fait appel � la m�thode put du
				 * thread pour qu'il prenne en compte l'arriv�e du nouveau
				 * buffer
				 */
			} else {
				thread.put();
				/* puis l'on r�-initialise le compteur de tabShort */
				compteur = 0;
			}
		}
	}

	/**
	 * Initialisation du syst�me de son - D�marrage de la ligne - D�marrage du
	 * thread de lecture
	 * 
	 * @throws LineUnavailableException
	 */
	private void initSoundSystem() throws LineUnavailableException {

		/* Pr�paration des structures de son */
		AudioFormat audioFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED, freqEch, sampleSizeInBits,
				channels, frameSize, freqEch, bigEndian);

		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
				audioFormat);

		/* Ouverture de la ligne audio */
		line = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		line.open(audioFormat);
		line.start();

		isPlaying = true;
		/*
		 * On lance le Thread de lecture du son, qui sera ex�cuter tant que
		 * isPlaying est �gal � true ==> il faut donc faire le isPlaying avant
		 * de lancer le Thread.
		 */
		thread = new ListenThread();
		thread.start();
	}

	/**
	 * Stop du Syst�me de son : - lib�ration de la ligne - fermeture du thread
	 */
	private void stopSoundSystem() {
		isPlaying = false;
		/* Lib�ration de la ligne audio */
		line.close();
	}

	@Override
	/**
	 * Lib�ration de la ligne son du syst�me, et stop du Thread de lecture
	 */
	public void stop() {
		stopSoundSystem();
		reset_buffer();
	}

	@Override
	/**
	 * Fait appel � la m�thode reset de la classe Module et vide en plus les
	 * diff�rents buffer
	 */
	public void reset() {
		super.reset();
		reset_buffer();
	}

	private void reset_buffer() {
		compteur = 0;
		shortBuffer.clear();
		shortBuffer.rewind(); // facultatif
	}

	/**
	 * Thread de lecture du son
	 * 
	 * http://java.sun.com/j2se/1.5.0/docs/guide/sound/programmer_guide/chapter4.html#116365
	 */
	class ListenThread extends Thread {
		int nb_put = 0;

		int nb_write = 0;

		public void run() {

			/*
			 * Tant que l'�tat est en cours de isPlaying, on �crit sur la carte
			 * son. le isPlaying est modifi� uniquement lors du compute()
			 */

			/*
			 * Ex�cution d'un sleep de 1 pour �viter une boucle infinie qui
			 * consomme toute la puissance du CPU. Ici, le CPU consomm�
			 * normalement et aucune coupure de son se produit.
			 */
			while (isPlaying) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * Remplissage du buffer de lecture avec les nouvelles valeurs re�ues
		 * dans le compute
		 * 
		 * Synchronized : on ne peut pas faire appel � cette m�thode tant que
		 * l'�criture sur la carte son du buffer pas eu lieu. Il n'y a donc pas
		 * de perte de buffer.
		 * 
		 */
		public synchronized void put() {

			shortBuffer.put(tabShort);

			/*
			 * R�-initialisation des valeurs des compteurs pour �viter de
			 * d�passer les valeurs maximum
			 */
//			if (nb_put > 50000) {
//				nb_put = nb_put - 50000;
//				nb_write = nb_write - 50000;
//			}
//
//			nb_put++;

			write();
		}

		/**
		 * Write : clonage de buffer pour �viter de bloquer le playBuffer (=
		 * ShortBuffer) V�rification qu'il n'y a pas de pertes de buffer
		 * �galement et indique au Thread qu'il peut jouer le nouveau buffer
		 * remplit
		 * 
		 */
		private void write() {
				playBuffer2 = playBuffer.clone();
				shortBuffer.clear();
				//nb_write++;

				/* �criture du playBuffer2 directement sur la carte son */
				line.write(playBuffer2, 0, playBuffer2.length);
				/*
				 * il ne faut pas faire de line.drain() pour ne pas avoir de
				 * coupures. le drain est d'ailleurs non utile avec
				 * l'utilisation des m�thodes synchronized put() et write() de
				 * ce thread.
				 */
		}
	}
}
