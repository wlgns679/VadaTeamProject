package vada.dao.impl.user.sign;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vada.constants.VADAConstants;
import vada.dao.impl.board.BoardDAOImpl;
import vada.dao.user.sign.JoinDAO;
import vada.dto.UserDTO;

public class JoinDAOImpl extends BoardDAOImpl implements JoinDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 회원가입 테이블 작성을 위한 user정보를 담은 파라미터 및 메소드
	@Override
	public int join(UserDTO userDTO) throws Exception {

		conn = getConnection();

		// insert into user values (?, ?, ?, ?, ?, ?, ?, now(), ?, 'no', ?, ?, ?)
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("INSERT_JOIN_SQL"));

		pstmt.setString(1, userDTO.getUserid());
		pstmt.setString(2, userDTO.getUserpw());
		pstmt.setString(3, userDTO.getAddress());
		pstmt.setString(4, userDTO.getDetailaddress());
		pstmt.setString(5, userDTO.getEmail());
		pstmt.setString(6, userDTO.getTel());
		System.out.println("Impl nickname====>" + userDTO.getNickname());
		pstmt.setString(7, userDTO.getNickname());
		pstmt.setString(8, "noneip"); // 채팅 진행시 필요한 IP
		pstmt.setString(9, "no"); // 관리자인지 아닌지
		pstmt.setString(10, userDTO.getName());
		pstmt.setInt(11, userDTO.getInterestcategorynum());

		int result = pstmt.executeUpdate();

		closeConnection(pstmt, conn);

		return result;

	} // join

	// 중복아이디 회원 검사를 위한 메소드
	@Override
	public boolean checkUserid(String userid) throws Exception {

		boolean flag = false;

		conn = getConnection();

		// select userid from user
		PreparedStatement pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_CHECK_USERID_SQL"));

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// 중복 아이디가 존재하면
			if (rs.getString("userid").equals(userid)) {
				flag = false;
				break;
			} else { // 중복 아이디가 존재하지 않으면
				flag = true;
			}
		}

		closeConnection(pstmt, conn);
		
		System.out.println("@@@@@@@@@@@@@@"+flag);
		return flag;
		
	} // checkUserid

	// 중복 닉네임 검사를 위한 메소드
	@Override
	public boolean checkNickname(String nickname) throws Exception {

		boolean flag = false;

		conn = getConnection();

		// select nickname from user
		pstmt = conn.prepareStatement(VADAConstants.props.getProperty("SELECT_CHECK_NICKNAME_SQL"));

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// 중복 닉네임이 존재하면
			if (rs.getString("nickname").equals(nickname)) {
				flag = false;
				break;
			} 
			// 중복 닉네임이 존재하지 않으면
			else { 
				flag = true;
			}
		}
		
		closeConnection(pstmt, conn);
		
		return flag;
		
	} // checkNickname

} // class
