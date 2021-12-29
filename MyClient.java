import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// ���y�Đ����ɕK�v
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import java.lang.Math;

// import java.awt.Point;
// import java.awt.Color;
// import java.awt.BorderLayout;


public class MyClient extends JFrame implements MouseListener,MouseMotionListener {
	private JButton buttonArray[][];
  private JButton passButton;
  private JButton resetButton;
  private int myColor;
  private int myTurn;
  private ImageIcon myIcon, yourIcon, s_myIcon, s_yourIcon;
	// private Container c;
  private JLayeredPane c;
  private boolean mySelect;
	// private ImageIcon blackIcon, whiteIcon, boardIcon;
  private ImageIcon redIcon, blueIcon, orangeIcon, catIcon, s_redIcon, s_blueIcon, s_orangeIcon, s_catIcon, bgImg;
	PrintWriter out;
  JLabel theLabel1;
  JLabel theLabel2;
  JLabel theLabel3;
  JLabel bg;
  private JButton guideArray[][];
  private JButton myArray[];
  private JButton yourArray[];
  //JLayeredPane layerPane;

  SoundPlayer theSoundPlayer2;

  int boardIconCount = 0;
  int whiteIconCount = 0;
  int blackIconCount = 0;  

	public MyClient() {
		// ���O�̓��̓_�C�A���O���J��
		String myName = JOptionPane.showInputDialog(null,"���O����͂��Ă�������","���O�̓���",JOptionPane.QUESTION_MESSAGE);
		if(myName.equals("")){
			myName = "No name";// ���O���Ȃ��Ƃ��́C"No name"�Ƃ���
		}

		String IPAddress = JOptionPane.showInputDialog(null,"IP�A�h���X����͂��Ă�������","IP�A�h���X�̓���",JOptionPane.QUESTION_MESSAGE);
		if(IPAddress.equals("")){
			IPAddress = "localhost";// ���O���Ȃ��Ƃ��́C"localhoat"�Ƃ���
		}

		// �E�B���h�E���쐬����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
		setTitle("MyClient");// �E�B���h�E�̃^�C�g����ݒ肷��
		setSize(1200, 850);// �E�B���h�E�̃T�C�Y��ݒ肷��
		// c = getContentPane();// �t���[���̃y�C�����擾����
    c = new JLayeredPane(); 
    this.getContentPane().add(c);
    c.setBackground(Color.RED);// �E�B���h�E�̐F�̐ݒ�

    // board�̐ݒ�
    bgImg = new ImageIcon("img/board1.png");
    bg = new JLabel(bgImg);
    c.add(bg);
    bg.setBounds(250, 50,700,700);
    c.setLayer(bg,0);


		// �A�C�R���̐ݒ�
    redIcon = new ImageIcon("img/zibun1.png");
    blueIcon = new ImageIcon("img/aite1.png");
    orangeIcon = new ImageIcon("img/masu1.png");
    catIcon = new ImageIcon("img/cat21.png");

    s_redIcon = new ImageIcon("img/s-zibun1.png");
    s_blueIcon = new ImageIcon("img/s-aite1.png");
    s_orangeIcon = new ImageIcon("img/s-masu1.png");
    s_catIcon = new ImageIcon("img/s-cat21.png");

		c.setLayout(null);// �������C�A�E�g�̐ݒ���s��Ȃ�

    // yourArray = new  JButton[5];
    // for(int i=0; i<5; i++){
    //   yourArray[i] = new JButton(blueIcon);
    //   c.add(yourArray[i]);
    //   yourArray[i].setBounds(i*100+600-80*(i+1)+60*i+80, i*80+20, 70, 70);
    //   yourArray[i].setContentAreaFilled(false);
    //   yourArray[i].setBorderPainted(false);
    //   yourArray[i].addMouseListener(this);
    //   // layerPane.add(yourArray[i]);
		// 	// layerPane.setLayer(yourArray[i] , i);
    // }

    // myArray = new  JButton[5];
    // for(int i=0; i<5; i++){
    //   myArray[i] = new JButton(redIcon);
    //   c.add(myArray[i]);
    //   myArray[i].setBounds(i*100+115-80*(i+1)+60*i+80, i*80+500, 70, 70);
    //   myArray[i].setContentAreaFilled(false);
    //   myArray[i].setBorderPainted(false);
    //   myArray[i].addMouseListener(this);
    //   // layerPane.add(myArray[i]);
		// 	// layerPane.setLayer(myArray[i], i);
    // }

    // �K�C�h�̐���ver2
		guideArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        guideArray[j][i] = new JButton(orangeIcon);
        c.add(guideArray[j][i]);
        guideArray[j][i].setBounds(i*70+530-70*j,j*70+5+70*i,140,140);
        guideArray[j][i].addMouseListener(this);
        c.setLayer(guideArray[j][i],j*5+i+1);
        guideArray[j][i].setActionCommand(Integer.toString(j*5+i));
        //guideArray[j][i].setContentAreaFilled(false);
        guideArray[j][i].setBorderPainted(false);
      }
		}

    guideArray[0][1].setIcon(redIcon);
    guideArray[0][2].setIcon(redIcon);
    guideArray[0][3].setIcon(blueIcon);
    guideArray[0][4].setIcon(blueIcon);

    // �T�[�o�ɐڑ�����
		Socket socket = null;
		try {
		  // "localhost"�́C���������ւ̐ڑ��Dlocalhost��ڑ����IP Address�i"133.42.155.201"�`���j�ɐݒ肷��Ƒ���PC�̃T�[�o�ƒʐM�ł���
			// 10000�̓|�[�g�ԍ��DIP Address�Őڑ�����PC�����߂āC�|�[�g�ԍ��ł���PC�㓮�삷��v���O��������肷��
			socket = new Socket(IPAddress, 10000);
		} catch (UnknownHostException e) {
			System.err.println("�z�X�g�� IP �A�h���X������ł��܂���: " + e);
		} catch (IOException e) {
			System.err.println("�G���[���������܂���: " + e);
		}

		MesgRecvThread mrt = new MesgRecvThread(socket, myName);//��M�p�̃X���b�h���쐬����
		mrt.start();// �X���b�h�𓮂����iRun�������j
	}
		
	// ���b�Z�[�W��M�̂��߂̃X���b�h
	public class MesgRecvThread extends Thread {
		Socket socket;
		String myName;
		
		public MesgRecvThread(Socket s, String n){
			socket = s;
			myName = n;
		}
		
	  // �ʐM�󋵂��Ď����C��M�f�[�^�ɂ���ē��삷��
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(myName);//�ڑ��̍ŏ��ɖ��O�𑗂�
        String myNumberStr = br.readLine();
        int myNumberInt = Integer.parseInt(myNumberStr);

        if(myNumberInt % 2 != 0){
          myColor=0;// player1:red
          myTurn=1;// ��s
          mySelect=false;
          myIcon=redIcon;
          s_myIcon=s_redIcon;
          yourIcon=blueIcon;
          s_yourIcon=s_blueIcon;
        }else{
          myColor=1;// player2:blue
          myTurn=0;// ��U
          myIcon=blueIcon;
          mySelect=false;
          s_myIcon=s_blueIcon;
          yourIcon=redIcon;
          s_yourIcon=s_redIcon;
        }

				while(true) {
					String inputLine = br.readLine();
          System.out.println("inputline="+inputLine);
					if (inputLine != null) {
						String[] inputTokens = inputLine.split(" ");
						String cmd = inputTokens[0];

            // // �p�X�@�\
            // if(cmd.equals("PASS")){
            //   myTurn = 1 - myTurn;
            //   theLabel2.setText("<html>�{�^���p�X�����ł��I</html>");
            //   System.out.println("�{�^���p�X����");
            //   if(myTurn==1){
            //       theLabel2.setText("<html>���Ȃ��̔Ԃł��I</html>");
            //       System.out.println("���Ȃ��̔Ԃł�");
            //     }else{
            //       theLabel2.setText("<html>����̔Ԃł��I</html>");
            //       System.out.println("����̔Ԃł�");
            //   }
            // }
            // // ���Z�b�g�@�\
            // if(cmd.equals("RESET")){
            //   for(int j=0;j<8;j++){
            //     for(int i=0; i<8; i++){
            //     buttonArray[j][i].setIcon(boardIcon);
            //     }
            //   }
            //   buttonArray[3][3].setIcon(whiteIcon);
            //   buttonArray[3][4].setIcon(blackIcon);
            //   buttonArray[4][3].setIcon(blackIcon);
            //   buttonArray[4][4].setIcon(whiteIcon); 
            //   if(myTurn==1){
            //       System.out.println("���Ȃ��̔Ԃ���͂��܂��");
            //     }else{
            //       System.out.println("����̔Ԃ���͂��܂��I");
            //   }
            //   System.out.println("���Z�b�g����");
            // }
         
            if(cmd.equals("SELECT")){
              String theBName = inputTokens[1];
              int theBnum = Integer.parseInt(theBName);
              int x = theBnum % 5;
              int y = theBnum / 5;
              int theColor = Integer.parseInt(inputTokens[2]);
              Icon theIcon = guideArray[y][x].getIcon();
              if(theColor == myColor){
                for(int j=0;j<5;j++){
                  for(int i=0; i<5; i++){
                    if(guideArray[j][i].getIcon() == s_myIcon){
                      guideArray[j][i].setIcon(myIcon);
                    }
                  }
                }
                if(theIcon == myIcon){
                  guideArray[y][x].setIcon(s_myIcon);
                }else{
                  guideArray[y][x].setIcon(myIcon);
                }
              }else{
                for(int j=0;j<5;j++){
                  for(int i=0; i<5; i++){
                    if(guideArray[j][i].getIcon() == s_yourIcon){
                      guideArray[j][i].setIcon(yourIcon);
                    }
                  }
                }
                if(theIcon == yourIcon){
                  guideArray[y][x].setIcon(s_yourIcon);
                }else{
                  guideArray[y][x].setIcon(yourIcon);
                }                
              }
            }


            // // �Ђ�����Ԃ��@�\
            // if(cmd.equals("PLACE")){
            //   String theBName = inputTokens[1];
            //   int theBnum = Integer.parseInt(theBName);
            //   int i = theBnum / 8;
            //   int j = theBnum % 8;
            //   int theColor = Integer.parseInt(inputTokens[2]);
              
            //   if(theColor==myColor){
            //     buttonArray[i][j].setIcon(myIcon);
            //     theSoundPlayer2.play();
            //   }else{
            //     buttonArray[i][j].setIcon(yourIcon);
            //   }
            //   colorCount();
            //   theLabel1.setText("<html>���̐��F"+whiteIconCount+"</html>");
            //   theLabel3.setText("<html>���̐��F"+blackIconCount+"</html>");

            //   if (passJudgeCount() != 0){
            //     myTurn = 1 - myTurn;
            //     if(myTurn==1){
            //       theLabel2.setText("<html>���Ȃ��̔Ԃł��I</html>");
            //       System.out.println("���Ȃ��̔Ԃł�");
            //     }else{
            //       theLabel2.setText("<html>����̔Ԃł��I</html>");
            //       System.out.println("����̔Ԃł�");
            //     }
            //   }else{
            //     theLabel2.setText("<html>�����p�X�����I</html>");
            //     System.out.println("�����p�X����");
            //     if(endJudgeCount() == 0){
            //       System.out.println("�I��");
            //       colorCount();
            //       theLabel1.setText("<html>���̐��F"+whiteIconCount+"</html>");
            //       theLabel3.setText("<html>���̐��F"+blackIconCount+"</html>");
            //       System.out.println("���̐�"+whiteIconCount);
            //       System.out.println("���̐�"+blackIconCount);
            //       winJudge();// ���s������s���A�e�L�X�g�\��
            //     }
            //   }
            // }

            // // FLIP�@�\
            // if(cmd.equals("FLIP")){
            //   String theBName = inputTokens[1];//�{�^���̖��O�i�ԍ��j�̎擾
            //   int theBnum = Integer.parseInt(theBName);//�{�^���̖��O�𐔒l�ɕϊ�����
            //   int i = theBnum / 8;
            //   int j = theBnum % 8;
            //   int theColor = Integer.parseInt(inputTokens[2]);//���l�ɕϊ�����
            //   if(theColor==myColor){
            //     buttonArray[i][j].setIcon(myIcon);//blackIcon�ɐݒ肷��
            //   }else{
            //     buttonArray[i][j].setIcon(yourIcon);
            //   }
            // }
					}else{
						break;
					}
				}
				socket.close();
			} catch (IOException e) {
				System.err.println("�G���[���������܂���: " + e);
			}
		}
	}


	public static void main(String[] args) {
		MyClient net = new MyClient();
		net.setVisible(true);
	}
  	
	public void mouseClicked(MouseEvent e) {
    System.out.println("�N���b�N");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    System.out.println("theArrayIndex="+temp);
    
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    //�R�}�̑I����Ԃ�����
    if(theIcon == myIcon || theIcon == s_myIcon){
        String msg = "SELECT"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
    }

    //label.setText("x:" + point.x + ",y:" + point.y);
    // if(theArrayIndex.equals("RESET")){
    //   String msg = "RESET";
    //   out.println(msg);
    //   out.flush();
    // }     
    // if(myTurn==1){
    //   if(theArrayIndex.equals("PASS")){
    //     String msg = "PASS";
    //     out.println(msg);
    //     out.flush();
    //   }     

    if(theIcon == orangeIcon){
      //int temp = Integer.parseInt(theArrayIndex);
      //System.out.println("theArrayIndex="+temp);
      // int x = temp % 8;
      // int y = temp / 8;

      //System.out.println("judgeButton="+judgeButton(y, x));
      // if(judgeButton(temp)){
      //   //�u����
      //   System.out.println("���s");
      //   theSoundPlayer2 = new SoundPlayer("443_2.wav");
      //   String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
      //   out.println(msg);
      //   out.flush();
      // } else {
      //   //�u���Ȃ�
      //   System.out.println("�����ɂ͔z�u�ł��܂���");
      // }
    }
  repaint();//��ʂ̃I�u�W�F�N�g��`�悵����
  // }
	}
	
	public void mouseEntered(MouseEvent e) {
    System.out.println("�z�o�[");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    // if (theIcon == myIcon){
    //   guideArray[y][x].setIcon(catIcon);
    // }
    c.setLayer(guideArray[y][x],1000);

	}
	
	public void mouseExited(MouseEvent e) {
    System.out.println("�z�o�[�����");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    //guideArray[y][x].setIcon(orangeIcon);
    c.setLayer(guideArray[y][x],y*5+x+1);
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

  // �u����Ֆʂ��ǂ����𔻒肷��֐�
  // public boolean judgeButton(int temp) {
  //   boolean flag = false;

  //   int x = temp % 5;
  //   int y = temp / 5;
    
  //   for (int j=-1;j<2;j++){
  //     for (int i=-1;i<2;i++){
  //       int posX = x + i;
  //       int posY = y + j;

  //       if(isExceededArea(posX, posY)){
  //         continue;
  //       }

  //       Icon theIcon = buttonArray[posY][posX].getIcon();
  //       int flipNum = flipButtons(y, x, j, i, true);        

  //       if(flipNum >= 1){
  //         flag=true;
  //         for(int dy=j, dx=i, k=0; k<flipNum; k++, dy+=j, dx+=i){
  //           //�{�^���̈ʒu�������
  //           int msgy = y + dy;
  //           int msgx = x + dx;
  //           int theArrayIndex = msgy*8 + msgx;
  //           //�T�[�o�ɏ��𑗂�
  //           String msg = "FLIP"+" "+theArrayIndex+" "+myColor;
  //           out.println(msg);
  //           out.flush();
  //         }
  //       }        
  //     }
  //   }
  //   return flag;
  // }

  // �Ђ�����Ԃ����Ƃ̂ł���Ֆʂ̌���Ԃ��֐�
  // ����s�Ő�����A�C�R���̎�ނ�ύX�Atrue�Ȃ�yourIcon�Afalse�Ȃ�myIcon�𐔂���
  // public int flipButtons(int y, int x, int j, int i, boolean s){
  //   int flipNum = 0;
  //   if ( (j==0) && (i==0) ) return 0;
  //   for(int dy=j, dx=i; ; dy+=j, dx+=i) {
  //     int posY = y + dy;
  //     int posX = x + dx;

  //     if(isExceededArea(posY, posX)){
  //       return 0;
  //     }

  //     Icon theIcon = buttonArray[posY][posX].getIcon();

  //     if(s){
  //       if(theIcon == boardIcon){
  //         flipNum = 0;
  //         break;
  //       }else if(theIcon == myIcon){
  //         break;
  //       }else if (theIcon == yourIcon){
  //         flipNum++;
  //       }
  //     }else{
  //       if(theIcon == boardIcon){
  //         flipNum = 0;
  //         break;
  //       }else if(theIcon == yourIcon){
  //         break;
  //       }else if (theIcon == myIcon){
  //         flipNum++;
  //       }
  //     }
  //   }
  //   return flipNum;
  // }

  // ���W��8�~8�̃}�X�ڂ𒴂��Ă��邩�ǂ����𔻒肷��֐�
  // public boolean isExceededArea(int posX, int posY){
  //   return posX < 0 || posY < 0 || posX > 5 || posY > 5;
  // }

  // // ���A���A�{�[�h�A���ꂼ��̐��𐔂���֐�
  // public void colorCount(){
  //   boardIconCount = 0;
  //   whiteIconCount = 0;
  //   blackIconCount = 0;
  //   for(int ia = 0; ia <8; ia++){
  //     for(int ja = 0; ja < 8; ja++){
  //       Icon theIcon = buttonArray[ia][ja].getIcon();
  //       if(theIcon == boardIcon){
  //         boardIconCount ++;
  //       }else if(theIcon == whiteIcon){
  //         whiteIconCount ++;
  //       }else if(theIcon == blackIcon){
  //         blackIconCount ++;
  //       }
  //     }
  //   }
  // }

  // // ���s������s���A�e�L�X�g�\������֐�
  // public void winJudge(){
  //   if(whiteIconCount==blackIconCount){
  //     theLabel2.setText("<html>�Ђ��킯����I</html>");
  //     System.out.println("�Ђ��킯");
  //   }else if(whiteIconCount>blackIconCount){
  //     if(myIcon == whiteIcon){
  //       theLabel2.setText("<html>�����I���߂łƂ��I</html>");
  //       System.out.println("����");
  //     }else{
  //       theLabel2.setText("<html>�����I�c�O�I</html>");
  //       System.out.println("����");
  //     }
  //   }else{
  //     if(myIcon == blackIcon){
  //       theLabel2.setText("<html>�����I���߂łƂ��I</html>");
  //       System.out.println("����");
  //     }else{
  //       theLabel2.setText("<html>�����I�c�O�I</html>");
  //       System.out.println("����");
  //     }
  //   }
  // }


  // // �p�X�𔻒�Ɏg���֐�
  // public boolean passJudge(int y, int x, boolean s) {
  //   boolean flag = false;
  //   int flipNum = 0;
  //   for (int j=-1; j<=1; j++){
  //     for (int i=-1; i<=1; i++){
  //       if ( (j==0) && (i==0) ) continue;
  //       if(s){
  //         if(myTurn==1){
  //           flipNum = flipButtons(y, x, j, i, false);
  //         }else{
  //           flipNum = flipButtons(y, x, j, i, true);
  //         }
  //       }else{
  //         if(myTurn==1){
  //           flipNum = flipButtons(y, x, j, i, true);
  //         }else{
  //           flipNum = flipButtons(y, x, j, i, false);
  //         }
  //       }
  //       if (flipNum >= 1){
  //         flag = true;
  //         break;
  //       }
  //     }
  //   }
  //   return flag;
  // }

  // // �Q�[���̏I���𔻒肷�邽�߂̃J�E���g�֐�
  // // 0�Ȃ�Q�[���I��
  // public int endJudgeCount(){
  //   int count = 0;
  //   for (int j = 0; j < 8; j++) {
  //     for (int i = 0; i < 8; i++) {
  //       Icon theIcon = buttonArray[j][i].getIcon();
  //       if((theIcon == boardIcon) && passJudge(j, i, false)) {
  //         count++;
  //       }
  //     }
  //   }
  //   System.out.println("judgeCount="+count);
  //   return count;
  // }

  // // �p�X�ł��邩���肷�邽�߂̃J�E���g�֐�
  // // �O�Ȃ�ς��A����ȊO�Ȃ玟�̐l��
  // public int passJudgeCount(){
  //   int count = 0;
  //   for (int j = 0; j < 8; j++) {
  //       for (int i = 0; i < 8; i++) {
  //         Icon theIcon = buttonArray[j][i].getIcon();
  //         if((theIcon == boardIcon) && passJudge(j, i, true)) {
  //               count++;
  //           }
  //       }
  //   }
  //   return count;
  // }
  
  // ���y�Đ�
  public class SoundPlayer{
    private AudioFormat format = null;
    private DataLine.Info info = null;
    private Clip clip = null;
    boolean stopFlag = false;
    Thread soundThread = null;
    private boolean loopFlag = false;

    public SoundPlayer(String pathname){
      File file = new File(pathname);
      try{
        format = AudioSystem.getAudioFileFormat(file).getFormat();
        info = new DataLine.Info(Clip.class, format);
        clip = (Clip) AudioSystem.getLine(info);
        clip.open(AudioSystem.getAudioInputStream(file));
      }catch(Exception e){
        e.printStackTrace();
      }
    }

    public void SetLoop(boolean flag){
      loopFlag = flag;
    }

    public void play(){
      soundThread = new Thread(){
        public void run(){
          long time = (long)clip.getFrameLength();//44100�Ŋ���ƍĐ����ԁi�b�j���ł�
          long endTime = System.currentTimeMillis()+time*1000/44100;
          clip.start();
          while(true){
            if(stopFlag){//stopFlag��true�ɂȂ����I��
              clip.stop();
              return;
            }
            if(endTime < System.currentTimeMillis()){//�Ȃ̒������߂�����I��
              if(loopFlag) {
                clip.loop(1);//�������[�v�ƂȂ�
              } else {
                clip.stop();
                return;
              }
            }
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      };
      soundThread.start();
    }

    public void stop(){
      stopFlag = true;
    }
  }
}