package org.intracubedesigner.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Aaron McClure
 * IntraCube Coordinate Designer V1.0
 *
 */
public class Client extends Thread {

	private static JLabel lblPos = new JLabel(), lblAbsPos = new JLabel(), lblSize = new JLabel();
	private static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();

	public static void main(String[] args) {
		lblPos.setForeground(Color.white);
		lblPos.setFont(new Font("sansserif", Font.BOLD, 32));

		lblAbsPos.setForeground(Color.cyan);
		lblAbsPos.setFont(new Font("sansserif", Font.BOLD, 32));

		lblSize.setForeground(Color.yellow);
		lblSize.setFont(new Font("sansserif", Font.BOLD, 32));

		frame.setSize(new Dimension(750,700));
		frame.add(panel);

		panel.setBackground(Color.black);
		panel.add(lblPos);
		panel.add(lblAbsPos);
		panel.add(lblSize);

		frame.setVisible(true);
		frame.setTitle("IntraCube Coordinate Designer V1.0");

		new Client().start();
	}

	public Point getMousePos(boolean abs){
		if (abs){
			return new Point(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
		}else{
			return new Point(MouseInfo.getPointerInfo().getLocation().x-panel.getLocationOnScreen().x, MouseInfo.getPointerInfo().getLocation().y-panel.getLocationOnScreen().y);
		}
	}

	public Dimension getFrameSize(){
		return frame.getSize();
	}

	@Override
	public void run(){
		while (frame.isVisible()){
			try{
				lblPos.setText("Mouse location: " + getMousePos(false).x + "," + getMousePos(false).y + "   ");
				lblAbsPos.setText("Mouse location (absolute): " + getMousePos(true).x + "," + getMousePos(true).y + "   ");
				lblSize.setText("Panel size: " + getFrameSize().width + "," + getFrameSize().height);
			}catch (Exception ex){
				break;
			}
		}
		System.exit(0);
	}
}
