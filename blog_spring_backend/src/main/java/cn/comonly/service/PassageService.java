package cn.comonly.service;

import java.util.List;

import cn.comonly.pojo.Passage;

public interface PassageService {

	List<Passage> passIndex();

	Passage passDetail(int id);

	int passChangeSubmit(Passage passage);

	int passAddSubmit(Passage passage);

	int passDelete(int id);

}
