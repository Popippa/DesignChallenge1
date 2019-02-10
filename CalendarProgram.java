/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

/**
 *
 * @author Arturo III
 * A person who f*cks up codes
 */

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import fileWriteRead.FileWR;
import fileWriteRead.csvWR;
import fileWriteRead.psvWR;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarProgram{
	
	private calendarView view;
	private calendarModel model;
	
        /**** Day Components ****/
	private int yearBound, monthBound, dayBound, yearToday, monthToday;

        /**** Swing Components ****/
	private JLabel monthLabel, yearLabel;
	private JButton btnPrev, btnNext, btnwrite;
	private JComboBox cmbYear;
	private JFrame frmMain;
	private Container pane;
	private JScrollPane scrollCalendarTable;
	private JPanel calendarPanel;
	//private addEvents Events = new addEvents();
        
        /**** Calendar Table Components ***/
	private JTable calendarTable;
	private DefaultTableModel modelCalendarTable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
	public CalendarProgram(calendarView view, calendarModel model){
		this.view = view;
		this.model = model;
	}
	
	public void run() {
		view = new calendarView(this);
		model.refreshCalendar(this, monthBound, yearBound);
		initNotification();
		getCmbYear().setSelectedItem(""+yearToday);
	}
	
	public void initNotification() {
		int i, som, nod;
		ArrayList<String[]> events = new ArrayList<String[]>();
    	ArrayList<String[]> holidays = new ArrayList<String[]>();
    	events=new psvWR().getData();
    	holidays=new csvWR().getData();
    	DateFormat v = new SimpleDateFormat("MM/dd/yyyy");
    	Date d = new Date();
    	String[] sndate= v.format(d).split("[/]");
    	GregorianCalendar cal = new GregorianCalendar(yearToday, monthToday, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		for(i=0; i<events.size();i++) {
			int tyear=Integer.parseInt(events.get(i)[2].replaceAll("\\s", ""));
			int tmonth=Integer.parseInt(events.get(i)[0])-1;
			int tday=Integer.parseInt(events.get(i)[1]);
			if(yearToday == tyear)
				if(monthToday == tmonth) {
					System.out.println("Event: "+tyear+", "+tmonth+", "+tday);
					System.out.println("Event Parse: "+Integer.parseInt(sndate[2])+", "+Integer.parseInt(sndate[0])+", "+Integer.parseInt(sndate[1]));
					if(tmonth == Integer.parseInt(sndate[0])-1 && tday == Integer.parseInt(sndate[1]) && yearToday == Integer.parseInt(sndate[2])) 
						model.setNotification(events, i, tmonth, tday, tyear);
				}
		}
		model.removeTag(events, this, yearToday, monthToday);
		for(i=0; i<holidays.size();i++) {
			int tyear=Integer.parseInt(holidays.get(i)[2].replaceAll("\\s", ""));
			int tmonth=Integer.parseInt(holidays.get(i)[0])-1;
			int tday=Integer.parseInt(holidays.get(i)[1]);
			if(yearToday >= tyear)
				if(monthToday == tmonth) {
					System.out.println("Holiday: "+tyear+", "+tmonth+", "+tday);
					System.out.println("Holiday Parse: "+Integer.parseInt(sndate[2])+", "+Integer.parseInt(sndate[0])+", "+Integer.parseInt(sndate[1]));
					if(tmonth == Integer.parseInt(sndate[0])-1 && tday == Integer.parseInt(sndate[1]) && yearToday <= Integer.parseInt(sndate[2])) {
						model.setNotification(holidays, i, tmonth, tday, yearToday);
					}
				}
		}
		model.removeTag(holidays, this, yearToday, monthToday);
	}	
	
///////////////////////////////////////////////////////////////////////////////////////////////////	

	public int getYearBound() {
		return yearBound;
	}
	public void setYearBound(int yearBound) {
		this.yearBound = yearBound;
	}
	public int getMonthBound() {
		return monthBound;
	}
	public void setMonthBound(int monthBound) {
		this.monthBound = monthBound;
	}
	public int getDayBound() {
		return dayBound;
	}
	public void setDayBound(int dayBound) {
		this.dayBound = dayBound;
	}
	public int getYearToday() {
		return yearToday;
	}
	public void setYearToday(int yearToday) {
		this.yearToday = yearToday;
	}
	public int getMonthToday() {
		return monthToday;
	}
	public void setMonthToday(int monthToday) {
		this.monthToday = monthToday;
	}
	public JLabel getMonthLabel() {
		return monthLabel;
	}
	public void setMonthLabel(JLabel monthLabel) {
		this.monthLabel = monthLabel;
	}
	public JLabel getYearLabel() {
		return yearLabel;
	}
	public void setYearLabel(JLabel yearLabel) {
		this.yearLabel = yearLabel;
	}
	public JButton getBtnPrev() {
		return btnPrev;
	}
	public void setBtnPrev(JButton btnPrev) {
		this.btnPrev = btnPrev;
	}
	public JButton getBtnNext() {
		return btnNext;
	}
	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}
	public JButton getBtnwrite() {
		return btnwrite;
	}
	public void setBtnwrite(JButton btnwrite) {
		this.btnwrite = btnwrite;
	}
	public JComboBox getCmbYear() {
		return cmbYear;
	}
	public void setCmbYear(JComboBox cmbYear) {
		this.cmbYear = cmbYear;
	}
	public JFrame getFrmMain() {
		return frmMain;
	}
	public void setFrmMain(JFrame frmMain) {
		this.frmMain = frmMain;
	}
	public Container getPane() {
		return pane;
	}
	public void setPane(Container pane) {
		this.pane = pane;
	}
	public JScrollPane getScrollCalendarTable() {
		return scrollCalendarTable;
	}
	public void setScrollCalendarTable(JScrollPane scrollCalendarTable) {
		this.scrollCalendarTable = scrollCalendarTable;
	}
	public JPanel getCalendarPanel() {
		return calendarPanel;
	}
	public void setCalendarPanel(JPanel calendarPanel) {
		this.calendarPanel = calendarPanel;
	}
	public addEvents getEvents() {
		return new addEvents(this);
	}
	/*public void setEvents(addEvents events) {
		Events = events;
	}*/
	public JTable getCalendarTable() {
		return calendarTable;
	}
	public void setCalendarTable(JTable calendarTable) {
		this.calendarTable = calendarTable;
	}
	public DefaultTableModel getModelCalendarTable() {
		return modelCalendarTable;
	}
	public void setModelCalendarTable(DefaultTableModel modelCalendarTable) {
		this.modelCalendarTable = modelCalendarTable;
	}

	public calendarView getView() {
		return view;
	}

	public void setView(calendarView view) {
		this.view = view;
	}

	public calendarModel getModel() {
		return model;
	}

	public void setModel(calendarModel model) {
		this.model = model;
	}
}