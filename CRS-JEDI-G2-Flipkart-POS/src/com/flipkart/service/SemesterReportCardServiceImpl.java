package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.flipkart.DAO.SemesterReportCardDAOImpl;
import com.flipkart.DAO.SemesterReportCardDAOInterface;
import com.flipkart.bean.SemesterReportCard;
import com.flipkart.exception.SemesterReportCardNotFound;

public class SemesterReportCardServiceImpl implements SemesterReportCardInterface {

	public static volatile SemesterReportCardServiceImpl instance = null;
	SemesterReportCardDAOInterface reportInstance = SemesterReportCardDAOImpl.getInstance();

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
	public boolean addSemesterReportCard(String studentId, int semester, float sgpa) throws SQLException {

		return reportInstance.addSemesterReportCard(studentId, semester, sgpa);
	}

	@Override
	public ArrayList<SemesterReportCard> getSemesterReportCardByStudentId(String userId) throws SQLException, SemesterReportCardNotFound {

		return reportInstance.getSemesterReportCardByStudentId(userId);
	}

}
