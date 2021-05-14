package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Dto;

public class Dao {
	private DataSource dataSource;

	public Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Dto> list() {
		ArrayList<Dto> dtos = new ArrayList<Dto>();

		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체
		ResultSet rs = null; // 결과값을 얻어오기위한 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bIndent asc";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			rs = prsm.executeQuery(); // 위 쿼리문을 db에 질의응답함

			while (rs.next()) { // 위 쿼리의 결과값을 반복문으로 받아옴
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");

				// DTO 객체를 생성
				Dto dto = new Dto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto); // 생성한 dto객체에 결과값을 arrayList에 추가하여 저장
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public Dto contentView(String bId) {
		upHit(bId); // 조회수 메소드를 호출하여 글제목을 누를때마다 조회수올라간다.

		Dto dtos = null;

		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체
		ResultSet rs = null; // 결과값을 얻어오기위한 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "select * from mvc_board where bId = ?";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			prsm.setInt(1, Integer.parseInt(bId)); // 위 쿼리문을 db에 질의응답함
			rs = prsm.executeQuery();

			while (rs.next()) { // 위 쿼리의 결과값을 반복문으로 받아옴
				int id = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");

				// 생성한 dto객체에 결과값을 새로운 객체에 저장
				dtos = new Dto(id, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	public void delete(String bId) {
		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "delete from mvc_board where bId = ?";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			prsm.setInt(1, Integer.parseInt(bId)); // 지정된 index에 해당 파라미터값을 넣는다.

			// 데이터베이스에서 insert문을 실행하고 실행에 영향받은 행 수를 int로 반환
			int rn = prsm.executeUpdate();
			System.out.println("delete 반환 결과: " + rn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			// 지정한 index에 해당 파라미터의 값을 넣는다.
			prsm.setString(1, bName);
			prsm.setString(2, bTitle);
			prsm.setString(3, bContent);
			prsm.setInt(4, Integer.parseInt(bId));

			int rn = prsm.executeUpdate(); // 데이터베이스에서 insert문을 실행하고 실행에 영향받은 행 수를 int로 반환
			System.out.println("modify 반환결과 : " + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) "
					+ "values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			// 지정한 index에 해당 파라미터의 값을 넣는다.
			prsm.setString(1, bName);
			prsm.setString(2, bTitle);
			prsm.setString(3, bContent);
			prsm.setInt(4, Integer.parseInt(bGroup));
			prsm.setInt(5, Integer.parseInt(bStep) + 1);
			prsm.setInt(6, Integer.parseInt(bIndent) + 1);

			int rn = prsm.executeUpdate(); // 데이터베이스에서 insert문을 실행하고 실행에 영향받은 행 수를 int로 반환
			System.out.println("답변 반환결과 : " + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public Dto replyView(String bId) {
		Dto dtos = null;

		Connection connection = null;
		PreparedStatement prsm = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			prsm = connection.prepareStatement(query);
			prsm.setInt(1, Integer.parseInt(bId));
			rs = prsm.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");

				dtos = new Dto(id, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}

	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null; // 데이터베이스를 접근할 객체
		PreparedStatement prsm = null; // 쿼리문을 실행할 객체

		try {
			connection = dataSource.getConnection(); // 데이터베이스 연결
			String query = "Insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values "
					+ "(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval,0,0)";
			prsm = connection.prepareStatement(query); // 위 쿼리문을 실행
			prsm.setString(1, bName); // 위 해당 컬럼을 db에 질의응답함
			prsm.setString(2, bTitle); // 위 해당 컬럼을 db에 질의응답함
			prsm.setString(3, bContent); // 위 해당 컬럼을 db에 질의응답함

			int rn = prsm.executeUpdate(); // 데이터베이스에서 insert문을 실행하고 실행에 영향받은 행 수를 int로 반환
			System.out.println("write 반환 결과 :" + rn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (prsm != null) {
					prsm.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private void replyShape(String bGroup, String bStep) {
		Connection connection = null;
		PreparedStatement prsm = null;

		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep + 1 " // 답변줄을 하나 추가한다
					+ "WHERE bGroup = ? " // 해당 게시글 그룹에 해당되어
					+ "AND bStep > ?"; // 추가할 답변 수가 기존 답변줄의 수보다 더 적다는 조건하에
			prsm = connection.prepareStatement(query);
			prsm.setInt(1, Integer.parseInt(bGroup));
			prsm.setInt(2, Integer.parseInt(bStep));

			int rn = prsm.executeUpdate();
			System.out.println("replyShape 반환 결과: " + rn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prsm != null)
					prsm.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private void upHit(String bId) {
		Connection connection = null;
		PreparedStatement prsm = null;

		try {
			connection = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			prsm = connection.prepareStatement(query);
			prsm.setString(1, bId);

			int rn = prsm.executeUpdate();
			System.out.println("upHit 반환 결과: " + rn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (prsm != null)
					prsm.close();
				if (connection != null)
					connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
