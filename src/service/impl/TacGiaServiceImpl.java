package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TacGiaDao;
import entity.TacGia;
import service.TacGiaService;

public class TacGiaServiceImpl implements TacGiaService {
	TacGiaDao tacGiaDao = new TacGiaDao();

	@Override
<<<<<<< HEAD
	public ArrayList<TacGia> getListTacGia() throws Exception {
		// TODO Auto-generated method stub
		return tacGiaDao.getListTacGia();
	}

	@Override
	public boolean themTacGia(TacGia t) throws Exception {
		if(tacGiaDao.kiemTraTonTaiTacGia(t.getTenTacGia()))
			return false;
		return tacGiaDao.themTacGia(t);
=======
	public List<TacGia> getListTacGia() {
		try {
			return tacGiaDao.getListTacGia();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public boolean themTacGia(TacGia t) {
		try {
			if(tacGiaDao.kiemTraTonTaiTacGia(t.getTenTacGia()))
				return false;
			return tacGiaDao.themTacGia(t);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
>>>>>>> 18a2abe (Update DAO & Service)
	}

	@Override
	public boolean xoaTacGia(String maTacGia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TacGia> getTacGia(String maTacGia) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return tacGiaDao.getTacGia(maTacGia);
	}

	@Override
	public TacGia timTacGia(String TacGia) throws SQLException {
		// TODO Auto-generated method stub
		return tacGiaDao.timTacGia(TacGia);
	}

}
=======
		try {
			return tacGiaDao.getTacGia(maTacGia);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public TacGia timTacGia(String TacGia) {
		try {
			return tacGiaDao.timTacGia(TacGia);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
>>>>>>> 18a2abe (Update DAO & Service)
