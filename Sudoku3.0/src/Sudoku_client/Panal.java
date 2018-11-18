package Sudoku_client;

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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Panal extends javax.swing.JPanel {

    Sudoku game;
    private Timer timer;
    private JButton nbtn = new JButton("new game");
    private static JTextField[][] boxes;
    // private JPasswordField pass = new JPasswordField("Sudoku");
    private JLabel label = new JLabel("      Timer :00 : 00 : 00");
    //private JLabel passsLabel = new JLabel("            your password :");
    private JPanel[][] paneles;
    private JPanel center, bPanel, levelPanel;
    private JButton nBtn, cBtn, eBtn, hardBtn, midBtn, easyBtn, submit;
    private JButton slove, Hint;
    private int[][] temp = new int[9][9];
    private int[][] grid = new int[9][9];
    private int counter = 0;
    private static Socket sock;
    private static String fileName;
    private static String fileName1;
    private static BufferedReader stdin;
    private static PrintStream os;
    public int is_player;

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

    public Panal() {
        initComponents();
        /*------------------------main panal  -------------------------------------*/
        center = new JPanel();  //main panel
        center.setLayout(new GridLayout(3, 3));     //grid for 3*3 
        center.setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        add(center);  //add main panel to frame 

        boxes = new JTextField[9][9];
        paneles = new JPanel[3][3];
//        passsLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
//        passsLabel.setForeground(Color.black);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.red, 4));
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                paneles[i][j] = new JPanel();
                paneles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                paneles[i][j].setLayout(new GridLayout(3, 3));
                center.add(paneles[i][j]);
            }
        }
        /*------------------------text fildes in boxes-------------------------------------*/

        for (int n = 0; n < 9; n++) {
            for (int i = 0; i < 9; i++) {
                boxes[n][i] = newtextfield();
                int fm = (n + 1) / 3;
                if ((n + 1) % 3 > 0) {
                    fm++;
                }
                int cm = (i + 1) / 3;
                if ((i + 1) % 3 > 0) {
                    cm++;
                }
                paneles[fm - 1][cm - 1].add(boxes[n][i]);   //add box to panel 
            }
        }
        /*------------------------panal for buttons -------------------------------------*/

        bPanel = new JPanel();
        bPanel.setBackground(Color.decode("#AABFFF"));
        bPanel.setBorder(BorderFactory.createLineBorder(Color.black, 6, true));
        bPanel.setLayout(new GridLayout(4, 3, 0, 20));

        /*------------------------panal for new game button -------------------------------------*/
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                label.setText(TimeFormat(counter));
                counter++;

            }
        };

        /*------------------------panal for new game button -------------------------------------*/
        //nBtn = new JButton("New Game");
        nbtn.setSize(20, 50);
        timer = new Timer(1000, action);
//        nBtn.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                counter = 0;
//                timer.start();
//                restgame();
//                Sudoku.newGame();
//
//            }


        /*------------------------panal for check game button -------------------------------------*/
        cBtn = new JButton("Check number +30s");

        cBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (boxes[i][j].getText().equals(String.valueOf(grid[i][j]))) {
                            boxes[i][j].setBackground(Color.green);

                        } else {
                            boxes[i][j].setBackground(Color.red);
                        }
                    }
                }
                counter += 30;
            }
        });
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
                restgame();
                counter = 0;
                timer.start();
                // Sudoku.setlevel(4);
                //  Sudoku.newGame();
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Hard.txt"));
                    String str;
                    int i = 0;
                    while ((str = in.readLine()) != null) {

                        int[] Element = new int[9];
                        String[] item = str.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < item.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Element[j] = Integer.parseInt(item[j]);
                            //System.out.print(Element[j] + " ");

                            temp[i][j] = Element[j];
                            if (temp[i][j] == 0) {
                                boxes[i][j].setText("");
                            } else {
                                boxes[i][j].setText(temp[i][j] + "");
                            }
                        }
                        i++;
//
//                       // System.out.println("");
//
////            System.out.println(str);
////            đọc ghi ra cả ma trận luôn
//                        // phải đọc từng cái ra. thì lúc đó ms có ma trân kq chứ.
////                    /nó/  đọc từng dòng mà
//                        // đuọc thế k gán đc. 1 dòng làm sao shơw đc 
                    }

                } catch (IOException ex) {
                }
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerHard.txt"));
                    String sc;
                    int i = 0;
                    while ((sc = in.readLine()) != null) {

                        int[] Answer = new int[9];
                        String[] ab = sc.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < ab.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Answer[j] = Integer.parseInt(ab[j]);
                            // System.out.print(Answer[j] + " ");

                            grid[i][j] = Answer[j];
//                            if(temp[i][j] == 0) {
//                                boxes[i][j].setText("");
//                            } else {
//                                boxes[i][j].setText(temp[i][j] + "");
//                            }
                        }
                        i++;

                    }

                } catch (IOException ex) {
                }
//                 try {
//                        OutputStream output = new FileOutputStream("player.txt");
//                        PrintStream printOut = new PrintStream(output);
//                        printOut.println();
//
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(Panal.class.getName()).log(Level.SEVERE, null, ex);
//                    }
            }
        });

        /*------------------------panal for new Hard button -------------------------------------*/
        midBtn = new JButton("Mideum");

        midBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                // Sudoku.setlevel(4);
                // Sudoku.newGame();
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Mid.txt"));
                    String str;
                    int i = 0;
                    while ((str = in.readLine()) != null) {

                        int[] Element = new int[9];
                        String[] item = str.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < item.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Element[j] = Integer.parseInt(item[j]);
                            //System.out.print(Element[j] + " ");

                            temp[i][j] = Element[j];
                            if (temp[i][j] == 0) {
                                boxes[i][j].setText("");
                            } else {
                                boxes[i][j].setText(temp[i][j] + "");
                            }
                        }
                        i++;

                        // System.out.println("");
//            System.out.println(str);
//            đọc ghi ra cả ma trận luôn
                        // phải đọc từng cái ra. thì lúc đó ms có ma trân kq chứ.
//                    /nó/  đọc từng dòng mà
                        // đuọc thế k gán đc. 1 dòng làm sao shơw đc 
                    }

                } catch (IOException ex) {
                }
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerMid.txt"));
                    String sc;
                    int i = 0;
                    while ((sc = in.readLine()) != null) {

                        int[] Answer = new int[9];
                        String[] ab = sc.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < ab.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Answer[j] = Integer.parseInt(ab[j]);
                            //System.out.print(Answer[j] + " ");

                            grid[i][j] = Answer[j];
//                            if(temp[i][j] == 0) {
//                                boxes[i][j].setText("");
//                            } else {
//                                boxes[i][j].setText(temp[i][j] + "");
//                            }
                        }
                        i++;

                    }

                } catch (IOException ex) {
                }
            }
        });


        /*------------------------panal for new Hard button -------------------------------------*/
        hardBtn = new JButton("Easy");

        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restgame();
                counter = 0;
                timer.start();
                // Sudoku.setlevel(2);
                // Sudoku.newGame();
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\Easy.txt"));
                    String str;
                    int i = 0;
                    while ((str = in.readLine()) != null) {

                        int[] Element = new int[9];
                        String[] item = str.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < item.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Element[j] = Integer.parseInt(item[j]);
                            //System.out.print(Element[j] + " ");

                            temp[i][j] = Element[j];
                            if (temp[i][j] == 0) {
                                boxes[i][j].setText("");
                            } else {
                                boxes[i][j].setText(temp[i][j] + "");
                            }
                        }
                        i++;

                        // System.out.println("");
//            System.out.println(str);
//            đọc ghi ra cả ma trận luôn
                        // phải đọc từng cái ra. thì lúc đó ms có ma trân kq chứ.
//                    /nó/  đọc từng dòng mà
                        // đuọc thế k gán đc. 1 dòng làm sao shơw đc 
                    }

                } catch (IOException ex) {

                }
                try {
                    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\AnswerEasy.txt"));
                    String sc;
                    int i = 0;
                    while ((sc = in.readLine()) != null) {

                        int[] Answer = new int[9];
                        String[] ab = sc.split(" "); //tach chuoi thanh cac phan tu chuoi

                        for (int j = 0; j < ab.length; j++) //doi kiem string sang int cua cac phan tu
                        {
                            Answer[j] = Integer.parseInt(ab[j]);
                            //System.out.print(Answer[j] + " ");

                            grid[i][j] = Answer[j];
//                            if(temp[i][j] == 0) {
//                                boxes[i][j].setText("");
//                            } else {
//                                boxes[i][j].setText(temp[i][j] + "");
//                            }
                        }
                        i++;

                    }

                } catch (IOException ex) {
                }
            }
        });

        submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isFine = true;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (boxes[i][j].getText().equals("")) {
                            isFine = false;
                            break;
                        } else if (Integer.parseInt(boxes[i][j].getText()) != grid[i][j]) {
                            isFine = false;
                            break;
                        }
                    }
                }
                if (isFine) {
                    JOptionPane.showMessageDialog(null, "Correct");
                    timer.stop();
                    try {
                        OutputStream output = new FileOutputStream("score.txt");
                        PrintStream printOut = new PrintStream(output);
                        printOut.println(counter);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Panal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        sock = new Socket("localhost", 4444);
                        stdin = new BufferedReader(new InputStreamReader(System.in));
                    } catch (Exception ex) {
                        System.err.println("Cannot connect to the server, try again later.");
                        System.exit(1);
                    }
                    sendFile();
                } else {
                    JOptionPane.showMessageDialog(null, "InCorrect.");

                }
            }
        });

        Hint = new JButton("Hint+30s");
        Hint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int check = 1;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (boxes[i][j].getText().equals("")) {
                            boxes[i][j].setText(grid[i][j] + "");
                            temp[i][j] = grid[i][j];
                            check = 0;
                            break;
                        }
                        if (check == 0) {
                            break;
                        }
                    }
                    if (check == 0) {
                        break;
                    }
                }
                counter += 30;
            }
        });
        /*------------------------add button panal and butons to frame and panel -------------------------------------*/
        bPanel.add(hardBtn);   //add new game button to 
        bPanel.add(midBtn);
        bPanel.add(easyBtn);
//        bPanel.add(nBtn);   //add new game button to 
        bPanel.add(cBtn);
        bPanel.add(eBtn);
        bPanel.add(label);
        bPanel.add(submit);
        bPanel.add(Hint);
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

    public void setTextLable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.temp[i][j] != 0) {
                    boxes[i][j].setText(String.valueOf(this.temp[i][j]));
                    boxes[i][j].setEditable(false);
                    boxes[i][j].setBackground(Color.decode("#C0DCC0"));
                } else {
                    boxes[i][j].setText("");
                }
            }
        }
    }

    public static void restgame() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boxes[i][j].setForeground(Color.black);
                boxes[i][j].setEditable(true);
                boxes[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private String TimeFormat(int count) {

        int hours = count / 3600;
        int minutes = (count - hours * 3600) / 60;
        int seconds = count - minutes * 60;

        return String.format("      Timer :" + "%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

//    private static Socket sock;
//    private static String fileName;
//    private static BufferedReader stdin;
//    private static PrintStream os;
//    public static void main(String[] args) throws IOException {
//        try {
//            sock = new Socket("localhost", 4444);
//            stdin = new BufferedReader(new InputStreamReader(System.in));
//        } catch (Exception e) {
//            System.err.println("Cannot connect to the server, try again later.");
//            System.exit(1);
//        }
//
//        //os = new PrintStream(sock.getOutputStream());
//        //       try {
////              switch (Integer.parseInt(selectAction())) {
////            case 1:
////                os.println("1");
//        sendFile();
////              break;
////            case 2:
////                os.println("2");
////                System.err.print("Enter file name: ");
////                fileName = stdin.readLine();
////                os.println(fileName);
////                receiveFile(fileName);
//        // break;
//        //}
////        } catch (Exception e) {
////            System.err.println("not valid input");
////        }
//
//        // sock.close();
//    }
//
////    public static String selectAction() throws IOException {
////        System.out.println("1. Send file.");
////        System.out.println("2. Recieve file.");
////        System.out.print("\nMake selection: ");
////
////        return stdin.readLine();
////    }
    public static void sendFile() {
        try {
//            System.err.print("Enter file name: ");
            // fileName = stdin.readLine();
            fileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\score.txt";
           // fileName1 = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\score.txt"
            File myFile = new File(fileName);
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            //bis.read(mybytearray, 0, mybytearray.length);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = sock.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(myFile.getName());
            dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();
            System.out.println("File " + fileName + " sent to Server.");
        } catch (Exception e) {
            System.err.println("File does not exist!");
        }
        
    }
//    public static void sendFile1() {
//        try {
////            System.err.print("Enter file name: ");
//            // fileName = stdin.readLine();
//            //fileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\score.txt";
//            fileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\player.txt";
//            File myFile = new File(fileName);
//            byte[] mybytearray = new byte[(int) myFile.length()];
//
//            FileInputStream fis = new FileInputStream(myFile);
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            //bis.read(mybytearray, 0, mybytearray.length);
//
//            DataInputStream dis = new DataInputStream(bis);
//            dis.readFully(mybytearray, 0, mybytearray.length);
//
//            OutputStream os = sock.getOutputStream();
//
//            //Sending file name and file size to the server
//            DataOutputStream dos = new DataOutputStream(os);
//            dos.writeUTF(myFile.getName());
//            dos.writeLong(mybytearray.length);
//            dos.write(mybytearray, 0, mybytearray.length);
//            dos.flush();
//            System.out.println("File " + fileName + " sent to Server.");
//        } catch (Exception e) {
//            System.err.println("File does not exist!");
//        }
//        
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
