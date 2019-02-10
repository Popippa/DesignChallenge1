package designchallenge1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class calendarView {
	
	public calendarView() {
		
	}
	
	public calendarView(CalendarProgram program) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {}
                
		program.setFrmMain(new JFrame ("Calendar Application"));
		program.getFrmMain().setSize(660, 750);
		program.setPane(program.getFrmMain().getContentPane());
		program.getPane().setLayout(null);
		program.getFrmMain().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		program.setMonthLabel(new JLabel ("January"));
		program.setYearLabel(new JLabel ("Change year:"));
		program.setCmbYear(new JComboBox());
		program.setBtnPrev(new JButton ("<<"));
		program.setBtnNext(new JButton (">>"));
		program.setBtnwrite(new JButton("Set Event/Holiday"));
		program.setModelCalendarTable(new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        });
                
        program.setCalendarTable(new JTable(program.getModelCalendarTable()));
        program.getCalendarTable().addMouseListener(new MouseAdapter(){  
            public void mouseClicked(MouseEvent evt){  
                int col = program.getCalendarTable().getSelectedColumn();  
                int row = program.getCalendarTable().getSelectedRow();
            }
         });
                
        program.setScrollCalendarTable(new JScrollPane(program.getCalendarTable()));
        program.setCalendarPanel(new JPanel(null));

        program.getCalendarPanel().setBorder(BorderFactory.createTitledBorder("Calendar"));
		
        program.getPane().add(program.getCalendarPanel());
        program.getCalendarPanel().add(program.getMonthLabel());
        program.getCalendarPanel().add(program.getYearLabel());
        program.getCalendarPanel().add(program.getBtnwrite());
        program.getCalendarPanel().add(program.getCmbYear());
        program.getCalendarPanel().add(program.getBtnPrev());
        program.getCalendarPanel().add(program.getBtnNext());
        program.getCalendarPanel().add(program.getScrollCalendarTable());
		
        program.getCalendarPanel().setBounds(0, 0, 640, 670);
        program.getMonthLabel().setBounds(320-program.getMonthLabel().getPreferredSize().width/2, 50, 200, 50);
        program.getYearLabel().setBounds(260, 610, 160, 40);
        program.getCmbYear().setBounds(460, 610, 160, 40);
        program.getBtnwrite().setBounds(20, 610, 160, 40);
        program.getBtnPrev().setBounds(20, 50, 100, 50);
        program.getBtnNext().setBounds(520, 50, 100, 50);
        program.getScrollCalendarTable().setBounds(20, 100, 600, 500);
                
        program.getFrmMain().setResizable(false);
        program.getFrmMain().setVisible(true);
		
		GregorianCalendar cal = new GregorianCalendar();
		program.setDayBound(cal.get(GregorianCalendar.DAY_OF_MONTH));
		program.setMonthBound(cal.get(GregorianCalendar.MONTH));
		program.setYearBound(cal.get(GregorianCalendar.YEAR)); 
		program.setMonthToday(program.getMonthBound());
		program.setYearToday(program.getYearBound());
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			program.getModelCalendarTable().addColumn(headers[i]);
		}
		
		program.getCalendarTable().getParent().setBackground(program.getCalendarTable().getBackground()); //Set background

		program.getCalendarTable().getTableHeader().setResizingAllowed(false);
		program.getCalendarTable().getTableHeader().setReorderingAllowed(false);

		program.getCalendarTable().setColumnSelectionAllowed(true);
		program.getCalendarTable().setRowSelectionAllowed(true);
		program.getCalendarTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		program.getCalendarTable().setRowHeight(76);
		program.getModelCalendarTable().setColumnCount(7);
		program.getModelCalendarTable().setRowCount(6);
		
		for(int i = program.getYearBound()-100; i <= program.getYearBound()+100; i++){
			program.getCmbYear().addItem(String.valueOf(i));
		}
		
		program.getBtnPrev().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (program.getMonthToday() == 0){
					program.setMonthToday(11);
					program.setYearToday(program.getYearToday() - 1);
				}
				else{
					program.setMonthToday(program.getMonthToday() - 1);
				}
				program.getModel().refreshCalendar(program, program.getMonthToday(), program.getYearToday());
				program.getCmbYear().setSelectedItem(""+program.getYearToday());
			}
		});
		
		program.getBtnNext().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (program.getMonthToday() == 11){
					program.setMonthToday(0);
					program.setYearToday(program.getYearToday() + 1);
				}
				else{
					program.setMonthToday(program.getMonthToday() + 1);
				}
				program.getModel().refreshCalendar(program, program.getMonthToday(), program.getYearToday());
				program.getCmbYear().setSelectedItem(""+program.getYearToday());
			}
		});

		program.getBtnwrite().addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
					program.getEvents().setVisible(true);
				}
		});

		program.getCmbYear().addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (program.getCmbYear().getSelectedItem() != null){
					String b = program.getCmbYear().getSelectedItem().toString();
					program.setYearToday(Integer.parseInt(b));
					program.getModel().refreshCalendar(program, program.getMonthToday(), program.getYearToday());
				}
			}
		});
	}
}