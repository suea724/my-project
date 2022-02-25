package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import table.Table;

public class MovieMain {
		
		public static void main(String[] args) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			final String LINE = "=================================\n";
			
			Scanner scanner = new Scanner(System.in);
			
			try{

				// JDBC ����̹� ���
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Connection ����
				conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "8819");
				Table table = new Table(conn, pstmt,rs);
				
				// �⺻ȭ�� ���
				StringBuffer buffer = new StringBuffer();
				buffer.append(LINE);
				buffer.append("(0) ����\n");
				buffer.append("(1) �����̼� ���� �� ������ �߰�\n");
				buffer.append("(2) ������ �̿��� �˻�\n");
				buffer.append("(3) �������� �̿��� �˻�\n");
				buffer.append("(4) �������� �̿��� �˻�\n");
				buffer.append(LINE);
				buffer.append("���ϴ� ��ȣ�� �Է��Ͻÿ�.\n");
				System.out.print(buffer);
				
				int input = scanner.nextInt();
				
				switch(input) {	// �ԷµǴ� 0~4 ���� ���� �ٸ� ��� ����
				
					case 0 :
						System.out.println("���α׷��� �����մϴ�.");
						break;
		            	
					case 1 :
						table.createTable();
						table.insertData();
						
						System.out.println("��� �غ� �������ϴ�.");
						break;
						
					case 2 :
						System.out.println("ã�����ϴ� ��ȭ ������ �Է����ּ���.");
						
						scanner.nextLine();
						String title = scanner.nextLine();	
						String searchTitleSQL = String.format("select * from movie where title like '%%%s%%'", title);
						
						if(title.length() > 1) {
							table.searchData(searchTitleSQL);
						} else {
							System.out.println("�˻�� �α��� �̻� �Է����ּ���.");
						}
							
						break;
						
					case 3 :
						System.out.println("���� �������� �Է��ϼ���.");
						int totNum = scanner.nextInt();
						String searchTotnumSQL = String.format("select * from movie where totalnum > %d", totNum);
						
						table.searchData(searchTotnumSQL);
						break;
						
					case 4 :
						System.out.println("�� ��¥�� �Է��ϼ���.");
						String date1 = scanner.next();
						String date2 = scanner.next();
						String searchDateSQL = String.format("select * from movie where releasedate between '%s' and '%s'", date1, date2);

						table.searchData(searchDateSQL);
						
						break;
						
					default :
						System.out.println("��ȣ�� �ٽ� �Է����ּ���.");
						break;
					
				}

			}catch (SQLException ex) {
				//Handle errors for JDBC
				ex.printStackTrace();
			} catch (Exception e){
			    //Handle errors for Class.forName
				e.printStackTrace();
			} finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}