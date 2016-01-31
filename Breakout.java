import java.awt.*;

public class Breakout {
	Breakout(Dimension dim, int fps, double speed) {
		Controller c = new Controller(dim, fps, speed);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		int fps = 30, speed = 3;
		double calculatedSpeed = 0;
		if (args.length > 1) {
			fps = Integer.parseInt(args[0]);
			speed = Integer.parseInt(args[1]);
		}
		else {
			fps = 30;
			speed = 3;
		}
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		switch(speed) {
		case 1:
			calculatedSpeed = 0.15;
			break;
		case 2:
			calculatedSpeed = 0.25;
			break;
		case 3:
			calculatedSpeed = 0.35;
			break;
		case 4:
			calculatedSpeed = 0.45;
			break;
		case 5:
			calculatedSpeed = 0.55;
			break;
		default:
			calculatedSpeed = 0.35;
		}
		Breakout bk = new Breakout(dim, fps, calculatedSpeed);
	}

}
