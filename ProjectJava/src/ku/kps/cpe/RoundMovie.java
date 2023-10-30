package ku.kps.cpe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

public class RoundMovie implements ActionListener{
	ImageIcon OriginalIcon = new ImageIcon();
	JFrame RoundMovie = new JFrame();
	JButton botIcon,backButton ;
	JButton[] showTimeBot;
	ImageIcon icon;
	JPanel Round = new JPanel();

	String MovieName;
	Boolean checkRound = false;
	String fileMovieName;
	String[] showTime = new String[100];
	int amountShowTime = -1;
	Color frameBag = new Color(229, 127, 127); 
	
	 int imageWidth = 250;
     int imageHeight = 400;
     
     File ShowtimeFile;
    
	
	
	public RoundMovie(String movie, String fileMovieName) throws IOException
	{	//System.out.println(movie);
		this.fileMovieName = fileMovieName;
		String[] text = movie.split("\\.");
		MovieName = text[0];
		showTimeBot = new JButton[100];
		System.out.println(MovieName);
		
		RoundMovie.setResizable(false);
		RoundMovie.setSize(800, 1000);
		RoundMovie.setLayout(null);
		//RoundMovie.setExtendedState(JFrame.MAXIMIZED_BOTH);
		RoundMovie.getContentPane().setBackground(Color.lightGray);
		
		
		Round.setLayout(null);
		Round.setBackground(frameBag);
		Round.setBounds(30, 120, 720, 500);
		
		
	
		
		OriginalIcon = new ImageIcon("MoviePoster//"+movie+".jpg");
		Image scaledImage = OriginalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
		icon = new ImageIcon(scaledImage); 
		botIcon = new JButton(icon);
		backButton = new JButton("Back");
		
		//Round.add(botIcon);
		botIcon.setBounds(50, 170, imageWidth, imageHeight);
		backButton.setBounds(440, 400, 120, 50);
		backButton.addActionListener(this);
		Round.add(backButton);
		/*Dimension botSize = new Dimension(200, 350);
		botIcon.setPreferredSize(botSize);*/
		
		RoundMovie.getContentPane().add(botIcon);
		RoundMovie.getContentPane().add(Round);
		
		
		Reader read = new FileReader(fileMovieName);
		BufferedReader br = new BufferedReader(read);
		
		
		
		
		
		int x = 300;
		int y = 50;
		int nextLine = 0;
		String line;
		
		while((line = br.readLine())!= null)
		{	
			
			
		
			if(line.equals(MovieName+" Round"))
			{
				checkRound = true ;
				continue;
			}
			
			else if(line.contains("----"))
			checkRound = false;
			
			if(checkRound)
			{	
				if(nextLine == 3)
				{	x = 300;
					y+=70;
					nextLine=0;
				}
				
				
				showTimeBot[++amountShowTime] = new JButton(line);
				
				showTime[amountShowTime] = line;
				
				showTimeBot[amountShowTime].setBounds(x, y, 120, 50);
				Round.add(showTimeBot[amountShowTime]);
				showTimeBot[amountShowTime].addActionListener(this);
				x+=140; 
				nextLine++;
				
				//System.out.println(line);
				System.out.println(showTime[amountShowTime]);
			}
			
			
		
			
			
		}
		
		
	
		//west.getContentPane().add(west,BorderLayout.WEST);
		//RoundMovie.add(Round);
		//Round.add(botIcon);
		int i;
		ShowtimeFile = new File(MovieName+"Seated.txt");
		 Writer write = new FileWriter(ShowtimeFile);
		 BufferedWriter writer = new BufferedWriter(write); 
		
		
		if(ShowtimeFile.length()==0)
		{	
			
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAA");
			if(ShowtimeFile.length()==0)
			{	
				writer.write(MovieName);
			}
			for(i = 0 ; i <= amountShowTime ; i++)
			{
				writer.write("\n"+showTime[i]+"\n");
				writer.write("----------------------");
			}
			
			writer.close();
		}
		
		
		
		
		
		
		
		RoundMovie.setVisible(true);
		
		RoundMovie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b =(JButton) e.getSource();
		
		if(b != backButton)
			new MovieSeat(MovieName,fileMovieName);
		else  
			{	
				try {
				new MainPage();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Hello");
				}
			}
			
		RoundMovie.dispose();
		
	}
}
