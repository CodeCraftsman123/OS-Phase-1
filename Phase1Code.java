import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class Phase1Code
{
    private static char[][] M;//M->Memory
    private static int IC;
    private static char[] IR;
    private static boolean C;
    private static char[] R;
    private static int indexForM;
    private static String buffer;
    private static FileWriter filewriter ;
    private static boolean f;


    private static void init()
    {
        M = new char[100][4];
        for(int i=0;i < 100;i++)
        {
            for(int j = 0 ; j < 4;j++)
            {
                M[i][j] = ' ';
            }
        }
        IC = 0;
        IR = new char[4];
        R = new char[4];
        C = false;
        buffer = null;

        try
        {
            filewriter = new FileWriter("E:\\Programming\\OSoutputFile1Phase1.txt");
        }
        catch(Exception e)
        {
            System.out.println("Problem in opening file");
            System.exit(1);
        }

        f = false;
        indexForM = 0;
    }

    private static void load(Scanner scanner)
    {
        while(true)
        {
            buffer = scanner.nextLine();
            if(buffer.substring(0,4).equalsIgnoreCase("$DTA"))
                return;
            else if(buffer.substring(0,4).equalsIgnoreCase("$AMJ"))
                continue;
            else
            {
                char[] arrayOfBuffer = buffer.toCharArray();
                int indexForArrayOfBuffer = 0;
                //
                int i = indexForM, j;
                while(true)
                {
                    for (j = 0; j < 4; j++)
                    {
                        M[i][j] = arrayOfBuffer[indexForArrayOfBuffer];
                        indexForArrayOfBuffer++;
                        if (indexForArrayOfBuffer == arrayOfBuffer.length)
                        {
                            f = true;
                            break;
                        }
                    }
                    if (f)
                        break;
                    i++;
                }
                indexForM = i;
                if (indexForM < 10)
                    indexForM = 10;
                else if (indexForM < 20)
                    indexForM = 20;
                else if (indexForM < 30)
                    indexForM = 30;
                else if (indexForM < 40)
                    indexForM = 40;
                else if (indexForM < 50)
                    indexForM = 50;
                else if (indexForM < 60)
                    indexForM = 60;
                else if (indexForM < 70)
                    indexForM = 70;
                else if(indexForM < 80)
                    indexForM = 80;
                else if(indexForM < 90)
                    indexForM = 90;
                else
                {
                    System.out.println("No space, out of memory\nExiting......");
                    System.exit(1);
                }
            }
            f = false;
        }
    }


    public static void main(String[] args) {
        File file = new File("E:\\Programming\\OSProject\\src\\jobfile.txt");
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(file);
        } catch (Exception e)
        {
            System.out.println("Problem in opening file");
            System.exit(1);
        }

        Phase1Code.init();
        Phase1Code.load(scanner);

        try
        {
            filewriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Problem in closing the file");
        }

        //PrintMemory
        for(int i = 0 ; i < 100;i++)
            System.out.println(Arrays.toString(M[i]));
    }
}
