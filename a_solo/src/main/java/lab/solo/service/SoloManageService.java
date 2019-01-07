package lab.solo.service;

import lab.solo.vo.UserVO;
import java.util.List;

public interface SoloManageService {
   public UserVO loginProc(String userid, String userpw);
   public int joinUser(UserVO user);
   public UserVO searchUser(String uid);
   public List<UserVO> searchAll();
   public int modifyUser(UserVO user, String userid);
   public int removeUser(String uid);
   public List<UserVO> findUser(String condition, String keyWord);
   public UserVO idCheck(String userid);
   
}