package cn.comonly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.comonly.dao.PassageDao;
import cn.comonly.pojo.Passage;

@Service
public class PassageServiceImpl implements PassageService {

	@Autowired 
	private PassageDao passageDao;
	
	public List<Passage> passIndex() {
		return passageDao.getPassageList("select * from pass_passage");
	}

	public Passage passDetail(int id) {
		return passageDao.getPassageById(id);
	}

	public int passChangeSubmit(Passage passage) {

		passageDao.changePassage(passage.getPnumber(), passage.getPtitle(), passage.getPimage(), passage.getPlabel(), passage.getPcount(), 
				passage.getPcoming(), passage.getPcomingUrl(), passage.getPkind(), passage.getPbelong());
		return 0;
	}

	public int passAddSubmit(Passage passage) {
		passageDao.addPassage(passage.getPtitle(), passage.getPimage(), passage.getPlabel(), passage.getPcount(), passage.getPcoming(), 
				passage.getPcomingUrl(), passage.getPkind(), passage.getPbelong());
		return 0;
	}

	public int passDelete(int id) {
		passageDao.deletePassage(id);
		return 0;
	}



}
