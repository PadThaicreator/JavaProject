package ku.kps.cpe;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class MovieSeat extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private int[][] seats;
    private int size = 12;
    private String MovieFile , movie;
    
    JLabel[] row = new JLabel[size];
    JLabel deluxe = new JLabel("Deluxe Seat");
    JButton backButton ;
    int x = 40;
	int y = 30;
	int nextLine = 0;
    
    
    
    public MovieSeat(String title, String MovieFile) {
        buttons = new JButton[size][size];
        seats = new int[size][size];
        
        this.movie = title;
        this.MovieFile = MovieFile;
        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1000);
        //setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        //setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        MouseClicked mouseListener = new MouseClicked();
        panel.setPreferredSize(new Dimension(800,700));
        //panel.setBackground(Color.LIGHT_GRAY);
        
        row[0] = new JLabel("A");
        row[0].setBounds(15, 30, 40, 40);
        row[0].setFont(new Font(null,Font.PLAIN,20));
        
        panel.add(row[0]);
        for (int i = 0; i < size; ) {
            for (int j = 0; j < size; j++) {
                
            	
            	
            	buttons[i][j] = new JButton();
                buttons[i][j].addMouseListener(mouseListener);
               // buttons[i][j].setPreferredSize(new Dimension(35,25));
                
              
                
                buttons[i][j].setBounds(x, y, 40, 40);
                
                x+=55;
                if(i<size-5) {
                	  buttons[i][j].setBackground(Color.RED); 
                }
                else {
                	 buttons[i][j].setBackground(Color.MAGENTA); 
                }
                
                panel.add(buttons[i][j]); 
                add(panel, BorderLayout.CENTER);
              
                
                if(j==size/2-1)
                	x+=50;
                if(j==size-1)
                {	
                	x = 40;
                	y +=45;
                	
                	
                }
                
                
            }
            i++;
            
            if( i < size)
            {	
            	
            	char temp = (char) ('A'+i);
            	
            	
            	 row[i] = new JLabel(temp+"");
            	 row[i].setFont(new Font(null,Font.PLAIN,20));
             	row[i].setBounds(15, y, 50, 50);
             	panel.add(row[i]);
            }
           
        }

        JPanel buttonPanel = new JPanel();
        JButton buyTicketButton = new JButton("Buy Ticket");
         backButton = new JButton("Back");
        
         backButton.addActionListener(this);
       
        

        buyTicketButton.addActionListener(new ActionListener() {
            

			@Override
            public void actionPerformed(ActionEvent e) {
                String nofi = new String(" ");
                boolean seatsSelected = false;
                
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (seats[i][j] == 1) {
                        	char S = (char) ('A'+i);
                        	nofi+=" ("+S+","+(j+1)+")";
                            //selectedSeats.append("(").append(i + 1).append(",").append(j + 1).append(") ");
                            seatsSelected = true;
                        }
                    }
                }

                if (seatsSelected) {
                    // Display the selected seats in a JOptionPane dialog
                    JOptionPane.showMessageDialog(null, "Selected Seats :"+nofi);
                } else {
                    // No seats selected, show a message
                    JOptionPane.showMessageDialog(null, "No seats selected.");
                }
            }
        });

        
        buttonPanel.add(backButton);
        buttonPanel.add(buyTicketButton);
        add(buttonPanel, BorderLayout.SOUTH);
 
        setVisible(true);
    }

    private class MouseClicked extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JButton button = (JButton) e.getSource();
            Icon seatIcon = new ImageIcon("Seated.png");
            if (e.getButton() == MouseEvent.BUTTON1) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (button == buttons[i][j] && buttons[i][j].isEnabled()) {
                            if (seats[i][j] == 1) {
                                seats[i][j] = 0;
                                buttons[i][j].setIcon(null);
                               if(i<size-5) {
                              	 buttons[i][j].setBackground(Color.RED); 
                              }
                              else {
                              	 buttons[i][j].setBackground(Color.MAGENTA); 
                              }
                            } else {
                                seats[i][j] = 1;
                                buttons[i][j].setIcon(seatIcon);
                                
                                 button.setBackground(Color.WHITE); // Change the button color to white
                            return;
                            }
                           
                        }
                    }
                }
            }
        }
    }

    
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		// TODO Auto-generated method stub
		if(b == backButton )
		try {
			new RoundMovie(movie, MovieFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}