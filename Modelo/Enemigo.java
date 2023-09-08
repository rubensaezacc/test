import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemigo extends Thread{

	private ZonaJuego zonaJuego;
	private Image imgBola1;
	private Image imgBola2;
	private Image imgBola3;
	private int posX, posY, alto, ancho, velocidad, dirH, dirY;
	private int tipo; //1 BOLA1  -  2 BOLA2  -  3 BOLA3
	private double rebote;
	private boolean intersectado;

	public Enemigo(ZonaJuego zonaJuego) {
		this.zonaJuego = zonaJuego;
		new Random();
		//CARGAR IMAGEN
		imgBola1 = new ImageIcon(getClass().getResource("Enemigos/Bola1.png")).getImage();
		imgBola2 = new ImageIcon(getClass().getResource("Enemigos/Bola2.png")).getImage();
		imgBola3 = new ImageIcon(getClass().getResource("Enemigos/Bola3.png")).getImage();
		//CARGAR DATOS
		posX = 300;
		tipo = 3;

	}

	public void run() {
		super.run();

		switch (tipo) {
		case 1:
			alto = 30;
			ancho = 30;
			velocidad = 5;
			intersectado = false;
			rebote = 1;
			break;
		case 2:
			alto = 65;
			ancho = 65;
			velocidad = 3;
			intersectado = false;
			rebote = 1;
			break;
		case 3:
			alto = 90;
			ancho = 90;
			velocidad = 2;
			intersectado = false;
			rebote = 1;
			break;

		default:
			break;
		}

		while(true) {
			//COLISION LATERAL
			posX = posX + velocidad * dirH;
			if (posX+ancho >= 700-25) {
				dirH = -1;
			}else if (posX <= 0) {
				dirH = 1;
			}

			//SISTEMA DE REBOTE
			posY =  (int) (posY - velocidad -rebote/10);	

			switch (tipo) {
			case 1:
				if (rebote <= 0) {
					if (posY+alto >= zonaJuego.getHeight()) {
						rebote = 40;
					}

				}
				break;
			case 2:
				if (rebote <= 0) {
					if (posY+alto >= zonaJuego.getHeight()) {
						rebote = 50;
					}

				}
				break;
			case 3:
				if (rebote <= 0) {
					if (posY+alto >= zonaJuego.getHeight()) {
						rebote = 60;
					}
				}
				break;

			default:
				break;
			}
			rebote--;


			zonaJuego.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void dibujar(Graphics g) {
		switch (tipo) {
		case 1:
			g.drawImage(imgBola1, posX, (int) posY, ancho, alto, null);
			break;
		case 2:
			g.drawImage(imgBola2, posX, (int) posY, ancho, alto, null);
			break;
		case 3:
			g.drawImage(imgBola3, posX, (int) posY, ancho, alto, null);
			break;

		default:
			break;
		}
	}



	//GETTERS Y SETTERS
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
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

	public int getDirH() {
		return dirH;
	}

	public void setDirH(int dirH) {
		this.dirH = dirH;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}

	public double getRebote() {
		return rebote;
	}

	public void setRebote(double rebote) {
		this.rebote = rebote;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(int d) {
		this.posY = d;
	}

	public boolean isIntersectado() {
		return intersectado;
	}

	public void setIntersectado(boolean intersectado) {
		this.intersectado = intersectado;
	}

	public Rectangle getBounds() {
		return new Rectangle(posX+10, posY-10, ancho-10, alto-20);

	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
