package ku.kps.cpe;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.*;

public class SelectMovie implements ActionListener {
	ImageIcon []icon;
	JFrame frame = new JFrame();
	JButton []bot;
	int amountMovie = -1;
	String data,movieName = "";
	//JTextField header = new JTextField("Movie Ticket");
	File Movie;
	String fileMovieName;
	JPanel movieList = new JPanel();
	public SelectMovie() throws IOException 
	{	frame.setLayout(new FlowLayout());
		Movie = new File(fileMovieName); 
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		icon = new ImageIcon[100];
		bot = new JButton[100];
		movieList.setSize(800, 800);
		fileMovieName = "MovieList.txt";
		Reader read = new FileReader(fileMovieName);
		BufferedReader br = new BufferedReader(read);
		frame.add(movieList);
		//frame.getContentPane().add(header,BorderLayout.NORTH);
		frame.getContentPane().add(movieList,BorderLayout.CENTER);
		movieList.setLayout(new GridLayout(3,3,3,3));
		
		if(Movie.length()!=0)
			System.out.println("Hello world");
		
		while((data = br.readLine())!= null)
		{	//System.out.println(movieName+"    "+data);
			if(data.equals(movieName+" Round"))
			{
				//System.out.println(movieName);
				 icon[++amountMovie] = new ImageIcon(movieName+".jpg"); 
				
				 bot[amountMovie] = new JButton(icon[amountMovie]);
				
				Dimension botSize = new Dimension(200, 350);
				
				bot[amountMovie].setPreferredSize(botSize);
				bot[amountMovie].addActionListener(this);
				movieList.add(bot[amountMovie]);
			
			}
			
            movieName = data;
            
		}
		read.close();
		
		frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b =(JButton) e.getSource();
		int i;
		for(i = 0 ; i <= amountMovie ; i++)
		if(b == bot[i])
		{	//System.out.println(i);
			String iconName = icon[i].getDescription();
			
				try {
					new RoundMovie(iconName,fileMovieName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			frame.dispose();
		}
		
		
	}
	
	
	
	
}
