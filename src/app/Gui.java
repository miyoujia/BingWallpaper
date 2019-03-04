package app;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Image;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

public class Gui extends JFrame {

	private JPanel contentPane;
	private ImageIcon img;
	private JLabel Jimg;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private App wallpaper=new App();
	private String imagePath;
	private JButton button_2;
	private JButton btning;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	void showImage() {
		img.setImage(img.getImage().getScaledInstance(1080, 720,Image.SCALE_DEFAULT));
		Jimg.setIcon(img);;
	}
	public Gui() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0,1116, 825);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Jimg = new JLabel("");
		Jimg.setBounds(14, 26, 1080, 720);
		Jimg.setBackground(SystemColor.menu);
		contentPane.add(Jimg);	
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnNewButton_1 = new JButton("bing\u58C1\u7EB8\u52A9\u624B");
		btnNewButton_1.setBounds(269, 2, 499, 27);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBackground(SystemColor.menu);
		contentPane.add(btnNewButton_1);
		
		btnNewButton = new JButton("\u83B7\u53D6bing\u58C1\u7EB8");
		btnNewButton.setBounds(35, 747, 176, 38);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str;
				try {
					str = App.sendGETRequest("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
			         System.out.println(str);
			         String path = wallpaper.getThePath(str);
			         imagePath=wallpaper.downLoadWallpaper(path);
			         img=new ImageIcon(imagePath);
			         showImage();
			         
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		button = new JButton("\u8BBE\u7F6E\u4E3A\u684C\u9762\u58C1\u7EB8");
		button.setBounds(249, 747, 176, 38);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wallpaper.settingWallpaper(imagePath);
			}
		});
		contentPane.add(button);
		
		button_1 = new JButton("\u8BFB\u53D6\u5386\u53F2\u8BB0\u5F55");
		button_1.setBounds(463, 747, 176, 38);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf=new JFileChooser(new File("D:\\Wallpaper"));
				jf.setAcceptAllFileFilterUsed(false);
				jf.addChoosableFileFilter(new FileFilter(){
					public boolean accept(File pathname) {
						if(pathname.isDirectory()==true) {
							return true;
						}
						String filename = pathname.getName();
						if(filename.endsWith(".jpg")||filename.endsWith(".jepg")){
							return true;
							}else
								return false;
						}

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "*.jpg *jpeg";
					}});
				if(jf.showOpenDialog(null)==jf.APPROVE_OPTION) {
					imagePath=jf.getSelectedFile().getPath();
					img=new ImageIcon(imagePath);
					showImage();
				}
			}
		});
		contentPane.add(button_1);
		
		button_2 = new JButton("\u5B9A\u65F6\u5207\u6362");
		button_2.setEnabled(false);
		button_2.setBounds(677, 747, 176, 38);
		contentPane.add(button_2);
		
		btning = new JButton("\u5F00\u53D1ing");
		btning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btning.setBounds(891, 747, 176, 38);
		btning.setEnabled(false);
		contentPane.add(btning);
		
		java.net.URL imgURL = Gui.class.getResource("img.jpg");
		//Icon startIcon = new ImageIcon(imgURL);
		//img=new ImageIcon("src/app/default.png");
		img =new ImageIcon(imgURL);
		showImage();
		Image image = img.getImage();
		BufferedImage bi = new BufferedImage(img.getIconWidth(),img.getIconHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.drawImage(image,0,0,null);
		g2d.dispose();
		try {
			imagePath="D:\\Wallpaper\\default.jpg";
			File file=new File(imagePath);
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			ImageIO.write(bi,"jpg",file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

