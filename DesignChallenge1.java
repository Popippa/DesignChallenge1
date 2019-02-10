/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;


/**
 *
 * @author fredd
 * Christian Alcala & Joshua Nicholas
 * People who do codes wrong
 */

public class DesignChallenge1 {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	calendarView view = new calendarView();
    	calendarModel model = new calendarModel();
    	
        CalendarProgram cp = new CalendarProgram(view, model);
        cp.run();
    }
}

