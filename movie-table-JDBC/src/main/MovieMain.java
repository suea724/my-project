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

				// JDBC 드라이버 등록
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Connection 열기
				conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "8819");
				Table table = new Table(conn, pstmt,rs);
				
				// 기본화면 출력
				StringBuffer buffer = new StringBuffer();
				buffer.append(LINE);
				buffer.append("(0) 종료\n");
				buffer.append("(1) 릴레이션 생성 및 데이터 추가\n");
				buffer.append("(2) 제목을 이용한 검색\n");
				buffer.append("(3) 관객수를 이용한 검색\n");
				buffer.append("(4) 개봉일을 이용한 검색\n");
				buffer.append(LINE);
				buffer.append("원하는 번호를 입력하시오.\n");
				System.out.print(buffer);
				
				int input = scanner.nextInt();
				
				switch(input) {	// 입력되는 0~4 값에 따라 다른 기능 수행
				
					case 0 :
						System.out.println("프로그램을 종료합니다.");
						break;
		            	
					case 1 :
						table.createTable();
						table.insertData();
						
						System.out.println("모든 준비가 끝났습니다.");
						break;
						
					case 2 :
						System.out.println("찾고자하는 영화 제목을 입력해주세요.");
						
						scanner.nextLine();
						String title = scanner.nextLine();	
						String searchTitleSQL = String.format("select * from movie where title like '%%%s%%'", title);
						
						if(title.length() > 1) {
							table.searchData(searchTitleSQL);
						} else {
							System.out.println("검색어를 두글자 이상 입력해주세요.");
						}
							
						break;
						
					case 3 :
						System.out.println("기준 관객수를 입력하세요.");
						int totNum = scanner.nextInt();
						String searchTotnumSQL = String.format("select * from movie where totalnum > %d", totNum);
						
						table.searchData(searchTotnumSQL);
						break;
						
					case 4 :
						System.out.println("두 날짜를 입력하세요.");
						String date1 = scanner.next();
						String date2 = scanner.next();
						String searchDateSQL = String.format("select * from movie where releasedate between '%s' and '%s'", date1, date2);

						table.searchData(searchDateSQL);
						
						break;
						
					default :
						System.out.println("번호를 다시 입력해주세요.");
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