package lab.solo.dao;

import java.util.HashMap;
import java.util.List;

import lab.solo.vo.Article;
import lab.solo.vo.UserVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SoloDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	@Autowired
	public void setTemplate(SqlSessionTemplate template) {
		this.template = template;
	}
	//로그인!!
	public UserVO login(String userid, String userpw){
		UserVO user = null;
		HashMap<String,String> hashmap = new HashMap<String, String>();
		hashmap.put("userid", userid);
		hashmap.put("userpw", userpw);
		user = template.selectOne("lab.solo.SoloMapper.login", hashmap);
		System.out.println(user);
		return user;
	}//login() end

	//회원가입!!
	public int joinProc(final UserVO user){
		int rows=0;
		rows = template.insert("lab.solo.SoloMapper.joinUser", user);
		return rows;
	}//joinProc() end
	
	//회원정보 수정!!
   public int modifyProc(UserVO user,String userid){
	      System.out.println("정보수정 아이디받아올게염!: "+userid);
	      System.out.println(   "정보수정 유저객체받아올게염!: "+user);
	      return template.insert("lab.solo.SoloMapper.modifyUser", user);
   }//modifyProc() end
	
	//회원정보삭제!!
	public int removeProc(final String userid){
		int rows = 0;
		rows = template.delete("lab.solo.SoloMapper.removeUser", userid);
		return rows;
	}//removeProc() end
	
	//회원 검색!!
	public UserVO searchUserProc(String userid){
		UserVO user =null;
		user = template.selectOne("lab.solo.SoloMapper.searchUser",userid);
		return user;
	}//searcUserProc() end
	
	//전체 회원 검색!!
	public List<UserVO> searchAllProc(){
		List<UserVO> users = null;
		users = template.selectList("lab.solo.SoloMapper.searchAll");
		return users;
	}//searAllProc() end
	
	//회원 키워드 검색!!
	public List<UserVO> findUser(String condition, String keword){
		List<UserVO> lists = null;
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put(condition, "%"+keword+"%");
		lists = template.selectList("lab.solo.SoloMapper.findUser", hashmap);
		return lists;
	}//findUser() end
	
	  //회원가입 아이디 중복체크!
	   public UserVO idCheckProc(String userid){
	      UserVO user = null;
	      user = template.selectOne("lab.solo.SoloMapper.id_check",userid);
	      System.out.println(user+": user값알아볼까?");

	      return user;
	   }
	
}
