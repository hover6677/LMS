package UI;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class ReportUI extends JPanel {
	private ArrayList<String> labels;
	private ArrayList<ArrayList<String>> data;
	private JTable table_1;
	public ReportUI() {
		labels = new ArrayList<String>();
		data = new ArrayList<ArrayList<String>>();
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(12, 45, 673, 281);
		
		
		JLabel lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblReport.setBounds(12, 0, 115, 50);
		add(lblReport);
		
		ReadLabels();
		ReadData();
		String[] labelArray = {"name","uid","temperature"};
		String[][] dataArray= {
				{"Tom","1313","36"},
				{"Allen","2351","37.5"}
		};
		panel_1.setLayout(null);
		
		add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 649, 255);
		panel_1.add(scrollPane);
		
		JTable table = new JTable(dataArray, labelArray);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBorder(new EmptyBorder(1, 1, 0, 0));
		
		
		
		
	}
	public void ReadLabels()
	{
		//Read from Database to labels
		labels.add("name");
		labels.add("uid");
		labels.add("temperature");
	}
	public void ReadData()
	{
		//Read Data from Database to data
		ArrayList<String> dataRecorder = new ArrayList<String>();
		dataRecorder.add("Tom");
		dataRecorder.add("1313");
		dataRecorder.add("36");
		data.add(dataRecorder);
		dataRecorder = new ArrayList<String>();
		dataRecorder.add("Allen");
		dataRecorder.add("2351");
		dataRecorder.add("37");
		
		
	}
}
