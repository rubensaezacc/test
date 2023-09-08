import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ZonaJuego extends Canvas {

	private MainJuego mainJuego;
	private EventosJuego eventosJuego;
	private Image fondo, buffer, bobVida, bolindrongos;
	private Graphics pantallaVirtual;
	private int vidas;
	private int nivel;
	private int puntuacion;
	private ArrayList<Jugador> arrayJugador;
	private ArrayList<Enemigo> arrayEnemigo1;
	private ArrayList<Enemigo> arrayEnemigo2;
	private ArrayList<Enemigo> arrayEnemigo3;
	private ArrayList<Disparo> arrayDisparo;
	private URL pop1, pop2, pop3;
	private SoundEffect se;
	
	public ZonaJuego(MainJuego mainJuego){
		this.mainJuego = mainJuego;
		//CARGAR IMAGEN
		fondo = new ImageIcon(getClass().getResource("Fondo.jpg")).getImage();
		bobVida = new ImageIcon(getClass().getResource("Bob/D0.png")).getImage();
		bolindrongos = new ImageIcon(getClass().getResource("Tutorial/Bolindrongos.png")).getImage();
		
		

		//CARGAR JUGADOR Y DISPARO
		vidas = 3;
		nivel = 1;
		arrayJugador = new ArrayList<Jugador>();
		arrayDisparo = new ArrayList<Disparo>();
		
		//CARGAR ENEMIGOS
		arrayEnemigo1 = new ArrayList<Enemigo>();
		arrayEnemigo2 = new ArrayList<Enemigo>();
		arrayEnemigo3 = new ArrayList<Enemigo>();

		//CARGA LOS SONIDOS
		se = new SoundEffect(this);
		pop1 = getClass().getResource("Sonidos/Pop1.wav");
		pop2 = getClass().getResource("Sonidos/Pop2.wav");
		pop3 = getClass().getResource("Sonidos/Pop3.wav");

		//CARGAR EVENTOS
		eventosJuego = new EventosJuego(this);
		
		//CARGAR PUNTUACION
		puntuacion = 0;
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		//CARGAR FUENTE
		Font font = new Font("Impact", Font.PLAIN, 35);
	    g.setFont(font);
	    g.setColor(Color.black);

	    
		//DIBUJAR FONDO
		g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), null);
		
		//DIBUJA AL JUGADOR
		if (arrayJugador.size()!=0) {
			for (Jugador jugador : arrayJugador) {
				if (eventosJuego.getInvulnerable()!=1) {
					jugador.dibujar(g);
				}
			}
		}
		
		//DIBUJA AL DISPARO
		if (arrayDisparo.size()!=0) {
			for (Disparo disparo : arrayDisparo) {
				disparo.dibujar(g);
			}
		}
		
		//DIBUJA AL ENEMIGO DE TIPO 3
		if (arrayEnemigo1.size()!=0) {
			for (Enemigo enemigo : arrayEnemigo1) {
				enemigo.dibujar(g);
			}
		}
		
		//DIBUJA AL ENEMIGO DE TIPO 2
		if (arrayEnemigo2.size()!=0) {
			for (Enemigo enemigo : arrayEnemigo2) {
				enemigo.dibujar(g);
			}
		}
		
		//DIBUJA AL ENEMIGO DE TIPO 1
		if (arrayEnemigo3.size()!=0) {
			for (Enemigo enemigo : arrayEnemigo3) {
				enemigo.dibujar(g);
			}
		}
		
		g.drawImage(bobVida, 10, 10, 60, 50, null);

		g.drawString(getVidas() +"", 75, 50);
		
		g.drawImage(bolindrongos, 450, 10, 50, 50, null);
		
		g.drawString(""+getPuntuacion(), 510, 50);
		
	}
	
	//BUFFER DE LA IMAGEN PARA EVITAR PARPADEOS
	public void update(Graphics g) {
		buffer = createImage(this.getWidth(), this.getHeight());
		pantallaVirtual = buffer.getGraphics();
		paint(pantallaVirtual);
		g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	
	
	//GETTERS Y SETTERS
	public Image getFondo() {
		return fondo;
	}

	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}

	public EventosJuego getEventosJuego() {
		return eventosJuego;
	}

	public void setEventosJuego(EventosJuego eventosJuego) {
		this.eventosJuego = eventosJuego;
	}

	public Image getBuffer() {
		return buffer;
	}

	public void setBuffer(Image buffer) {
		this.buffer = buffer;
	}

	public Graphics getPantallaVirtual() {
		return pantallaVirtual;
	}

	public void setPantallaVirtual(Graphics pantallaVirtual) {
		this.pantallaVirtual = pantallaVirtual;
	}

	public ArrayList<Enemigo> getArrayEnemigo1() {
		return arrayEnemigo1;
	}

	public void setArrayEnemigo1(ArrayList<Enemigo> arrayEnemigo1) {
		this.arrayEnemigo1 = arrayEnemigo1;
	}

	public ArrayList<Enemigo> getArrayEnemigo2() {
		return arrayEnemigo2;
	}

	public void setArrayEnemigo2(ArrayList<Enemigo> arrayEnemigo2) {
		this.arrayEnemigo2 = arrayEnemigo2;
	}

	public ArrayList<Enemigo> getArrayEnemigo3() {
		return arrayEnemigo3;
	}

	public void setArrayEnemigo3(ArrayList<Enemigo> arrayEnemigo3) {
		this.arrayEnemigo3 = arrayEnemigo3;
	}

	public ArrayList<Disparo> getArrayDisparo() {
		return arrayDisparo;
	}

	public void setArrayDisparo(ArrayList<Disparo> arrayDisparo) {
		this.arrayDisparo = arrayDisparo;
	}
	
	public ArrayList<Jugador> getArrayJugador() {
		return arrayJugador;
	}

	public void setArrayJugador(ArrayList<Jugador> arrayJugador) {
		this.arrayJugador = arrayJugador;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public SoundEffect getSe() {
		return se;
	}

	public void setSe(SoundEffect se) {
		this.se = se;
	}

	public URL getPop1() {
		return pop1;
	}

	public void setPop1(URL pop1) {
		this.pop1 = pop1;
	}

	public URL getPop2() {
		return pop2;
	}

	public void setPop2(URL pop2) {
		this.pop2 = pop2;
	}

	public URL getPop3() {
		return pop3;
	}

	public void setPop3(URL pop3) {
		this.pop3 = pop3;
	}
	public MainJuego getMainJuego() {
		return mainJuego;
	}

	public void setMainJuego(MainJuego mainJuego) {
		this.mainJuego = mainJuego;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}
