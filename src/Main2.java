import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class Main2 {
	public static void main(String[] args){
		JFrame frame = new JFrame("new headline reader");
		frame.setSize(500,500);
		frame.setIconImage(new ImageIcon("C:/Users/ashug/OneDrive/Pictures/newsIcon.jpg").getImage() );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel inputpanel = new JPanel();
		inputpanel.setBackground(Color.orange);
		inputpanel.setLayout(new FlowLayout());

      JLabel label = new JLabel("enter the country code");
	  label.setFont(new Font("Arial",Font.BOLD,15) );

	  JTextField countryField = new JTextField(5);
	  countryField.setBorder(new BevelBorder(BevelBorder.LOWERED));

	  JButton fetchButton = new JButton("get details");
	  fetchButton.setForeground(Color.black);
	  fetchButton.setBackground(Color.white);
	  fetchButton.setBorder(new BevelBorder(BevelBorder.RAISED));

	  inputpanel.add(label);
	  inputpanel.add(countryField);
	  inputpanel.add(fetchButton);

	  frame.add(inputpanel, BorderLayout.NORTH);

	  ImageIcon image = new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-08 at 12.13.25 AM.jpeg");
	  BackgroundTextImage newsArea = new BackgroundTextImage(image.getImage());

	  newsArea.setFont(new Font("Arial", Font.BOLD, 12));
	  newsArea.setLineWrap(true);
	  newsArea.setWrapStyleWord(true);
	  newsArea.setEditable(false);

	  JScrollPane spane = new JScrollPane(newsArea);
	  frame.add(spane, BorderLayout.CENTER);

	  fetchButton.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			  String country = countryField.getText();
			  if(!country.isEmpty()) {

				  newsArea.clearImage();
				  String response = NewsApiRequest.getdata(country);
				  String result = JsonNewsdata.parsedata(response);
				  newsArea.setText(result);
			  }
			  else{
				  newsArea.setText("invalid country code");
			  }
		  }
	  });

	  frame.setVisible(true);
	}
}

class BackgroundTextImage extends JTextArea {
	private Image backgroundImage;

	public BackgroundTextImage(Image imageB) {
		this.backgroundImage = imageB;
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	public void clearImage(){
		this.backgroundImage = null;
		repaint();
	}
}

