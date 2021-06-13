package com.flipkart.service;

import java.util.ArrayList;

import com.flipkart.bean.SemesterReportCard;

public class SemesterReportCardServiceImpl implements SemesterReportCardInterface {

	public static volatile SemesterReportCardServiceImpl instance = null;

	public static SemesterReportCardServiceImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (SemesterReportCardServiceImpl.class) {
				instance = new SemesterReportCardServiceImpl();
			}
		}
		return instance;
	}

	@Override
	public boolean addSemesterReportCard(String studentID, int semester, float sgpa) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
