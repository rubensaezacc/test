import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainJuego extends JFrame {

	private JPanel contentPane;
	private ZonaJuego zonaJuego;
	private Menu menu;
	private URL[] musica;
	private Musica m;
	private int estadoJuego;//0 - Menu, 1 - Juego

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainJuego frame = new MainJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJuego() {
		setTitle("Chaka-Pang");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJuego.class.getResource("/Tutorial/Bolindrongos.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		musica = new URL[5];

		for (int i = 0; i < musica.length; i++) {
			musica[i]= getClass().getResource("Sonidos/Musica"+(i+1)+".wav");
		}
		m = new Musica(this);
		
		m.setFile(musica[2]);
		m.play();

		
		//estadoJuego SE CAMBIA PARA CAMBIAR EL CANVAS
		estadoJuego = 0;	
		estadoJuego(estadoJuego);
	}

	public void estadoJuego(int estadoJuego) {
		
		switch (estadoJuego) {
		case 0:
			
			try {
				contentPane.remove(zonaJuego);
				zonaJuego.getEventosJuego().getTimerCheck().stop();
			} catch (Exception e) {
				System.out.println("ZonaJuego no eliminado");
			}
			
			menu = new Menu(this);
			contentPane.add(menu);
			contentPane.revalidate();
			menu.setFocusable(true);
			menu.requestFocus();
			break;
			
		case 1:	
			try {
				menu.getReloj().stop();
				menu.getRelojPersonaje().stop();
				contentPane.remove(menu);
			} catch (Exception e) {
				System.out.println("Menu no eliminado");
			}
			
			zonaJuego = new ZonaJuego(this);
			contentPane.add(zonaJuego);
			contentPane.revalidate();
			zonaJuego.setFocusable(true);
			zonaJuego.requestFocus();
			break;

		default:
			break;
		}
	}

	public int getEstadoJuego() {
		return estadoJuego;
	}

	public void setEstadoJuego(int estadoJuego) {
		this.estadoJuego = estadoJuego;
	}

}
