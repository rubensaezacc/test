import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Menu extends Canvas{
	private MainJuego mainJuego;
	private Timer reloj, relojPersonaje;
	private Image[] imgD;
	private Image fondo, buffer, titulo, btnComenzar, btnAyuda, btnSalir, btnPuntuaciones, enemigo, ayuda;
	private Graphics pantallaVirtual;
	private int random;
	private int estadoAyuda;
	private int imgActual;
	private double rotacion = 0;


	public Menu(MainJuego mainJuego) {
		this.mainJuego = mainJuego;
		estadoAyuda = 0; //MANTENER ESTO A 0 PARA QUE EL CUADRO DE AYUDA NO SALTE AUTOMATICAMENTE
		
		//CARGAR IMAGENES BOB
		imgD = new Image [23];
		for (int i = 0; i < imgD.length; i++) {
			imgD[i]= new ImageIcon(getClass().getResource("Bob/D"+(i+1)+".png")).getImage();
		}
		
		//RANDOMIZADOR PARA CAMBIARLE EL COLOR A LA BOLA
		random = (int) (Math.random()*3+1);

		//CARGAR FONDO
		fondo = new ImageIcon(getClass().getResource("Fondo.jpg")).getImage();		
		
		//CARGAR BOTONES
		titulo = new ImageIcon(getClass().getResource("Botones/Titulo.png")).getImage();
		btnComenzar = new ImageIcon(getClass().getResource("Botones/Comenzar.png")).getImage();
		btnAyuda = new ImageIcon(getClass().getResource("Botones/Ayuda.png")).getImage();
		btnSalir = new ImageIcon(getClass().getResource("Botones/Salir.png")).getImage();
		enemigo = new ImageIcon(getClass().getResource("Enemigos/Bola"+random+".png")).getImage();
		ayuda = new ImageIcon(getClass().getResource("Tutorial/Tutorial.png")).getImage();
		btnPuntuaciones = new ImageIcon(getClass().getResource("Botones/Puntuaciones.png")).getImage();

		//CARGAR EVENTOS DEL MENU
		new EventosMenu(this, this.mainJuego);
		

		//TIMER PARA LA BOLA QUE DA VUELTAS
		reloj = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		reloj.start();
		
		//TIMER PARA BOB CORRIENDO
		relojPersonaje = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imgActual=(imgActual+1)%imgD.length;
				repaint();
			}
		});
		relojPersonaje.start();


	}


	public void paint(Graphics g) {
		super.paint(g);		
		//DIBUJAR FONDO Y BOTONES
		g.drawImage(fondo, 0, 0, 700, 515, null);
		g.drawImage(titulo, 335-250, 0, 500, 70, null);
		
		g.drawImage(btnComenzar, 50, 120, 175, 50, null);
		g.drawImage(btnAyuda, 50, 220, 175, 50, null);
		g.drawImage(btnSalir, 50, 320, 175, 50, null);

		g.drawImage(btnPuntuaciones, 570, 15, 70, 75, null);

		//SEGUN EL ESTADO SE MOSTRARÁ LA AYUDA
		if (estadoAyuda == 1) {
			//DIBUJAR RECUADRO DE AYUDA
			g.drawImage(ayuda, 250, 75, 420, 385, null);
			//DIBUJAR A BOB CORRIENDO
			g.drawImage(imgD[imgActual], 260, 155, 60, 50, null);

		}else if (estadoAyuda == 0) {
			//DIBUJA LA BOLA QUE DA VUELTAS
			Graphics2D gr2=(Graphics2D) g;
			gr2.translate(500, 260);
			gr2.rotate(rotacion);
			gr2.drawImage(enemigo, -100, -95, 200, 200, this);
			rotacion = rotacion + 0.01;
		}

	}

	//BUFFER DE LA IMAGEN PARA EVITAR PARPADEOS
	public void update(Graphics g) {
		buffer = createImage(this.getWidth(), this.getHeight());
		pantallaVirtual = buffer.getGraphics();
		paint(pantallaVirtual);
		g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	
	
	
	
	
	
	//GETTERS Y SETTERS
	public Rectangle getBoundsbtnComenzar() {
		return new Rectangle(50, 120, 175, 50);
		
	}
	public Rectangle getBoundsbtnControles() {
		return new Rectangle(50, 220, 175, 50);
		
	}
	public Rectangle getBoundsbtnSalir() {
		return new Rectangle(50, 320, 175, 50);
		
	}
	public Rectangle getBoundsbtnPuntuaciones() {
		return new Rectangle(580, 15, 70, 50);
		
	}
	
	
	

	
	
	public Image getBtnComenzar() {
		return btnComenzar;
	}


	public void setBtnComenzar(Image btnComenzar) {
		this.btnComenzar = btnComenzar;
	}


	public Image getbtnAyuda() {
		return btnAyuda;
	}


	public void setbtnAyuda(Image btnAyuda) {
		this.btnAyuda = btnAyuda;
	}


	public Image getBtnSalir() {
		return btnSalir;
	}


	public void setBtnSalir(Image btnSalir) {
		this.btnSalir = btnSalir;
	}


	public Timer getReloj() {
		return reloj;
	}


	public void setReloj(Timer reloj) {
		this.reloj = reloj;
	}


	public int getEstadoAyuda() {
		return estadoAyuda;
	}


	public void setEstadoAyuda(int estadoAyuda) {
		this.estadoAyuda = estadoAyuda;
	}


	public Timer getRelojPersonaje() {
		return relojPersonaje;
	}


	public void setRelojPersonaje(Timer relojPersonaje) {
		this.relojPersonaje = relojPersonaje;
	}
}
