package hello.hellospring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import hello.hellospring.domain.Member;

public class JdbcMemberRepository implements MemberRepository{

	
	private final DataSource dataSource;
	
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Member save(Member member) {
		String sql = "insert into member(name) values(?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			conn = getConnection();
			
//			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, member.getName());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			
		} catch (Exception e) {
			System.out.println("JdbcMemberRepository: save메소드 오류");
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
