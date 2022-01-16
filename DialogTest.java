import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;//�摜�����ɕK�v
import java.awt.geom.*;//�摜�����ɕK�v

public class DialogTest extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    public DialogTest() {
        //�E�B���h�E���쐬����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
        this.setSize(1024, 786);		//�E�B���h�E�̃T�C�Y��ݒ肷��
        Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�

        //�{�^���̐���
        JButton theButton1 = new JButton("�_�C�A���O���J��");//�{�^�����쐬
        theButton1.setBounds(245,245,150,30);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton1.setActionCommand("PUSH_Button1");//�{�^�����N���b�N�����Ƃ���actionPerformed��theCmd�Ŏ󂯂Ƃ镶����
        theButton1.addActionListener(this);//�{�^�����N���b�N�����Ƃ���actionPerformed�Ŏ󂯎�邽��

        c.add(theButton1);//�y�C���ɓ\��t����

     }

    public static void main(String[] args) {
        DialogTest gui = new DialogTest();
        gui.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("�}�E�X���N���b�N����");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("�}�E�X��������");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("�}�E�X�������");
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

        //theButton1���������Ƃ��ɁC�_�C�A���O��\������
        if(theCmd.equalsIgnoreCase("PUSH_Button1")){
            WinDialogWindow dlg = new WinDialogWindow(this);
            dlg.add(new JButton());
            setVisible(true);
        }
    }
}

//�_�C�A���O�̂��߂̃N���X
//�G���N���b�N���������悤�ɂ��Ă���
class WinDialogWindow extends JDialog implements ActionListener{
    WinDialogWindow(JFrame owner) {
        super(owner);//�Ăяo�����ƂƂ̐e�q�֌W�̐ݒ�D������R�����g�A�E�g����ƕʁX�̃_�C�A���O�ɂȂ�

		    Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�

        // JButton theButton = new JButton();//�摜��\��t���郉�x��
        // ImageIcon theImage = new ImageIcon("win.jpg");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        // theButton.setIcon(theImage);//���x����ݒ�
        // theButton.setBounds(0,0,526,234);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        // theButton.addActionListener(this);//�{�^�����N���b�N�����Ƃ���actionPerformed�Ŏ󂯎�邽��
        // c.add(theButton);//�_�C�A���O�ɓ\��t����i�\��t���Ȃ��ƕ\������Ȃ�

        setTitle("You Win!");//�^�C�g���̐ݒ�
        setSize(800, 500);//�傫���̐ݒ�
        setResizable(false);//�g��k���֎~//true�ɂ���Ɗg��k���ł���悤�ɂȂ�
        setUndecorated(true); //�^�C�g����\�����Ȃ�
        setModal(true);//������܂ŉ���G��Ȃ�����ifalse�ɂ���ƐG���j

        // JPanel scene4 = new JPanel();
        // c.add(scene4);
        // scene4.setBounds(112, 143, 800, 500);

        c.setBackground(Color.green);

        JButton rePlayButton = new JButton("������������");
        JButton endButton = new JButton("�����т���");
        c.add(rePlayButton);
        c.add(endButton);
        rePlayButton.addActionListener(this);
        rePlayButton.setActionCommand("REPLAY");
        endButton.addActionListener(this);
        endButton.setActionCommand("END");

        rePlayButton.setBounds(250, 200, 300, 100);
        endButton.setBounds(250, 350, 300, 100);

        //�_�C�A���O�̑傫����\���ꏊ��ύX�ł���
        //�e�̃_�C�A���O�̒��S�ɕ\���������ꍇ�́C�e�̃E�B���h�E�̒��S���W�����߂āC�q�̃_�C�A���O�̑傫���̔������炷
        setLocation(owner.getBounds().x+owner.getWidth()/2-this.getWidth()/2,owner.getBounds().y+owner.getHeight()/2-this.getHeight()/2);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        this.dispose();//Dialog��p������
    }
}