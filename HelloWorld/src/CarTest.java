import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("자동차일련번호 : ");
        int carSn = scan.nextInt();
        scan.nextLine();
        System.out.println("자동차이름 : ");
        String carName = scan.nextLine();
        System.out.println("자동차가격 : ");
        int carPrice = scan.nextInt();
        scan.nextLine();
        System.out.println("자동차소유자 : ");
        String carOwner = scan.nextLine();
        System.out.println("자동차년식 : ");
        int carYear = scan.nextInt();
        scan.nextLine();
        System.out.println("자동차타입 : ");
        String carType = scan.nextLine();


    }

    public void carInfoInt() {

    }

}
