package lab.solo.service;

import java.util.List;

import lab.solo.dao.SoloDAO;
import lab.solo.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SoloManageServiceImpl implements SoloManageService{
   private SoloDAO dao;

   public SoloManageServiceImpl() {
      super();
   }
   @Autowired
   public void setDao(SoloDAO dao) {
      this.dao = dao;
   }
   
   @Override
   public UserVO loginProc(String userid, String userpw) {
	   System.out.println(dao.login(userid, userpw));
      return dao.login(userid, userpw);
   }

   @Override
   public UserVO searchUser(String uid) {
      
      return dao.searchUserProc(uid);
   }
   @Override
   public List<UserVO> searchAll() {
      
      return dao.searchAllProc();
   }
   @Override
   public int modifyUser(UserVO user, String userid) {
	      
	      return dao.modifyProc(user, userid);
	   }
   @Override
   public int removeUser(String uid) {
      
      return dao.removeProc(uid);
   }
   @Override
   public int joinUser(UserVO user) {
      
      return dao.joinProc(user);
   }
   
   public List<UserVO> findUser(String condition, String keyword){
      return dao.findUser(condition, keyword);
   }
   
   public UserVO idCheck(String userid) {
	      System.out.println(userid+"서비스임플꺼에양!");
	      return dao.idCheckProc(userid);
	   }
}