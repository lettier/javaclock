/*

DAVID C LETTIER

(C) 2012.

http://www.lettier.com/

*/


import java.awt.*;
import java.lang.*;
import java.awt.geom.*;
import java.math.*;
import java.awt.event.*;


public class Clock extends Frame  {
	
	Clock( int hrs, int mns, int scnds) {
	
		hours = hrs;
		minutes = mns;
		seconds = scnds;
		
		javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
       	t.start();
	}

	public void paint(Graphics g) {
	
	
		Graphics2D ga = (Graphics2D)g;
		
		Insets insets = getInsets();
		
		int xCenter = getWidth() / 2;
		int yCenter = ( getHeight() / 2 ) + insets.top - insets.bottom;
		
		int hourRadius   = ( (getWidth()/2)-(insets.left+insets.right) ) - 40;
		int minuteRadius = ( (getWidth()/2)-(insets.left+insets.right) ) - 10;
		int secondRadius = ( (getWidth()/2)-(insets.left+insets.right) ) - 5;
		
		ga.setColor(Color.blue);
		
		ga.setStroke(new BasicStroke(3));
		
		ga.drawOval( insets.left, insets.top, getWidth()-(insets.right+insets.left), getHeight()-(insets.bottom+insets.top) );		
		
		Line2D hourHand = new Line2D.Double( xCenter, yCenter, xCenter+hourRadius*Math.cos( (double) Math.toRadians( (((hours*5)*6)-90) ) ),  yCenter+hourRadius*Math.sin( Math.toRadians( (((hours*5)*6)-90)) ) );
		
		Line2D minuteHand = new Line2D.Double( xCenter, yCenter, xCenter+minuteRadius*Math.cos( Math.toRadians( ((minutes*6)-90)) ),  yCenter+minuteRadius*Math.sin( Math.toRadians( ((minutes*6)-90)) ) );
		
		Line2D secondHand = new Line2D.Double( xCenter, yCenter, xCenter+secondRadius*Math.cos( Math.toRadians( ((seconds*6)-90)) ), yCenter+secondRadius*Math.sin( Math.toRadians( ((seconds*6)-90)) ) );
		
		ga.setColor(Color.green);
		
		ga.draw( hourHand   );
		
		ga.setColor(Color.black);
		
		ga.draw( minuteHand );
		
		ga.setColor(Color.red);
		
		ga.draw( secondHand );
		
		seconds = seconds + 1;
		
		if ( seconds > 60 )
		{
			seconds = 0;
			minutes = minutes + 1;
		}
		
		if ( minutes > 60 )
		{
			minutes = 0;
			hours = hours + 1;
		}
		
		if ( hours > 12 )
		{
			hours = 1;
		}
		
		setSize( 400 , 400 + insets.top + insets.bottom );
		
		if ( paintOnce )
		{
			 repaint();
			 paintOnce = false;
		}
	}	
	
	public static void main( String [] args )
	{
		int hours = Integer.parseInt( args[0] );
		int minutes = Integer.parseInt( args[1] );
		int seconds = Integer.parseInt( args[2] );
		
		Frame frm = new Clock( hours, minutes, seconds);

		frm.setSize( 400, 400 );
		frm.setVisible(true);
	}
	
	int hours,minutes,seconds;
	boolean paintOnce = true;
}
