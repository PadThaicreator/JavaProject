package ku.kps.cpe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MainPage implements ActionListener{
	static String fileMovieName="MovieList.txt";
	
	JButton[] MovieButton = new JButton[100];
	File MovieList;
	ImageIcon OriginalIcon = new ImageIcon();
	ImageIcon[]	MovieIcon = new ImageIcon[100];
	String[] MovieName = new String[100];
	 JFrame frame = new JFrame("Movie Ticket");
	int amountMovie = -1;
	String data,movie=" " ;
	
    public MainPage() throws IOException {
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 1000);
        //fixed Size
        frame.setResizable(false);
        MovieList = new File(fileMovieName);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,100));
        panel.setPreferredSize(new Dimension(600,2500));
        panel.setBackground(Color.LIGHT_GRAY);
        
        int imageWidth = 250;
        int imageHeight = 400;
        
        Reader read = new FileReader(MovieList);
		BufferedReader br = new BufferedReader(read);
        
		/*if(MovieList.length()!=0)
			System.out.println("Hello World");*/
			
		while((data = br.readLine())!= null)
		{	//System.out.println(movieName+"    "+data);
			if(data.contains("Round"))
			{	
				//System.out.println(movie);
				
				MovieName[++amountMovie]=movie;
				
		
				//System.out.println(MovieName[amountMovie]);
				//MovieIcon[++amountMovie] = new ImageIcon(movieName+".jpg"); 
				
				OriginalIcon = new ImageIcon(MovieName[amountMovie]+".jpg");
				Dimension botSize = new Dimension(imageWidth, imageHeight);
				

				
				
			

				Image scaledImage = OriginalIcon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
				
				
				MovieIcon[amountMovie] = new ImageIcon(scaledImage); 
			//	System.out.println(MovieIcon[amountMovie]);
			//	System.out.println("ASDASD");

				MovieButton[amountMovie] = new JButton(MovieIcon[amountMovie]);
				
				
				
				MovieButton[amountMovie].setPreferredSize(botSize);
				MovieButton[amountMovie].addActionListener(this);

				panel.add(MovieButton[amountMovie]);
			
			}
			
            movie = data;
            
		}
		read.close();
        
        

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane Panel2 = new JScrollPane(panel);
        
        
        
        
        
       
        // Make the vertical scrollbar always visible
        Panel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(Panel2);
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b =(JButton) e.getSource();
		int i;
		for(i = 0 ; i <= amountMovie ; i++)
		if(b == MovieButton[i])
		{	System.out.println(i);
			
			
			System.out.println(MovieName[i]);
			
				try {
					new RoundMovie(MovieName[i],fileMovieName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			frame.dispose();
		}
		
	}
}