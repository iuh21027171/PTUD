package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.SachLoiDao;
import entity.SachLoi;
import service.SachLoiService;

public class SachLoiServiceImpl implements SachLoiService {
	SachLoiDao sachLoiDao = new SachLoiDao();

	@Override
	public int themSachLoi(SachLoi sl) throws SQLException {
		// TODO Auto-generated method stub
		return sachLoiDao.themSachLoi(sl);
	}

	@Override
	public int capNhatSoLuong(SachLoi sl) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return sachLoiDao.capNhatSoLuong(sl);
	}
=======
        try {
            return sachLoiDao.capNhatSoLuong(sl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
>>>>>>> 18a2abe (Update DAO & Service)

	@Override
	public List<SachLoi> getAllSachLoi() throws SQLException {
		// TODO Auto-generated method stub
		return sachLoiDao.getAllSachLoi();
	}

	@Override
	public void xoaSachLoi(String maSach, String loi) {
<<<<<<< HEAD
		sachLoiDao.xoaSachLoi(maSach, loi);
		
	}
=======
        try {
            sachLoiDao.xoaSachLoi(maSach, loi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
>>>>>>> 18a2abe (Update DAO & Service)

}
