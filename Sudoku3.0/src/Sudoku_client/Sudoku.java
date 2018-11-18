package Sudoku_client;


//import .*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;

public class Sudoku {
    public String server;
    static JFrame frame;
    static Panal p;
    private  static int[][] grid;
    private static int[][] temp;
    private static Random ran = new Random();
    private static int level = 2;
      private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;

    public static void main(String[] args) {
        
        grid = new int[9][9];
        temp = new int[9][9];
        frame = new JFrame();
        frame.setResizable(false);
        frame.setLocation(320, 40);
        frame.setSize(650, 650);
        frame.setTitle("</Suduko Client >");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         p = new Panal();
        frame.setContentPane(p);
        frame.setVisible(true);
//         try {
//            sock = new Socket("localhost", 4444);
//           stdin = new BufferedReader(new InputStreamReader(System.in));
//        } catch (Exception e) {
//            System.err.println("Cannot connect to the server, try again later.");
//            System.exit(1);
//        }
//
//        //os = new PrintStream(sock.getOutputStream());
//
// //       try {
////              switch (Integer.parseInt(selectAction())) {
////            case 1:
////                os.println("1");
//                sendFile();
////              break;
////            case 2:
////                os.println("2");
////                System.err.print("Enter file name: ");
////                fileName = stdin.readLine();
////                os.println(fileName);
////                receiveFile(fileName);
//               // break;
//        //}
////        } catch (Exception e) {
////            System.err.println("not valid input");
////        }
//
//
//       // sock.close();
//    }
//
////    public static String selectAction() throws IOException {
////        System.out.println("1. Send file.");
////        System.out.println("2. Recieve file.");
////        System.out.print("\nMake selection: ");
////
////        return stdin.readLine();
////    }
//
//   public static void sendFile() {
//     
//        try {
////            System.err.print("Enter file name: ");
////            fileName = stdin.readLine();
//            fileName = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Socket\\score.txt"; 
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
//            System.out.println("File "+fileName+" sent to Server.");
//        } catch (Exception e) {
//            System.err.println("File does not exist!");
//        }
   }
    

    public static void newGame() {
//        int k = 0;
//        ArrayList<Integer> randomnumber = getRandomNum();
//        
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                grid[i][j] = 0;
//                if (((j + 2) % 2) == 0 && ((i + 2) % 2) == 0) {
//                    grid[i][j] = randomnumber.get(k);
//        // giờ đọc từng cái xong gán cho cái grid này. :D để lấy tọa độ từng ô.
//                    k++;
//                    if (k == 9) {
//                        k = 0;
//                    }
//                }
//            }
//        }
//
//        if (search(grid)) {
//            System.out.println("OK !!");
//        }
        int rann = ran.nextInt(level);
        int c = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[i][j] = 0;
                if (c < rann) {
                    c++;
                    continue;
                } else {
                    rann = ran.nextInt(level);
                    c = 0;
                    temp[i][j] = grid[i][j];
                }
            }
        }

//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                System.err.print(grid[i][j]+" ");
//            }
//            System.out.println("sssssssssssssssssssssss");
//        }
        p.setarray(grid, temp);
        p.setTextLable();
    }

    public static int[][] getFreeCellList(int[][] grid) {

        int numberOfFreeCells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    numberOfFreeCells++;
                }
            }
        }

        int[][] freeCellList = new int[numberOfFreeCells][2];
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    freeCellList[count][0] = i;
                    freeCellList[count][1] = j;
                    count++;
                }
            }
        }

        return freeCellList;
    }

    public static boolean search(int[][] grid) {
        int[][] freeCellList = getFreeCellList(grid);
        int k = 0;
        boolean found = false;

        while (!found) {
            //get free element one by one
            int i = freeCellList[k][0];
            int j = freeCellList[k][1];
            // if element equal 0 give 1 to first test
            if (grid[i][j] == 0) {
                grid[i][j] = 1;
            }
            // now check 1 if is avaible
            if (isAvaible(i, j, grid)) {
                //if free is equal k ==> board sloved
                if (k + 1 == freeCellList.length) {
                    found = true;
                } else {
                    k++;
                }
            }
            //increase element  by 1 
            else if (grid[i][j] < 9) {
                grid[i][j] = grid[i][j] + 1;
            } 
            //now if element value eqaule 9 backtrack to later element
            else {
                while (grid[i][j] == 9) {
                    grid[i][j] = 0;
                    if (k == 0) {
                        return false;
                    }
                    k--; //backtrack to later element
                    i = freeCellList[k][0];
                    j = freeCellList[k][1];
                }
                grid[i][j] = grid[i][j] + 1;
            }
        }

        return true;
    }

    public static boolean isAvaible(int i, int j, int[][] grid) {

        // Check   row
        for (int column = 0; column < 9; column++) {
            if (column != j && grid[i][column] == grid[i][j]) {
                return false;
            }
        }

        // Check  column
        for (int row = 0; row < 9; row++) {
            if (row != i && grid[row][j] == grid[i][j]) {
                return false;
            }
        }

        // Check box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {//      i=5 ,j=2   || row =3  col=0   ||i=3  j=0
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (row != i && col != j && grid[row][col] == grid[i][j]) {
                    return false;
                }
            }
        }

        return true; //else return true
    }

    public static ArrayList<Integer> getRandomNum() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (Integer i = 1; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public static void setlevel(int lev) {
        level = lev;
    }
    
    }

