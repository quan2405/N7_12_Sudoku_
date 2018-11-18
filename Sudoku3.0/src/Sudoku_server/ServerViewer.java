package Sudoku_server;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicBorders;

public class ServerViewer extends javax.swing.JPanel {

    Sudoku1 game;
    private JPanel center, bPanel, levelPanel;
    private JButton  hardBtn, midBtn, easyBtn;
    private JButton  eBtn ;
;
    private int[][] temp = new int[9][9];
    private int[][] grid = new int[9][9];

    public JTextField newtextfield() {
        JTextField j = new JTextField("");
        j.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        j.setFont(new Font(Font.DIALOG, Font.PLAIN, 25));
        j.setHorizontalAlignment(JTextField.CENTER);
        /*-------------------mouse lisner----------------*/
        j.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.decode("#f6ea80")));
                    ((JTextField) e.getSource()).setBackground(Color.decode("#f6ea80"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.lightGray));
                    ((JTextField) e.getSource()).setBackground(Color.white);
                }
            }
        });
        /*------------------------------------------------*/

        j.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (j.isEditable()) {
                    ((JTextField) e.getSource()).setForeground(Color.decode("#0c4"));
                } else {
                    ((JTextField) e.getSource()).setForeground(Color.black);
                }
            }
        });
        return j;
    }
    
    public ServerViewer() {
        initComponents();
        /*------------------------main panal  -------------------------------------*/
        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        
 
        /*------------------------panal for buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#AABFFF"));
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        bPanel.setLayout(new GridLayout(4, 3, 0, 20));


        /*------------------------panal for new Exit button -------------------------------------*/
        eBtn = new JButton("Exit");

        eBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /*------------------------panal for new Hard button -------------------------------------*/
        easyBtn = new JButton("Hard");

        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sudoku1.setlevel(4);
                Sudoku1.newGame();
                String file2;
                String file1;
                file1 = "Hard";
                file2="AnswerHard";
            File file = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Hard.txt");
                   file.delete();
             File file3 = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerHard.txt");
                   file3.delete();       
                
        try{
            file1 = file1 + ".txt";
            file2 = file2  + ".txt";

            FileWriter writer = new FileWriter(file1, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer.write(temp[i][j]+" ");
			writer.write(System.getProperty( "line.separator" ));
		} 
           
            FileWriter writer1 = new FileWriter(file2, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer1.write(grid[i][j]+" ");
			writer1.write(System.getProperty( "line.separator" ));
		} 
            writer.close();
            writer1.close();
       }catch(Exception ex){
            System.out.println(ex.getMessage());
       }
            JOptionPane.showMessageDialog(center, "Tao ban co thanh cong vao file " + file1);

    }                           
        });
        
        /*------------------------panal for new Hard button -------------------------------------*/
        midBtn = new JButton("Mideum");

        midBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sudoku1.setlevel(3);
                Sudoku1.newGame();
                   String file2;
                String file1;
                file1 = "Mid";
                file2="AnswerMid";
                File file = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Mid.txt");
                   file.delete();
                File file3 = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerMid.txt");
                   file3.delete();        
        try{
            
            file1 = file1  + ".txt";
            file2 = file2  + ".txt";

            FileWriter writer = new FileWriter(file1, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer.write(temp[i][j]+" ");
			writer.write(System.getProperty( "line.separator" ));
		} 
           
            FileWriter writer1 = new FileWriter(file2, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer1.write(grid[i][j]+" ");
			writer1.write(System.getProperty( "line.separator" ));
		} 
            writer.close();
            writer1.close();
       }catch(Exception ex){
            System.out.println(ex.getMessage());
       }
            JOptionPane.showMessageDialog(center, "Tao ban co thanh cong vao file " + file1);

            }
        });
        
        /*------------------------panal for new Hard button -------------------------------------*/
        hardBtn = new JButton("Easy");

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sudoku1.setlevel(2);
                Sudoku1.newGame();
                File file = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Easy.txt");
                   file.delete();
               File file3 = new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerEasy.txt");
                   file3.delete();         
                String file1;
                String file2;
                file1 = "Easy";
                file2="AnswerEasy";
        try{
            //            Random rand = new Random();
//            int  n = rand.nextInt(10000) + 1;
            file1 = file1  + ".txt";
            file2 = file2  + ".txt";

            FileWriter writer = new FileWriter(file1, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer.write(temp[i][j]+" ");
			writer.write(System.getProperty( "line.separator" ));
		} 
           
            FileWriter writer1 = new FileWriter(file2, true);
		for (int i = 0; i<9; i++) 
		{ 
			for (int j = 0; j<9; j++) 
                                writer1.write(grid[i][j]+" ");
			writer1.write(System.getProperty( "line.separator" ));
		} 
            writer.close();
            writer1.close();
       }catch(Exception ex){
            System.out.println(ex.getMessage());
       }
            JOptionPane.showMessageDialog(center, "Tao ban co thanh cong vao file " + file1);

            }
        });
        

        /*------------------------add button panal and butons to frame and panel -------------------------------------*/
        bPanel.add(hardBtn);   //add new game button to 
        bPanel.add(midBtn);
        bPanel.add(easyBtn);
        bPanel.add(eBtn);
        add(bPanel, "South");   //add button panel to frame 

    }

    public void setarray(int[][] grid, int[][] temp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.temp[i][j] = temp[i][j];
                this.grid[i][j] = grid[i][j];
            }
        }
    }
 
    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;
    private static Socket clientSocket1 = null;

    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new CLIENTConnection(clientSocket));
                Thread t1 = new Thread(new CLIENTConnection(clientSocket1));
                t.start();

            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
