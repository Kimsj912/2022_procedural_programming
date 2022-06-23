package ch11;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileMaker {
    public static void main(String[] args) throws IOException{
        // 값을 입력받음
        Scanner sc = new Scanner(System.in);
        System.out.print("입력: ");
        String inputVal = sc.nextLine();

        StringTokenizer st = new StringTokenizer(inputVal, " \n\t\r\f.");
        // (1) 단어의 개수를 화면에 출력
        int count = st.countTokens();
        System.out.printf("단어 개수: %d\n",count);
        // (0) 주어진 문장을 단어로 분리해 배열로 저장
        String[] inputValStrs = new String[count];
        int idx=0;
        while(st.hasMoreTokens()){
            inputValStrs[idx++] = st.nextToken();
        }
        // (2) 단어를 정렬한 후(sort)
        Arrays.sort(inputValStrs);
        // (3) 정렬된 모든 단어를 출력하고,
        System.out.print("정렬된 토큰: ");
        StringBuilder sortedVal = new StringBuilder();
        for (String val:inputValStrs) { sortedVal.append(val).append(", "); }
        System.out.println(sortedVal.toString());
        // (4) 마지막으로 정렬된 단어들을 다시 "sort.txt"파일에 저장
        FileWriter fw = new FileWriter(new File("sort.txt"));
        fw.write(sortedVal.toString());
        fw.close();
    }
}