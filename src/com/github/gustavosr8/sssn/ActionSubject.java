package com.github.gustavosr8.sssn;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ActionSubject {
	
	private ArrayList<ActionListener> listenerArr = new ArrayList<ActionListener>();
	
	
	public void attach(ActionListener listener) {
		listenerArr.add(listener);
	}
	
	public void detach(ActionListener listener) {
		listenerArr.remove(listener);
	}
	
	public void notify(ActionEvent e) {
		for( ActionListener al: listenerArr) {
			al.actionPerformed(e);
		}
	}

}
