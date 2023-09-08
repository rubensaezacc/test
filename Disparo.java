import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/*
 * SUBE DESDE LA ALTURA DEL JUGADOR HASTA EL MAXIMO, LUEGO DESAPARECE
 */
public class Disparo extends Thread {
	private ZonaJuego zonaJuego;
	private Image imgDisparo;
	private int posX, posY, alto, ancho, velocidad;
	private boolean disparo;

	public Disparo(ZonaJuego zonaJuego) {
		this.zonaJuego = zonaJuego;
		//CARGAR IMAGEN
		if (zonaJuego.getArrayJugador().size() != 0) {
			for (int j = 0; j < zonaJuego.getArrayJugador().size(); j++) {
				imgDisparo = new ImageIcon(getClass().getResource("Disparo/Disparo.png")).getImage();
				posX = 0;
				posY = zonaJuego.getArrayJugador().get(j).getPosY()+100;
				alto = 770;
				ancho = 10;
				velocidad = 10;
				disparo = false;
			}
		}

	}
	public void dibujar(Graphics g) {
		g.drawImage(imgDisparo, posX, posY, ancho, alto, null);

	}


	@Override
	public void run() {
		super.run();
		while (true) { 	//SI SE DISPARA, LA BALA SE MUEVE HASTA ARRIBA

			if (disparo) {
				posY -= velocidad;
				zonaJuego.repaint();
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




	//GETTERS Y SETTERS
	public ZonaJuego getZonaJuego() {
		return zonaJuego;
	}
	public void setZonaJuego(ZonaJuego zonaJuego) {
		this.zonaJuego = zonaJuego;
	}
	public Image getImgDisparo() {
		return imgDisparo;
	}
	public void setImgDisparo(Image imgDisparo) {
		this.imgDisparo = imgDisparo;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public boolean isDisparo() {
		return disparo;
	}
	public void setDisparo(boolean disparo) {
		this.disparo = disparo;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX, posY, ancho, alto);

	}



}
