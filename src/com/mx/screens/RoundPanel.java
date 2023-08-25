package com.mx.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/*
 * Clase auxiliar utilizada para poder crear JPanel con forma redondeada.
 */
public class RoundPanel extends JPanel{

	 @Override
	    protected void paintComponent(Graphics g) {
	        // Llamamos al método paintComponent de la clase padre
	        super.paintComponent(g);

	        // Creamos un objeto Graphics2D para tener más control sobre el dibujo
	        Graphics2D g2d = (Graphics2D) g.create();

	        // Creamos un objeto Shape redondo utilizando Ellipse2D
	        Shape roundShape = new Ellipse2D.Double(0, 0, getWidth(), getHeight());

	        // Rellenamos el panel con un color
	        g2d.setColor(getBackground());
	        g2d.fill(roundShape);

	        // Desechamos el objeto Graphics2D
	        g2d.dispose();
	    }

	    @Override
	    public boolean isOpaque() {
	        // Hacemos que el panel sea transparente
	        return false;
	    }
	
}
