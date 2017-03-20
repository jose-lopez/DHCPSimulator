//package examples.party;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import jade.core.behaviours.OneShotBehaviour;
import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;

import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;
import javax.swing.event.ChangeEvent;

import java.lang.Integer;

public class MainUIFrame extends JFrame {
		
	JPanel contentPane;
	JLabel lblConfiguracionDelServidor = new JLabel();
	JLabel lblDireccionDeRed = new JLabel();
	MaskFormatter mask = new MaskFormatter();
	MaskFormatter mask_1 = new MaskFormatter();
	JLabel lblDireccionDelServidor = new JLabel();
	JLabel lblPool = new JLabel();
	JLabel lblConfiguracionDeLa = new JLabel();
	JLabel lblNumeroDeHosts = new JLabel();
	JSlider slider_1 = new JSlider();
	JButton btnIniciar = new JButton();
	JButton btnDetener = new JButton();
	JLabel lblnumHosts = new JLabel();
	JFormattedTextField formattedTextField = new JFormattedTextField(mask);
	JLabel label = new JLabel(".");
	JFormattedTextField formattedTextField_2 = new JFormattedTextField(mask);
	JLabel label_1 = new JLabel(".");
	JFormattedTextField formattedTextField_3 = new JFormattedTextField(mask);
	JLabel label_2 = new JLabel(".");
	JFormattedTextField formattedTextField_4 = new JFormattedTextField(mask);
	JLabel label_3 = new JLabel("/");
	JFormattedTextField formattedTextField_5 = new JFormattedTextField(mask_1);
	JFormattedTextField formattedTextField_1 = new JFormattedTextField(mask);
	JLabel label_4 = new JLabel(".");
	JFormattedTextField formattedTextField_6 = new JFormattedTextField(mask);
	JLabel label_5 = new JLabel(".");
	JFormattedTextField formattedTextField_7 = new JFormattedTextField(mask);
	JLabel label_6 = new JLabel(".");
	JFormattedTextField formattedTextField_8 = new JFormattedTextField(mask);
	JLabel label_7 = new JLabel("/");
	JFormattedTextField formattedTextField_9 = new JFormattedTextField(mask_1);
	JFormattedTextField formattedTextField_10 = new JFormattedTextField(mask);
	JLabel label_8 = new JLabel(".");
	JFormattedTextField formattedTextField_11 = new JFormattedTextField(mask);
	JLabel label_9 = new JLabel(".");
	JFormattedTextField formattedTextField_12 = new JFormattedTextField(mask);
	JLabel label_10 = new JLabel(".");
	JFormattedTextField formattedTextField_13 = new JFormattedTextField(mask);
	JLabel label_11 = new JLabel("/");
	JFormattedTextField formattedTextField_14 = new JFormattedTextField(mask_1);
	JButton btnAadir = new JButton("Añadir");
	
	protected DHCPServer m_owner;
	int i = 1;
	private final JButton btnCheck = new JButton("Check");
	private final JButton btnCheck_1 = new JButton("Check");




	public MainUIFrame(DHCPServer owner) {


				try {
					jbIniti();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				m_owner = owner;
	}



	private void jbIniti() throws Exception  {
		//Reads DHCPBrain.pl
		String t1 = "consult('DHCPBrain.pl')";
		System.out.println(t1 + " " + (Query.hasSolution(t1) ? "succeeded" : "failed"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Simulador DHCP");
		
		
		lblConfiguracionDelServidor.setText("Configuracion del servidor");
		lblConfiguracionDelServidor.setBounds(12, 12, 191, 15);
		contentPane.add(lblConfiguracionDelServidor);
		

		lblDireccionDeRed.setText("Direccion de red");
		lblDireccionDeRed.setBounds(22, 39, 127, 15);
		contentPane.add(lblDireccionDeRed);
		
		mask.setMask("###");
		mask.setPlaceholderCharacter('_');
		mask_1.setMask("##");
		mask_1.setPlaceholderCharacter('_');
		
		
		lblDireccionDelServidor.setText("Direccion del servidor");
		lblDireccionDelServidor.setBounds(15, 63, 160, 15);
		contentPane.add(lblDireccionDelServidor);
		
		
		
		lblPool.setText("Pool de direcciones");
		lblPool.setBounds(22, 93, 148, 15);
		contentPane.add(lblPool);
		
		
		lblConfiguracionDeLa.setText("Configuracion de la simulacion");
		lblConfiguracionDeLa.setBounds(12, 127, 223, 15);
		contentPane.add(lblConfiguracionDeLa);
		
		
		lblNumeroDeHosts.setText("Numero de Hosts");
		lblNumeroDeHosts.setBounds(26, 154, 127, 15);
		contentPane.add(lblNumeroDeHosts);
		
		
		slider_1.setBounds(213, 153, 160, 16);
		contentPane.add(slider_1);
		slider_1.setValue(1);
		slider_1.setMaximum(253);
		slider_1.addChangeListener(new ChangeListener () {
			public void stateChanged(ChangeEvent e) {
				slider_hosts_stateChanged(e);
			}
		});
		
		
		btnIniciar.setText("Iniciar");
		btnIniciar.setBounds(86, 195, 117, 25);
		contentPane.add(btnIniciar);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIniciar_actionPerformed(e);
			}
		});
		
		
		btnDetener.setText("Detener");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetener_actionPerformed(e);
			}
		});
		btnDetener.setBounds(332, 195, 117, 25);
		contentPane.add(btnDetener);
		
		
		lblnumHosts.setText("1");
		lblnumHosts.setBounds(375, 154, 70, 15);
		contentPane.add(lblnumHosts);
		

		formattedTextField.setBounds(193, 39, 28, 17);
		contentPane.add(formattedTextField);
		
		
		label.setBounds(228, 41, 15, 15);
		contentPane.add(label);
		
		formattedTextField_2.setBounds(239, 39, 28, 17);
		contentPane.add(formattedTextField_2);
		
		label_1.setBounds(274, 41, 15, 15);
		contentPane.add(label_1);
		
		formattedTextField_3.setBounds(286, 39, 28, 17);
		contentPane.add(formattedTextField_3);
		
		label_2.setBounds(321, 43, 15, 15);
		contentPane.add(label_2);
		
		formattedTextField_4.setBounds(332, 39, 28, 17);
		contentPane.add(formattedTextField_4);
		
		label_3.setBounds(367, 41, 15, 15);
		contentPane.add(label_3);
		
		formattedTextField_5.setBounds(378, 39, 20, 17);
		contentPane.add(formattedTextField_5);
		//int foo = Integer.parseInt(formattedTextField_5.getText());
		//int fo = 2^(32-foo);
		
		formattedTextField_1.setBounds(193, 62, 28, 17);
		contentPane.add(formattedTextField_1);
		
		label_4.setBounds(228, 64, 15, 15);
		contentPane.add(label_4);
		
		formattedTextField_6.setBounds(239, 62, 28, 17);
		contentPane.add(formattedTextField_6);
		
		label_5.setBounds(274, 64, 15, 15);
		contentPane.add(label_5);
		
		formattedTextField_7.setBounds(286, 62, 28, 17);
		contentPane.add(formattedTextField_7);
		
		label_6.setBounds(321, 66, 15, 15);
		contentPane.add(label_6);
		
		formattedTextField_8.setBounds(332, 62, 28, 17);
		contentPane.add(formattedTextField_8);
		
		label_7.setBounds(367, 64, 15, 15);
		contentPane.add(label_7);
		
		formattedTextField_9.setBounds(378, 62, 20, 17);
		contentPane.add(formattedTextField_9);
		

		formattedTextField_10.setBounds(193, 89, 28, 17);
		contentPane.add(formattedTextField_10);
		
		
		label_8.setBounds(228, 91, 15, 15);
		contentPane.add(label_8);
		
		formattedTextField_11.setBounds(239, 89, 28, 17);
		contentPane.add(formattedTextField_11);
		
		label_9.setBounds(274, 91, 15, 15);
		contentPane.add(label_9);
		
		formattedTextField_12.setBounds(286, 89, 28, 17);
		contentPane.add(formattedTextField_12);
		
		label_10.setBounds(321, 93, 15, 15);
		contentPane.add(label_10);
		
		formattedTextField_13.setBounds(332, 89, 28, 17);
		contentPane.add(formattedTextField_13);
		
		label_11.setBounds(367, 91, 15, 15);
		contentPane.add(label_11);
		
		formattedTextField_14.setBounds(378, 89, 20, 17);
		contentPane.add(formattedTextField_14);

		
		btnAadir.setBounds(410, 83, 80, 25);
		contentPane.add(btnAadir);
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAadir_actionPerformed(e);
			}
		});
		
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btncheck_actionPerformed(e);
			}
		});
		btnCheck.setBounds(410, 34, 80, 25);
		
		contentPane.add(btnCheck);
		btnCheck_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheck_1_actionPerformed(e);
			}
		});
		btnCheck_1.setBounds(410, 58, 80, 25);
		
		contentPane.add(btnCheck_1);
	}
	
	void slider_hosts_stateChanged(ChangeEvent e) {
		lblnumHosts.setText(Integer.toString( slider_1.getValue()));
	}
	
	void btnIniciar_actionPerformed(ActionEvent e){
		// add a behaviour to the host to start the conversation going
		m_owner.addBehaviour( new OneShotBehaviour() {

								public void action() {
	                              	((DHCPServer) myAgent).inviteHosts( slider_1.getValue());
	                          	}
	                      	} );
	}
	
	
	void btnDetener_actionPerformed(ActionEvent e) {
	// add a behaviour to the host to end the party
	m_owner.addBehaviour( new OneShotBehaviour() {
                          	public void action() {
                              	((DHCPServer) myAgent).takeDown();
                          	}
                      	} );
	}
	
	void btncheck_actionPerformed(ActionEvent e){

		
		String A= formattedTextField.getText();
		String B= formattedTextField_2.getText();
		String C= formattedTextField_3.getText();
		String D= formattedTextField_4.getText();
		String E= formattedTextField_5.getText();
		String F= A+","+B+","+C+","+D+","+E;
		
		String t1 = "netAdd("+F+")";
		
		
		Query q1 = new Query(t1);
		
		if(q1.hasSolution()){
			
			System.out.println("La direccion de red es valida");	
			formattedTextField.setEnabled( false );
			formattedTextField_2.setEnabled( false );
			formattedTextField_3.setEnabled( false );
			formattedTextField_4.setEnabled( false );
			formattedTextField_5.setEnabled( false );
			formattedTextField_1.setText(A);
			formattedTextField_6.setText(B);
			formattedTextField_7.setText(C);
			formattedTextField_9.setText(E);
			formattedTextField_1.setEnabled(false);
			formattedTextField_6.setEnabled(false);
			formattedTextField_7.setEnabled(false);
			formattedTextField_9.setEnabled(false);
			formattedTextField_10.setText(A);
			formattedTextField_11.setText(B);
			formattedTextField_12.setText(C);
			formattedTextField_14.setText(E);
			formattedTextField_10.setEnabled(false);
			formattedTextField_11.setEnabled(false);
			formattedTextField_12.setEnabled(false);
			formattedTextField_14.setEnabled(false);

			
		}
		else{
			
			System.out.println("La direccion de red no es valida");
			
		}
	}
	
	void btnCheck_1_actionPerformed(ActionEvent e){

		
		String A= formattedTextField_1.getText();
		String B= formattedTextField_6.getText();
		String C= formattedTextField_7.getText();
		String D= formattedTextField_8.getText();
		String E= formattedTextField_9.getText();
		String F= A+","+B+","+C+","+D+","+E;
		
		String t3 = "serAdd("+F+")";
		
		//String t1 = "asserta(inUse("+F+"))";
		
		Query q2 = new Query(t3);
		
		if(q2.hasSolution()){
			
			System.out.println("La direccion del servidor es valida");
			
			//Query q4 = new Query(t1);
			//System.out.println(q4);
			
			formattedTextField_8.setEnabled(false);
	
		}
		else{
			
			System.out.println("La direccion del servidor no es valida");
			
		}
	}
	
	
	void btnAadir_actionPerformed(ActionEvent e){
		
		String A= formattedTextField_10.getText();
		String B= formattedTextField_11.getText();
		String C= formattedTextField_12.getText();
		String D= formattedTextField_13.getText();
		String E= formattedTextField_14.getText();
		String F= A+","+B+","+C+","+D+","+E;
		
		String t4 = "serAdd("+F+")";
		
		//String t3 = "asserta(inUse("+F+"))";
		
		Query q3 = new Query(t4);
				if(q3.hasSolution()){
					
					//Query q1 = new Query(t3);
					//System.out.println(q1);

			try {
				
				String addipool = A+"."+B+"."+C+"."+D+"/"+E;
				m_owner.updateCatalogue(new jpl.Integer(i),addipool);
				formattedTextField_10.resetKeyboardActions();
				i++;
			}
			catch (Exception ev) {
				JOptionPane.showMessageDialog(MainUIFrame.this, "Invalid values. "+ev.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
			}


			
		}
		else{
			System.out.println("La direccion que ha intentado añadir al pool no es valida");
			
		}
		

	}

}
