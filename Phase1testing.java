import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class Phase1testing
{
    private static final char[][] M = new char[100][4];//M->Memory
    private static int IC;
    private static final char[] IR = new char[4];
    private static boolean C;
    private static final char[] R = new char[4];
    private static int indexForM;
    private static String buffer;
    private static FileWriter filewriter ;
    private static boolean flag1;
    private static boolean flag2;
    private static Scanner scanner;

    private static void INIT()
    {
        for(char[]rowInMemory:M)
            Arrays.fill(rowInMemory,'-');

        Arrays.fill(IR,'-');
        Arrays.fill(R,'-');

        IC = 0;
        C = false;
        buffer = null;


        flag1 = false;
        flag2 = false;
        indexForM = 0;
    }

    private static void READ()
    {
        IR[3] = '0';
        buffer = scanner.nextLine();
        char[] array = buffer.toCharArray();
        int indexForarray = 0;
        boolean flag = false;
        int startingAddress = Integer.parseInt(""+IR[2]+IR[3]);
        int endingAddress = startingAddress + 10;
        for(int i = startingAddress;i < endingAddress ;i++)
        {
            for(int j = 0 ; j < 4;j++)
            {
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

    private static void WRITE()
    {
        IR[3] = '0';
        StringBuilder sb = new StringBuilder();
        int startingAddress = Integer.parseInt(""+IR[2]+IR[3]);
        int endingAddress = startingAddress+10;
        for(int i = startingAddress; i < endingAddress;i++)
        {
            for(int j = 0 ; j < 4 ; j++)
            {
                    if(M[i][j] == '-')
                        sb.append(" ");
                    else
                        sb.append(M[i][j]);
            }
        }
        sb.append("\n");
        try
        {
            filewriter.write(new String(sb));
            filewriter.flush();//After we write using filewriter.write(), if there is any data remaining in buffer, filewriter.flush() forces to write the remaining contents of buffer to file
        }
        catch(Exception e)
        {
            System.out.println("Problem in writing inside the file");
        }
    }

    private static void TERMINATE()
    {
        try
        {
            filewriter.write("\n\n\n");
            filewriter.flush();
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
                Phase1testing.READ();
                break;
            case 2:
                Phase1testing.WRITE();
                break;
            case 3:
                Phase1testing.TERMINATE();
                break;
        }
    }

    private static void EXECUTEUSERPROGRAM()
    {
        while(true)
        {
            int SI;
            for (int i = 0; i < 4; i++)
                IR[i] = M[IC][i];
            IC++;
            String opcode;

            if (IR[1] == ' ')
                opcode = "" + IR[0];
            else
                opcode = "" + IR[0] + IR[1];



            int operand = 0;
            if (IR[1] != ' ')
            {
                String s = "" + IR[2] + IR[3];
                operand = Integer.parseInt(s);
            }
            switch (opcode)
            {
                case "LR":
                    for (int i = 0; i < 4; i++)
                        R[i] = M[operand][i];
                    break;
                case "SR":
                    for (int i = 0; i < 4; i++)
                        M[operand][i] = R[i];
                    break;
                case "CR":
                    C = true;
                    for (int i = 0; i < 4; i++)
                    {
                        if (M[operand][i] != R[i])
                        {
                            C = false;
                            break;
                        }
                    }
                    break;
                case "BT":
                    if (C)
                        IC = Integer.parseInt((("" + IR[2]) + IR[3]));
                    break;
                case "GD":
                    SI = 1;
                    Phase1testing.MOS(SI);
                    break;
                case "PD":
                    SI = 2;
                    Phase1testing.MOS(SI);
                    break;
                case "H":
                    SI = 3;
                    Phase1testing.MOS(SI);
                    return;
            }
        }
    }

    private static void STARTEXECUTION()
    {
        IC = 0;
        Phase1testing.EXECUTEUSERPROGRAM();
    }

    private static void LOAD()
    {
        while(scanner.hasNextLine())
        {
            buffer = scanner.nextLine();
            String first4CharOfInputLine = buffer.substring(0, 4);
            if (first4CharOfInputLine.equals("$AMJ"))
            {
                flag1 = true;
            }
            else if (first4CharOfInputLine.equals("$DTA"))
            {
                Phase1testing.STARTEXECUTION();
                flag1 = true;
            }
            else if (first4CharOfInputLine.equals("$END"))
            {
                for(int k = 0 ; k < M.length ; k++)
                    System.out.println(k+":"+Arrays.toString(M[k]));
                System.out.println("-".repeat(83)+"Job Over"+"-".repeat(83));
                Phase1testing.INIT();
                flag1=true;
            }
            if (!flag1)
            {
                char[] arrayOfBuffer = buffer.toCharArray();
                int indexForArrayOfBuffer = 0;
                int i = indexForM, j;
                while(true)
                {
                    for (j = 0; j < 4; j++)
                    {
                        M[i][j] = arrayOfBuffer[indexForArrayOfBuffer];
                        indexForArrayOfBuffer++;
                        if (indexForArrayOfBuffer >= arrayOfBuffer.length)
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
            flag2 = false;
            flag1 = false;
        }
    }

    public static void main(String[] args)
    {
        File file = new File("E:\\Programming\\OSProject\\src\\OSinputFile1Phase1.txt");
        try
        {
            scanner = new Scanner(file);
        }
        catch (Exception e)
        {
            System.out.println("Problem in opening file");
            System.exit(1);
        }

        try
        {
            filewriter = new FileWriter("E:\\Programming\\OSProject\\src\\OSoutputFile1Phase1.txt");
        }
        catch(Exception e)
        {
            System.out.println("Problem in opening file");
            System.exit(1);
        }


        Phase1testing.INIT();
        Phase1testing.LOAD();

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