import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class JLayeredSample extends JFrame implements MouseListener{
	private JLayeredPane c;
	private JButton button_a, button_b, button_c;

	public JLayeredSample() {
	    
		//�E�B���h�E���쐬����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
		setTitle("JLayeredPane");//�E�B���h�E�̃^�C�g����ݒ肷��
		setSize(500,500);//�E�B���h�E�̃T�C�Y��ݒ肷��
		c = new JLayeredPane();
		this.getContentPane().add(c);
		
		//�A�C�R���̐ݒ�
		ImageIcon image_a = new ImageIcon("cat1.png");
		ImageIcon image_b = new ImageIcon("cat1.png");
		ImageIcon image_c = new ImageIcon("cat1.png");
		
		button_a = new JButton();
		button_a.setIcon(image_a);//�C���[�W��ݒ�
		c.add(button_a);//�y�C���ɓ\��t����
		button_a.setBounds(10, 10,208, 242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        button_a.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
		c.setLayer(button_a,50); //���C���[�̐����傫��������O
    button_a.setContentAreaFilled(false);
    button_a.setBorderPainted(false);

		button_b = new JButton();
		button_b.setIcon(image_b);//�C���[�W��ݒ�
		c.add(button_b);//�y�C���ɓ\��t����
		button_b.setBounds(100, 100,208, 242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        button_b.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
		c.setLayer(button_b,100); //���C���[�̐����傫��������O
    button_b.setContentAreaFilled(false);
    button_b.setBorderPainted(false);
		
		button_c = new JButton();
		button_c.setIcon(image_c);//�C���[�W��ݒ�
		c.add(button_c);//�y�C���ɓ\��t����
		button_c.setBounds(190, 190,208, 242);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        button_c.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
		c.setLayer(button_c,200); //���C���[�̐����傫��������O
    button_c.setContentAreaFilled(false);
    button_c.setBorderPainted(false);
		
		//�{�^���̐����Ɣz�u
        JButton theButton1 = new JButton("���C���[�̕ύX");//�{�^�����쐬�C�e�L�X�g�̕\��
        c.add(theButton1);//�y�C���ɓ\��t����
        theButton1.setBounds(350,30,140,45);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
	}
	
	public static void main(String[] args) {
		JLayeredSample form = new JLayeredSample();
		form.setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) {//�{�^�����N���b�N�����Ƃ��̏���
		System.out.println("�N���b�N");
		
		JButton theButton = (JButton)e.getComponent();//�^���Ⴄ�̂ŃL���X�g����

		System.out.println(theButton.getText());
		
		if(theButton.getText().equals( "���C���[�̕ύX")){
			
			//���C���[�����ɕύX����
			int layer_a = c.getLayer(button_a);
			int layer_b = c.getLayer(button_b);
			int layer_c = c.getLayer(button_c);
			
			System.out.println("button_a = "+c.getLayer(button_a));
			System.out.println("button_b = "+c.getLayer(button_b));
			System.out.println("button_c = "+c.getLayer(button_c));

			c.setLayer(button_a, layer_c);
			c.setLayer(button_b, layer_a);
			c.setLayer(button_c, layer_b);
			
			System.out.println("button_a = "+c.getLayer(button_a));
			System.out.println("button_b = "+c.getLayer(button_b));
			System.out.println("button_c = "+c.getLayer(button_c));
						
			repaint();//�I�u�W�F�N�g�̍ĕ`����s��
		}
	}
	
	public void mouseEntered(MouseEvent e) {//�}�E�X���I�u�W�F�N�g�ɓ������Ƃ��̏���
		System.out.println("�}�E�X��������");
	}
	
	public void mouseExited(MouseEvent e) {//�}�E�X���I�u�W�F�N�g����o���Ƃ��̏���
		System.out.println("�}�E�X�E�o");
	}
	
	public void mousePressed(MouseEvent e) {//�}�E�X�ŃI�u�W�F�N�g���������Ƃ��̏����i�N���b�N�Ƃ̈Ⴂ�ɒ��Ӂj
		System.out.println("�}�E�X��������");
	}
	
	public void mouseReleased(MouseEvent e) {//�}�E�X�ŉ����Ă����I�u�W�F�N�g�𗣂����Ƃ��̏���
		System.out.println("�}�E�X�������");
	}
	
	public void mouseDragged(MouseEvent e) {//�}�E�X�ŃI�u�W�F�N�g�Ƃ��h���b�O���Ă���Ƃ��̏���
		System.out.println("�}�E�X���h���b�O");
	}

	public void mouseMoved(MouseEvent e) {//�}�E�X���I�u�W�F�N�g��ňړ������Ƃ��̏���
		System.out.println("�}�E�X�ړ�");
	}
}
