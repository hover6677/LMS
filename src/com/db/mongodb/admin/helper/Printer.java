package com.db.mongodb.admin.helper;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import com.ui.admin.mainframe.AdminMainFrame;

public class Printer implements Printable {
    final Component comp;

    public Printer(Component comp){
        this.comp = comp;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page_index) 
            throws PrinterException {
        if (page_index > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = format.getImageableHeight();
        double pWidth = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
    /*public static void main(String args[]) {
    	AdminMainFrame yourComponent = new AdminMainFrame();
    	PrinterJob pjob = PrinterJob.getPrinterJob();
    	PageFormat preformat = pjob.defaultPage();
    	preformat.setOrientation(PageFormat.LANDSCAPE);
    	PageFormat postformat = pjob.pageDialog(preformat);
    	//If user does not hit cancel then print.
    	if (preformat != postformat) {
    	    //Set print component
    	    pjob.setPrintable(new Printer(yourComponent), postformat);
    	    if (pjob.printDialog()) {
    	        try {
					pjob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	}
    }*/
}
