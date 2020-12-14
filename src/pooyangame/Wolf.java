package pooyangame;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Wolf extends JLabel {
	private static final long serialVersionUID = 1L;
	public Wolf wolf = this;
	private final static String TAG = "Wolf : ";

	private PooyanApp pooyanApp;
	private Pooyan pooyan;
	private int floor = 0;
	
	private ImageIcon iconWolfM4, iconWolfM5, iconWalkWolfR, iconAttackStayWolf, iconAttackStayWolfR, iconAttackWolf1,
			iconAttackWolf2, iconBallonMint, iconBallonMintPop1, iconBallonMintPop2, iconBallonMintPop3 ,iconFallingWolf1, iconFallingWolf2,
			iconDieWolf;
	public int x = 0;
	public int y = -30;
	public boolean isDown = false;
	public boolean isRight = false;
	public boolean isRightGround = false;
	public boolean isUp = false;
	public boolean isAttack = false;

	public boolean wolfStatus = true;
	
	public int rand;

	public JLabel laBallonMint;
	
	public Wolf(PooyanApp pooyanApp, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.pooyan = pooyan;
		
		iconWolfM4 = new ImageIcon("images/WolfMint4.png");
		iconWolfM5 = new ImageIcon("images/WolfMint5.png");
		iconWalkWolfR = new ImageIcon("images/walkWolfR.gif");
		iconAttackStayWolf = new ImageIcon("images/attackStayWolf.gif");
		iconAttackStayWolfR = new ImageIcon("images/attackStayWolfR.png");
		iconAttackWolf1 = new ImageIcon("images/attackWolf1.png");
		iconAttackWolf2 = new ImageIcon("images/attackWolf2.png");
		iconBallonMint = new ImageIcon("images/ballonMint.png");
		iconBallonMintPop1 = new ImageIcon("images/ballonMintPop1.png");
		iconBallonMintPop2 = new ImageIcon("images/ballonMintPop2.png");
		iconBallonMintPop3 = new ImageIcon("images/ballonMintPop3.png");
		iconFallingWolf1 = new ImageIcon("images/fallingWolf1.png");
		iconFallingWolf2 = new ImageIcon("images/fallingWolf2.png");
		iconDieWolf = new ImageIcon("images/dieWolf.png");
		laBallonMint = new JLabel(iconBallonMint);
		
		setIcon(iconWolfM4);
		setSize(130, 130);
		setLocation(x, y);
		rand = (int) (Math.random() * 300) + 20;
		moveRight();
	}

	public void attackedFall() {
		while(true) {
			try {
				setIcon(iconFallingWolf1);
				y=y+3;
				Thread.sleep(10);
				setIcon(iconFallingWolf2);
				y=y+3;
				Thread.sleep(10);
				if(y>490) {
					setIcon(iconDieWolf);
					Thread.sleep(1000);
					pooyanApp.remove(this);
					pooyanApp.repaint();
					pooyanApp.wolves.remove(this);
					pooyanApp.count--;
					System.out.println(pooyanApp.count);
					pooyanApp.remainWolf --;
					pooyanApp.laRemainWolf.setText(""+pooyanApp.remainWolf);
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void moveFall() {
//		System.out.println(TAG + "moveFall");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					setIcon(iconWolfM4);
					while (isDown) {
						if (y > 490) {
							isDown = false;
							isRightGround = true;
							wolf.moveRight();
							setIcon(iconWalkWolfR);
							break;
						}
						if (wolfStatus == false) {
							break;
						}
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	public void moveRight() {
//		System.out.println(TAG + "moveRight");
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				setIcon(iconWalkWolfR);
				while (isRight) {
					if(wolfStatus == false) {
						break;
					}
					if (y==-30) { // 위에서 이동
						if (x >= rand) {
							isRight = false;
							moveFall();
							break;
						}
						x++;
						setLocation(x, y);
						
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (y > 490 && wolfStatus == true) { // 밑에서 이동
						if (x > 540) {
							isRight = false;
							if(pooyanApp.floor<4) {
								isUp = true;
								pooyanApp.floor = pooyanApp.floor + 1;
								floor = pooyanApp.floor;
								wolf.moveUP();
								break;
							} else {
								pooyanApp.remove(wolf);
								pooyanApp.wolves.remove(wolf);
								pooyanApp.repaint();
								pooyanApp.count--;
								System.out.println("늑대 " + pooyanApp.wolves.size());
								break;
							}
							
						}
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

	public void moveUP() {
		 System.out.println(TAG + "moveUp");

		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(iconAttackStayWolf);
				while (isUp) {
					if(wolfStatus == false) {
						break;
					}
					if (floor == 1 && y < 400 && wolfStatus == true) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 2 && y < 320 && wolfStatus == true) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 3 && y < 230 && wolfStatus == true) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					if (floor == 4 && y < 150 && wolfStatus == true) {
						isUp = false;
						isAttack = true;
						wolf.attack();
						break;
					}
					y--;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void attack() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(iconAttackStayWolfR);
				while (isAttack) {
					try {
						System.out.println(TAG+"공격");
						Thread.sleep(5000); // 5초마다 공격
						setIcon(iconAttackWolf1);
						x=500;
						setLocation(x, y);
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackWolf2);
						// 플레이어 좌표가 울프의 근접공격 좌표와 같아지면 플레이어 죽음
						if(x <= pooyan.x +50 ) {
							if(y+30 >= pooyan.y && y+30 <= pooyan.y +50) {
								pooyanApp.reset();
								break;
							}
						}
						if(wolfStatus==false) {
							break;
						}
						Thread.sleep(800); // 공격 모션 딜레이
						setIcon(iconAttackStayWolfR);
						x=541;
						setLocation(x, y);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}).start();
	}
}
