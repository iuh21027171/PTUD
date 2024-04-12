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
	}

	@Override
	public boolean xoaChatLieu(String maChatLieu) {
		return false;
	}

	@Override
	public List<ChatLieu> getChatLieu(String maChatLieu) {
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