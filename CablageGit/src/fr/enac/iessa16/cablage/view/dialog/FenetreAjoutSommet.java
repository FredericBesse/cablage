package fr.enac.iessa16.cablage.view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.enac.iessa16.cablage.model.Modele;

@SuppressWarnings("serial")
public class FenetreAjoutSommet extends JDialog implements ActionListener {

	/*
	 * Attributs
	 */
	private Modele modele;
	private JTextField nomEntree;
	private JTextField abscisseEntree;
	private JTextField ordonneeEntree;
	private double abscisse;
	private double ordonnee;

	public FenetreAjoutSommet(Modele monModel, double abscisse, double ordonnee) {

		super();
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
		this.modele = monModel;
		this.setTitle("Ajout Sommet");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.initComponent();
		this.pack();
		this.setVisible(true);
	}

	private void initComponent() {

		JPanel contenuSommet = new JPanel();
		contenuSommet.setLayout(new GridLayout(3, 2));
		JLabel nomSommet = new JLabel("Nom");
		this.nomEntree = new JTextField(10);
		nomSommet.setLabelFor(nomEntree);
		contenuSommet.add(nomSommet);
		contenuSommet.add(nomEntree);

		JLabel abscisse = new JLabel("Abscisse");
		abscisseEntree = new JTextField(10);
		abscisseEntree.setText(Double.toString(this.abscisse));
		abscisse.setLabelFor(abscisseEntree);
		contenuSommet.add(abscisse);
		contenuSommet.add(abscisseEntree);

		JLabel ordonnee = new JLabel("Ordonnée");
		ordonneeEntree = new JTextField(10);
		ordonneeEntree.setText(Double.toString(this.ordonnee));
		ordonnee.setLabelFor(ordonneeEntree);
		contenuSommet.add(ordonnee);
		contenuSommet.add(ordonneeEntree);

		this.add(contenuSommet, BorderLayout.CENTER);

		JPanel bouton = new JPanel();

		JButton ok = new JButton("ok");
		ok.addActionListener(this);
		bouton.add(ok);

		JButton annuler = new JButton("annuler");
		annuler.addActionListener(this);
		bouton.add(annuler);

		this.add(bouton, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String nomSommet;
		double abscisseSommet;
		double ordonneeSommet;
		String s = e.getActionCommand();

		// si bouton ok
		if (s.equals("ok")) {
			// FIXME gerer les exceptions format

			nomSommet = nomEntree.getText();
			abscisseSommet = Double.parseDouble(abscisseEntree.getText());
			ordonneeSommet = Double.parseDouble(ordonneeEntree.getText());
			modele.ajouterSommetEffectif(abscisseSommet, ordonneeSommet, nomSommet);
			dispose();
		}
		
		// si bouton annuler
		if (s.equals("annuler")) {
			this.dispose();
		}
	}
}
