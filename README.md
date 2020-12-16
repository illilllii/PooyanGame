# PooyanGame

## PPT (그림을 클릭하세요)
ppt 이미지 삽입
## 시연영상 (그림을 클릭하세요)
video 이미지 삽입

푸얀(플레이어)
푸얀의 기능
1. 상하 이동
2. 아이템 획득(고기 폭탄)
3. 공격
### 1. 상하 이동
* 키보드 방향키 ↑, ↓
* 독립적인 쓰레드 사용
* 푸얀의 위치에 따라 줄의 길이 변경

`protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 216, 225));
		g2.setStroke(new BasicStroke(3));
		g2.drawLine(jpPlayer.getLocation().x + 35, 100, jpPlayer.getLocation().x + 35, jpPlayer.getLocation().y);
	}`
	
고기 폭탄 획득 시 +200
2. 공격
화살 공격 -> 맞으면 +200
고기폭탄 공격 -> 맞으면 +400
중력이 작용해 포물선을 그리면서 떨어짐

적(늑대)
늑대의 기능
1. 자동 이동
오른쪽 -> 아래 -> 오른쪽 -> 위쪽
2. 공격
근거리 공격(사다리 공격)
장거리 공격(폭탄 던지기 공격)
