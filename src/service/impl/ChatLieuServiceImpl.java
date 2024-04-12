package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChatLieuDao;
import entity.ChatLieu;
import service.ChatLieuService;

public class ChatLieuServiceImpl implements ChatLieuService {
	ChatLieuDao chatLieuDao = new ChatLieuDao();

	@Override
<<<<<<< HEAD
	public ArrayList<ChatLieu> getListChatLieu() throws Exception {
		// TODO Auto-generated method stub
		return chatLieuDao.getListChatLieu();
	}

	@Override
	public boolean themChatLieu(ChatLieu l) throws Exception {
		if(chatLieuDao.kiemTraTonTaiChatLieu(l.getTenChatLieu()))
			return false;
		return chatLieuDao.themChatLieu(l);
=======
	public ArrayList<ChatLieu> getListChatLieu() {
		try {
			return new ArrayList<>(chatLieuDao.getListChatLieu());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean themChatLieu(ChatLieu l) {
		try {
			if(chatLieuDao.kiemTraTonTaiChatLieu(l.getTenChatLieu()))
				return false;
			return chatLieuDao.themChatLieu(l);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
>>>>>>> 18a2abe (Update DAO & Service)
	}

	@Override
	public boolean xoaChatLieu(String maChatLieu) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> 18a2abe (Update DAO & Service)
		return false;
	}

	@Override
	public List<ChatLieu> getChatLieu(String maChatLieu) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return chatLieuDao.getChatLieu(maChatLieu);
	}

	@Override
	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		// TODO Auto-generated method stub
		return chatLieuDao.timChatLieu(tenChatLieu);
	}

}
=======
		try {
			return chatLieuDao.getChatLieu(maChatLieu);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ChatLieu timChatLieu(String tenChatLieu) {
		try {
			return chatLieuDao.timChatLieu(tenChatLieu);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
