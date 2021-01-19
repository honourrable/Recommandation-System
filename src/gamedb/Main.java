package gamedb;																															
														
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final DefaultTableModel purchase;
	private final DefaultTableModel game;
	private static JTextField textField;
	private JTextField textField_1;
	private static int inputOfUser;
	private static int inputOfGame;
	private static int sizeOfList;
	
	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setResizable(false);
					frame.setTitle("Game Shot");
					ImageIcon img = new ImageIcon("img/icon.png");
					frame.setIconImage(img.getImage());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() throws FileNotFoundException{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// JFrame tanımı
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Object[] columnNames = {"Game ID","Game Name"};				// Oyun tablosu tanımı
		game = new DefaultTableModel(columnNames, 0);
		contentPane.setLayout(null);
		JTable table = new JTable(game);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 24, 372, 309);
		scrollPane.setPreferredSize(new Dimension(220, 80));
		TableColumn column;
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(300);
		contentPane.add(scrollPane);
		
		Object[] columnNames2 = {"User ID","Game ID","Score"};		// Oynama saati kayıt tablosu tanımı
		purchase = new DefaultTableModel(columnNames2, 0);
		JTable table2 = new JTable(purchase);
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(442, 24, 220, 309);
		scrollPane2.setPreferredSize(new Dimension(220, 80));
		contentPane.add(scrollPane2);
		
		JLabel lblNewLabel_2 = new JLabel("5155 games listed");
		lblNewLabel_2.setBounds(22, 336, 121, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("70489 entries listed");
		lblNewLabel_3.setBounds(442, 336, 140, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setBounds(22, 377, 45, 13);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(22, 400, 71, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Game ID");
		lblNewLabel_1.setBounds(22, 437, 57, 13);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(22, 460, 71, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("System Recommendation");
		lblNewLabel_4.setBounds(161, 437, 146, 13);
		contentPane.add(lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(161, 457, 233, 53);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		ImageIcon img = new ImageIcon(new ImageIcon("img/image.jpg").getImage().getScaledInstance(220, 100, Image.SCALE_DEFAULT));
		JLabel picLabel = new JLabel();
		picLabel.setSize(220, 95);
		picLabel.setLocation(442, 414);
		picLabel.setIcon(img);
		contentPane.add(picLabel);
		
		
		ArrayList<User> list  = new ArrayList<User>();				// Puanlama tablosunun okunması ve ilgili array-list'e yerleştirilmesi 
		File file = new File("score2.txt");
		Scanner scanner = new Scanner(file);
		float val;
		int counter = 0, sizeOflist = 0, temp2 = 0, temp3 = 0;
		while(scanner.hasNextFloat()){
			val=scanner.nextFloat();
			if(counter==0){
				sizeOflist = (int) val;
			}
			else if(counter==1){
				temp2 = (int) val;
			}
			else{
				temp3 = (int) val;
				User user=new User();
				user.id=sizeOflist;
				user.score= new HashMap<Integer, Integer>();
				user.score.put(temp2, temp3);
				list.add(user);
				counter=-1;
			}
			counter++;
		}
		int uid, gid = 0, time = 0;
		for(User user : list) {
			uid = user.id;
			for (HashMap.Entry<Integer, Integer> entry : user.score.entrySet()) {
				gid = entry.getKey();
				time = (int) entry.getValue();
			}
			Object[] row = {uid, gid, time};
			purchase.addRow(row);
		}
		
		
		ArrayList<Game> list2  = new ArrayList<Game>();				// Oyun tablosunun okunması ve ilgili array-list'e yerleştirilmesi
		File file2 = new File("games.txt");
		Scanner in = new Scanner(file2);
		String name;
		int id = 1;
		while(in.hasNextLine()){
			name = in.nextLine().replaceAll("\\d+","");
			Game game=new Game();
			game.id= id;
			game.games= new HashMap<Integer, String>();
			game.games.put(id, name);
			list2.add(game);
			id++;
		}
		gid = 0;
		name = "";
		for(Game gameTemp : list2) {
			gid = gameTemp.id;
			for (HashMap.Entry<Integer, String> entry : gameTemp.games.entrySet()) {
				gid = entry.getKey();
				name = entry.getValue();
			}
			Object[] row = {gid, name};
			game.addRow(row);
		}	
		sizeOfList = sizeOflist;
		scanner.close();
		in.close();
		

		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()!=0) {
					inputOfUser = Integer.parseInt(textField.getText()); 						// Arayüzden kullanıcı id'sini aldık
				}
				if(textField_1.getText().length()!=0) {
					inputOfGame = Integer.parseInt(textField_1.getText()); 						// Arayüzden oyun id'sini aldık
				}
				
				String nameOfGame = "";
				for(Game gameTemp : list2) {
					if(gameTemp.id == inputOfGame)												// Id'si verilen oyunun ismini tablomuzdan aradık ve değişkende tuttuk
					for (HashMap.Entry<Integer, String> entry : gameTemp.games.entrySet()) {
						nameOfGame = entry.getValue();
					}
					
				}
				
				
				HashMap<Integer,Integer> distances = new HashMap<Integer,Integer>();			
				ArrayList<Integer> sortedDistances = new ArrayList<Integer>();
				int dist;
				for (int t = 1; t<=sizeOfList; t++) {											// sizeOfList list'teki en son kaydın user id'si
					if(t != inputOfUser) {
						dist = (int) findDistance(list,inputOfUser,t);			
					    distances.put(dist,t);
					    sortedDistances.add(dist);
					}	    
				}

				Collections.sort(sortedDistances);
				int similar = sortedDistances.get(0);
				int indexOfsimilar = distances.get(similar);									// Kullanıcılar ile olan uzaklıkların sıralanması
																								
				int K = kValue(sizeOfList);
				List<Integer> shortestDistances = sortedDistances.subList(0, K);
				for(Integer cur : shortestDistances) {
					System.out.println("Uzaklık : " + cur);
				}
				int result, sum = 0, point = 0, numSimilar = 0;
				double rmse = 0;
				for (int cur=0; cur<shortestDistances.size(); cur++) {
					similar = sortedDistances.get(cur); 
					indexOfsimilar = distances.get(similar);
					System.out.println("En benzer kullanıcılar " + indexOfsimilar);
					
					for(User user : list) {
						if(user.id == indexOfsimilar) {
							for (HashMap.Entry<Integer, Integer> entry : user.score.entrySet()) {
								if(entry.getKey() == inputOfGame) {
									point = (int) entry.getValue();
									sum = sum + point;											// Root mean square hata hesaplaması için ilgili oyunun tahmin edilen puanından
									rmse = rmse + Math.pow(((double) point - 3.5731413), 2);	// gerçek değeri çıkardık ve böylece sistem başarısını da ölçmüş olduk
									numSimilar++;												// En benzer kişilerden sadece hedef oyunu oynamış kişilerin sayısını saydık
								}	
							}
						}
					}
				}
				
				String output = Integer.toString(inputOfGame) + " - " + nameOfGame.replaceAll("\\s+", " ");	
				
				if(numSimilar == 0) {															// Benzer kişilerden oyunu oynayan yoksa doğrudan ekrana ilgili mesajı yazdırdık
					output += "\nNo recommendation!\nThe related user hasn't played the game!";
				}					
				else{	
					result = sum / numSimilar;													// İlgili oyunu oynamış benzer kişileri de burda sum değişkenine böldük
					rmse = Math.sqrt(rmse / numSimilar);										// rmse değişkeni root mean square error hesaplaması için, karekökünü aldık
					if(result>50) {																// 50 puan ve üstü öneri yapılacak oyun
						output += "\nis recommended! Predicted score is " + Integer.toString(result);
					}
					else																		// 50 puan altında olduğunda ise öneri yapılmayacak
						output += "\nis not recommended! Predicted score is " + Integer.toString(result);
					String formatRMSE = String.format("%.3f",rmse);
					output += "\nApproximate error is " + formatRMSE;
				}		
				textArea.setText(output);
			}
		});
		btnNewButton.setBounds(22, 505, 71, 36);
		contentPane.add(btnNewButton);
	}
	
	
	private float findDistance(ArrayList<User> list, int id, int id2){
		
		int[] user = new int[5000]; int i = 0;
		int[] user2 = new int[5000]; int j = 0;
				
		for(User userIterator : list) {																	// Çift indeksli sayılar oyun id, tekler puan. Ortak oynadığı 
			if(userIterator.id == id) {																	// oyun puanları data set
				for (HashMap.Entry<Integer, Integer> entry : userIterator.score.entrySet()) {
					user[i++] = entry.getKey();
					user[i++] = entry.getValue();
				}
			}
			
		}
		for(User userIterator : list) {
			if(userIterator.id == id2) {
				for (HashMap.Entry<Integer, Integer> entry : userIterator.score.entrySet()) {
					user2[j++] = entry.getKey();
					user2[j++] = entry.getValue();
				}
			}
		}
		
		int x, y, term, sum = 0, k = 0;
		boolean	control = false;
		while(k < Math.max(i, j)) {
			if(user[k] == user2[k]) {
				x = user[k+1];
				y = user2[k+1];
				term = (y - x) * (y - x);
				sum = sum + term;
				control = true;
			}
			else {																	// Ortak oynanılmamış her oyun için uzaklığı belli bir miktar arttırdık
				sum = sum + 15;														// farklı olan her oyun için karekök(15)/2 = 2 kadar bir değer ekledik
			}
			k += 2;
		}
		if(control) {																// Eğer ortak oyun en az bir tane varsa control = 1
			String convertedSum = Integer.toString(sum);
			double convertedToDoubleSum = Double.parseDouble(convertedSum);
			double distance = Math.abs (Math.sqrt (convertedToDoubleSum ) );
			String convertedDistance = Double.toString(distance);
			return Float.parseFloat(convertedDistance);
		}
		else					
			return 999;																// Ortak oyun hiç yoksa control = 0 ve aralarındaki uzaklık çok büyük bir değer
	}
	
	public int kValue (int sizeOflist) {
	    int size = sizeOflist;
	    String sizeString = Integer.toString(size) ;					// Karekök işlemi için double tipe cast yaptık 
	    double sizeDouble = Double.parseDouble(sizeString);				// Integer -> String -> Double şeklinde yaptık dönüşümü,
	    double root = Math.sqrt(sizeDouble);							// ilgili metodları kullanabilmek için
	    double K = root / 2 ;
	    int num = Math.round( (float) K);								// En sonda da float tipine cast yaptık
	    if (num%2 != 0) {												// K değeri çift ise doğrudan döndürdük
	        return num ;
	    }
	    else {															// Tek ise -1 yapıp döndürdük
	        return num - 1 ;
	    }
	}
}
