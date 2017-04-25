package fr.enac.iessa16.cablage.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.enac.iessa16.cablage.controller.Controleur;

public class BarreOutils extends JToolBar{
	private JButton nouveau;
	private JButton enregistrer;
	
	public BarreOutils(Controleur controleur) {
		super();
		// TODO Auto-generated constructor stub
		 
        setOrientation(JToolBar.HORIZONTAL);
       ImageIcon iconnouveau = new ImageIcon("/home/eleve/IESSA/hedidira/git/applicationCablage/cablage/CablageGit/file/new.png");
        this.nouveau = new JButton(iconnouveau);
        //newbutton.setPreferredSize(new Dimension(25,25));
        
        
        /*     savebutton = new JButton(new ImageIcon("images/Save.gif"));
        deletebutton = new JButton(new ImageIcon("images/Delete.gif"));
        refreshbutton = new JButton(new ImageIcon("images/Delete.gif"));
        searchbutton = new JButton(new ImageIcon("images/Find.png"));
        preferencebutton = new JButton(new ImageIcon("images/Preferences.gif"));
        aboutbutton = new JButton(new ImageIcon("images/About.gif"));
      */  //setRollover(true);
	    nouveau.setToolTipText("Nouveau Graphe Vide");
	    nouveau.setActionCommand("GrapheVide");
	    nouveau.addActionListener(controleur.getControleMenu());
	    this.add(nouveau);
	   
	    
        /*savebutton.setToolTipText("Save");
        this.add(savebutton);
        deletebutton.setToolTipText("Delete");
        add(deletebutton);
        refreshbutton.setToolTipText("Refresh");
        add(refreshbutton);
        searchbutton.setToolTipText("Search..");
        add(searchbutton);
        preferencebutton.setToolTipText("Changer paramètres de connection");
        add(preferencebutton);
        aboutbutton.setToolTipText("About this..");
        add(aboutbutton);
 
        GroupLayout toolbarlayout = new GroupLayout(this);
                this.setLayout(toolbarlayout);
                toolbarlayout.setHorizontalGroup(
                    toolbarlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(toolbarlayout.createSequentialGroup()
                        .addComponent(this, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(300, Short.MAX_VALUE))
                );
                toolbarlayout.setVerticalGroup(
                    toolbarlayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(toolbarlayout.createSequentialGroup()
                        .addComponent(this, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(254, Short.MAX_VALUE))
                );
 
 
 
    }
		
		
		
		
	}



	private JButton nouveau;
	private JButton enregistrerSous;
	
	
		 
	   // JButton newbutton, savebutton, deletebutton, refreshbutton, searchbutton, preferencebutton, exitbutton, aboutbutton;
	 
	    makeToolBar(){
	*/
	
	
	

}}
