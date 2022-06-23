package ch10;

public class MonsterTest {
    public static void main(String[] args) {
        Monster orc = new Monster("오크");
        Monster skeleton = new Monster("해골");
        Monster.battle(orc, skeleton);
    }

}

class Monster {
    private String name;
    private int hp;
    private static int maxHp = 30;   // 모든 몬스터의 최대 체력
    public String getName(){return name;}
    public void setHp(int hp){this.hp = hp;}
    public int getHp(){return hp;}
    public static void setMaxHp(int maxHp){Monster.maxHp = maxHp;}
    public static int getMaxHp(){return maxHp;}

    public Monster(String name) {
       /* 생성자를 완성하시오 */
        this.name = name;
        this.hp=maxHp;
    }
    public void attack(Monster enemy) {
        /* 인스턴스 메소드를 완성하시오 */
        enemy.setHp(enemy.getHp()-10);
        System.out.printf("[%s]의 공격 -> %s의 체력: %d/%d\n",this.name, enemy.getName(), enemy.getHp(), getMaxHp());
    }
    public static void battle(Monster a, Monster b) {
       while (a.hp >0 && b.hp >0) {
           Monster attacker = (Math.random() < 0.5) ? a: b;
           Monster defender = (attacker == a) ? b : a;
           /* 클래스 메소드를 완성하시오 */
           attacker.attack(defender);
        }
    }
}