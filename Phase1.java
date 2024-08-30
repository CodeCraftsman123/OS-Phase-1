import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class Phase1
{
    private static char[][] M;//M->Memory
    private static int IC;
    private static char[] IR;
    private static boolean C;
    private static char[] R;
    private static int indexForM;
    private static String buffer;
    private static FileWriter filewriter ;
    private static boolean flag1;
    private static boolean flag2;


    private static void init()
    {
        M = new char[100][4];
        for(int i=0;i < 100;i++)
        {
            for(int j = 0 ; j < 4;j++)
            {
                M[i][j] = '-';
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

        flag1 = false;
        flag2 = false;
        indexForM = 0;
    }

    private static void read()
    {
        IR[3] = '0';
        char[] array = buffer.toCharArray();
        int indexForarray = 0;
        boolean flag = false;
        for(int i = Integer.parseInt(""+IR[2]+IR[3]);i < ((Integer.parseInt(""+IR[2]+IR[3]))+10) ;i++)
        {
            for(int j = 0 ; j < 4;j++)
            {
                if(array[indexForarray] == ' ')
                    M[i][j] = '-';
                else
                    M[i][j]=array[indexForarray];
                indexForarray++;
                if(indexForarray >= array.length)
                {
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
    }

    private static void write()
    {
        IR[3] = '0';
        StringBuilder sb = new StringBuilder();
        for(int i = Integer.parseInt(""+IR[2]+IR[3]); i < ((Integer.parseInt(""+IR[2]+IR[3]))+ 10);i++)
        {
            for(int j = 0 ; j < 4 ; j++)
            {
                if(M[i][j] == '-')
                    sb.append(" ");
                else
                    sb.append(M[i][j]);
            }
        }

        try
        {
            filewriter.write(new String(sb));
        }
        catch(Exception e)
        {
            System.out.println("Problem in writing of file");
        }
    }

    private static void terminate()
    {
        try
        {
            filewriter.write("\n\n");
        }
        catch(Exception e)
        {
            System.out.println("Problem");
            System.exit(1);
        }
    }


    private static void MOS(int SI)
    {
        switch(SI)
        {
            case 1:
                Phase1.read();
                break;
            case 2:
                Phase1.write();
                break;
            case 3:
                Phase1.terminate();
                break;
        }
    }

    private static void executeUserProgram()
    {
        while(true)
        {
            int SI = -1;
            for (int i = 0; i < 4; i++)
                IR[i] = M[IC][i];
            IC++;
            String first2char = null;
            if (IR[1] != '-')
                first2char = "" + IR[0] + IR[1];
            else
                first2char = "" + IR[0];


            int index = 0;
            if (IR[1] != '-') {
                String s = "" + IR[2] + IR[3];
                index = Integer.parseInt(s);
            }
            switch (first2char) {
                case "LR":
                    System.arraycopy(M[index], 0, R, 0, 4);
                    break;
                case "SR":
                    System.arraycopy(R, 0, M[index], 0, 4);
                    break;
                case "CR":
                    boolean flag = true;
                    for (int i = 0; i < 4; i++) {
                        if (M[index][i] != R[i]) {
                            flag = false;
                            break;
                        }
                    }
                    C = flag;
                    break;
                case "BT":
                    if (C)
                        IC = Integer.parseInt((("" + IR[3]) + IR[4]));
                    break;
                case "GD":
                    SI = 1;
                    Phase1.MOS(SI);
                    break;
                case "PD":
                    SI = 2;
                    Phase1.MOS(SI);
                    break;
                case "H":
                    SI = 3;
                    Phase1.MOS(SI);
                    return;
            }
        }
    }

    private static void startExecution()
    {
        IC = 0;
        Phase1.executeUserProgram();
    }

    private static void load(Scanner scanner)
    {
        buffer = null;
        while(scanner.hasNextLine())
        {
            buffer = scanner.nextLine();
            if (buffer.length() >= 4)
            {
                String first4char = buffer.substring(0, 4);
                if (first4char.equals("$AMJ"))
                {
                    flag1 = true;
                }
                else if (first4char.equals("$DTA"))
                {
                    buffer = scanner.nextLine();
                    Phase1.startExecution();
                    flag1 = true;
                } else if (first4char.equals("$END"))
                {
                    Phase1.init();
                }
                if (!flag1)
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
                                flag2 = true;
                                break;
                            }
                        }
                        if (flag2)
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
            }
            flag1 = false;
        }
    }

    public static void main(String[] args)
    {
        File file = new File("E:\\Programming\\OSinputFile1Phase1.txt");
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(file);
        } catch (Exception e)
        {
            System.out.println("Problem in opening file");
            System.exit(1);
        }

        Phase1.init();
        Phase1.load(scanner);

        try
        {
            filewriter.close();
        }
        catch(Exception e)
        {
            System.out.println("Problem in closing the file");
        }
    }
}