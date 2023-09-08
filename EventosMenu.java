import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EventosMenu {
	private Rectangle rMouse, rBoton1, rBoton2, rBoton3, rBoton4;
	private ArrayList<String> listaPuntuaciones;
	private ArrayList<Number> listaPuntos;
	private int[] arrayPuntuaciones;
	private int aux;
	private String p = "";
	private String puntuacionesTotal = "";
	private boolean nombres = true;

	public EventosMenu(Menu menu, MainJuego mainJuego) {
		
		listaPuntuaciones = new ArrayList<String>();
		listaPuntos = new ArrayList<Number>();
		
		menu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				rMouse = new Rectangle(e.getX(), e.getY(), 1, 1);
				rBoton1 = new Rectangle(menu.getBoundsbtnComenzar());
				rBoton2 = new Rectangle(menu.getBoundsbtnControles());
				rBoton3 = new Rectangle(menu.getBoundsbtnSalir());
				rBoton4 = new Rectangle(menu.getBoundsbtnPuntuaciones());

				if (rMouse.intersects(rBoton1)) {
					mainJuego.estadoJuego(1);
				}
				if (rMouse.intersects(rBoton2)) {					
					if (menu.getEstadoAyuda() == 0) {
						menu.setEstadoAyuda(1);
					}else if (menu.getEstadoAyuda() == 1) {
						menu.setEstadoAyuda(0);
					}
				}
				if (rMouse.intersects(rBoton3)) {
					System.exit(1);
				}
				if (rMouse.intersects(rBoton4)) {
					
				    FileReader f;
					try {
						f = new FileReader("puntuaciones.txt");
					      BufferedReader b = new BufferedReader(f);
					      while((p = b.readLine())!=null) {
					    	if (p.equals("null")) {
								
							}else {
								listaPuntuaciones.add(p);
							}
					      }
					      b.close();
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					arrayPuntuaciones = new int[listaPuntuaciones.size()];
					
					//SEPARA LAS PUNTUACIONES
					for (String string : listaPuntuaciones) {
						for (String string2 : string.split(" : ")) {
							if (nombres) {
 								nombres = false;
							}else {
								listaPuntos.add(Integer.parseInt(string2));
								nombres = true;
							}
						}
					}
					
					//INSERTA EN UN ARRAY TODAS LAS PUNTUACIONES
					for (int i = 0; i < arrayPuntuaciones.length; i++) {
						arrayPuntuaciones[i]= (int) listaPuntos.get(i);
					}
					
					//ORDENAR ARRAY DE PUNTUACIONES
					for (int i = 0; i < arrayPuntuaciones.length-1; i++) {
						for (int j = 0; j < arrayPuntuaciones.length-1-i; j++) {
							if (arrayPuntuaciones[j] < arrayPuntuaciones[j+1]) {
								aux = arrayPuntuaciones[j+1];
								arrayPuntuaciones[j+1] = arrayPuntuaciones[j];
								arrayPuntuaciones[j] = aux;
							}
						}
					}
					
					//EMPAREJAR LAS PUNTUACIONES ORDENADAS CON LA LISTA DE PUNTUACIONES
					for (int i = 0; i < arrayPuntuaciones.length; i++) {
						for (int j = 0; j < listaPuntuaciones.size(); j++) {
							if (listaPuntuaciones.get(j).contains(" : "+arrayPuntuaciones[i])) {
								puntuacionesTotal += listaPuntuaciones.get(j) + "\n";
								listaPuntuaciones.set(j, "");
							}
						}
					}
					
					
					


					
					JOptionPane.showMessageDialog(mainJuego, puntuacionesTotal);
					
					listaPuntos.clear();
					listaPuntuaciones.clear();
					puntuacionesTotal = "";
				}
				
			}

			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {

			}
		});
	}



}
