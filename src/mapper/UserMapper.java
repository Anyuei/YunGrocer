package mapper;

import java.sql.ResultSet;

import com.YunGrocer.javabeans.YGUser;

import utils.RowMapper;

public class UserMapper implements RowMapper<YGUser>{

	public YGUser rowMapper(ResultSet rs) {
		try{
			YGUser user = new YGUser(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5)
					);
			return user;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("封装结果集错误！~");
		}
	}

}
