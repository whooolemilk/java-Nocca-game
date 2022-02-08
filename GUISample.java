import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;//Timer�̗��p���ɕK�v

import java.io.File;//���y�Đ����ɕK�v
import javax.sound.sampled.AudioFormat;//���y�Đ����ɕK�v
import javax.sound.sampled.AudioSystem;//���y�Đ����ɕK�v
import javax.sound.sampled.Clip;//���y�Đ����ɕK�v
import javax.sound.sampled.DataLine;//���y�Đ����ɕK�v

public class GUISample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    JButton theButton1;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton[] theButtonMulti;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton theButtonSoundStart1;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton theButtonSoundStart2;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton theButtonSoundStop;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JLabel theLabelA;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    ImageIcon icon1;
    JTextField theTextFieldForScrollBar;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JScrollBar theScrollBar;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton theButtonStart;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JButton theButtonStop;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    Timer timer;//Timer�𗘗p����Dimport javax.swing.Timer��import����K�v������i��ɒǉ�����j
    JProgressBar theProgressBar;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    JLabel theLabelForProgressBar;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    SoundPlayer theSoundPlayer1;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾
    SoundPlayer theSoundPlayer2;//�ǂ�����ł��A�N�Z�X�ł���悤�ɁC�N���X�̃����o�Ƃ��Đ錾

    public GUISample() {
        //�E�B���h�E���쐬����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
        this.setTitle("GUISample");		//�E�B���h�E�̃^�C�g����ݒ肷��
        this.setSize(1000,600);		//�E�B���h�E�̃T�C�Y��ݒ肷��
        Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�
        c.setBackground(Color.ORANGE);//�E�B���h�E�̐F�̐ݒ�

        //�{�^���̐����Ɣz�u
        theButton1 = new JButton("�{�^���̃T���v��");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButton1);//�y�C���ɓ\��t����
        theButton1.setBounds(45,45,140,45);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //�G�̕t�����{�^���̐����Ɣz�u
        icon1 = new ImageIcon("images.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JButton theButton2 = new JButton(icon1);//�{�^�����쐬�C�摜��ݒ肷��
        c.add(theButton2);//�y�C���ɓ\��t����
        theButton2.setBounds(45,90,208,242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton2.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //�{�^���̋�ʂ̕��@�͂���������܂��D���ŋ�ʂ��Ă��܂��܂���D
        //���̉��K�ł́CActionCommand�ŋ�ʂ������g���Ă��܂��D
        //ActionCommand�ŋ��

        //�����{�^���̋�ʂ̗�iActionCommand�ŋ�ʂ̂��߁CsetActionCommand�ɓ���Ă��܂��DmouseClicked���ŁCgetActionCommand���g���Ď��o���Ă��܂��j
        JButton[] theButtonMulti = new JButton[3];
        for( int i = 0; i<3 ; i++){
          theButtonMulti[i] = new JButton("Button "+i);//�{�^�����쐬�C�e�L�X�g�̕\��
          c.add(theButtonMulti[i]);//�y�C���ɓ\��t����
          theButtonMulti[i].setBounds(45+i*90,350,80,25);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
          theButtonMulti[i].addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
          theButtonMulti[i].setActionCommand(Integer.toString(i));//�{�^���ɏ���t������i���̃{�^�����𔻒f���邽�߂ɗ��p�j�CInteger.toString���g���Ă���̂́CsetActionCommand�����������󂯎��Ȃ�����
        }
        theLabelA = new JLabel("���ʕ\�����x��");
        c.add(theLabelA);
        theLabelA.setBounds(320,350,120,25);
        theLabelA.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //���Đ��p�̃{�^���̍쐬
        theButtonSoundStart1 = new JButton("���y�̍Đ�1(BGM)");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButtonSoundStart1);//�y�C���ɓ\��t����
        theButtonSoundStart1.setBounds(45,400,150,25);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButtonSoundStart1.setActionCommand("PlaySound1");
        theButtonSoundStart1.addActionListener(this);

        theButtonSoundStop = new JButton("���y�̒�~");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButtonSoundStop);//�y�C���ɓ\��t����
        theButtonSoundStop.setBounds(200,400,100,25);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButtonSoundStop.setActionCommand("StopSound");
        theButtonSoundStop.addActionListener(this);

        theButtonSoundStart2 = new JButton("���y�̍Đ�2(���ʉ�)");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButtonSoundStart2);//�y�C���ɓ\��t����
        theButtonSoundStart2.setBounds(45,430,150,25);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButtonSoundStart2.setActionCommand("PlaySound2");
        theButtonSoundStart2.addActionListener(this);

        //�F�t�����x���̍쐬
        JLabel theLabel1 = new JLabel("�F�����̃��x��");
        c.add(theLabel1);
        theLabel1.setBounds(275,25,140,25);
        theLabel1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theLabel1.setForeground(Color.BLUE); //�����F�̐ݒ�DColor�̐ݒ�́C���̃y�[�W�����ĉ������@http://www.javadrive.jp/tutorial/color/

        //�w�i�F�t�����x���̍쐬
        JLabel theLabel2 = new JLabel("���F����/���w�i���x��");
        c.add(theLabel2);
        theLabel2.setBounds(275,50,140,25);
        theLabel2.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theLabel2.setForeground(Color.WHITE); //�����F�̐ݒ�DColor�̐ݒ�́C���̃y�[�W�����ĉ������@http://www.javadrive.jp/tutorial/color/
        theLabel2.setBackground(Color.BLACK); //�����̔w�i�F�̐ݒ�D
        theLabel2.setOpaque(true);//���x����s�����ɂ��Ȃ��Ɣw�i�F�������Ȃ��̂ŁC�s�����ɂ��邷��

        //�摜�t�����x���̍쐬
        ImageIcon icon2 = new ImageIcon("images.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JLabel theLabel3 = new JLabel(icon2);
        c.add(theLabel3);
        theLabel3.setBounds(275,90,208,242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theLabel3.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theLabel3.setForeground(Color.WHITE); //�����F�̐ݒ�DColor�̐ݒ�́C���̃y�[�W�����ĉ������@http://www.javadrive.jp/tutorial/color/

        //�摜�t�����x���̍쐬
        ImageIcon icon3 = new ImageIcon("images.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JLabel theLabel4 = new JLabel(icon3);
        c.add(theLabel4);
        theLabel4.setBounds(275,150,208,242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theLabel4.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theLabel4.setForeground(Color.WHITE); //�����F�̐ݒ�DColor�̐ݒ�́C���̃y�[�W�����ĉ������@http://www.javadrive.jp/tutorial/color/
        
        //�e�L�X�g�t�B�[���h�̍쐬
        JTextField theTextField = new JTextField("�e�L�X�g�t�B�[���h");
        c.add(theTextField);
        theTextField.setBounds(500,25,140,25);
        theTextField.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //�e�L�X�g�G���A�̍쐬
        JTextArea theTextArea = new JTextArea("�e�L�X�g�G���A\n�����s����̓���");
        c.add(theTextArea);
        theTextArea.setBounds(500,50,140,80);
        theTextArea.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        
        //�X�N���[���{�b�N�X�̂���e�L�X�g�G���A
        JTextArea theTextArea2 = new JTextArea("�X�N���[���t���̃e�L�X�g�G���A\n�����s����̓���\n1\n2\n3\n4\n5");
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS �����ƃX�N���[���o�[�͕K�v�ȂƂ��ɏo�Ă��܂�
        JScrollPane theScrollPane = new JScrollPane(theTextArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        c.add(theScrollPane);
        theScrollPane.setBounds(650,50,140,80);
        theScrollPane.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���   

        //�`�F�b�N�{�b�N�X�̍쐬
        JPanel theCheckPanel = new JPanel();
        c.add(theCheckPanel);
        JCheckBox theCheck1 = new JCheckBox("White");
        JCheckBox theCheck2 = new JCheckBox("Red");
        JCheckBox theCheck3 = new JCheckBox("Green");
        JCheckBox theCheck4 = new JCheckBox("Blue");
        theCheckPanel.add(theCheck1);//�y�C���ɓ\��t����
        theCheckPanel.add(theCheck2);//�y�C���ɓ\��t����
        theCheckPanel.add(theCheck3);//�y�C���ɓ\��t����
        theCheckPanel.add(theCheck4);//�y�C���ɓ\��t����
        theCheckPanel.setBounds(500,250,300,35);
        theCheckPanel.setBackground(Color.CYAN);
        theCheck1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //���W�I�{�^���̍쐬
        JPanel theRadioPanel = new JPanel();

        c.add(theRadioPanel);
        JRadioButton theRadio1 = new JRadioButton("White");
        JRadioButton theRadio2 = new JRadioButton("Red");
        JRadioButton theRadio3 = new JRadioButton("Green");
        JRadioButton theRadio4 = new JRadioButton("Blue");
        theRadioPanel.add(theRadio1);//�y�C���ɓ\��t����
        theRadioPanel.add(theRadio2);//�y�C���ɓ\��t����
        theRadioPanel.add(theRadio3);//�y�C���ɓ\��t����
        theRadioPanel.add(theRadio4);//�y�C���ɓ\��t����
        theRadioPanel.setBounds(500,300,300,35);
        theRadioPanel.setBackground(Color.green);

        ButtonGroup theRaidoGroup = new ButtonGroup();//�O���[�v�����r���I�ɓ���
        theRaidoGroup.add(theRadio1);
        theRaidoGroup.add(theRadio2);
        theRaidoGroup.add(theRadio3);
        theRaidoGroup.add(theRadio4);

        theRadio1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //���X�g�{�b�N�X�̍쐬�Ɣz�u
        Choice theChoice = new Choice();//���X�g�{�b�N�X�̍쐬
        theChoice.add("��");//���X�g���ڂ̒ǉ�
        theChoice.add("��");//���X�g���ڂ̒ǉ�
        theChoice.add("��");//���X�g���ڂ̒ǉ�
        theChoice.add("��");//���X�g���ڂ̒ǉ�
        c.add(theChoice);//�y�C���ɓ\��t����
        theChoice.setBounds(500,150,140,20);

        //���j���[�o�[�̍쐬
        JMenuBar theMenuBar = new JMenuBar();

        //���j���[�̍쐬
        JMenu theMenu = new JMenu("�F�̑I��");
        theMenuBar.add(theMenu);//���j���[�o�[�ɒǉ�

        //���j���[���ڂ̍쐬
        JMenuItem mi1 = new JMenuItem("White");
        JMenuItem mi2 = new JMenuItem("Red");
        JMenuItem mi3 = new JMenuItem("Green");
        JMenuItem mi4 = new JMenuItem("Blue");

        //���j���[���ڂ̃��j���[�ւ̒ǉ�
        theMenu.add(mi1);
        theMenu.add(mi2);
        theMenu.add(mi3);
        theMenu.add(mi4);

        //���j���[���ڂ�addActionListener���X�i�[�ւ̒ǉ�
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);

        //���j���[�o�[��z�u����
        this.setJMenuBar(theMenuBar);//���j���[�o�[�̔z�u�̓t���[����

        //�X�N���[���o�[
        theScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 5, 0, 105);
        theScrollBar.setPreferredSize(new Dimension(150, 17));
        c.add(theScrollBar);
        theScrollBar.setBounds(500,350,300,20);
        theScrollBar.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        //�X�N���[���o�[�̒l��\�����邨�e�L�X�g�t�B�[���h
        theTextFieldForScrollBar = new JTextField(Integer.toString(theScrollBar.getValue()));
        c.add(theTextFieldForScrollBar);
        theTextFieldForScrollBar.setBounds(820,350,40,20);
        theTextFieldForScrollBar.addActionListener(this);//�A�N�V�����i�e�L�X�g�t�B�[���h�̏ꍇ��Enter�������Ƃ������j, ActionListener��implements����K�v������܂�
        theTextFieldForScrollBar.setActionCommand("ScrollBarChange");

        //�v���O���X�o�[
        theProgressBar = new JProgressBar(1, 100);
        theProgressBar.setValue(50);
        c.add(theProgressBar);
        theProgressBar.setBounds(500,400,300,10);
        theProgressBar.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        //Start�{�^��
        theButtonStart = new JButton("Start");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButtonStart);//�y�C���ɓ\��t����
        theButtonStart.setBounds(840,400,70,20);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButtonStart.addActionListener(this);//�A�N�V�����i�{�^�����������Ƃ������j�D���̑��̃}�E�X�̓����ɂ͔������Ȃ��j�CActionListener��implements����K�v������܂��DaddMouseListener�̏ꍇ�ɂ́CmouseClicked�ɏ����D
        theButtonStart.setActionCommand("ProgressBarStart");//�A�N�V�����R�}���h��ݒ�
        //Stop�{�^��
        theButtonStop = new JButton("Stop");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButtonStop);//�y�C���ɓ\��t����
        theButtonStop.setBounds(920,400,70,20);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButtonStop.addActionListener(this);//�A�N�V�����i�{�^�����������Ƃ������j�D���̑��̃}�E�X�̓����ɂ͔������Ȃ��j�CActionListener��implements����K�v������܂��DaddMouseListener�̏ꍇ�ɂ́CmouseClicked�ɏ����D
        theButtonStop.setActionCommand("ProgressBarStop");//�A�N�V�����R�}���h��ݒ�
        //�v���O���X�o�[�̒l��\�����郉�x��
        theLabelForProgressBar = new JLabel(Integer.toString(theProgressBar.getValue()));
        c.add(theLabelForProgressBar);
        theLabelForProgressBar.setBounds(820,390,40,20);

    }

    public static void main(String[] args) {
        GUISample gui = new GUISample();
        gui.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("�}�E�X���N���b�N����");
        Object theObj = e.getComponent();
        System.out.println(theObj);
        System.out.println("�N���X����" + theObj.getClass().getName());
        String theClass = theObj.getClass().getName();//�N���X�����g���ē�����ς���

        //JButton�N���X�̎��̓���
        if(theClass.equals("javax.swing.JButton")){
            System.out.println("�{�^�����N���b�N");
            //�I�u�W�F�N�g�ŋ�ʂ����
            String theCmd = ((JButton)theObj).getActionCommand();//theObj����xJButton�ɃL���X�g���Ă���CgetActionCommand���Ă�ł��܂��DtheObj�́C�^��Object�Ȃ̂ŁCJButton�ɂ��Ȃ��ƁCgetActionCommand���ĂׂȂ�����
            if(theCmd.equals("0")){
                theLabelA.setText("Click Button "+theCmd);
            }

            //�{�^���ɕt�����e�L�X�g�ŋ�ʂ����
            String theText = ((JButton)theObj).getText();
            if(theText.equals("Button 1")){
                theLabelA.setText("Click "+theText);
            }

            //�{�^���ɕt����ꂽ�C���[�W�iICON�j�ŋ�ʂ����
            Icon theIcon = ((JButton)theObj).getIcon();
            if(theIcon == icon1){
                theLabelA.setText("Click "+theIcon.toString());
            }
        }

        //JLabel�̎��̓���
        if(theClass.equals("javax.swing.JLabel")){
            System.out.println("���x�����N���b�N");
            //���x���͋�ʕ��@�����܂肠��܂���̂ł��C�I�u�W�F�N�g���m�Ŕ�r���āC��ʂ͉\�ł��D
            if(e.getSource()==theLabelA){
                theLabelA.setText("Click LabelA");
            }else{
                theLabelA.setText("Click LabelA�ȊO");
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("�}�E�X��������");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("�}�E�X�������");

        Object theObj = e.getComponent();
        String theClass = theObj.getClass().getName();//�N���X�����g���ē�����ς���

        //Scroll�o�[�̓���
        if(theClass.equals("javax.swing.JScrollBar")){
            //�{���Ȃ�C�����ȊO���������ꍇ�̑Ώ��iTry-catch�������ׂ��j
            //�X�N���[���o�[�̌��݂̒l��\��
            JScrollBar tempScrollBar = (JScrollBar)e.getComponent();
            theTextFieldForScrollBar.setText(Integer.toString(tempScrollBar.getValue()));
        }
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("�}�E�X��������");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("�}�E�X�E�o");
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("�}�E�X���h���b�O");
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("�}�E�X�ړ�");
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("�A�N�V��������");
        System.out.println(e.getSource());
        String theCmd = e.getActionCommand();
        System.out.println("ActionCommand: "+theCmd);
        Object theObj = e.getSource();
        System.out.println("�N���X����" + theObj.getClass().getName());
        String theClass = theObj.getClass().getName();//�N���X�����g���ē�����ς���

        //ActionCommand�̓��e�œ����𐧌䂷��
        if(theCmd.equals("ScrollBarChange")){
            //Scroll�o�[�̈ʒu�𐔎��ɍ��킹��
            JTextField tempTextField = (JTextField)e.getSource();
            int theValue = Integer.parseInt(tempTextField.getText());
            theScrollBar.setValue(theValue);
        }
        //�v���O���X�o�[�̊J�n�����CsetActionCommand�ŁC"ProgressBarStart""��ݒ肵�Ă���
        if(theCmd.equals("ProgressBarStart")){
            theLabelA.setText("ProgressBarStart");

            timer = new Timer(100 , this);
            timer.setActionCommand("timer");
            theProgressBar.setValue(0);
            theLabelForProgressBar.setText("0");
            theButtonStart.setEnabled(false);//Start�{�^�����G��Ȃ��悤�ɂ���
            timer.start();
        }
        //�v���O���X�o�[�̏I�������CsetActionCommand�ŁC"ProgressBarStop"��ݒ肵�Ă���
        if(theCmd.equals("ProgressBarStop")){
            theLabelA.setText("ProgressBarStop");
            timer.stop();
            theButtonStart.setEnabled(true);//Start�{�^�����G���悤�ɂ���
        }
        //���y�Đ�
        if(theCmd.equals("PlaySound1")){
            theSoundPlayer1 = new SoundPlayer("5425.wav");
            theSoundPlayer1.SetLoop(true);//�a�f�l�Ƃ��čĐ����J��Ԃ�
            theSoundPlayer1.play();
        }
        if(theCmd.equals("PlaySound2")){
            theSoundPlayer2 = new SoundPlayer("443_2.wav");
            theSoundPlayer2.play();
        }
        //���y��~
        if(theCmd.equals("StopSound")){
            theSoundPlayer1.stop();
        }
        //Timer�̏���
        if(theCmd.equals("timer")){
            theLabelA.setText("Timer");
            int count = theProgressBar.getValue();
            count = count + 1;
            theProgressBar.setValue(count);
            theLabelForProgressBar.setText(Integer.toString(count));
            if(count>=100){//�^�C�}�[���Ƃ߂�
                timer.stop();
            }
        }

        //���j���[�̑I���ɑΉ���������
        if(theClass.equals("javax.swing.JMenuItem")){
            if(theCmd.equals("White")){
                theButton1.setForeground(Color.WHITE);
            }else if(theCmd.equals("Red")){
                theButton1.setForeground(Color.RED);
            }else if(theCmd.equals("Green")){
                theButton1.setForeground(Color.GREEN);
            }else if(theCmd.equals("Blue")){
                theButton1.setForeground(Color.BLUE);
            }
        }
    }

    //���L�𗘗p���Ă��܂��D�ꕔ���������Ă��܂�(��~�@�\��t��)    �D
    //http://nautilus2580.hatenablog.com/entry/2015/11/07/223457
    //mp3��wav�ɕϊ��́C���L�̃T�C�g�łł��܂�
    //http://online-audio-converter.com/ja/
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
                //clip.setLoopPoints(0,clip.getFrameLength());//�������[�v�ƂȂ�
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
                    System.out.println("PlaySound time="+time);
                    long endTime = System.currentTimeMillis()+time*1000/44100;
                    clip.start();
                    System.out.println("PlaySound time="+(int)(time/44100));
                    while(true){
                        if(stopFlag){//stopFlag��true�ɂȂ����I��
                            System.out.println("PlaySound stop by stopFlag");
                            clip.stop();
                            return;
                        }
                        System.out.println("endTime="+endTime);
                        System.out.println("currentTimeMillis="+System.currentTimeMillis());
                        if(endTime < System.currentTimeMillis()){//�Ȃ̒������߂�����I��
                            System.out.println("PlaySound stop by sound length");
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
            System.out.println("StopSound");
        }

    }
}
