package FinalProject;

import java.io.*;
import java.util.*;

public class finalproject {

    // Constants
    private static final String FILE_NAME = "juso.txt"; // 파일 주소
    private static final String[] MENU = {"연락처 출력", "연락처 등록", "연락처 삭제", "끝내기"}; // 메뉴 내용 (유지보수성을 위해 분리)
    private static final String[] DEL_MENU = {"순번 삭제", "이름 삭제", "번호 삭제", "메인 메뉴로 이동"};  // 삭제 세부 메뉴 내용 (유지보수성을 위해 분리)
    private static enum DEL_WORK {NUM, NAME, PHONE} // 삭제 행동 관리용 enum

    // Variables
    private static File file; // 주소록이 저장될 파일
    private static int phoneNumCnt; // 전화번호 총 개수
    private static final MyUserReader mr = new MyUserReader(); // 유저로 부터 받는 정보를 처리하는 클래스

    // Main
    public static void main(String[] args) throws IOException{
        System.out.println("[기말프로젝트] 60181627 김수정");
        System.out.println("[연락처 관리 프로그램]");

        // file initialize
        file = new File(FILE_NAME);
        if (!file.exists()) file.createNewFile(); // 파일이 없다면 새로 만든다.
        else {
            // 이미 파일이 있을 경우, 시작할 때 총 연락처의 개수가 몇개인지 미리 파악함.
            BufferedReader fileBr = new BufferedReader(new FileReader(file));
            while (fileBr.readLine() != null) phoneNumCnt++;
        }

        // start
        while (true) {
            print_menu();
        }
    }

    // 0. 초기의 메뉴화면을 출력=====================================================================
    public static void print_menu() throws IOException{
        System.out.println(); // 간격 맞추기 위한 공백
        for (int i = 1; i < MENU.length + 1; i++) {
            System.out.printf("%d. %s\n", i, MENU[i - 1]);
        }
        System.out.print("\n메뉴를 선택하세요: ");
        switch (checkMenu(MENU, "잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ")) {
            case 1 -> view_juso();
            case 2 -> add_juso();
            case 3 -> delete_juso();
            case 4 -> System.exit(0);
        }
    }

    // Method - 유저가 입력한 메뉴 번호가 올바른지 체크하여 올바르지 않은 값이 입력시 wrongMsg를 출력하고 다시 이를 실행
    public static int checkMenu(String[] menu, String wrongMsg) throws IOException{
        try {
            int ret = mr.readNextInt();
            if (ret < 1 || ret > menu.length) throw new NumberFormatException();
            return ret;
        } catch (NumberFormatException e) {
            System.out.printf("\n%s",wrongMsg);
            return checkMenu(menu, wrongMsg);
        }
    }


    // 1. 현재 저장된 juso.txt 파일에서 연락처를 읽어서 화면에 출력 ========================================
    public static void view_juso() throws IOException{
        // 연락처 가져오기
        BufferedReader fileBr = new BufferedReader(new FileReader(file));

        // initialize variables
        int loop = 0; // 연락처 개수를 세기 위함.
        String line = ""; // 파일로 부터 읽은 한 줄의 내용을 저장.
        StringTokenizer st; // line을 공백에 따라 분리하기 위함.
        System.out.println(); // 출력 간격을 맞추기 위해 추가.

        // start looping...
        while ((line = fileBr.readLine()) != null) {
            // 연락처 정보를 읽어온다
            st = new StringTokenizer(line);
            String index = st.nextToken();
            String name = st.nextToken();
            String age = st.nextToken();
            String phoneNum = st.nextToken();

            // 읽어온 내용을 바로바로 형식에 맞춰 출력한다
            System.out.printf("[%s] %-7s %-5s %s\n", index, name, age, phoneNum);

            // add loop (To check how many contacts are there)
            loop += 1;
        }
        // 읽은 연락처가 없을 경우 이를 사용장에게 알린다
        if (loop == 0) System.out.println("저장된 연락처가 없습니다.");

        // phoneNumCnt(연락처의 총 개수)를 재설정해준다.
        // (add할때 최대한 오차가 없도록 view, delete 모두에서 재설정 과정을 거친다)
        phoneNumCnt = loop;
    }


    // 2. 새로 입력하는 연락처를 juso.txt 파일에 저장 =======================================
    public static void add_juso() throws IOException{
        // 연락처 정보 입력받기
        while (true) {
            System.out.println(); // 간격 맞추기 위한 공백
            System.out.print("이름 입력: ");
            String name = mr.readNextString();

            // 어느 때건 연락처 추가 중에 0만 입력이되면 연락처 추가 로직 종료되도록 반복
            if (name.equals("0")) {
                System.out.println("연락처 추가 로직을 종료합니다.");
                return;
            }

            System.out.print("나이 입력: ");
            String age = input_age_with_validation();
            // 어느 때건 연락처 추가 중에 0만 입력이되면 연락처 추가 로직 종료되도록 반복
            if (age.equals("0")) {
                System.out.println("연락처 추가 로직을 종료합니다.");
                return;
            }

            System.out.print("번호 입력: ");
            String phoneNum = input_phoneNum_with_validation();
            // 어느 때건 연락처 추가 중에 0만 입력이되면 연락처 추가 로직 종료되도록 반복
            if (phoneNum.equals("0")) {
                System.out.println("연락처 추가 로직을 종료합니다.");
                return;
            }

            // 연락처 저장하기
            // FireWriter의 두번째 인자로 true를 넘겨주면 fw.wurte()로 바은 값을 뒤로 계속 추가해줌.
            FileWriter fw = new FileWriter(file, true);
            fw.write(++phoneNumCnt + " " + name + " " + age + " " + phoneNum + "\n");
            fw.flush();
        }
    }

    // Method - To check whether the entered age value satisfies validation.
    private static String input_age_with_validation() throws IOException{
        // 전세계에서 가장 오래 산 사람의 나이가 122살이라 1살~122살까지의 정수만 입력받을 수 있다. (그 외엔 NumberFormatException 발생)
        try {
            String age = mr.readNextString();
            if (Integer.parseInt(age)==0) return "0";// add_juso 메소드로 복귀시 종료됨.
            else if (Integer.parseInt(age) < 1 || Integer.parseInt(age) > 122) throw new NumberFormatException();
            else return age;
        } catch (NumberFormatException e) {
            System.out.print("나이가 올바르지 않습니다. 나이를 다시 입력하세요: ");
            return input_age_with_validation();
        }
    }

    // Method - To check whether the entered phoneNum value satisfies validation.
    private static String input_phoneNum_with_validation() throws IOException{
        // 010-xxxx-xxxx의 양식을 만족하는 값만 입력받을 수 있다. (그 외엔 Exception 발생)
        try {
            String phoneNum = mr.readNextString();
            if(phoneNum.equals("0")) return "0";
            else if (phoneNum.length() == 13 && phoneNum.charAt(3) == '-' && phoneNum.charAt(8) == '-') return phoneNum;
            else throw new Exception();
        } catch (Exception e) {
            System.out.print("전화번호 입력 양식에 맞춰 다시 입력해주세요.(ex. 010-1234-1234) : ");
            return input_phoneNum_with_validation();
        }
    }

    // 3. 사용자가 선택한 연락처를 juso.txt 파일에서 삭제 ==========================================
    public static void delete_juso() throws IOException{
        // 메뉴보여주기
        System.out.println(); // 간격 맞추기 위한 공백
        for (int i = 1; i < DEL_MENU.length + 1; i++) System.out.printf("%d. %s\n", i, DEL_MENU[i - 1]);

        // 세부 메뉴 입력 받기
        System.out.print("\n세부 메뉴를 선택하세요: ");
        switch (checkMenu(DEL_MENU, "잘못된 세부 메뉴입니다. 세부 메뉴를 다시 선택하세요: ")) {
            case 1 -> delete_juso_by(DEL_WORK.NUM);
            case 2 -> delete_juso_by(DEL_WORK.NAME);
            case 3 -> delete_juso_by(DEL_WORK.PHONE);
            // case 4의 "메인 메뉴로 이동"에대한 행동은  delete_juso가 종료되면 이어지는 행동과 동일하여 따로 처리할 필요가 없다.
        }
    }

    // Method - Enum으로 받은 값(num, name, phone)에 따른 삭제를 구현한 함수
    public static void delete_juso_by(DEL_WORK work) throws IOException{
        // 삭제할 세부 메뉴에 따라 삭제할 대상을 읽어온다.
        System.out.printf("\n삭제할 연락처 %s? ",
                work == DEL_WORK.NUM ? "순번은" :
                work == DEL_WORK.NAME ? "이름은" :
                    "번호는"); // work == DEL_WORK.PHONE
        String val = mr.readNextString();

        // 0을 입력받으면 삭제로직 종료되도록 처리
        if(val.equals("0")) {
            System.out.println("연락처 삭제 로직을 종료합니다.");
            return;
        }

        // initialize
        // ( while문 내에서 여러번 선언 시 메모리공간 차지가 커 while문 밖에 선언만 따로 위치)
        int loop = 0; // 연락처 개수를 세며 index를 작성하기 위함.
        String line = ""; // 파일로 부터 읽은 한 줄의 내용을 저장.
        StringTokenizer st; // line을 공백에 따라 분리하기 위함.
        StringBuilder sb = new StringBuilder(); // 삭제되지 않을 연락처를 한번에 모으기 위함.
        BufferedReader fileBr = new BufferedReader(new FileReader(file)); // 파일을 읽기 위함.
        boolean isDeleted = false; // 삭제된 연락처가 있는지 체크

        // 파일 읽기 (더이상 읽을 줄이 없을 때까지 line에 그 값을 넣으며 읽음)
        while ((line = fileBr.readLine()) != null) {
            // 파일로부터 index, name, age, phoneNum을 가져온다.
            st = new StringTokenizer(line);
            String index = st.nextToken();
            String name = st.nextToken();
            String age = st.nextToken();
            String phoneNum = st.nextToken();

            // work에 따라 일치하는 대상과 그에 맞는 삭제 결과를 예시자료처럼 출력함.
            if ((work == DEL_WORK.NUM && Objects.equals(index, val))
                || (work == DEL_WORK.PHONE && Objects.equals(phoneNum, val))){
                    isDeleted = true;
                    System.out.printf("%s번 연락처가 삭제되었습니다.\n",index);
            } else if (work == DEL_WORK.NAME && Objects.equals(name, val)) {
                isDeleted = true;
                System.out.printf("%s 연락처가 삭제되었습니다.\n", name);
            } else {
                // 새로운 값을 sb에 추가해줌.
                sb.append(++loop+ " " + name + " " + age + " " + phoneNum + "\n");
            }
        }

        if (isDeleted) {
            // 삭제가 정상적으로 이뤄졌다면 삭제할 내용이 제외된 내용을 파일에 입력하고 종료
            BufferedWriter fw = new BufferedWriter(new FileWriter(file));
            fw.write(sb.toString()); // 내용 없으면 입력 안하기
            fw.flush();
            // phoneNumCnt(연락처의 총 개수)를 재설정해준다. (add할때 최대한 오차가 없도록 view, delete 모두에서 재설정 과정을 거친다)
            phoneNumCnt = loop;
        } else {
            // 삭제할 연락처가 없다면 이를 사용자에게 알리고 해당 함수를 다시 실행시킴.
            System.out.println("존재하지 않는 연락처입니다.");
            delete_juso_by(work);
        }
    }

    // Inner Class - Read Input values from user
    private static class MyUserReader {
        // Field
        BufferedReader br;
        StringTokenizer st;

        // Constructor
        public MyUserReader(){ br = new BufferedReader(new InputStreamReader(System.in)); }

        // Method - read String from user
        public String readNextString() throws IOException{
            return br.readLine();
        }

        // Method - read Int from user
        public int readNextInt() throws IOException{
            st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

}
