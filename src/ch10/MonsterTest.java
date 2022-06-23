package ch10;

public class MonsterTest {
    public static void main(String[] args) {
        Monster orc = new Monster("��ũ");
        Monster skeleton = new Monster("�ذ�");
        Monster.battle(orc, skeleton);
    }

}

class Monster {
    private String name;
    private int hp;
    private static int maxHp = 30;   // ��� ������ �ִ� ü��
    public String getName(){return name;}
    public void setHp(int hp){this.hp = hp;}
    public int getHp(){return hp;}
    public static void setMaxHp(int maxHp){Monster.maxHp = maxHp;}
    public static int getMaxHp(){return maxHp;}

    public Monster(String name) {
       /* �����ڸ� �ϼ��Ͻÿ� */
        this.name = name;
        this.hp=maxHp;
    }
    public void attack(Monster enemy) {
        /* �ν��Ͻ� �޼ҵ带 �ϼ��Ͻÿ� */
        enemy.setHp(enemy.getHp()-10);
        System.out.printf("[%s]�� ���� -> %s�� ü��: %d/%d\n",this.name, enemy.getName(), enemy.getHp(), getMaxHp());
    }
    public static void battle(Monster a, Monster b) {
       while (a.hp >0 && b.hp >0) {
           Monster attacker = (Math.random() < 0.5) ? a: b;
           Monster defender = (attacker == a) ? b : a;
           /* Ŭ���� �޼ҵ带 �ϼ��Ͻÿ� */
           attacker.attack(defender);
        }
    }
}