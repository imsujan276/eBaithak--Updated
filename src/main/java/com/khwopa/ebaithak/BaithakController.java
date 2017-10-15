package com.khwopa.ebaithak;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khwopa.ebaithak.dao.AttachmentDao;
import com.khwopa.ebaithak.dao.BaithakDao;
import com.khwopa.ebaithak.dao.BaithakMembersDao;
import com.khwopa.ebaithak.dao.EmojiDao;
import com.khwopa.ebaithak.dao.FriendsDao;
import com.khwopa.ebaithak.dao.MessageDao;
//import com.khwopa.ebaithak.dao.NotificationDao;
import com.khwopa.ebaithak.dao.UserDao;
import com.khwopa.ebaithak.models.Attachment;
import com.khwopa.ebaithak.models.BaithakMembers;
import com.khwopa.ebaithak.models.Message;
import com.khwopa.ebaithak.models.User;

@Controller
public class BaithakController {

	@Autowired
	private UserDao uDao;
	@Autowired
	private BaithakDao bDao;
	@Autowired
	private BaithakMembersDao bmDao;
	@Autowired
	private FriendsDao fDao;
	@Autowired
	private EmojiDao eDao;
	@Autowired
	private MessageDao mDao;
	@Autowired
	private AttachmentDao aDao;
	
	//@Autowired
	//private NotificationDao nDao;
	
	private String username;
	private static long group_id;
	private static long my_id;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 	Controller for the get view of the baithak.
	 */
	@RequestMapping(value="/baithak", method=RequestMethod.GET)
	public String baithak(@ModelAttribute User user, HttpSession session, Model model){
		
		if (StringUtils.isEmpty(session.getAttribute("username"))) {
			logger.info("/login - Session value is empty - redirected to login from baithak");
			return "login";
		}
		
		String username = (String)session.getAttribute("username");
		my_id = uDao.getUserId(username);
		model.addAttribute("myId", my_id);
		model.addAttribute("myUsername",username);
		model.addAttribute("friendsList",fDao.getFriends(username));
		model.addAttribute("memberList",bmDao.getMembers(group_id));
		model.addAttribute("activeMemberList", bmDao.getActiveMembers(group_id));
		model.addAttribute("baithak",bDao.getBaithak(group_id));
		model.addAttribute("messageList", mDao.getMessage(group_id));
		model.addAttribute("allUserList", uDao.getAllUser());
		model.addAttribute("emojiList", eDao.getAllEmoji());
		model.addAttribute("attachmentList", aDao.getAllFiles());
		for (Attachment key : aDao.getAllFiles()) {
			System.out.println(key.getFile());
		}
		return "baithak";
		
	}

	/**
	 * 	Controller for the post view of the baithak.
	 */
	@RequestMapping(value="/baithak", method=RequestMethod.POST)
	public String getBaithak(HttpServletRequest request, HttpSession session, Model model){

		username = request.getParameter("userName");
		String str = request.getParameter("groupId");
		group_id = Long.parseLong(str);
		model.addAttribute("baithak",bDao.getBaithak(group_id));
		model.addAttribute("myUsername",username);
		my_id = uDao.getUserId(username);

		int i = bmDao.isExist(group_id, my_id);
		if(i == 0) {
			BaithakMembers members = new BaithakMembers();
			members.setAddedBy(my_id);
			members.setGroupId(group_id);
			members.setUserId(my_id);
			bmDao.addMembers(members);
		}
		
		model.addAttribute("myId", my_id);
		model.addAttribute("friendsList",fDao.getFriends(username));
		model.addAttribute("memberList",bmDao.getMembers(group_id));
		model.addAttribute("activeMemberList", bmDao.getActiveMembers(group_id)); 
		model.addAttribute("messageList", mDao.getMessage(group_id));
		model.addAttribute("allUserList", uDao.getAllUser());
		model.addAttribute("emojiList", eDao.getAllEmoji());
		model.addAttribute("attachmentList", aDao.getAllFiles());
		
		return "baithak";
		
	}
	
	/**
	 * 	Controller for the view of emoji.
	 */
	@RequestMapping(value="/setemoji", method=RequestMethod.POST)
	public String setEmoji(HttpServletRequest request){
		
		String ename = request.getParameter("emojiName");
		Message msg = new Message();
		msg.setGroupId(group_id);
		msg.setMessage(ename);
		msg.setSenderId(my_id);
		mDao.createMessage(msg);
		
		return "redirect:/baithak";
		
	}
	
	/**
	 * 	Controller for the view of the leave group.
	 */
	@RequestMapping(value="/leavegroup", method=RequestMethod.POST)
	public String leavegroup(HttpServletRequest request,@ModelAttribute BaithakMembers member, HttpSession session, Model model){		
		
		String user_Id = request.getParameter("userId");
		Long uId = Long.parseLong(user_Id);
		System.out.println(uId);
		bmDao.deleteMembers(uId,group_id);

		return "redirect:/home";
		
	}

	/**
	 * 	Controller for the view of the delete group.
	 */
	@RequestMapping(value="/deletegroup", method=RequestMethod.GET)
	public String deletegroup(){		
		
		boolean result = bDao.deletebaithak(group_id);
		if(result == true){
			bmDao.deleteMembersByGroup(group_id);
			mDao.deleteMessage(group_id);
			return "redirect:/home";	
		}

		return "baithak";
		
	}


	/**
	 * 	Controller for the view of  add members in the group.
	 */
	@RequestMapping(value="/addMembers", method=RequestMethod.POST)
	public String addMembers(HttpServletRequest request,@ModelAttribute BaithakMembers member, HttpSession session, Model model){		
		
		String added_By = request.getParameter("created_by");
		Long addedBy = Long.parseLong(added_By);
		String group_Id = request.getParameter("groupId");
		Long groupId = Long.parseLong(group_Id);
		String user_Id = request.getParameter("userId");
		Long userId = Long.parseLong(user_Id);
		
		int i = bmDao.isExist(groupId, userId);
		if(i == 0) {
			BaithakMembers members = new BaithakMembers();
			members.setAddedBy(addedBy);
			members.setGroupId(groupId);
			members.setUserId(userId);
			bmDao.addMembers(members);
		}
		
		model.addAttribute("myId", my_id);
		model.addAttribute("allUserList", uDao.getAllUser());
		model.addAttribute("baithak",bDao.getBaithak(groupId));
		model.addAttribute("friendsList",fDao.getFriends(username));
		model.addAttribute("memberList",bmDao.getMembers(groupId));
		model.addAttribute("activeMemberList", bmDao.getActiveMembers(groupId));

		return "redirect:/baithak";
		
	}
	

	/**
	 * 	Controller for the view of delete members in the group.
	 */
	@RequestMapping(value="/deleteMembers", method=RequestMethod.POST)
	public String deleteMembers(HttpServletRequest request,@ModelAttribute BaithakMembers member, HttpSession session, Model model){		
		
		String user_Id = request.getParameter("userId");
		Long uId = Long.parseLong(user_Id);
		System.out.println(uId);
		bmDao.deleteMembers(uId,group_id);

		return "redirect:/baithak";
		
	}

	/**
	 * 	Controller for the view of text baithak.
	 */
	@RequestMapping(value="/textbaithak", method=RequestMethod.POST)
	public String textBaithak(@ModelAttribute Message msg, HttpServletRequest request, HttpSession session, Model model){

		String str = request.getParameter("groupId");
		Long gId = Long.parseLong(str);
		//group_id = gId;
		model.addAttribute("baithak",bDao.getBaithak(gId));
		String name = (String) session.getAttribute("username");
		Long userId = uDao.getUserId(name);
		System.out.println(msg.getMessage());
		
		if(msg.getMessage().equalsIgnoreCase(null)){
			logger.info("Empty message Ignored...");
		}else{
			msg.setGroupId(gId);
			msg.setSenderId(userId);
			msg.setCreatedAt(new Date().toString());
			
			mDao.createMessage(msg);
			
			logger.info("message = " +msg.getMessage());
		}
	
		model.addAttribute("allUserList", uDao.getAllUser());
		model.addAttribute("messageList", mDao.getMessage(gId));
		model.addAttribute("myId", userId);
		return "redirect:/baithak";
	}

	/**
	 * 	Controller for the view of chat frame.
	 */
	@RequestMapping(value="chatframe", method=RequestMethod.GET)
	public String allChat(@RequestParam("bId") Long bId,Model model, HttpSession session) {
	   
		String username = (String)session.getAttribute("username");
		Long userId = uDao.getUserId(username);
		
		model.addAttribute("allUserList", uDao.getAllUser());
		model.addAttribute("messageList", mDao.getMessage(bId));
		model.addAttribute("emojiList", eDao.getAllEmoji());
		model.addAttribute("attachmentList", aDao.getAllFiles());
		model.addAttribute("myId", userId);
		
	   return "chatframe";
	}

	/**
	 * 	Controller for the view of set message.
	 */
	@RequestMapping(value="setMessages", method=RequestMethod.GET)
	@ResponseBody 
	public String setMessage(Model model,@RequestParam("bId") Long bId,@RequestParam("uId") Long uId,@RequestParam("message") String msg) {
	   	
		Message msg1 = new Message();
		msg1.setGroupId(bId);
		msg1.setSenderId(uId);
		msg1.setMessage(msg);
		msg1.setCreatedAt(new Date().toString());
		
		mDao.createMessage(msg1);
		
		logger.info("message = " +msg1.getMessage());
		
		return bId+uId+msg;
	}

	/**
	 * 	Controller for the view of get message.
	 */
	@RequestMapping(value="getMessages", method=RequestMethod.GET)
	@ResponseBody 
	public List<Message> getMessage(@RequestParam("bId") Long bId,Model model) {
	   return mDao.getMessage(bId);
	}
	
}
