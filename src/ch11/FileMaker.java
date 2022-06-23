package ch11;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileMaker {
    public static void main(String[] args) throws IOException{
        // ���� �Է¹���
        Scanner sc = new Scanner(System.in);
        System.out.print("�Է�: ");
        String inputVal = sc.nextLine();

        StringTokenizer st = new StringTokenizer(inputVal, " \n\t\r\f.");
        // (1) �ܾ��� ������ ȭ�鿡 ���
        int count = st.countTokens();
        System.out.printf("�ܾ� ����: %d\n",count);
        // (0) �־��� ������ �ܾ�� �и��� �迭�� ����
        String[] inputValStrs = new String[count];
        int idx=0;
        while(st.hasMoreTokens()){
            inputValStrs[idx++] = st.nextToken();
        }
        // (2) �ܾ ������ ��(sort)
        Arrays.sort(inputValStrs);
        // (3) ���ĵ� ��� �ܾ ����ϰ�,
        System.out.print("���ĵ� ��ū: ");
        StringBuilder sortedVal = new StringBuilder();
        for (String val:inputValStrs) { sortedVal.append(val).append(", "); }
        System.out.println(sortedVal.toString());
        // (4) ���������� ���ĵ� �ܾ���� �ٽ� "sort.txt"���Ͽ� ����
        FileWriter fw = new FileWriter(new File("sort.txt"));
        fw.write(sortedVal.toString());
        fw.close();
    }
}