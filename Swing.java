package app;

import java.awt.Color;

import javax.swing.*;

public class Swing {
                
	public static void main(String[] args) {
				
		// frame is a GUI window :
		
		JFrame frame = new JFrame(); // create a frame 
		
		frame.setSize(450,450); // set the window size width,hight
		
		frame.setTitle("my app");// set the window title 
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);// set the red x button to close | .DO_NOTHING_ON_CLOSE | .HIDE_ON_CLOSE
		                                                   
		
		frame.getContentPane().setBackground(Color.green);// set the window bcg color for custom color .setBackground(new color(x,y,z))
		
		///////////////////////////////////////////////////////////////////////////
		
		//to import an img :
		
		ImageIcon img = new ImageIcon("link");
		
		frame.setIconImage(img.getImage()); //to make an icon on the window
		
		frame.setVisible(true); // make the frame visible always in the bottom of the code !!

		////////////////////////////////////////////////////////////////////////////
		
		// labels to display texts , image :
		
		JLabel label = new JLabel();// create a label
		
		label.setText("the text of label");
		
		label.setIcon(img);// fill the label with img
		
		frame.add(label);// to add the label to the frame
		
		
		
		
		
		
		
	}

}
