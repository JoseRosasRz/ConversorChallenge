package com.mx.screens;

import java.awt.Component;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/*
 * Clase que ayuda a renderizar los iconos dentro de JComboBox
 */
public class JComboBoxRenderer extends JLabel implements ListCellRenderer{

	Hashtable<Object, ImageIcon> elementsList;
	
	
	public JComboBoxRenderer(Hashtable<Object, ImageIcon> elementsList) {
		this.elementsList = elementsList;
	}


	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setIcon(elementsList.get(value));
		setText(value.toString());
		return this;
	}
	


}
