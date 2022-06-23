package FinalProject;

import java.io.*;
import java.util.*;

public class finalproject {

    // Constants
    private static final String FILE_NAME = "juso.txt"; // ���� �ּ�
    private static final String[] MENU = {"����ó ���", "����ó ���", "����ó ����", "������"}; // �޴� ���� (������������ ���� �и�)
    private static final String[] DEL_MENU = {"���� ����", "�̸� ����", "��ȣ ����", "���� �޴��� �̵�"};  // ���� ���� �޴� ���� (������������ ���� �и�)
    private static enum DEL_WORK {NUM, NAME, PHONE} // ���� �ൿ ������ enum

    // Variables
    private static File file; // �ּҷ��� ����� ����
    private static int phoneNumCnt; // ��ȭ��ȣ �� ����
    private static final MyUserReader mr = new MyUserReader(); // ������ ���� �޴� ������ ó���ϴ� Ŭ����

    // Main
    public static void main(String[] args) throws IOException{
        System.out.println("[�⸻������Ʈ] 60181627 �����");
        System.out.println("[����ó ���� ���α׷�]");

        // file initialize
        file = new File(FILE_NAME);
        if (!file.exists()) file.createNewFile(); // ������ ���ٸ� ���� �����.
        else {
            // �̹� ������ ���� ���, ������ �� �� ����ó�� ������ ����� �̸� �ľ���.
            BufferedReader fileBr = new BufferedReader(new FileReader(file));
            while (fileBr.readLine() != null) phoneNumCnt++;
        }

        // start
        while (true) {
            print_menu();
        }
    }

    // 0. �ʱ��� �޴�ȭ���� ���=====================================================================
    public static void print_menu() throws IOException{
        System.out.println(); // ���� ���߱� ���� ����
        for (int i = 1; i < MENU.length + 1; i++) {
            System.out.printf("%d. %s\n", i, MENU[i - 1]);
        }
        System.out.print("\n�޴��� �����ϼ���: ");
        switch (checkMenu(MENU, "�߸��� �޴��Դϴ�. �޴��� �ٽ� �����ϼ���: ")) {
            case 1 -> view_juso();
            case 2 -> add_juso();
            case 3 -> delete_juso();
            case 4 -> System.exit(0);
        }
    }

    // Method - ������ �Է��� �޴� ��ȣ�� �ùٸ��� üũ�Ͽ� �ùٸ��� ���� ���� �Է½� wrongMsg�� ����ϰ� �ٽ� �̸� ����
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


    // 1. ���� ����� juso.txt ���Ͽ��� ����ó�� �о ȭ�鿡 ��� ========================================
    public static void view_juso() throws IOException{
        // ����ó ��������
        BufferedReader fileBr = new BufferedReader(new FileReader(file));

        // initialize variables
        int loop = 0; // ����ó ������ ���� ����.
        String line = ""; // ���Ϸ� ���� ���� �� ���� ������ ����.
        StringTokenizer st; // line�� ���鿡 ���� �и��ϱ� ����.
        System.out.println(); // ��� ������ ���߱� ���� �߰�.

        // start looping...
        while ((line = fileBr.readLine()) != null) {
            // ����ó ������ �о�´�
            st = new StringTokenizer(line);
            String index = st.nextToken();
            String name = st.nextToken();
            String age = st.nextToken();
            String phoneNum = st.nextToken();

            // �о�� ������ �ٷιٷ� ���Ŀ� ���� ����Ѵ�
            System.out.printf("[%s] %-7s %-5s %s\n", index, name, age, phoneNum);

            // add loop (To check how many contacts are there)
            loop += 1;
        }
        // ���� ����ó�� ���� ��� �̸� ����忡�� �˸���
        if (loop == 0) System.out.println("����� ����ó�� �����ϴ�.");

        // phoneNumCnt(����ó�� �� ����)�� �缳�����ش�.
        // (add�Ҷ� �ִ��� ������ ������ view, delete ��ο��� �缳�� ������ ��ģ��)
        phoneNumCnt = loop;
    }


    // 2. ���� �Է��ϴ� ����ó�� juso.txt ���Ͽ� ���� =======================================
    public static void add_juso() throws IOException{
        // ����ó ���� �Է¹ޱ�
        while (true) {
            System.out.println(); // ���� ���߱� ���� ����
            System.out.print("�̸� �Է�: ");
            String name = mr.readNextString();

            // ��� ���� ����ó �߰� �߿� 0�� �Է��̵Ǹ� ����ó �߰� ���� ����ǵ��� �ݺ�
            if (name.equals("0")) {
                System.out.println("����ó �߰� ������ �����մϴ�.");
                return;
            }

            System.out.print("���� �Է�: ");
            String age = input_age_with_validation();
            // ��� ���� ����ó �߰� �߿� 0�� �Է��̵Ǹ� ����ó �߰� ���� ����ǵ��� �ݺ�
            if (age.equals("0")) {
                System.out.println("����ó �߰� ������ �����մϴ�.");
                return;
            }

            System.out.print("��ȣ �Է�: ");
            String phoneNum = input_phoneNum_with_validation();
            // ��� ���� ����ó �߰� �߿� 0�� �Է��̵Ǹ� ����ó �߰� ���� ����ǵ��� �ݺ�
            if (phoneNum.equals("0")) {
                System.out.println("����ó �߰� ������ �����մϴ�.");
                return;
            }

            // ����ó �����ϱ�
            // FireWriter�� �ι�° ���ڷ� true�� �Ѱ��ָ� fw.wurte()�� ���� ���� �ڷ� ��� �߰�����.
            FileWriter fw = new FileWriter(file, true);
            fw.write(++phoneNumCnt + " " + name + " " + age + " " + phoneNum + "\n");
            fw.flush();
        }
    }

    // Method - To check whether the entered age value satisfies validation.
    private static String input_age_with_validation() throws IOException{
        // �����迡�� ���� ���� �� ����� ���̰� 122���̶� 1��~122������� ������ �Է¹��� �� �ִ�. (�� �ܿ� NumberFormatException �߻�)
        try {
            String age = mr.readNextString();
            if (Integer.parseInt(age)==0) return "0";// add_juso �޼ҵ�� ���ͽ� �����.
            else if (Integer.parseInt(age) < 1 || Integer.parseInt(age) > 122) throw new NumberFormatException();
            else return age;
        } catch (NumberFormatException e) {
            System.out.print("���̰� �ùٸ��� �ʽ��ϴ�. ���̸� �ٽ� �Է��ϼ���: ");
            return input_age_with_validation();
        }
    }

    // Method - To check whether the entered phoneNum value satisfies validation.
    private static String input_phoneNum_with_validation() throws IOException{
        // 010-xxxx-xxxx�� ����� �����ϴ� ���� �Է¹��� �� �ִ�. (�� �ܿ� Exception �߻�)
        try {
            String phoneNum = mr.readNextString();
            if(phoneNum.equals("0")) return "0";
            else if (phoneNum.length() == 13 && phoneNum.charAt(3) == '-' && phoneNum.charAt(8) == '-') return phoneNum;
            else throw new Exception();
        } catch (Exception e) {
            System.out.print("��ȭ��ȣ �Է� ��Ŀ� ���� �ٽ� �Է����ּ���.(ex. 010-1234-1234) : ");
            return input_phoneNum_with_validation();
        }
    }

    // 3. ����ڰ� ������ ����ó�� juso.txt ���Ͽ��� ���� ==========================================
    public static void delete_juso() throws IOException{
        // �޴������ֱ�
        System.out.println(); // ���� ���߱� ���� ����
        for (int i = 1; i < DEL_MENU.length + 1; i++) System.out.printf("%d. %s\n", i, DEL_MENU[i - 1]);

        // ���� �޴� �Է� �ޱ�
        System.out.print("\n���� �޴��� �����ϼ���: ");
        switch (checkMenu(DEL_MENU, "�߸��� ���� �޴��Դϴ�. ���� �޴��� �ٽ� �����ϼ���: ")) {
            case 1 -> delete_juso_by(DEL_WORK.NUM);
            case 2 -> delete_juso_by(DEL_WORK.NAME);
            case 3 -> delete_juso_by(DEL_WORK.PHONE);
            // case 4�� "���� �޴��� �̵�"������ �ൿ��  delete_juso�� ����Ǹ� �̾����� �ൿ�� �����Ͽ� ���� ó���� �ʿ䰡 ����.
        }
    }

    // Method - Enum���� ���� ��(num, name, phone)�� ���� ������ ������ �Լ�
    public static void delete_juso_by(DEL_WORK work) throws IOException{
        // ������ ���� �޴��� ���� ������ ����� �о�´�.
        System.out.printf("\n������ ����ó %s? ",
                work == DEL_WORK.NUM ? "������" :
                work == DEL_WORK.NAME ? "�̸���" :
                    "��ȣ��"); // work == DEL_WORK.PHONE
        String val = mr.readNextString();

        // 0�� �Է¹����� �������� ����ǵ��� ó��
        if(val.equals("0")) {
            System.out.println("����ó ���� ������ �����մϴ�.");
            return;
        }

        // initialize
        // ( while�� ������ ������ ���� �� �޸𸮰��� ������ Ŀ while�� �ۿ� ���� ���� ��ġ)
        int loop = 0; // ����ó ������ ���� index�� �ۼ��ϱ� ����.
        String line = ""; // ���Ϸ� ���� ���� �� ���� ������ ����.
        StringTokenizer st; // line�� ���鿡 ���� �и��ϱ� ����.
        StringBuilder sb = new StringBuilder(); // �������� ���� ����ó�� �ѹ��� ������ ����.
        BufferedReader fileBr = new BufferedReader(new FileReader(file)); // ������ �б� ����.
        boolean isDeleted = false; // ������ ����ó�� �ִ��� üũ

        // ���� �б� (���̻� ���� ���� ���� ������ line�� �� ���� ������ ����)
        while ((line = fileBr.readLine()) != null) {
            // ���Ϸκ��� index, name, age, phoneNum�� �����´�.
            st = new StringTokenizer(line);
            String index = st.nextToken();
            String name = st.nextToken();
            String age = st.nextToken();
            String phoneNum = st.nextToken();

            // work�� ���� ��ġ�ϴ� ���� �׿� �´� ���� ����� �����ڷ�ó�� �����.
            if ((work == DEL_WORK.NUM && Objects.equals(index, val))
                || (work == DEL_WORK.PHONE && Objects.equals(phoneNum, val))){
                    isDeleted = true;
                    System.out.printf("%s�� ����ó�� �����Ǿ����ϴ�.\n",index);
            } else if (work == DEL_WORK.NAME && Objects.equals(name, val)) {
                isDeleted = true;
                System.out.printf("%s ����ó�� �����Ǿ����ϴ�.\n", name);
            } else {
                // ���ο� ���� sb�� �߰�����.
                sb.append(++loop+ " " + name + " " + age + " " + phoneNum + "\n");
            }
        }

        if (isDeleted) {
            // ������ ���������� �̷����ٸ� ������ ������ ���ܵ� ������ ���Ͽ� �Է��ϰ� ����
            BufferedWriter fw = new BufferedWriter(new FileWriter(file));
            fw.write(sb.toString()); // ���� ������ �Է� ���ϱ�
            fw.flush();
            // phoneNumCnt(����ó�� �� ����)�� �缳�����ش�. (add�Ҷ� �ִ��� ������ ������ view, delete ��ο��� �缳�� ������ ��ģ��)
            phoneNumCnt = loop;
        } else {
            // ������ ����ó�� ���ٸ� �̸� ����ڿ��� �˸��� �ش� �Լ��� �ٽ� �����Ŵ.
            System.out.println("�������� �ʴ� ����ó�Դϴ�.");
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
